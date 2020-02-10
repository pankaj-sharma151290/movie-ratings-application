package com.springboot.ms.solrmoviesservice.resource;

import com.springboot.ms.solrmoviesservice.model.Movie;
import com.springboot.ms.solrmoviesservice.resourceobject.MoviesRO;
import com.springboot.ms.solrmoviesservice.service.MovieService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/solr")
public class MovieResource {

    @Autowired
    private MovieService movieService;

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public MoviesRO getMovies(){
        return movieService.getMovies();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{movieId}")
    public Movie getMovieById(@PathVariable String movieId){
        if(StringUtils.isBlank(movieId)){
        }
        return movieService.getMovieById(movieId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movies")
    public Movie addMovie(@RequestBody Movie movie){
        return movieService.addMovie(movie);
    }

}
