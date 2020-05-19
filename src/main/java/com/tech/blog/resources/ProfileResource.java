package com.tech.blog.resources;

import com.tech.blog.model.User;
import com.tech.blog.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProfileResource {

    @Autowired
    private ProfileService profileService;

    @GetMapping()
    public ResponseEntity<User> getUserInfo(@RequestParam() String id) {
        if (id.equalsIgnoreCase("undefined") || id.equalsIgnoreCase("null")) {
            System.out.println("wrong data came from client side");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<User> user = profileService.getUser(id);
        return user.isPresent() ? new ResponseEntity<>(user.get(), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping()
    public ResponseEntity<User> saveUserInfo(@RequestBody User user) {
        User saveUser = profileService.saveUser(user);
        return new ResponseEntity<>(saveUser, HttpStatus.OK);
    }
}
