package com.ahuggins.spring_data_jpa_demo.controllers;

import java.util.Collection;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.spring_data_jpa_demo.models.Movie;
import com.ahuggins.spring_data_jpa_demo.repositories.MovieRepository;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieRepository repo;

    public MovieController(MovieRepository repo){
        this.repo = repo;
    }

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable int id){
        Optional<Movie> optional = repo.findById(id);
        return optional.isPresent() ? 
            new ResponseEntity<Movie>(optional.get(), HttpStatus.FOUND) : 
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public Iterable<Movie> findAllMovies(){
        return repo.findAll();
    }

    @PostMapping("/{id}")
    public Movie createMovie(@PathVariable int id, @RequestBody Movie movie){
        return null;
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie){
        return null;
    }

    @DeleteMapping("/{id}")
    public Movie deleteMovieById(@PathVariable int id){
        return null;
    }
}
