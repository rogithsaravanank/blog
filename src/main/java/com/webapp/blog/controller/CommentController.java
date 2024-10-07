package com.webapp.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webapp.blog.entity.Comment;
import com.webapp.blog.service.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    CommentService commentService;

    
    @PostMapping(value="/posts/{postId}/comments/",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> postComment(@PathVariable Long postId, @RequestBody Comment comment){
        return commentService.postComment(postId,comment);
        // return ResponseEntity.status(201).body("Comment added");
    }

    @GetMapping(value="/posts/{postId}/comments/",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getComment(@PathVariable Long postId){
        return commentService.getComment(postId);
        // return ResponseEntity.status(201).body("Comment added");
    }

    @DeleteMapping(value="/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id){
        return commentService.deleteComment(id);
    }

}
