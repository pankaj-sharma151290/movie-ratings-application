package com.springboot.ms.movieratings.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.springboot.ms.movieratings.model.Rating;
import com.springboot.ms.movieratings.resourceobject.RatingRO;
import com.springboot.ms.movieratings.resourceobject.RatingsRO;
import com.springboot.ms.movieratings.service.RatingService;
import com.springboot.ms.movieratings.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class TestRatingController {

    private MockMvc mockMvc;

    @InjectMocks
    private RatingController ratingController;

    @Mock
    private RatingService ratingService;

    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testGetRatings() throws Exception {
        RatingsRO dummyRatingsRO = new RatingsRO(TestUtils.createDummyRatingsList());
        Mockito.when(ratingService.getAllRatings()).thenReturn(dummyRatingsRO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rating")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RatingsRO ratingsRO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RatingsRO.class);
        List<Rating> actualList = ratingsRO.getRatings();
        assertThat(actualList, notNullValue());
        assertThat(actualList, hasSize(3));
        assertThat(actualList.get(1).getId(), is(dummyRatingsRO.getRatings().get(1).getId()));
        Mockito.verify(ratingService, Mockito.times(1)).getAllRatings();
    }

    @Test
    public void testGetRatingsByMovieId() throws Exception {
        String movieId = "Movie_001";
        RatingsRO dummyRatingsRO = new RatingsRO(TestUtils.createDummyRatingsList(movieId));
        Mockito.when(ratingService.getRatingsByMovieId(movieId)).thenReturn(dummyRatingsRO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rating")
                .param("movieId", movieId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RatingsRO ratingsRO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RatingsRO.class);
        List<Rating> actualList = ratingsRO.getRatings();
        assertThat(actualList, notNullValue());
        assertThat(actualList, hasSize(3));
        assertThat(actualList.get(1).getId(), is(dummyRatingsRO.getRatings().get(1).getId()));
        assertThat(actualList.get(1).getMovieId(), is(movieId));
        assertThat(actualList.get(2).getMovieId(), is(movieId));
        assertThat(actualList.get(0).getMovieId(), is(movieId));
        Mockito.verify(ratingService, Mockito.times(1)).getRatingsByMovieId(movieId);
    }

    @Test
    public void testGetRatingsById() throws Exception {
        String ratingId = "001";
        Optional<Rating> dummyRating = Optional.of( new Rating(ratingId, "Movie_001", "User_001", 2, "I give 2 stars"));
        Mockito.when(ratingService.getRatingById(ratingId)).thenReturn(dummyRating);

        mockMvc.perform(MockMvcRequestBuilders.get("/rating/{ratingId}",ratingId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ratingId)))
                .andExpect(jsonPath("$.movieId", is(dummyRating.get().getMovieId())))
                .andExpect(jsonPath("$.userId", is(dummyRating.get().getUserId())))
                .andExpect(jsonPath("$.rating", is(dummyRating.get().getRating())))
                .andExpect(jsonPath("$.description", is(dummyRating.get().getDescription())));

        Mockito.verify(ratingService,Mockito.times(1)).getRatingById(ratingId);
    }

    @Test
    public void testGetRatingsByUserId() throws Exception {
        String userId = "User_001";
        RatingsRO dummyRatingsRO = new RatingsRO(TestUtils.createUserDummyRatingsList(userId));
        Mockito.when(ratingService.getRatingsByUserId(userId)).thenReturn(dummyRatingsRO);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/rating/user").param("userId",userId)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        RatingsRO ratingsRO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RatingsRO.class);
        List<Rating> actualList = ratingsRO.getRatings();
        assertThat(actualList, notNullValue());
        assertThat(actualList, hasSize(3));
        assertThat(actualList.get(1).getId(), is(dummyRatingsRO.getRatings().get(1).getId()));
        assertThat(actualList.get(1).getUserId(), is(userId));
        assertThat(actualList.get(2).getUserId(), is(userId));
        assertThat(actualList.get(0).getUserId(), is(userId));

        Mockito.verify(ratingService,Mockito.times(1)).getRatingsByUserId(userId);
    }

    public void testGetAddRating() throws Exception {
        RatingRO dummyRatingRO = new RatingRO("001", "Movie_001", "User_001", 2, "I give 2 stars");
        Mockito.when(ratingService.addRating(Mockito.any(RatingRO.class))).thenReturn(dummyRatingRO);

        mockMvc.perform(MockMvcRequestBuilders.post("/rating")
                .content(objectMapper.writeValueAsString(dummyRatingRO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(dummyRatingRO.getId())))
                .andExpect(jsonPath("$.movieId", is(dummyRatingRO.getMovieId())))
                .andExpect(jsonPath("$.userId", is(dummyRatingRO.getUserId())))
                .andExpect(jsonPath("$.rating", is(dummyRatingRO.getRating())))
                .andExpect(jsonPath("$.description", is(dummyRatingRO.getDescription())));

        Mockito.verify(ratingService,Mockito.times(1)).addRating(Mockito.any(RatingRO.class));

    }
}

