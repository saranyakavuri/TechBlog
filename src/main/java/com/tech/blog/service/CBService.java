package com.tech.blog.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.codec.TypeRef;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.MutationResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.blog.config.CouchBaseConfig;
import com.tech.blog.model.InterviewQuestion;
import com.tech.blog.model.University;
import com.tech.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.tech.blog.model.Constants.UNIVS_NAMES_DOC_ID;

@Service
public class CBService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CBService.class);

    @Autowired
    private CouchBaseConfig couchBaseConfig;
    @Autowired
    private Cluster cluster;
    @Autowired
    private ObjectMapper mapper;
    
    public void upsertDocsToProfileBucket(Object userObject, String docId) {
        Collection cbDefaultCollection = getProfileBucketDefaultCollection();
        MutationResult mutationResult = cbDefaultCollection.upsert(docId, userObject);

        if (mutationResult != null) {
            LOGGER.info("document id {} successfully upserted to couchbase", docId);
        }
    }

    public Optional<User> getUserFromProfileBucket(String userId) {

        Collection cbDefaultCollection = getProfileBucketDefaultCollection();
        Optional<User> user;

        if (cbDefaultCollection.exists(userId).exists()) {
            GetResult getResult = cbDefaultCollection.get(userId);
            user = Optional.of(getResult.contentAs(User.class));
        } else {
            // user is not in our couchbase bucket so create one document for user
            user = Optional.of(User.createNewUser(userId));
            upsertDocsToProfileBucket(user, userId);
        }

        return user;
    }

    private Collection getProfileBucketDefaultCollection() {
        String profileBucket = couchBaseConfig.getProfileBucket();
        Bucket bucket = cluster.bucket(profileBucket);
        return bucket.defaultCollection();
    }

    @Cacheable(cacheNames = "univs-names-cache", key = "#matcher")
    public Set<String> filterUnivsMatcher(String matcher) throws InterruptedException {
        Thread.sleep(4000);
        Collection profileBucketDefaultCollection = getProfileBucketDefaultCollection();
        GetResult getResult = profileBucketDefaultCollection.get(UNIVS_NAMES_DOC_ID);
        University univsSet = getResult.contentAs(University.class);
        String lowercaseMatcher = matcher.toLowerCase();
        return univsSet.getUnivsnames().stream()
                .map(String::toLowerCase)
                .filter(univs -> univs.startsWith(lowercaseMatcher) || univs.contains(lowercaseMatcher))
                .limit(300)
                .collect(Collectors.toSet());
    }

    public void postInterviewQuestions() {
        LinkedList<InterviewQuestion> interviewQuestionLinkedList = new LinkedList<>();
        interviewQuestionLinkedList.add(new InterviewQuestion("java_1", "what is your name",
                new HashSet<>(Arrays.asList("srinath", "saranya", "anasuya")), "srinath"));
        interviewQuestionLinkedList.add(new InterviewQuestion("java_2", "where do you born",
                new HashSet<>(Arrays.asList("gurazala", "guntur", "macherla", "hyderabad", "usa")), "gurazala"));
        interviewQuestionLinkedList.add(new InterviewQuestion("java_3", "where do you live now",
                new HashSet<>(Arrays.asList("Tucson", "Dublin", "Kansas", "Gurazala")), "Tucson"));
        Collection profileBucketDefaultCollection = getProfileBucketDefaultCollection();
        profileBucketDefaultCollection.upsert("Interview_Questions", interviewQuestionLinkedList);
    }

    public LinkedList<InterviewQuestion> getQuestions() {
        Collection profileBucketDefaultCollection = getProfileBucketDefaultCollection();
        GetResult interview_questions = profileBucketDefaultCollection.get("Interview_Questions");
        LinkedList<InterviewQuestion> interviewQuestionLinkedList = interview_questions.contentAs(new TypeRef<LinkedList<InterviewQuestion>>(){});
        return interviewQuestionLinkedList;
    }
}
