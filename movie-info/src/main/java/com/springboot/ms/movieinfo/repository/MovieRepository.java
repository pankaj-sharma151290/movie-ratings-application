package com.springboot.ms.movieinfo.repository;

import com.springboot.ms.movieinfo.model.Movie;

import java.util.Map;
import java.util.Optional;

public interface MovieRepository {

    public static final String MOVIE_ENTITY="MOVIE";

    public Boolean save(Movie movie);
    public Optional<Movie> findById(String id);
    public Map<String, Movie> findAll();
    public Boolean update(Movie movie);
    public Boolean delete(String id);

}
