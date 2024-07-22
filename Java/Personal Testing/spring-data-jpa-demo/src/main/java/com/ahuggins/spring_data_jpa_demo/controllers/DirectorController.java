package com.ahuggins.spring_data_jpa_demo.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.spring_data_jpa_demo.models.Director;
import com.ahuggins.spring_data_jpa_demo.services.DirectorService;


@RestController
@RequestMapping("/directors")
public class DirectorController {
    private DirectorService service;

    public DirectorController(DirectorService service){
        this.service = service;
    }

    @GetMapping
    public Iterable<Director> findAllDirectors(){
        return service.findAll();
    }

    @GetMapping("/director/{id}")
    public ResponseEntity<Director> findDirectorById(@PathVariable int id) {
        Optional<Director> director = service.findById(id);
        return director.isPresent() ?
            new ResponseEntity<>(director.get(), HttpStatus.FOUND) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Director createDirector(@RequestBody Director director){
        return service.save(director);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateDirector(@PathVariable int id, @RequestBody Director director){
        service.update(id, director);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDirectorById(@PathVariable int id){
        service.deleteById(id);
    }
}
