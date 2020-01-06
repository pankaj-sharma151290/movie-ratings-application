package com.springboot.ms.movieratings.service;

import com.springboot.ms.movieratings.model.Rating;
import com.springboot.ms.movieratings.resourceobject.RatingRO;
import com.springboot.ms.movieratings.resourceobject.RatingsRO;

import java.util.Optional;

public interface RatingService {

    public RatingsRO getAllRatings();

    public Optional<Rating> getRatingById(String id);

    public RatingsRO getRatingsByMovieId(String movieId);

    public RatingsRO getRatingsByUserId(String userId);

    public RatingRO addRating(RatingRO ratingRO);

    public void deleteRating(Rating rating);
}
