package com.webapp.blog.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.blog.entity.Post;
import com.webapp.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createPost(@RequestBody Post post){
        postService.createPost(post);
        return ResponseEntity.status(200).body("Post Created");
    }

    @GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
        
    }

    @GetMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public Optional<Post> getPostsByID(@PathVariable Long id){
        return postService.getPostById(id);
        
    }

    @PutMapping(consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updatePost(@RequestBody Post post){
        if (!postService.existsById(post.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id is not available");
            }
        postService.updatePost(post);
        return ResponseEntity.status(200).body("Post Updated");
    }

    @DeleteMapping(value="/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deletePostsByID(@PathVariable Long id){
       return postService.deletePostsByID(id);
        // return ResponseEntity.status(200).body("Post Deleted");
    }

}
