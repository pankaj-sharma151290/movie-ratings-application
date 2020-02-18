package com.springboot.microservice.moviescatalog.client.fallback;

import com.springboot.microservice.moviescatalog.client.MovieInfoServieClient;
import com.springboot.microservice.moviescatalog.resourceobject.MovieRO;
import com.springboot.microservice.moviescatalog.resourceobject.MoviesRO;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieInfoServiceClientFallback implements MovieInfoServieClient {

    @Autowired
    Logger logger;

    @Override
    public MoviesRO getMovies() {
        logger.error("connect timed out executing method getMovies ", this);
        return new MoviesRO("Movie-info server is under maintenance, Please try after some time.");
    }

    @Override
    public MovieRO getMovieByID(String movieId) {
        logger.error("connect timed out executing method getMovieByID ", this);
        return new MovieRO("Movie-info server is under maintenance, Please try after some time.");
    }

    @Override
    public MovieRO getMovieByIDExtResource(String movieId) {
        logger.error("connect timed out executing method getMovieByIDExtResource ", this);
        return new MovieRO("Movie-info server is under maintenance, Please try after some time.");
    }
}
