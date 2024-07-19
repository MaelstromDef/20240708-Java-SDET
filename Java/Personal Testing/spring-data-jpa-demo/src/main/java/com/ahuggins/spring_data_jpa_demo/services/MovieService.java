package com.ahuggins.spring_data_jpa_demo.services;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ahuggins.spring_data_jpa_demo.models.Movie;
import com.ahuggins.spring_data_jpa_demo.repositories.MovieRepository;

@Service
public class MovieService {
    MovieRepository repo;

    public MovieService(MovieRepository repo){
        this.repo = repo;
    }

    public Iterable<Movie> findAll(){
        return repo.findAll();
    }

    public Optional<Movie> findById(int id){
        return repo.findById(id);
    }

    public Movie save(Movie movie){
        return repo.save(movie);
    }

    public void update(int id, Movie movie){
        if(!repo.existsById(id)) throw new NoSuchElementException("Movie with id " + id);

        movie.setId(id);
        repo.save(movie);
    }

    public void deleteById(int id){
        repo.deleteById(id);
    }
}