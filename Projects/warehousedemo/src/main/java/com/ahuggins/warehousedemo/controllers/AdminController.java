package com.ahuggins.warehousedemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahuggins.warehousedemo.dtos.AdministratorDto;
import com.ahuggins.warehousedemo.models.Administrator;
import com.ahuggins.warehousedemo.services.AdminService;


@CrossOrigin
@RestController
public class AdminController {
    private AdminService service;

    public AdminController(AdminService service){
        this.service = service;
    }

    // GET METHODS

    @GetMapping
    public List<AdministratorDto> getAdministrators() {
        return service.getAllAdministrators();
    }

    // POST METHODS

    @PostMapping("/login")
    public String login(@RequestBody Administrator admin){
        try {
            return service.login(admin);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public AdministratorDto signup(@RequestBody Administrator admin){
        Optional<AdministratorDto> dto = service.createAdministrator(admin);

        if(dto.isPresent()) return dto.get();
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    // PUT METHODS

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AdministratorDto updateAccount(@RequestAttribute int adminId, @RequestBody Administrator admin) 
        throws IllegalAccessException, ResponseStatusException{
        
        Optional<AdministratorDto> dto = service.updateAdministrator(adminId, admin);

        if(dto.isPresent()) return dto.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // DELETE METHODS

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteAccount(@RequestAttribute int adminId){
        service.deleteAdministrator(adminId);
    }
}