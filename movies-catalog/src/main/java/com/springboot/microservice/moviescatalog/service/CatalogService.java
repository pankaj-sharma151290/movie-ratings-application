package com.springboot.microservice.moviescatalog.service;

import com.springboot.microservice.moviescatalog.resourceobject.MovieRatingsCatalogRO;
import com.springboot.microservice.moviescatalog.resourceobject.MovieRatingCatalogRO;

public interface CatalogService {

    public MovieRatingsCatalogRO getMovieRatingsCatalog();

    public MovieRatingsCatalogRO getMovieRatingsCatalogForUser(String userId);

    public MovieRatingCatalogRO getMovieRatingCatalogForUser(String userId);

}
