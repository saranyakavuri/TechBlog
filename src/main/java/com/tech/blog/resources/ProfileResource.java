package com.tech.blog.resources;

import com.tech.blog.model.User;
import com.tech.blog.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileResource {

    @Autowired
    private ProfileService profileService;

    @GetMapping()
    public User getUserInfo(@RequestParam() String id) throws IOException, InterruptedException {
        return profileService.getUser(id);
    }

    @PostMapping()
    public User saveUserInfo(@RequestBody User user) throws IOException {
        User saveUser = profileService.saveUser(user);
        return saveUser;
    }
}
