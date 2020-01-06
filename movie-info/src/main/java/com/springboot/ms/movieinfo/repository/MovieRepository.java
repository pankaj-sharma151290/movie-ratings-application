package com.springboot.ms.movieinfo.repository;

import com.springboot.ms.movieinfo.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {
}
