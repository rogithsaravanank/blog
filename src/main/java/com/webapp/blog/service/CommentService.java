package com.webapp.blog.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.webapp.blog.entity.Comment;
import com.webapp.blog.entity.Post;
import com.webapp.blog.entity.User;
import com.webapp.blog.repo.CommentRepo;
import com.webapp.blog.repo.PostRepo;
import com.webapp.blog.repo.UserRepo;


@Service
public class CommentService {

    @Autowired
    CommentRepo commentRepo;

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    public ResponseEntity<String> postComment(Long postId, Comment comment) {
        Optional<Post> postFromDb = postRepo.findById(postId);
        Post updatingPost = postFromDb.get();
        if (updatingPost == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Post Unavailable");
        }

        comment.setPost(updatingPost);
        commentRepo.save(comment);

        return ResponseEntity.status(201).body("Comment added");

    }

    public List<Comment> getComment(Long postId) {  
        Optional<Post> postFromDb = postRepo.findById(postId);
        Post updatingPost = postFromDb.get();

        return commentRepo.findByPost(updatingPost);
    }

    public ResponseEntity<String> deleteComment(Long id) {
        Optional<Comment> commentFromDb = commentRepo.findById(id);
        Comment deletingComment = commentFromDb.get();
        if (deletingComment == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Comment Unavailable");
        }

        Optional<User> userOptional=userRepo.findById(deletingComment.getUser().getId());
        User existingUser=userOptional.get();
        if(existingUser.getRole().equals("USER")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Permission Unavailable");
        }

        commentRepo.delete(deletingComment);
        return ResponseEntity.status(201).body("Comment Deleted");
    }



}
