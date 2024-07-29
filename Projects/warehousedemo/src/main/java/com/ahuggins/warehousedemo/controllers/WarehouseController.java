package com.ahuggins.warehousedemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahuggins.warehousedemo.models.Warehouse;
import com.ahuggins.warehousedemo.services.WarehouseService;



@RestController
@RequestMapping("/{adminId}")
public class WarehouseController {
    private WarehouseService service;

    public WarehouseController(WarehouseService service){
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Warehouse> getAllWarehouses(@PathVariable int adminId){
        return service.getAllWarehouses(adminId);
    }

    @GetMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.FOUND)
    public Warehouse getWarehouseById(@PathVariable int adminId, @PathVariable int warehouseId){
        Optional<Warehouse> warehouse = service.getWarehouseById(adminId, warehouseId);
        
        if(warehouse.isPresent()) return warehouse.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
