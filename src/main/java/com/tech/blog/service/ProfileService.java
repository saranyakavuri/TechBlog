package com.tech.blog.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.blog.model.Contact;
import com.tech.blog.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private static final Logger LOG = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    private ObjectMapper mapper;

    final File file = Paths.get("users.json").toFile();

    public User getUser(String id) throws IOException {
        if (id.equalsIgnoreCase("undefined") || id.equalsIgnoreCase(null)) {
            System.out.println("wrong data came from client side");
            return null;
        }
        LOG.info("getting user profile information with id {}",id);
        List<User> userList = mapper.readValue(file, new TypeReference<List<User>>(){});
        Optional<User> userInDb = userList.stream()
                .filter(user -> user.getUserId().equalsIgnoreCase(id))
                .findFirst();
        if (userInDb.isPresent()) {
            return userInDb.get();
        } else {
            LOG.warn("user is not present in database creating new user {}", id);
            User newUser = new User(id, "",  new Contact("", "", "", "", "", ""),
                    new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            userList.add(newUser);
            mapper.writeValue(file, userList);
            return newUser;
        }
    }

    public User saveUser(User user) throws IOException {

        List<User> userList = mapper.readValue(file, new TypeReference<List<User>>(){});
        Optional<User> foundUser = userList.stream().filter(u -> u.getUserId() != null
                && u.getUserId().equalsIgnoreCase(user.getUserId())).findFirst();

        if (foundUser.isPresent()) {
            userList.remove(foundUser.get());
        }

        userList.add(user);
        List<User> finalList = userList.stream().filter(u -> u.getUserId() != null).collect(Collectors.toList());
        mapper.writeValue(file, finalList);

        LOG.info("user list in db {}", finalList);

        return user;
    }

}
