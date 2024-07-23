package com.ahuggins.warehousedemo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ahuggins.warehousedemo.dtos.AdministratorDto;
import com.ahuggins.warehousedemo.mappers.AdminMapper;
import com.ahuggins.warehousedemo.repositories.AdministratorRepository;

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
        throw new Exception("ASDFASDFs");
    }
}
