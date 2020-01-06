package com.springboot.microservice.moviescatalog.resourceobject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MoviesRO {

    private Iterable<MovieRO> movies;
    private String servicePort;
    private String error;

    public MoviesRO() {
    }

    public MoviesRO(Iterable<MovieRO> movies) {
        this.movies = movies;
    }

    public MoviesRO(String error) {
        this.error = error;
    }

    public Iterable<MovieRO> getMovies() {
        return movies;
    }

    public void setMovies(Iterable<MovieRO> movies) {
        this.movies = movies;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}