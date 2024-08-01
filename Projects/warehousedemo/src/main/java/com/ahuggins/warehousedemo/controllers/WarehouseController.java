package com.ahuggins.warehousedemo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ahuggins.warehousedemo.dtos.WarehouseDto;
import com.ahuggins.warehousedemo.models.Warehouse;
import com.ahuggins.warehousedemo.services.WarehouseService;


@CrossOrigin
@RestController
@RequestMapping("/{adminId}")
public class WarehouseController {
    private WarehouseService service;

    public WarehouseController(WarehouseService service){
        this.service = service;
    }

    // GET METHODS

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<Warehouse> getAllWarehouses(@RequestAttribute int adminId){
        return service.getAllWarehouses(adminId);
    }

    @GetMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.FOUND)
    public Warehouse getWarehouseById(@RequestAttribute int adminId, @PathVariable int warehouseId){
        Optional<Warehouse> warehouse = service.getWarehouseById(adminId, warehouseId);
        
        if(warehouse.isPresent()) return warehouse.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/warehouseByName")
    @ResponseStatus(HttpStatus.FOUND)
    public Warehouse getWarehouseByName(@RequestAttribute int adminId, @RequestParam String warehouseName){
        Optional<Warehouse> warehouse = service.getWarehouseByName(adminId, warehouseName);

        if(warehouse.isPresent()) return warehouse.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/warehouseByLocation")
    @ResponseStatus(HttpStatus.FOUND)
    public List<Warehouse> getWarehouseByLocation(@RequestAttribute int adminId, @RequestParam String location){
        List<Warehouse> warehouses = service.getWarehouseByLocation(adminId, location);

        if(warehouses.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return warehouses;
    }

    // POST METHODS

    @PostMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.CREATED)
    public WarehouseDto createWarehouse(@RequestAttribute int adminId, @RequestBody Warehouse warehouse){
        Optional<WarehouseDto> optional = service.createWarehouse(adminId, warehouse);

        if(optional.isPresent()) return optional.get();
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    // PUT METHODS

    @PutMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.OK)
    public WarehouseDto updateWarehouse(@RequestAttribute int adminId, @PathVariable int warehouseId, @RequestBody Warehouse warehouse){
        Optional<WarehouseDto> optional = Optional.empty();
        try{
            optional = service.updateWarehouse(adminId, warehouseId, warehouse);
        }catch(DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        if(optional.isPresent()) return optional.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    // DELETE METHODS

    @DeleteMapping("/{warehouseId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteWarehouse(@RequestAttribute int adminId, @PathVariable int warehouseId){
        service.deleteWarehouse(adminId, warehouseId);
    }
}