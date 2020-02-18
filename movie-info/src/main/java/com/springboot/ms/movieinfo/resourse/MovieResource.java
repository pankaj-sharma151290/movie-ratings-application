package com.springboot.ms.movieinfo.resourse;

import com.springboot.ms.movieinfo.exceptionhandling.MovieNotFoundException;
import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.resourseobject.MoviesRO;
import com.springboot.ms.movieinfo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController

@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public MoviesRO getMovies() {
       return movieService.getMoviesList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public MovieRO getMovieByIdIntResource(@PathVariable String movieId) {
        MovieRO movieRO = movieService.getMovieByID(movieId);
        if(Objects.isNull(movieRO))
            throw new MovieNotFoundException(movieId);
        return movieRO;
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public MovieRO addMovie(@RequestBody MovieRO movieRO) {
        return movieService.addMovie(movieRO);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/extres/themoviedb/{movieId}")
    public MovieRO getMovieByIdExtResource(@PathVariable  String movieId) {
        return movieService.getMovieByIdExtResource(movieId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "")
    public MovieRO updateMovie(@RequestBody MovieRO movieRO) {
        return movieService.addMovie(movieRO);
    }

}

