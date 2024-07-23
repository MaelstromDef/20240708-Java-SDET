package com.ahuggins.warehousedemo.services;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Handles JWT generation and verification for the warehouse manager project.
 */
@Service
public final class JwtService {
    /**
     * Gets a JWT that authorizes requests to retrieve/modify a company's stored data.
     * @param companyName   Name of the company to authorize.
     * @return  Company's JWT that expires in 1 hour.
     * @throws UnsupportedEncodingException 
     */
    public static String getCompanyJwt(String companyName) throws Exception{
        // Retrieve key from env and encrypt
        byte[] key;
        try{
            key = Base64.getEncoder().encode(System.getenv("secretKey").getBytes("UTF-8"));
        }catch(Exception e){
            throw new Exception("Failed to generate company JWT");
        }

        // Build JWT
        return Jwts.builder()
            .claim("companyName", companyName)
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
            .signWith(Keys.hmacShaKeyFor(key))
            .compact();
    }
}
