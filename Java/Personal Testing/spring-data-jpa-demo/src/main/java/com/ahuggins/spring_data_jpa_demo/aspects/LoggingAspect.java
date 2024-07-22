package com.ahuggins.spring_data_jpa_demo.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;                // Simple logging facade for java - abstraction for logging frameworks
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("within(com.ahuggins.spring_data_jpa_demo.controllers.MovieController)")
    public void checkMovie(){

    }

    @Pointcut("within(com.ahuggins.spring_data_jpa_demo.controllers.DirectorController)")
    public void checkDirector(){

    }

    // Will run before *every* method in MovieController.
    @Before("checkMovie()")
    public void request(JoinPoint joinPoint){
        logger.warn("AHHHHHHHHHHHHHHHHHh");
        logger.debug("A request was made to {} with the arguments: {}",
        joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
    }

    
}
