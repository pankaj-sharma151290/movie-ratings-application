package com.springboot.ms.movieinfo.service.serviceimpl;

import com.springboot.ms.movieinfo.client.TheMovieDBExtServiceClient;
import com.springboot.ms.movieinfo.model.Movie;
import com.springboot.ms.movieinfo.repository.MovieRepository;
import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.resourseobject.MoviesRO;
import com.springboot.ms.movieinfo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private TheMovieDBExtServiceClient movieDBExtClient;

    @Value("${api.moviedb.key}")
    private String movieDbApiKey;

    public MovieServiceImpl() {
    }

    @Override
    public MoviesRO getMoviesList() {
        final MoviesRO moviesRo = new MoviesRO(movieRepository.findAll().values());
        moviesRo.setServicePort(getActiveServiceInstancePort());
        return moviesRo;
    }

    @Override
    public MovieRO getMovieByID(String movieID) {
        //movieRepository.findById(movieID).ifPresent(new UpdateMovieRO(movieRO));
        MovieRO movieRO = null;
        Optional<Movie> movie = movieRepository.findById(movieID);
        if (movie.isPresent()) {
            movieRO = new MovieRO(movie.get());
            movieRO.setServicePort(getActiveServiceInstancePort());
        }
        return movieRO;
    }

    @Override
    public MovieRO getMovieByIdExtResource(String movieId) {
       // MovieRO movieRO = movieDBService.getMovieData(movieId);
        MovieRO movieRO = movieDBExtClient.getMovieByID(movieId, movieDbApiKey);
        if (Objects.nonNull(movieRO))
            addMovie(movieRO);
        return movieRO;
    }

   /* protected class UpdateMovieRO implements Consumer<Movie> {
        private MovieRO movieRO;
        UpdateMovieRO(MovieRO movieRO) {
            this.movieRO = movieRO;
        }
        public UpdateMovieRO() {
        }
        @Override
        public void accept(Movie movie) {
            this.movieRO.setId(movie.getId());
            this.movieRO.setTitle(movie.getTitle());
            this.movieRO.setOverview(movie.getOverview());
        }
    }*/

    @Override
    public Boolean addMovie(MovieRO movieRO) {
        return movieRepository.save(movieRoToMovieMapper(movieRO));
    }

    private String getActiveServiceInstancePort() {
        return environment.getProperty("local.server.port");
    }

    private Movie movieRoToMovieMapper(MovieRO movieRO) {
        return new Movie(movieRO.getId(), movieRO.getTitle(), movieRO.getOverview());
    }
}
