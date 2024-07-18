package com.ahuggins.spring_data_jpa_demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahuggins.spring_data_jpa_demo.models.Director;

public interface DirectorRepository extends JpaRepository<Director, Integer>{
    
}
