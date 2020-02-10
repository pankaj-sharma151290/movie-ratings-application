package com.springboot.microservice.moviescatalog.client;

import com.springboot.microservice.moviescatalog.resourceobject.MoviesRO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="movie-info-service")
public interface MovieInfoServieClient {

    @RequestMapping("/movies")
    public MoviesRO getMovies();

}
