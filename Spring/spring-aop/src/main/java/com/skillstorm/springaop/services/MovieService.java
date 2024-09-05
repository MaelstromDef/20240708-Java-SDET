package com.skillstorm.springaop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skillstorm.springaop.models.Director;
import com.skillstorm.springaop.models.Movie;
import com.skillstorm.springaop.repositories.DirectorRepository;
import com.skillstorm.springaop.repositories.MovieRepository;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectorService directorService;

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();       // calls the findAll() method in JpaRepository
    }

    public Movie findMovieById(int id) {
        
        Optional<Movie> movie = movieRepository.findById(id);
        
        // if our database has a movie with a matching id, return it
        if(movie.isPresent()) {             // ispresent checks if the optional returned the object
            return movie.get();             // get will retrieve the object
        }

        //otherwise return null
        return null;
    }

    public List<Movie> findMoviesByRating(int rating) {

        Optional<List<Movie>> movies = movieRepository.findAllByRatingGreaterThanEqual(rating);

        if(movies.isPresent()) {
            return movies.get();
        }

        return null;
    }

    public Movie saveMovie(Movie movie) {

        /*
         * save performs an isNew() check using your primary key
         */
        Director directorWithId = directorService.saveDirector(movie.getDirector());
        movie.setDirector(directorWithId);
        return movieRepository.save(movie);                // comes OOTB with jpa respository
    }

    //http://localhost:8282/movies/movie/updateTitle?newTitle=Not The Friend Zonem
    public int updateTitle(Movie movie, String newTitle){

        return movieRepository.updateMovieTitle(movie.getId(), movie.getTitle());
    }

    public int updateMovie(int id, String title, int rating, Director director) {
        return movieRepository.updateMovie(id, title, rating);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.delete(movie);
    }
}
