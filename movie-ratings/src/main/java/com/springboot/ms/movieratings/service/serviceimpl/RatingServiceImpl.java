package com.springboot.ms.movieratings.service.serviceimpl;

import com.springboot.ms.movieratings.resourceobject.RatingRO;
import com.springboot.ms.movieratings.service.RatingService;
import com.springboot.ms.movieratings.model.Rating;
import com.springboot.ms.movieratings.repository.RatingRepository;
import com.springboot.ms.movieratings.resourceobject.RatingsRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RatingServiceImpl implements RatingService {

    public RatingServiceImpl() {
    }

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private Environment environment;

    @Override
    public RatingsRO getAllRatings() {
        List<Rating> ratingList = new ArrayList<>();
        ratingRepository.findAll().forEach(ratingList::add);
        RatingsRO ratingsRO = new RatingsRO(ratingList);
        ratingsRO.setServicePort(getActiveServiceInstancePort());
        return ratingsRO;
    }

    @Override
    public Optional<Rating> getRatingById(String id) {
        return ratingRepository.findById(id);
    }

    @Override
    public RatingsRO getRatingsByMovieId(String movieId) {
        Iterable<Rating> ratingIterable = ratingRepository.findAllByMovieId(movieId);
        RatingsRO ratingsRO = new RatingsRO(StreamSupport.stream(ratingIterable.spliterator(), false).collect(Collectors.toList()));
        ratingsRO.setServicePort(getActiveServiceInstancePort());
        return ratingsRO;
    }

    @Override
    public RatingsRO getRatingsByUserId(String userId) {
        Iterable<Rating> ratingIterable = ratingRepository.findAllByUserId(userId);
        RatingsRO ratingsRO = new RatingsRO(StreamSupport.stream(ratingIterable.spliterator(), false).collect(Collectors.toList()));
        ratingsRO.setServicePort(getActiveServiceInstancePort());
        return ratingsRO;
    }

    @Override
    public RatingRO addRating(RatingRO ratingRO) {
        return new RatingRO(ratingRepository.save(RoToModelMapper(ratingRO)));
    }

    @Override
    public void deleteRating(Rating rating) {
        ratingRepository.delete(rating);
    }

    private String getActiveServiceInstancePort() {
        return environment.getProperty("local.server.port");
    }

    private Rating RoToModelMapper(RatingRO ratingRO) {
       return new Rating(ratingRO.getId(), ratingRO.getMovieId(), ratingRO.getUserId(), ratingRO.getRating(), ratingRO.getDescription());
    }
}
