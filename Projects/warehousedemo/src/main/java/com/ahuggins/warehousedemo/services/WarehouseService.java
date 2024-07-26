package com.ahuggins.warehousedemo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ahuggins.warehousedemo.dtos.AdministratorDto;
import com.ahuggins.warehousedemo.models.Administrator;
import com.ahuggins.warehousedemo.models.Warehouse;
import com.ahuggins.warehousedemo.repositories.WarehouseRepository;

@Service
public class WarehouseService {
    private WarehouseRepository repo;

    public WarehouseService(WarehouseRepository repo){
        this.repo = repo;
    }

    public List<Warehouse> getAllWarehouses(){
        return repo.findAll();
    }

    public Optional<Warehouse> getWarehouseById(int id, int adminId){
        return Optional.empty();//repo.findByIdAndAdministrator(id, new Administrator(id));
    }

    public Optional<Warehouse> getWarehouseByName(String name, int adminId){
        return Optional.empty();//repo.findByNameAndAdministrator(name, new Administrator(adminId));
    }

    public List<Warehouse> getWarehouseByLocation(String location, int adminId){
        return repo.findByLocationAndAdministrator(location, new Administrator(adminId));
    }

    public Optional<Warehouse> createWarehouse(Warehouse warehouse) {
        if(repo.findById(warehouse.getId()).isEmpty()){
            return Optional.of(repo.save(warehouse));
        }
        
        return Optional.empty();
    }

    public Optional<Warehouse> updateWarehouse(Warehouse warehouse){
        throw new UnsupportedOperationException();
    }
}
