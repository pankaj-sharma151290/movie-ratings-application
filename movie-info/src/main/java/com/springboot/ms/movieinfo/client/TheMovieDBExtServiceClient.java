package com.springboot.ms.movieinfo.client;

import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.service.MovieService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name= "the-movie-db-client", url = "${api.moviedb.base.url}", fallback = TheMovieDBExtServiceClientFallback.class)
public interface TheMovieDBExtServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/3/movie/{movieId}")
    public MovieRO getMovieByID(@PathVariable String movieId, @RequestParam("api_key") String apiKey);
}

@Component
class TheMovieDBExtServiceClientFallback implements  TheMovieDBExtServiceClient{

    @Autowired
    Logger logger;

    @Autowired
    MovieService movieService;

    @Override
    public MovieRO getMovieByID(String movieId, String apiKey) {
        logger.error("Error while executing TheMovieDBExtServiceClient ", this);
        return movieService.getMovieByID(movieId);
    }
}