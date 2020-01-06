package com.springboot.ms.movieinfo.resourseobject;

import com.springboot.ms.movieinfo.model.Movie;

import java.util.List;

public class MoviesRO {

    private Iterable<Movie> movies;
    private String servicePort;

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

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }
}
