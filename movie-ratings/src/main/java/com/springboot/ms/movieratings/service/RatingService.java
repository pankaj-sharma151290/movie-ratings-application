package com.springboot.ms.movieratings.service;

import com.springboot.ms.movieratings.model.Rating;
import com.springboot.ms.movieratings.resourceobject.RatingRO;
import com.springboot.ms.movieratings.resourceobject.RatingsRO;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Optional;

public interface RatingService {

    public RatingsRO getAllRatings();

    public Optional<Rating> getRatingById(String id);

    public RatingsRO getRatingsByMovieId(String movieId, boolean isCacheable);

    public RatingsRO getRatingsByUserId(String userId, boolean isCacheable);

    public RatingRO addRating(RatingRO ratingRO);

    public Boolean deleteRating(String ratingId);
}
