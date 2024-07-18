package com.ahuggins.spring_data_jpa_demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ahuggins.spring_data_jpa_demo.models.Director;
import com.ahuggins.spring_data_jpa_demo.models.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query(value = "select m from Movie m ORDER BY rating LIMIT 3", nativeQuery = false)
    public List<Movie> findTop3();

    public List<Movie> findByDirector(Director director);
}