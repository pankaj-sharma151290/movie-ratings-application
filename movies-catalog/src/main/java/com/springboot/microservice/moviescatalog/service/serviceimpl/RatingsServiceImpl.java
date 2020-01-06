package com.springboot.microservice.moviescatalog.service.serviceimpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springboot.microservice.moviescatalog.resourceobject.RatingRO;
import com.springboot.microservice.moviescatalog.resourceobject.RatingsRO;
import com.springboot.microservice.moviescatalog.service.RatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RatingsServiceImpl implements RatingsService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "getRatingsDataFallback")
    public RatingsRO getRatingsData(String url){
        return restTemplate.getForObject(url, RatingsRO.class);
    }

    @SuppressWarnings("unused")
    private RatingsRO getRatingsDataFallback(String url){
        return new RatingsRO("Ratings server is under maintenance, Please try after some time.");
    }
}
