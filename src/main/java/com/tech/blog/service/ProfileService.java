package com.tech.blog.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileService.class);


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CBService cbService;

    @Cacheable(cacheNames = "profile-cache", key = "'profile'+#id")
    public User getUser(String id) throws IOException, InterruptedException {
        Thread.sleep(4000);
        if (id.equalsIgnoreCase("undefined") || id.equalsIgnoreCase(null)) {
            System.out.println("wrong data came from client side");
            return null;
        }

        LOG.info("getting user profile information with id {}",id);

        return cbService.getUserFromProfileBucket(id);
    }

    @CacheEvict(cacheNames = "profile-cache", key = "'profile'+#user.userId")
    public User saveUser(User user) throws IOException {

        cbService.upsertDocsToProfileBucket(user, user.getUserId());

        return user;

    }

}
