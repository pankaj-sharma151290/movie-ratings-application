package com.springboot.microservice.moviescatalog.client;

import com.springboot.microservice.moviescatalog.client.fallback.MovieRatingsServiceClientFallback;
import com.springboot.microservice.moviescatalog.resourceobject.MoviesRO;
import com.springboot.microservice.moviescatalog.resourceobject.RatingsRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="${movie.ratings.service}" , fallback = MovieRatingsServiceClientFallback.class)
public interface MovieRatingsServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/rating")
    public RatingsRO getRatingsByMovieId(@RequestParam("movieId") String movieId);

    @RequestMapping(method = RequestMethod.GET, value = "/rating/user")
    public RatingsRO getRatingsByUserId(@RequestParam("userId")String userId);

}
