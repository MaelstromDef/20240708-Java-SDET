package com.ahuggins.warehousedemo.aspects;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ahuggins.warehousedemo.controllers.WarehouseController;
import com.ahuggins.warehousedemo.services.SecurityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiMonitor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(handler.getClass().equals(WarehouseController.class)){
            String authorization = request.getHeader("authorization");
            if(!SecurityService.validate(authorization)) throw new IllegalAccessException("JWT Unauthorized.");
        }

        return true;    // Default status
    }
}
