package com.springboot.ms.movieinfo.resourse;

import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.resourseobject.MoviesRO;
import com.springboot.ms.movieinfo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/movies")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET, value = "/greet")
    public String greetings() {
        return "Hello";
    }

    @RequestMapping(method = RequestMethod.GET, value = "")
    public MoviesRO getMovies() {
       return movieService.getMoviesList();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{movieId}")
    public MovieRO getMovieByIdIntResource(@PathVariable String movieId) {
        return movieService.getMovieByID(movieId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public MovieRO addMovie(@RequestBody MovieRO movieRO) {
        return movieService.addMovie(movieRO);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/extres/themoviedb/{movieId}")
    public MovieRO getMovieByIdExtResource(@PathVariable  String movieId) {
        return movieService.getMovieByIdExtResource(movieId);
    }
}

