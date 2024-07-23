package com.ahuggins.warehousedemo.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    // Basic admin retrieval commands should only be used in testing

    @Pointcut("execution(com.ahuggins.warehousedemo.controllers.AdminController.findAllAdministrators)")
    void checkFindAdmins(){}

    @Pointcut("execution(com.ahuggins.warehousedemo.controllers.AdminController.findAdministratorById)")
    void checkFindAdminById(){}

    @Before("checkFindAdmins() && checkFindAdminById()")
    public void request(JoinPoint joinPoint){
        String flag = System.getenv("allowFindAdmin");
        if(flag.isEmpty() || flag.equals("prod")) throw new UnsupportedOperationException("Find admininstrator operations no longer allowed");
    }
}
