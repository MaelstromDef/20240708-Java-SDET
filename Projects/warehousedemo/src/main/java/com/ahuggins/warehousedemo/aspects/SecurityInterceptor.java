package com.ahuggins.warehousedemo.aspects;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ahuggins.warehousedemo.controllers.WarehouseController;
import com.ahuggins.warehousedemo.services.SecurityService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor extends OncePerRequestFilter{
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        //Ensure authorization
        // if(handler.getClass().equals(WarehouseController.class)){
        //     String authorization = request.getHeader("authorization");
        //     if(!SecurityService.validate(authorization)) throw new IllegalAccessException("JWT Unauthorized.");
        // }

        // Add adminId to request attributes.
        String authHeader = request.getHeader("authorization");
        if(authHeader == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Integer adminId = SecurityService.getClaim(authHeader, SecurityService.ID_CLAIM, Integer.class);
        if(adminId == null) return;

        request.setAttribute("adminId", adminId);

        filterChain.doFilter(request, response);
    }
}