package com.tech.blog.profile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.blog.profile.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    @Autowired
    private ObjectMapper mapper;

    File file = new File("src\\main\\resources\\users.json");

    public User getUser(String id) throws IOException {
        List<User> userList = mapper.readValue(file, new TypeReference<List<User>>(){});
        return userList.stream().filter(user -> user.getUserId().equalsIgnoreCase(id)).findFirst().get();
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

        finalList.stream().forEach(System.out::println);
        return user;
    }

}
