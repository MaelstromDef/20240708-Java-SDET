package com.ahuggins.spring_data_jpa_demo.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ahuggins.spring_data_jpa_demo.models.Director;
import com.ahuggins.spring_data_jpa_demo.repositories.DirectorRepository;

@Service
public class DirectorService {
    private DirectorRepository repo;

    public DirectorService(DirectorRepository repo){
        this.repo = repo;
    }

    public Iterable<Director> findAll(){
        return repo.findAll();
    }

    public Optional<Director> findById(int id){
        return repo.findById(id);
    }

    public Director save(Director director){
        return repo.save(director);
    }

    public void update(int id, Director director){
        if(!repo.existsById(id)) throw new NoSuchElementException("Director with id " + id);

        director.setId(id);
        repo.save(director);
    }

    public void deleteById(int id){
        repo.deleteById(id);
    }
}
