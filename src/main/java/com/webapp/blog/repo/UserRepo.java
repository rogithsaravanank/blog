package com.webapp.blog.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.blog.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
} 
