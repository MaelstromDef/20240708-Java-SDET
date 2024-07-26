package com.ahuggins.warehousedemo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.warehousedemo.models.Warehouse;
import com.ahuggins.warehousedemo.services.WarehouseService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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
    
}
