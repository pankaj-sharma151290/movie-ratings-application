package com.springboot.ms.movieratings.repository;


import com.springboot.ms.movieratings.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends MongoRepository<Rating, String> {

    public Iterable<Rating> findAllByMovieId(String movieId);

    public Iterable<Rating> findAllByUserId(String userId);

}
