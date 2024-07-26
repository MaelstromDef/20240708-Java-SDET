package com.ahuggins.warehousedemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.warehousedemo.models.Warehouse;
import com.ahuggins.warehousedemo.services.SecurityService;
import com.ahuggins.warehousedemo.services.WarehouseService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/warehouses")
public class WarehouseController {
    WarehouseService service;

    public WarehouseController(WarehouseService service){
        this.service = service;
    }

    @GetMapping
    public List<Warehouse> findAllWarehouses() {
        return service.getAllWarehouses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable int id, @RequestAttribute int adminId){
        Optional<Warehouse> optional = service.getWarehouseById(id, adminId);

        return optional.isPresent() ?
            new ResponseEntity<>(optional.get(), HttpStatus.FOUND) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Warehouse> findWarehouseByName(@RequestParam String name, @RequestAttribute int adminId){
        Optional<Warehouse> optional = service.getWarehouseByName(name, adminId);

        return optional.isPresent() ?
            new ResponseEntity<>(optional.get(), HttpStatus.FOUND) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findWarehouseByLocation(@RequestParam String location, @RequestAttribute int adminId) {
        List<Warehouse> warehouses = service.getWarehouseByLocation(location, adminId);

        // Return warehouses
        return warehouses.isEmpty() ? 
            new ResponseEntity<>(HttpStatus.NOT_FOUND) :
            new ResponseEntity<>(warehouses, HttpStatus.FOUND);
    }
}
