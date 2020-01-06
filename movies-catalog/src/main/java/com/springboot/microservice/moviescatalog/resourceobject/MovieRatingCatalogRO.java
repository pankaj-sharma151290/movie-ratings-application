package com.springboot.microservice.moviescatalog.resourceobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieRatingCatalogRO {

    private List<MovieRatingRO> movieRatingROList;
    private String error;

    public MovieRatingCatalogRO() {
    }

    public MovieRatingCatalogRO(List<MovieRatingRO> movieRatingROList, String error) {
        this.movieRatingROList = movieRatingROList;
        this.error = error;
    }

    public List<MovieRatingRO> getMovieRatingROList() {
        return movieRatingROList;
    }

    public void setMovieRatingROList(List<MovieRatingRO> movieRatingROList) {
        this.movieRatingROList = movieRatingROList;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
