package com.springboot.ms.movieinfo.service;

import com.springboot.ms.movieinfo.model.Movie;
import com.springboot.ms.movieinfo.repository.MovieRepository;
import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.resourseobject.MoviesRO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.mockito.Mockito.*;

@SpringBootTest
public class TestMovieServie {

    @SpyBean
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    @MockBean
    private MovieDBService movieDBService;

    @Test
    void sample() {
        System.out.println("----- TestMovieService -----");
    }

    @Test
    void testAddMovie() {
        Movie movieStub = new Movie("123", "Movie_123", "Movie_123");
        when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(movieStub);
        MovieRO actual = movieService.addMovie(new MovieRO(movieStub));
        Assertions.assertEquals(movieStub.getId(), actual.getId());
    }

    @Test
    void testGetMoviesList() {
        Iterable<Movie> moviesStub = getMoviesStub();
        List<Movie> expectedMovieList = (List<Movie>) moviesStub;
        when(movieRepository.findAll()).thenReturn(moviesStub);

        MoviesRO moviesRO = movieService.getMoviesList();
        Stream<Movie> movieStream = StreamSupport.stream(moviesRO.getMovies().spliterator(), false);

        Assertions.assertEquals(expectedMovieList.size(), movieStream.count());
        Mockito.verify(movieRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testGetMovieByID() {
        String movieId = "123";
        Optional<Movie> movieStub = Optional.of(new Movie("123", "Movie_123", "Movie_123"));
        when(movieRepository.findById(movieId)).thenReturn(movieStub);

        MovieRO movieRO = movieService.getMovieByID(movieId);
        Assertions.assertEquals(movieId, movieRO.getId());
        Mockito.verify(movieRepository, times(1)).findById(movieId);
    }

    @Test
    void getMovieByIdExtResource(){
        String movieId = "123";
        MovieRO movieStub = new MovieRO("123", "Movie_123", "Movie_123");
        when(movieDBService.getMovieData(movieId)).thenReturn(movieStub);
        doReturn(movieStub).when(movieService).addMovie(any());
        MovieRO movieRO = movieService.getMovieByIdExtResource(movieId);

        Assertions.assertEquals(movieId, movieRO.getId());
        Mockito.verify(movieDBService,times(1)).getMovieData(movieId);
        Mockito.verify(movieService, times(1)).addMovie(any());
        Mockito.verify(movieRepository, times(0)).save(any());

    }

    private Iterable<Movie> getMoviesStub() {
        return Arrays.asList(
                new Movie("123", "Movie_123", "Movie_123"),
                new Movie("1231", "Movie_1231", "Movie_1231"),
                new Movie("1232", "Movie_1232", "Movie_1232")
        );
    }
}
