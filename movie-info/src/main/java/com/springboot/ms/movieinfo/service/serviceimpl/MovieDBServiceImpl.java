package com.springboot.ms.movieinfo.service.serviceimpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.service.MovieDBService;
import com.springboot.ms.movieinfo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class MovieDBServiceImpl implements MovieDBService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${api.moviedb.key}")
    private String movieDbApiKey;

    @Value("${api.moviedb.url}")
    private String movieDbApiUrl;

    @Autowired
    MovieService movieService;

    @Override
    @HystrixCommand(fallbackMethod = "getMovieDataFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "2000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")})
    public MovieRO getMovieData(String movieId)  {
        Map<String, String> urlParam = new HashMap<>();
        urlParam.put("movieId", movieId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(movieDbApiUrl)
                .queryParam("api_key", movieDbApiKey);
        String apiUrl = builder.buildAndExpand(urlParam).toUriString();
        return restTemplate.getForObject(apiUrl, MovieRO.class);
    }

    @SuppressWarnings("unused")
    private MovieRO getMovieDataFallback(String movieId) {
        return movieService.getMovieByID(movieId);
    }

}
