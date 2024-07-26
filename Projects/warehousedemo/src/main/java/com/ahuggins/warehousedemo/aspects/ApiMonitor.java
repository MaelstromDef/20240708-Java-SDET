package com.ahuggins.warehousedemo.aspects;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.ahuggins.warehousedemo.controllers.WarehouseController;
import com.ahuggins.warehousedemo.services.SecurityService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApiMonitor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // Ensure authorization
        if(handler.getClass().equals(WarehouseController.class)){
            String authorization = request.getHeader("authorization");
            if(!SecurityService.validate(authorization)) throw new IllegalAccessException("JWT Unauthorized.");
        }

        // Add adminId to request attributes.
        request.setAttribute("adminId", 
            SecurityService.getClaim(request.getHeader("authorization"), SecurityService.ID_CLAIM, Integer.class));

        return true;    // Default status
    }
}
