package com.tech.blog.model;

import java.util.Map;

public class InterviewQuestionMap {

    private Map<InterviewQuestion, String> interviewQuestionStringMap;

    public InterviewQuestionMap(Map<InterviewQuestion, String> interviewQuestionStringMap) {
        this.interviewQuestionStringMap = interviewQuestionStringMap;
    }

    public Map<InterviewQuestion, String> getInterviewQuestionStringMap() {
        return interviewQuestionStringMap;
    }

    public void setInterviewQuestionStringMap(Map<InterviewQuestion, String> interviewQuestionStringMap) {
        this.interviewQuestionStringMap = interviewQuestionStringMap;
    }

    @Override
    public String toString() {
        return "InterviewQuestionMap[interviewQuestionStringMap=" + interviewQuestionStringMap +"]";
    }
}
