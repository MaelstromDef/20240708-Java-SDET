package com.ahuggins.warehousedemo.services;

import java.nio.file.AccessDeniedException;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Example;
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
    
    public String login(Administrator admin) throws Exception{
        String companyName=admin.getCompanyName(), password=SecurityService.hashString(admin.getPassword());

        // Find and verify admin
        List<Administrator> fromRepo = repo.findByCompanyNameAndPassword(companyName, password);
        if(fromRepo.isEmpty()) throw new Exception("Credentials not found");

        // Create JWT
        return SecurityService.getJwt(companyName);
    }

    public Optional<AdministratorDto> createAdministrator(Administrator admin) {
        if(repo.findByCompanyName(admin.getCompanyName()).isEmpty()){
            try {
                String companyName=admin.getCompanyName(), password=SecurityService.hashString(admin.getPassword());
                admin.setPassword(password);
                return Optional.of(mapper.toDto(repo.save(admin)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return Optional.empty();
    }
}