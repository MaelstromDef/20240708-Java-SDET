package com.ahuggins.warehousedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahuggins.warehousedemo.models.Warehouse;

public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    
}
