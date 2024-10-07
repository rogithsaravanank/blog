package com.webapp.blog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.blog.entity.Comment;
import com.webapp.blog.entity.Post;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long>{
    
    List<Comment> findByPost(Post post);

}
