package com.ahuggins.warehousedemo.services;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.ahuggins.warehousedemo.dtos.AdministratorDto;
import com.ahuggins.warehousedemo.mappers.AdminMapper;
import com.ahuggins.warehousedemo.models.Administrator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Handles JWT generation and verification for the warehouse manager project.
 */
@Service
public final class SecurityService {
    private static AdminMapper mapper;

    public static final String ID_CLAIM = "id";
    public static final String C_NAME_CLAIM = "companyName";

    public SecurityService(AdminMapper mapper){
        if(SecurityService.mapper == null) SecurityService.mapper = mapper;
    }

    /**
     * Gets a JWT that authorizes requests to retrieve/modify a company's stored data.
     * @param admin   Admin to authorize.
     * @return  Company's JWT that expires in 1 hour.
     * @throws UnsupportedEncodingException 
     */
    public static String getJwt(AdministratorDto admin) throws Exception{
        // Build JWT
        return Jwts.builder()
            .claim(ID_CLAIM, admin.getId())
            .claim(C_NAME_CLAIM, admin.getCompanyName())
            .setIssuedAt(Date.from(Instant.now()))
            .setExpiration(Date.from(Instant.now().plusSeconds(3600)))
            .signWith(getSigningKey())
            .compact();
    }

    /**
     * Hashes a string.
     * @param str   String to hash.
     * @return      The hashed string.
     * @throws Exception 
     */
    public static String hashString(String str) throws Exception{
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(str.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new Exception("Failed to hash string.");
        }
    }

    /**
     * Validates that a jwt was generated by the application, and that it belongs to a given administrator. 
     * Highest level security, used for administrator modification requests.
     * @param jwt   Authorizing JWT.
     * @param admin Authorizing administrator.
     * @return      Where the JWT is authorized or not.
     */
    public static boolean validateAdmin(String jwt, AdministratorDto admin) {
        try {
            Jwts.parserBuilder()
                .require(ID_CLAIM, admin.getId())
                .require(C_NAME_CLAIM, admin.getCompanyName())
                .setSigningKey(getSigningKey())
                .build()
                .parse(jwt);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * Validates that a jwt was generated by the application, and that it belongs to a given administrator. 
     * Highest level security, used for administrator modification requests.
     * @param jwt   Authorizing JWT.
     * @param admin Authorizing administrator.
     * @return      Where the JWT is authorized or not.
     */
    public static boolean validateAdmin(String jwt, Administrator admin) {
        return validateAdmin(jwt, mapper.toDto(admin));
    }

    /**
     * Validates that a jwt was generated by the application. Less secure than validateAdmin, use with caution.
     * @param jwt   Authorizing JWT.
     * @return      Where the JWT is authorized or not.
     */
    public static boolean validate(String jwt){
        try{
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parse(jwt);
        }catch(Exception e){
            return false;
        }

        return true;
    }

    public static <T> T getClaim(String jwt, String claim, Class<T> classType){
        try {
            Claims claims = (Claims) Jwts.parserBuilder()
                .setSigningKey(SecurityService.getSigningKey())
                .build()
                .parse(jwt).getBody();
            return claims.get(claim, classType);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static String getClaim(String jwt, String claim){
        return getClaim(jwt, claim, String.class);
    }

    private static SecretKey getSigningKey() throws Exception{
        // Retrieve key from env and encrypt
        byte[] key;
        try{
            key = Base64.getEncoder().encode(System.getenv("secretKey").getBytes("UTF-8"));
        }catch(Exception e){
            throw new Exception("Encryption failure.");
        }

        return Keys.hmacShaKeyFor(key);
    }
}
