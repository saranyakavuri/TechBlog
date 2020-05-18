package com.tech.blog.resources;

import com.tech.blog.model.InterviewQuestion;
import com.tech.blog.service.CBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Set;

@RestController
@RequestMapping("/cb")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CBResource {

    @Autowired
    private CBService cbService;

    @GetMapping("/universityMatcher")
    private Set<String> getMatchedUniversityList(@RequestParam String matcher) throws InterruptedException {
        return cbService.filterUnivsMatcher(matcher);
    }

    @PostMapping("/postquestions")
    private void postInterviewQuestionsToCB() {
        cbService.postInterviewQuestions();
    }

    @GetMapping("/questions")
    private LinkedList<InterviewQuestion> getQuestions() {
        return cbService.getQuestions();
    }
}
