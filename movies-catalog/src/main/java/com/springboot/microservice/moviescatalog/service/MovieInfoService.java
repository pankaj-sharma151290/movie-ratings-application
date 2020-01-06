package com.springboot.microservice.moviescatalog.service;

import com.springboot.microservice.moviescatalog.resourceobject.MovieRO;
import com.springboot.microservice.moviescatalog.resourceobject.MoviesRO;

public interface MovieInfoService {

    public MovieRO getMovieData(String url, String urlParam);

    public MoviesRO getMovieData(String url);

}
