package com.springboot.microservice.moviescatalog.client.fallback;

import com.springboot.microservice.moviescatalog.client.MovieRatingsServiceClient;
import com.springboot.microservice.moviescatalog.resourceobject.RatingsRO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieRatingsServiceClientFallback implements MovieRatingsServiceClient {

    @Autowired
    Logger logger;

    @Override
    public RatingsRO getRatingsByMovieId(String movieId) {
        logger.error("connect timed out executing method getRatingsByMovieId ", this);
        return new RatingsRO("Ratings server is under maintenance, Please try after some time.");
    }

    @Override
    public RatingsRO getRatingsByUserId(String userId) {
        logger.error("connect timed out executing method getRatingsByUserId", this);
        return new RatingsRO("Ratings server is under maintenance, Please try after some time.");
    }
}
