package com.tech.blog.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileService.class);


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CBService cbService;

    public User getUser(String id) throws IOException {
        if (id.equalsIgnoreCase("undefined") || id.equalsIgnoreCase(null)) {
            System.out.println("wrong data came from client side");
            return null;
        }

        LOG.info("getting user profile information with id {}",id);

        return cbService.getUserFromProfileBucket(id);
    }

    public User saveUser(User user) throws IOException {

        cbService.upsertDocsToProfileBucket(user, user.getUserId());

        return user;

    }

}
