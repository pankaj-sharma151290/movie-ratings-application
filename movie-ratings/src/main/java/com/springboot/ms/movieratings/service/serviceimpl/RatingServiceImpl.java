package com.springboot.ms.movieratings.service.serviceimpl;

import com.springboot.ms.movieratings.resourceobject.RatingRO;
import com.springboot.ms.movieratings.service.RatingService;
import com.springboot.ms.movieratings.model.Rating;
import com.springboot.ms.movieratings.repository.RatingRepository;
import com.springboot.ms.movieratings.resourceobject.RatingsRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "Ratings-LRU-Cache", key = "'RatingById'+#id")
    public Optional<Rating> getRatingById(String id){
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ratingRepository.findById(id);
    }

    @Override
    @CacheEvict(value = "Ratings-LRU-Cache", key = "'RatingsByMovieId'+#movieId", condition = "!#isCacheable", beforeInvocation = true)
    @Cacheable(value = "Ratings-LRU-Cache", key = "'RatingsByMovieId'+#movieId", condition = "#isCacheable")
    public RatingsRO getRatingsByMovieId(String movieId, boolean isCacheable) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterable<Rating> ratingIterable = ratingRepository.findAllByMovieId(movieId);
        RatingsRO ratingsRO = new RatingsRO(StreamSupport.stream(ratingIterable.spliterator(), false).collect(Collectors.toList()));
        ratingsRO.setServicePort(getActiveServiceInstancePort());
        return ratingsRO;
    }

    @Override
    @CacheEvict(value = "Ratings-LRU-Cache", key = "'RatingsByUserID'+#userId", condition = "!#isCacheable", beforeInvocation = true)
    @Cacheable(value = "Ratings-LRU-Cache", key = "'RatingsByUserID'+#userId", condition = "#isCacheable")
    public RatingsRO getRatingsByUserId(String userId, boolean isCacheable) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterable<Rating> ratingIterable = ratingRepository.findAllByUserId(userId);
        RatingsRO ratingsRO = new RatingsRO(StreamSupport.stream(ratingIterable.spliterator(), false).collect(Collectors.toList()));
        ratingsRO.setServicePort(getActiveServiceInstancePort());
        return ratingsRO;
    }

    @Override
    @CachePut(value = "Ratings-LRU-Cache", key = "'RatingById'+#id")
    public RatingRO addRating(RatingRO ratingRO) {
        return new RatingRO(ratingRepository.save(RoToModelMapper(ratingRO)));
    }

    @Override
    @CacheEvict(value = "Ratings-LRU-Cache", key = "'RatingById'+#id", beforeInvocation = true)
    public Boolean deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
        return true;
    }

    private String getActiveServiceInstancePort() {
        return environment.getProperty("local.server.port");
    }

    private Rating RoToModelMapper(RatingRO ratingRO) {
       return new Rating(ratingRO.getId(), ratingRO.getMovieId(), ratingRO.getUserId(), ratingRO.getRating(), ratingRO.getDescription());
    }
}
