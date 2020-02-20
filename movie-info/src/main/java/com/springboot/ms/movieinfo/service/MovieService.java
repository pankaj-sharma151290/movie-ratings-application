package com.springboot.ms.movieinfo.service;

import com.springboot.ms.movieinfo.model.Movie;
import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.resourseobject.MoviesRO;
import org.springframework.stereotype.Service;

public interface MovieService {

    public MoviesRO getMoviesList();

    public MovieRO getMovieByID(String movieId);

    public Boolean addMovie(MovieRO movie);

    public MovieRO getMovieByIdExtResource(String movieId);

}
