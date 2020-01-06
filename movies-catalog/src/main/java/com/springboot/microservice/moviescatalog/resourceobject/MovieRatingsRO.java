package com.springboot.microservice.moviescatalog.resourceobject;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieRatingsRO {

    private MovieRO movie;
    private List<RatingRO> ratings;

    public MovieRatingsRO() {
    }

    public MovieRO getMovie() {
        return movie;
    }

    public void setMovie(MovieRO movie) {
        this.movie = movie;
    }

    public List<RatingRO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingRO> ratings) {
        this.ratings = ratings;
    }
}
