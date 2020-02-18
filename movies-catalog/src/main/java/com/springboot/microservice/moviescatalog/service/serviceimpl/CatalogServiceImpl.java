package com.springboot.microservice.moviescatalog.service.serviceimpl;

import com.springboot.microservice.moviescatalog.client.MovieInfoServieClient;
import com.springboot.microservice.moviescatalog.client.MovieRatingsServiceClient;
import com.springboot.microservice.moviescatalog.resourceobject.*;
import com.springboot.microservice.moviescatalog.service.CatalogService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CatalogServiceImpl implements CatalogService {

    @Autowired
    private Environment environment;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${movie.info.service}")
    private String movieInfoServiceName;

    @Value("${movie.ratings.service}")
    private String ratingsServiceName;

    @Autowired
    private MovieInfoServieClient movieInfoServieClient;

    @Autowired
    private MovieRatingsServiceClient movieRatingsServiceClient;

    @Override
    public MovieRatingsCatalogRO getMovieRatingsCatalog() {
        MovieRatingsCatalogRO movieRatingsCatalogRO = new MovieRatingsCatalogRO();
        MoviesRO movies = movieInfoServieClient.getMovies();
        if (Objects.nonNull(movies) && Objects.nonNull(movies.getMovies()) && Strings.isBlank(movies.getError())) {
            movieRatingsCatalogRO.setActiveMovieServiceInstance(movies.getServicePort());
            List<MovieRatingsRO> movieRatingList = new ArrayList<>(0);
            for (MovieRO movieRO : movies.getMovies()) {
                MovieRatingsRO movieRatingsRO = new MovieRatingsRO();
                movieRatingsRO.setMovie(movieRO);
                RatingsRO ratings = movieRatingsServiceClient.getRatingsByMovieId(movieRO.getId());
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

        RatingsRO ratings = movieRatingsServiceClient.getRatingsByUserId(userId);
        if (Objects.nonNull(ratings) && Strings.isBlank(ratings.getError())) {
            List<MovieRatingsRO> movieRatingsList = new ArrayList<>(0);
            movieRatingsCatalogRO.setActiveRatingsServiceInstance(ratings.getServicePort());
            for (RatingRO ratingRO : ratings.getRatings()) {
                MovieRatingsRO movieRatingRO = new MovieRatingsRO();
                movieRatingRO.setRatings(Arrays.asList(ratingRO));
                String movieId = ratingRO.getMovieId();
                MovieRO movieRO = movieInfoServieClient.getMovieByID(movieId);
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

        RatingsRO ratings = movieRatingsServiceClient.getRatingsByUserId(userId);
        if (Objects.nonNull(ratings) && Strings.isBlank(ratings.getError())) {
            for (RatingRO ratingRO : ratings.getRatings()) {
                String movieId = ratingRO.getMovieId();
                MovieRO movieRO = movieInfoServieClient.getMovieByIDExtResource(movieId);
                movieRatingROList.add(new MovieRatingRO(movieRO, ratingRO));
            }
            movieRatingCatalogRO.setMovieRatingROList(movieRatingROList);
        } else {
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
