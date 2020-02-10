package com.springboot.ms.solrmoviesservice.repository;

import com.springboot.ms.solrmoviesservice.model.Movie;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SolrMovieRepository extends SolrCrudRepository<Movie, String> {
}
