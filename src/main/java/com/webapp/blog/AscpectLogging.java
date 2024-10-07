package com.webapp.blog;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AscpectLogging {

    @Before("execution(public * com.webapp.blog.service.CommentService.getComment(..))")
    public void logBefore(){
        System.out.println("Before Execution");
    }

    @After("execution(public * com.webapp.blog.service.CommentService.getComment(..))")//Executes a like finally block
    public void logAfter(){
        System.out.println("After Execution");
    }

    @AfterReturning("execution(public * com.webapp.blog.service.CommentService.getComment(..))")//Executes after returing
    public void logAfterReturning(){
        System.out.println("After Returing");
    }

    @AfterThrowing("execution(public * com.webapp.blog.service.CommentService.getComment(..))")//Executes after Excetion is thrown
    public void logAfterThrowing(){
        System.out.println("After Throwing");
    }


    
}
