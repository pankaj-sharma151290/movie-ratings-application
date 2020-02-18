package com.springboot.ms.movieinfo.exceptionhandling;

public class MovieNotFoundException extends RuntimeException {

    public MovieNotFoundException(String movieId){
        super(String.format("Movie with id: %s not found", movieId));
    }

}
