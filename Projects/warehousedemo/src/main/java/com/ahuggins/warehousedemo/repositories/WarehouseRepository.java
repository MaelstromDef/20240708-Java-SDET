package com.ahuggins.warehousedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahuggins.warehousedemo.models.Administrator;
import com.ahuggins.warehousedemo.models.Warehouse;
import java.util.List;
import java.util.Optional;


public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
    List<Warehouse> findByIdAndAdministrator(int id, Administrator administrator);

    List<Warehouse> findByAdministrator(Administrator administrator);

    List<Warehouse> findByName(String name);
    List<Warehouse> findByNameAndAdministrator(String name, Administrator administrator);

    List<Warehouse> findByLocation(String location);
    List<Warehouse> findByLocationAndAdministrator(String location, Administrator administrator);
}