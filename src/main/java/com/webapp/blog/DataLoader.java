package com.webapp.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.webapp.blog.service.PostService;
import com.webapp.blog.service.UserService;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserService userService;

    @Autowired
    PostService postService;


    @Override
    public void run(String... args) throws Exception {
        userService.loadUsers();
        postService.loadPosts();
    }
    

}
