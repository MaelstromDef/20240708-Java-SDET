package com.ahuggins.warehousedemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.warehousedemo.dtos.AdministratorDto;
import com.ahuggins.warehousedemo.models.Administrator;
import com.ahuggins.warehousedemo.services.AdminService;

import io.jsonwebtoken.Jwts;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private AdminService service;

    public AdminController(AdminService service){
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AdministratorDto> findAllAdministrators() {
        return service.getAllAdministrators();
    }
    
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public AdministratorDto findAdministratorById(@PathVariable int id) {
        Optional<AdministratorDto> adminDto = service.getAdministratorById(id);
        return adminDto.isPresent() ? adminDto.get() : null;
    }

    @GetMapping
    public ResponseEntity<AdministratorDto> loginAdministrator(@RequestParam String companyName, @RequestBody String password){
        throw new UnsupportedOperationException("loginAdministrator not implented.");
    }

    @PostMapping
    public void createAdministrator(@RequestBody Administrator admin) {
        AdministratorDto adminDto = new AdministratorDto();
        admin.setCompanyName(admin.getCompanyName());

        throw new UnsupportedOperationException("createAdministrator not implemented");
    }

    @PutMapping("/{id}")
    public void updateAdministrator(@RequestBody Administrator admin, @RequestHeader String jwt){
        throw new UnsupportedOperationException("updateAdministrator not implemented.");
    }

    @DeleteMapping("/{id}")
    public void deleteAdministrator(){
        throw new UnsupportedOperationException("deleteAdministrator not implemented.");
    }

}