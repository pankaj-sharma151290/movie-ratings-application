package com.springboot.ms.movieratings.utils;

import com.springboot.ms.movieratings.model.Rating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtils {

    public static List<Rating> createDummyRatingsList() {
        return new ArrayList<>(Arrays.asList(
                new Rating("001", "Movie_001", "User_001", 2, "I give 2 stars"),
                new Rating("002", "Movie_002", "User_002", 3, "I give 3 stars"),
                new Rating("003", "Movie_003", "User_003", 4, "I give 4 stars")
        ));
    }

    public static List<Rating> createDummyRatingsList(String movieId) {
        return new ArrayList<>(Arrays.asList(
                new Rating("001", movieId, "User_001", 2, "I give 2 stars"),
                new Rating("002", movieId, "User_002", 3, "I give 3 stars"),
                new Rating("003", movieId, "User_003", 4, "I give 4 stars")
        ));
    }

    public static List<Rating> createUserDummyRatingsList(String UserId) {
        return new ArrayList<>(Arrays.asList(
                new Rating("001", "Movie_001", UserId, 2, "I give 2 stars"),
                new Rating("002", "Movie_002", UserId, 3, "I give 3 stars"),
                new Rating("003", "Movie_003", UserId, 4, "I give 4 stars")
        ));
    }

}
