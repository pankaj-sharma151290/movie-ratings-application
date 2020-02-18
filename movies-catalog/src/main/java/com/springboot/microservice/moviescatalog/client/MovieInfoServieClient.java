package com.springboot.microservice.moviescatalog.client;

import com.springboot.microservice.moviescatalog.client.fallback.MovieInfoServiceClientFallback;
import com.springboot.microservice.moviescatalog.resourceobject.MovieRO;
import com.springboot.microservice.moviescatalog.resourceobject.MoviesRO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="${movie.info.service}", fallback = MovieInfoServiceClientFallback.class)
public interface MovieInfoServieClient {

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public MoviesRO getMovies();

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{movieId}")
    public MovieRO getMovieByID(@PathVariable String movieId);

    @RequestMapping(method = RequestMethod.GET, value = "/movies/extres/themoviedb/{movieId}")
    public MovieRO getMovieByIDExtResource(@PathVariable  String movieId);

}
