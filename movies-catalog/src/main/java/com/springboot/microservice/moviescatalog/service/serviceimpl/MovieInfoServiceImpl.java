package com.springboot.microservice.moviescatalog.service.serviceimpl;

import com.springboot.microservice.moviescatalog.resourceobject.MoviesRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservice.moviescatalog.resourceobject.MovieRO;
import com.springboot.microservice.moviescatalog.service.MovieInfoService;

@Service
public class MovieInfoServiceImpl implements MovieInfoService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getMovieDataFallback")
    public MovieRO getMovieData(String url, String urlParam){
        return restTemplate.getForObject(url, MovieRO.class, urlParam);
    }

    @Override
    @HystrixCommand(fallbackMethod = "getMoviesDataFallback")
    public MoviesRO getMovieData(String url){
        return restTemplate.getForObject(url, MoviesRO.class);
    }

    @SuppressWarnings("unused")
    private MovieRO getMovieDataFallback(String url, String urlParam){
        return new MovieRO("Movie-info server is under maintenance, Please try after some time.");
    }

    @SuppressWarnings("unused")
    private MoviesRO getMoviesDataFallback(String url){
        return new MoviesRO("Movie-info server is under maintenance, Please try after some time.");
    }
}
