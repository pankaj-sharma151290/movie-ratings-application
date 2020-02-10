package com.springboot.ms.solrmoviesservice.resourceobject;

import com.springboot.ms.solrmoviesservice.model.Movie;

public class MoviesRO {

    private Iterable<Movie> movies;

    public MoviesRO() {
    }

    public MoviesRO(Iterable<Movie> movies) {
        this.movies = movies;
    }

    public Iterable<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Iterable<Movie> movies) {
        this.movies = movies;
    }

}
