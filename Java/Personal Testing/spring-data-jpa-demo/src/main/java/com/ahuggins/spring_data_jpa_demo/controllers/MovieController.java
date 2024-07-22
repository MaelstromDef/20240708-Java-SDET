package com.ahuggins.spring_data_jpa_demo.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.ahuggins.spring_data_jpa_demo.models.Movie;
import com.ahuggins.spring_data_jpa_demo.services.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService service;

    public MovieController(MovieService repo){
        this.service = repo;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World!";
    }

    @GetMapping
    public Iterable<Movie> findAllMovies(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable int id){
        Optional<Movie> optional = service.findById(id);
        return optional.isPresent() ? 
            new ResponseEntity<Movie>(optional.get(), HttpStatus.FOUND) : 
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Movie createMovie(@RequestBody Movie movie){
        return service.save(movie);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateMovie(@PathVariable int id, @RequestBody Movie movie){
        service.update(id, movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteMovieById(@PathVariable int id){
        service.deleteById(id);
    }
}
