package com.ahuggins.warehousedemo.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.warehousedemo.dtos.AdministratorDto;
import com.ahuggins.warehousedemo.models.Administrator;
import com.ahuggins.warehousedemo.services.AdminService;

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

    /**
     * Returns all administrators currently in the database.
     * 
     * @return  A list of all administrators in the database.
     * @apiNote This feature will be inaccessible within prod.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<AdministratorDto> findAllAdministrators() {
        return service.getAllAdministrators();
    }
    
    /**
     * Retrieves a single administrator from the database.
     * 
     * @param id    The id of the administrator to retrieve.
     * @return      The administrator or a not found HTTP status.
     * @apiNote     This feature will be inaccessible within prod.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDto> findAdministratorById(@PathVariable int id) {
        Optional<AdministratorDto> adminDto = service.getAdministratorById(id);
        return adminDto.isPresent() ? 
            new ResponseEntity<AdministratorDto>(adminDto.get(), HttpStatus.FOUND) : 
            new ResponseEntity<AdministratorDto>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdministrator(@RequestBody Administrator admin){
        try {
            String jwt = service.login(admin);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping
    public ResponseEntity<AdministratorDto> createAdministrator(@RequestBody Administrator admin) {
        Optional<AdministratorDto> optional = service.createAdministrator(admin);
        return optional.isPresent() ? 
            new ResponseEntity<>(optional.get(), HttpStatus.CREATED) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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