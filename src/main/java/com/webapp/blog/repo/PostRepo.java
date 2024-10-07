package com.webapp.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.blog.entity.Post;

@Repository
public interface PostRepo extends JpaRepository<Post,Long>{
    
}
