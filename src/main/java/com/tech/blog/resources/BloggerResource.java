package com.tech.blog.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BloggerResource {

    @GetMapping("/")
    public String sayHelloToBlogger() {
        return "<h1>Hello Blogger !!!</h1>";
    }
}
