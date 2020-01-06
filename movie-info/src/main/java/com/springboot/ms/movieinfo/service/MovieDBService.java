package com.springboot.ms.movieinfo.service;

import com.springboot.ms.movieinfo.resourseobject.MovieRO;

public interface MovieDBService {

    public MovieRO getMovieData(String movieId);
}
