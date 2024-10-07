package com.webapp.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.blog.entity.User;
import com.webapp.blog.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public void createUser(User user) {
        userRepo.save(user);
    }

    public Optional<User> getUser(Long id) {
        return userRepo.findById(id);
    }

    public boolean existsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {

            return userRepo.existsByEmail(email);
        
    }

    public void loadUsers() {
        ObjectMapper obj=new ObjectMapper();
        try{
            List<User> lst=obj.readValue(Files.readAllBytes(Paths.get("src/main/resources/users.json")),new TypeReference<List<User>>() {});
            userRepo.saveAll(lst);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }


    
}
