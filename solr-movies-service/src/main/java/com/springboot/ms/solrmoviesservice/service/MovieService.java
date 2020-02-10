package com.springboot.ms.solrmoviesservice.service;

import com.springboot.ms.solrmoviesservice.model.Movie;
import com.springboot.ms.solrmoviesservice.resourceobject.MoviesRO;

public interface MovieService {

    MoviesRO getMovies();
    Movie getMovieById(String movieId);
    Movie addMovie(Movie movie);


}
