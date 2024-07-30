package com.ahuggins.warehousedemo.aspects;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ahuggins.warehousedemo.services.SecurityService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

        String authHeader = request.getHeader("authorization"); // Grab token from request

        // No tokens found
        if(authHeader == null) {
            request.removeAttribute("adminId");
            filterChain.doFilter(request, response);
            return;
        }// Grab admin ID from token
        Integer adminId = SecurityService.getClaim(authHeader, SecurityService.ID_CLAIM, Integer.class);
        if(adminId == null) return;

        request.setAttribute("adminId", adminId);
        

        filterChain.doFilter(request, response);
    }
}