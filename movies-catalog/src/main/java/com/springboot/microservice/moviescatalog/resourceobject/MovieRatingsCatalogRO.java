package com.springboot.microservice.moviescatalog.resourceobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieRatingsCatalogRO {

    private List<MovieRatingsRO> movieList;

    private List<Integer> availableMovieServiceInstances;

    private List<Integer> availableRatingsServiceInstances;

    private String activeMovieServiceInstance;

    private String activeRatingsServiceInstance;

    private String error;

    public MovieRatingsCatalogRO() {
    }

    public MovieRatingsCatalogRO(List<MovieRatingsRO> movieList) {
        this.movieList = movieList;
    }

    public List<MovieRatingsRO> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<MovieRatingsRO> movieList) {
        this.movieList = movieList;
    }

    public List<Integer> getAvailableMovieServiceInstances() {
        return availableMovieServiceInstances;
    }

    public void setAvailableMovieServiceInstances(List<Integer> availableMovieServiceInstances) {
        this.availableMovieServiceInstances = availableMovieServiceInstances;
    }

    public List<Integer> getAvailableRatingsServiceInstances() {
        return availableRatingsServiceInstances;
    }

    public void setAvailableRatingsServiceInstances(List<Integer> availableRatingsServiceInstances) {
        this.availableRatingsServiceInstances = availableRatingsServiceInstances;
    }

    public String getActiveMovieServiceInstance() {
        return activeMovieServiceInstance;
    }

    public void setActiveMovieServiceInstance(String activeMovieServiceInstance) {
        this.activeMovieServiceInstance = activeMovieServiceInstance;
    }

    public String getActiveRatingsServiceInstance() {
        return activeRatingsServiceInstance;
    }

    public void setActiveRatingsServiceInstance(String activeRatingsServiceInstance) {
        this.activeRatingsServiceInstance = activeRatingsServiceInstance;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
