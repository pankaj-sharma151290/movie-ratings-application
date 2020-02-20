package com.springboot.ms.movieinfo.repository.repositoryimpl;

import com.springboot.ms.movieinfo.model.Movie;
import com.springboot.ms.movieinfo.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository{

    private RedisTemplate<String, Movie> redisTemplate;
    private HashOperations hashOperations;

    public MovieRepositoryImpl(RedisTemplate<String, Movie> redisTemplate) {
        this.redisTemplate = redisTemplate;

        hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public Boolean save(Movie movie) {
        hashOperations.put(MOVIE_ENTITY,movie.getId(),movie);
        return true;
    }

    @Override
    public Optional<Movie> findById(String id) {
        return Optional.ofNullable( (Movie) hashOperations.get(MOVIE_ENTITY,id));
    }

    @Override
    public Map<String, Movie> findAll() {
        return hashOperations.entries(MOVIE_ENTITY);
    }

    @Override
    public Boolean update(Movie movie) {
        save(movie);
        return true;
    }

    @Override
    public Boolean delete(String id) {
        hashOperations.delete(MOVIE_ENTITY,id);
        return true;
    }
}
