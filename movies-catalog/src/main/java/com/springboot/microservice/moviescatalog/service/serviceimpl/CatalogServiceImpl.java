package com.springboot.microservice.moviescatalog.service.serviceimpl;

import com.springboot.microservice.moviescatalog.client.MovieInfoServieClient;
import com.springboot.microservice.moviescatalog.resourceobject.*;
import com.springboot.microservice.moviescatalog.service.CatalogService;
import com.springboot.microservice.moviescatalog.service.MovieInfoService;
import com.springboot.microservice.moviescatalog.service.RatingsService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private MovieInfoService movieInfoService;

    @Autowired
    private RatingsService ratingsService;

    @Value("${movie.info.service}")
    private String movieInfoServiceName;

    @Value("${movie.ratings.service}")
    private String ratingsServiceName;

    @Autowired
    private MovieInfoServieClient movieInfoServieClient;

    @Override
    public MovieRatingsCatalogRO getMovieRatingsCatalog() {
        MovieRatingsCatalogRO movieRatingsCatalogRO = new MovieRatingsCatalogRO();
        String movieServiceURL = "http://" + movieInfoServiceName + "/movies";
        String ratingServiceURL = "http://" + ratingsServiceName + "/rating";

        //MoviesRO movies = movieInfoService.getMovieData(movieServiceURL);
        MoviesRO movies = movieInfoServieClient.getMovies();
        if (Objects.nonNull(movies) && Objects.nonNull(movies.getMovies()) && Strings.isBlank(movies.getError())) {
            movieRatingsCatalogRO.setActiveMovieServiceInstance(movies.getServicePort());
            List<MovieRatingsRO> movieRatingList = new ArrayList<>(0);
            for (MovieRO movieRO : movies.getMovies()) {
                MovieRatingsRO movieRatingsRO = new MovieRatingsRO();
                movieRatingsRO.setMovie(movieRO);
                String reqParam = "?movieId=" + movieRO.getId();
                RatingsRO ratings = ratingsService.getRatingsData(ratingServiceURL.concat(reqParam));
                movieRatingsCatalogRO.setActiveRatingsServiceInstance(ratings.getServicePort());
                movieRatingsRO.setRatings(ratings.getRatings());
                movieRatingList.add(movieRatingsRO);
            }
            movieRatingsCatalogRO.setMovieList(movieRatingList);
        } else {
            movieRatingsCatalogRO.setError(movies.getError());
        }
        updateAvailableServiceInstances(movieRatingsCatalogRO);
        return movieRatingsCatalogRO;
    }

    @Override
    public MovieRatingsCatalogRO getMovieRatingsCatalogForUser(String userId) {
        MovieRatingsCatalogRO movieRatingsCatalogRO = new MovieRatingsCatalogRO();

        String baseMovieServiceURL = "http://" + movieInfoServiceName + "/movies/{movieId}";
        String baseRatingServiceURL = "http://" + ratingsServiceName + "/rating/user";
        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(baseRatingServiceURL)
                .queryParam("userId", userId);
        String ratingsServiceURL = urlBuilder.buildAndExpand().toUriString();
        RatingsRO ratings = ratingsService.getRatingsData(ratingsServiceURL);
        if (Objects.nonNull(ratings) && Strings.isBlank(ratings.getError())) {
            List<MovieRatingsRO> movieRatingsList = new ArrayList<>(0);
            movieRatingsCatalogRO.setActiveRatingsServiceInstance(ratings.getServicePort());
            for (RatingRO ratingRO : ratings.getRatings()) {
                MovieRatingsRO movieRatingRO = new MovieRatingsRO();
                movieRatingRO.setRatings(Arrays.asList(ratingRO));
                String movieId = ratingRO.getMovieId();
                MovieRO movieRO = movieInfoService.getMovieData(baseMovieServiceURL, movieId);
                movieRatingRO.setMovie(movieRO);
                movieRatingsList.add(movieRatingRO);
            }
            updateAvailableServiceInstances(movieRatingsCatalogRO);
            movieRatingsCatalogRO.setMovieList(movieRatingsList);
        } else {
            movieRatingsCatalogRO.setError(ratings.getError());
        }
        return movieRatingsCatalogRO;
    }

    @Override
    public MovieRatingCatalogRO getMovieRatingCatalogForUser(String userId) {
        MovieRatingCatalogRO movieRatingCatalogRO = new MovieRatingCatalogRO();
        List<MovieRatingRO> movieRatingROList = new ArrayList<>(0);

        String baseMovieServiceURL = "http://" + movieInfoServiceName + "/movies/extres/themoviedb/{movieId}";
        String baseRatingServiceURL = "http://" + ratingsServiceName + "/rating/user";

        UriComponentsBuilder urlBuilder = UriComponentsBuilder.fromUriString(baseRatingServiceURL)
                .queryParam("userId", userId);
        String ratingsServiceURL = urlBuilder.buildAndExpand().toUriString();
        RatingsRO ratings = ratingsService.getRatingsData(ratingsServiceURL);
        if (Objects.nonNull(ratings) && Strings.isBlank(ratings.getError())) {
            for (RatingRO ratingRO : ratings.getRatings()) {
                String movieId = ratingRO.getMovieId();
                MovieRO movieRO = movieInfoService.getMovieData(baseMovieServiceURL, movieId);
                movieRatingROList.add(new MovieRatingRO(movieRO, ratingRO));
            }
            movieRatingCatalogRO.setMovieRatingROList(movieRatingROList);
        }
        else {
            movieRatingCatalogRO.setError(ratings.getError());
        }
        return movieRatingCatalogRO;
    }

    private String getActiveServiceInstancePort() {
        return environment.getProperty("local.server.port");
    }

    private List<Integer> getAvailableServiceInstance(String serviceId) {
        return discoveryClient.getInstances(serviceId).stream().map(instance -> instance.getPort()).collect(Collectors.toList());
    }

    private void updateAvailableServiceInstances(MovieRatingsCatalogRO MovieRatingsCatalogRO) {
        MovieRatingsCatalogRO.setAvailableMovieServiceInstances(getAvailableServiceInstance(movieInfoServiceName));
        MovieRatingsCatalogRO.setAvailableRatingsServiceInstances(getAvailableServiceInstance(ratingsServiceName));
    }

}
