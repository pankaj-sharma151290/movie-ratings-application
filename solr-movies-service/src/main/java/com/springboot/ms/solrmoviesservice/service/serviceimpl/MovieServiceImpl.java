package com.springboot.ms.solrmoviesservice.service.serviceimpl;

import com.springboot.ms.solrmoviesservice.model.Movie;
import com.springboot.ms.solrmoviesservice.repository.SolrMovieRepository;
import com.springboot.ms.solrmoviesservice.resourceobject.MoviesRO;
import com.springboot.ms.solrmoviesservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private SolrMovieRepository movieRepository;

    @Override
    public MoviesRO getMovies() {
        return new MoviesRO(movieRepository.findAll());
    }

    @Override
    public Movie getMovieById(String movieId) {
        Optional<Movie> movie = movieRepository.findById(movieId);
        return movie.isPresent() ? movie.get() : null;
    }

    @Override
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }
}
