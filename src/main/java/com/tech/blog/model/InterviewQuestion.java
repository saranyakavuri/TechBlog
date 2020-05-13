package com.tech.blog.model;

import java.util.Set;

public class InterviewQuestion {
    private String qid;
    private String question;
    private Set<String> options;
    private String answer;

    public InterviewQuestion() {}

    public InterviewQuestion(String qid, String question, Set<String> options, String answer) {
        this.qid = qid;
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public String getQid() {
        return qid;
    }

    public void setQid(String qid) {
        this.qid = qid;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<String> getOptions() {
        return options;
    }

    public void setOptions(Set<String> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "InterviewQuestion{" +
                "qid='" + qid + '\'' +
                ", question='" + question + '\'' +
                ", options=" + options +
                ", answer='" + answer + '\'' +
                '}';
    }
}
