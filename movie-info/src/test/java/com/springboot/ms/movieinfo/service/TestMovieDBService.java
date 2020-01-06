/*
package com.springboot.ms.movieinfo.service;

import com.springboot.ms.movieinfo.resourseobject.MovieRO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class TestMovieDBService {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private MovieDBService movieDBService;

    @Test
    void testGetMovieData() {
        String movieId = "123";
        MovieRO movieStub = new MovieRO("123", "Movie_123", "Movie_123");
        String expectedURL = "https://api.themoviedb.org/3/movie/123?api_key=28485c27273cea60973e02d57136aae2";
        Mockito.when(restTemplate.getForObject(Mockito.any(), Mockito.any(), (Object) Mockito.any())).thenReturn(movieStub);

        MovieRO movieRO = movieDBService.getMovieData(movieId);
        Assertions.assertEquals(movieId, movieRO.getId());
        //verify how many times method was called
        Mockito.verify(restTemplate, Mockito.times(1)).getForObject(Mockito.any(), Mockito.any(), (Object) Mockito.any());

        //verify what URL was used
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(restTemplate).getForObject(captor.capture(), Mockito.any(), (Object) Mockito.any());
        Assertions.assertEquals(expectedURL, captor.getValue());
    }

}
*/
