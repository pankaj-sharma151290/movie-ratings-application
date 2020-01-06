package com.springboot.ms.movieratings.resourceobject;

import com.springboot.ms.movieratings.model.Rating;

public class RatingRO {

    private String id;
    private String movieId;
    private String userId;
    private Integer rating;
    private String description;
    private String error;

    public RatingRO() {
    }

    public RatingRO(String error) {
        this.error = error;
    }

    public RatingRO(String id, String movieId, String userId, Integer rating, String description) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.rating = rating;
        this.description = description;
    }

    public RatingRO(Rating rating) {
        this.id = rating.getId();
        this.movieId = rating.getMovieId();
        this.userId = rating.getUserId();
        this.rating = rating.getRating();
        this.description = rating.getDescription();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id='" + id + '\'' +
                ", movieId='" + movieId + '\'' +
                ", userId='" + userId + '\'' +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }

}
