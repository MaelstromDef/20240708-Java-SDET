package com.ahuggins.warehousedemo.services;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ahuggins.warehousedemo.dtos.AdministratorDto;
import com.ahuggins.warehousedemo.mappers.AdminMapper;
import com.ahuggins.warehousedemo.models.Administrator;
import com.ahuggins.warehousedemo.repositories.AdministratorRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class AdminService {
    private AdministratorRepository repo;
    private AdminMapper mapper;

    public AdminService(AdministratorRepository repo, AdminMapper mapper){
        this.repo = repo;
        this.mapper = mapper;
    }

    public List<AdministratorDto> getAllAdministrators(){
        return repo.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Optional<AdministratorDto> getAdministratorById(int id){
        return repo.findById(id).map(mapper::toDto);
    }
    
    public String login(String companyName, String password) throws Exception{
        // Find and verify admin
        Administrator admin = repo.findByCompanyNameAndPassword(companyName, password);
        if(admin == null) throw new Exception("Credentials not found.");

        // Create JWT
        return JwtService.getCompanyJwt(companyName);
    }
}