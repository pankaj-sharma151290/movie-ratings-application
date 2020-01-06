package com.springboot.microservice.moviescatalog.resourceobject;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieRatingRO {

    private MovieRO movieRO;
    private RatingRO ratingRO;

    public MovieRatingRO() {
    }

    public MovieRatingRO(MovieRO movieRO, RatingRO ratingRO) {
        this.movieRO = movieRO;
        this.ratingRO = ratingRO;
    }

    public MovieRO getMovieRO() {
        return movieRO;
    }

    public void setMovieRO(MovieRO movieRO) {
        this.movieRO = movieRO;
    }

    public RatingRO getRatingRO() {
        return ratingRO;
    }

    public void setRatingRO(RatingRO ratingRO) {
        this.ratingRO = ratingRO;
    }
}
