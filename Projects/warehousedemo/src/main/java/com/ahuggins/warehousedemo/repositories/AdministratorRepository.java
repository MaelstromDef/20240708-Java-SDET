package com.ahuggins.warehousedemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahuggins.warehousedemo.models.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    public Administrator findByCompanyNameAndPassword(String companyName, String password);
}
