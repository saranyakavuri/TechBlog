package com.tech.blog.model;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.KeyDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class JsonKeyDeserializer extends KeyDeserializer {

    @Override
    public InterviewQuestion deserializeKey(String key, DeserializationContext ctxt) throws IOException {
//        InterviewQuestion interviewQuestion = new InterviewQuestion();
//        String[] strings = key.split(":");
//        for (String s : strings) {
//            String[] keyValues = s.split("=");
//            String keyString = keyValues[0].trim();
//            String value = keyValues[1].trim();
//            if (keyString.equalsIgnoreCase("qid")) {
//                interviewQuestion.setQid(value);
//            } else if (keyString.equalsIgnoreCase("question")) {
//                interviewQuestion.setQuestion(value);
//            } else if (keyString.equalsIgnoreCase("options")) {
//                String setString = value;
//                String commaString = setString.substring(1, setString.length()-1);
//                String[] setStrings = commaString.split(",");
//                interviewQuestion.setOptions(Arrays.stream(setStrings).map(String::trim).collect(Collectors.toSet()));
//            }
//        }
//        return interviewQuestion;
        return null;
    }
}
