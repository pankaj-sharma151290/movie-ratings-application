package com.springboot.ms.movieratings.service;

import com.springboot.ms.movieratings.model.Rating;
import com.springboot.ms.movieratings.repository.RatingRepository;
import com.springboot.ms.movieratings.resourceobject.RatingRO;
import com.springboot.ms.movieratings.resourceobject.RatingsRO;
import com.springboot.ms.movieratings.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class TestRatingService {

    @MockBean
    private RatingRepository ratingRepository;

    @Autowired
    private RatingService ratingService;

    @Test
    public void testGetAllRatings() {
        List<Rating> dummyRatingsList = TestUtils.createDummyRatingsList();
        Mockito.when(ratingRepository.findAll()).thenReturn(dummyRatingsList);

        RatingsRO ratingsRO = ratingService.getAllRatings();
        List<Rating> actualList = ratingsRO.getRatings();
        assertThat(actualList, notNullValue());
        assertThat(actualList, hasSize(3));
        assertThat(actualList.get(1).getId(), is(dummyRatingsList.get(1).getId()));
        Mockito.verify(ratingRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetRatingById() {
        String ratingId = "001";
        Optional<Rating> dummyRating = Optional.of(new Rating("001", "Movie_001", "User_001", 2, "I give 2 stars"));
        Mockito.when(ratingRepository.findById(ratingId)).thenReturn(dummyRating);

        Rating rating = ratingService.getRatingById(ratingId).get();
        Assertions.assertEquals(ratingId, rating.getId());
        Mockito.verify(ratingRepository, Mockito.times(1)).findById(ratingId);
    }

    @Test
    public void testGetRatingsByMovieId() {
        String movieId = "Movie_001";
        List<Rating> dummyRatingsList = TestUtils.createDummyRatingsList(movieId);
        Mockito.when(ratingRepository.findAllByMovieId(movieId)).thenReturn(dummyRatingsList);

        RatingsRO ratingsRO = ratingService.getRatingsByMovieId(movieId);
        List<Rating> actualList = ratingsRO.getRatings();
        assertThat(actualList, notNullValue());
        assertThat(actualList, hasSize(3));
        assertThat(actualList.get(1).getId(), is(dummyRatingsList.get(1).getId()));
        assertThat(actualList.get(1).getMovieId(), is(movieId));
        assertThat(actualList.get(2).getMovieId(), is(movieId));
        assertThat(actualList.get(0).getMovieId(), is(movieId));
        Mockito.verify(ratingRepository, Mockito.times(1)).findAllByMovieId(movieId);
    }

    @Test
    public void testGetRatingsByUserId() {
        String userId = "User_001";
        List<Rating> dummyRatingsList = TestUtils.createUserDummyRatingsList(userId);
        Mockito.when(ratingRepository.findAllByUserId(userId)).thenReturn(dummyRatingsList);

        RatingsRO ratingsRO = ratingService.getRatingsByUserId(userId);
        List<Rating> actualList = ratingsRO.getRatings();
        assertThat(actualList, notNullValue());
        assertThat(actualList, hasSize(3));
        assertThat(actualList.get(1).getId(), is(dummyRatingsList.get(1).getId()));
        assertThat(actualList.get(1).getUserId(), is(userId));
        assertThat(actualList.get(2).getUserId(), is(userId));
        assertThat(actualList.get(0).getUserId(), is(userId));
        Mockito.verify(ratingRepository, Mockito.times(1)).findAllByUserId(userId);
    }

    @Test
    public void testAddRating() {
        Rating dummyRating = new Rating("001", "Movie_001", "User_001", 2, "I give 2 stars");
        Mockito.when(ratingRepository.save(Mockito.any(Rating.class))).thenReturn(dummyRating);

        RatingRO ratingRO = ratingService.addRating(new RatingRO(dummyRating));
        assertThat(ratingRO.getId(), is(dummyRating.getId()));
        assertThat(ratingRO.getMovieId(), is(dummyRating.getMovieId()));
        assertThat(ratingRO.getUserId(), is(dummyRating.getUserId()));
        assertThat(ratingRO.getRating(), is(dummyRating.getRating()));
        Mockito.verify(ratingRepository, Mockito.times(1)).save(Mockito.any(Rating.class));
    }

    @Test
    public void testDeleteRating() {
        Mockito.doNothing().when(ratingRepository).delete(Mockito.any(Rating.class));
        ratingService.deleteRating(new Rating("001", "Movie_001", "User_001", 2, "I give 2 stars"));
        Mockito.verify(ratingRepository, Mockito.times(1)).delete(Mockito.any(Rating.class));
    }
}
