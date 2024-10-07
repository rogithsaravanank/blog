package com.webapp.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.blog.entity.User;
import com.webapp.blog.service.UserService;

@RestController 
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // @PostMapping(value = "/register" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<String> createUser(@RequestBody User user) {
    //     // userService.createUser(user);
    //     // return ResponseEntity.status(200).body("User created");
    //     try {
    //         userService.createUser(user);
    //         return ResponseEntity.status(201).body("User created successfully");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(500).body("Error creating user");
    //     }
    // }

    @GetMapping("/test")
public ResponseEntity<String> testEndpoint() {
    return ResponseEntity.ok("Test endpoint is working!");
}


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            // Check if username or email already exists
            if (userService.existsByUsername(user.getUsername())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is already taken");
            }
            if (userService.existsByEmail(user.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is already in use");
            }

            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User createds successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating user");
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

}
