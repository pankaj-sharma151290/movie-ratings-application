package com.springboot.microservice.moviescatalog.service;

import com.springboot.microservice.moviescatalog.resourceobject.RatingRO;
import com.springboot.microservice.moviescatalog.resourceobject.RatingsRO;

public interface RatingsService {

    public RatingsRO getRatingsData(String url);

}
