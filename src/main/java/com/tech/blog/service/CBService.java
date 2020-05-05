package com.tech.blog.service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.kv.MutationResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.blog.config.CouchBaseConfig;
import com.tech.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CBService {
    Logger logger = LoggerFactory.getLogger(CBService.class);

    @Autowired
    private CouchBaseConfig couchBaseConfig;
    @Autowired
    private Cluster cluster;
    @Autowired
    private ObjectMapper mapper;
    
    public void upsertDocsToProfileBucket(Object userObject, String docId) throws JsonProcessingException {
        String profileBucket = couchBaseConfig.getProfileBucket();
        Bucket bucket = cluster.bucket(profileBucket);
        Collection collection = bucket.defaultCollection();
        MutationResult mutationResult = collection.upsert(docId, userObject);

        if (mutationResult != null) {
            logger.info("document id {} successfully upserted to couchbase", docId);
        }
    }

    public User getUserFromProfileBucket(String userId) throws IOException {
        Bucket bucket = cluster.bucket(couchBaseConfig.getProfileBucket());
        Collection collection = bucket.defaultCollection();

        User user;

        if (collection.exists(userId).exists()) {
            GetResult getResult = collection.get(userId);
            user = getResult.contentAs(User.class);
        } else {
            // user is not in our couchbase bucket so create one document for user
            user = User.createNewUser(userId);
            upsertDocsToProfileBucket(user, userId);
        }

        return user;
    }
}
