package com.webapp.blog.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.webapp.blog.entity.Post;
import com.webapp.blog.entity.User;
import com.webapp.blog.repo.PostRepo;
import com.webapp.blog.repo.UserRepo;

@Repository
public class PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    public void createPost(Post post) {

        Optional<User> userOptional=userRepo.findById(post.getUser().getId());
        User existingUser=userOptional.get();
        post.setUser(existingUser);
        postRepo.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepo.findAll();
    }

    public Optional<Post> getPostById(Long id) {
        return postRepo.findById(id);
    }

    public boolean existsById(Long id) {
        return postRepo.existsById(id);
    }

    public void updatePost(Post post) {
        // Optional<Post> prePost = postRepo.findById(post.getId());
        postRepo.save(post);
    }

    public ResponseEntity<String> deletePostsByID(Long id) {
        Optional<Post> postOptional=postRepo.findById(id);

        Post existingPost=postOptional.get();
        Optional<User> userOptional=userRepo.findById(existingPost.getUser().getId());
        User existingUser=userOptional.get();
        System.out.println("Role "+existingUser.getRole());
        if(existingUser.getRole().equals("USER")){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("delete permission not available for the user");
        }

        postRepo.deleteById(id);

        
        return ResponseEntity.status(200).body("Post Deleted");
    }

    public void loadPosts() {
        ObjectMapper obj=new ObjectMapper();
        try{
            List<Post> lst=obj.readValue(Files.readAllBytes(Paths.get("src/main/resources/posts.json")),new TypeReference<List<Post>>() {});
            postRepo.saveAll(lst);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
    
}
