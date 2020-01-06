package com.springboot.ms.movieinfo.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.ms.movieinfo.model.Movie;
import com.springboot.ms.movieinfo.resourse.MovieResource;
import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import com.springboot.ms.movieinfo.resourseobject.MoviesRO;
import com.springboot.ms.movieinfo.service.MovieService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
public class TestMovieResouceTwo {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Mock
    private MovieService movieService;

    @InjectMocks
    private MovieResource movieResource;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(movieResource).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGreet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/greet"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    public void testGetMovieByIdIntResource() throws Exception {
        String movieId = "123";
        MovieRO moviesRO = new MovieRO("123", "Movie_123", "Movie_123");
        Mockito.when(movieService.getMovieByID(movieId)).thenReturn(moviesRO);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/{movieId}", movieId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(moviesRO.getId())))
                .andExpect(jsonPath("$.title", Matchers.is(moviesRO.getTitle())))
                .andExpect(jsonPath("$.overview", Matchers.is(moviesRO.getOverview())));

        Mockito.verify(movieService, Mockito.times(1)).getMovieByID(movieId);
    }

    @Test
    public void testGetMovieByIdExtResource() throws Exception {
        String movieId = "123";
        MovieRO moviesRO = new MovieRO("123", "Movie_123", "Movie_123");
        Mockito.when(movieService.getMovieByIdExtResource(movieId)).thenReturn(moviesRO);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/extres/themoviedb/{movieId}", movieId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(moviesRO.getId())))
                .andExpect(jsonPath("$.title", Matchers.is(moviesRO.getTitle())))
                .andExpect(jsonPath("$.overview", Matchers.is(moviesRO.getOverview())));

        Mockito.verify(movieService, Mockito.times(1)).getMovieByIdExtResource(movieId);
    }

    @Test
    public void testGetMovies() throws Exception {
        MoviesRO moviesRO = new MoviesRO(getMoviesStub());
        Mockito.when(movieService.getMoviesList()).thenReturn(moviesRO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/movies/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        MoviesRO response = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), MoviesRO.class);
        assertThat(StreamSupport.stream(response.getMovies().spliterator(), false).collect(Collectors.toList()), hasSize(3));
        Mockito.verify(movieService, Mockito.times(1)).getMoviesList();
    }

    @Test
    public void testAddMovie() throws Exception {
        MovieRO movieRO = new MovieRO("123", "Movie_123", "Movie_123");
        Mockito.when(movieService.addMovie(Mockito.any(MovieRO.class))).thenReturn(movieRO);

        String requestBody = objectMapper.writeValueAsString(movieRO);
        mockMvc.perform(MockMvcRequestBuilders
                .post("/movies")
                .content(requestBody).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(movieRO.getId())));

        Mockito.verify(movieService).addMovie(Mockito.any(MovieRO.class));

    }

    private Iterable<Movie> getMoviesStub() {
        return Arrays.asList(
                new Movie("123", "Movie_123", "Movie_123"),
                new Movie("1231", "Movie_1231", "Movie_1231"),
                new Movie("1232", "Movie_1232", "Movie_1232")
        );
    }

}
