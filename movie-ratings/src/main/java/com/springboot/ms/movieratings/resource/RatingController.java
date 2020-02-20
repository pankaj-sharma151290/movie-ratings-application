package com.springboot.ms.movieratings.resource;

import com.springboot.ms.movieratings.model.Rating;
import com.springboot.ms.movieratings.resourceobject.RatingRO;
import com.springboot.ms.movieratings.resourceobject.RatingsRO;
import com.springboot.ms.movieratings.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping(method = RequestMethod.GET, value = "")
    public RatingsRO getRatings() {
        return ratingService.getAllRatings();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{ratingId}")
    public RatingRO getRatingById(@PathVariable String ratingId) {
        RatingRO ratingRO;
        Optional<Rating> rating = ratingService.getRatingById(ratingId);
        if(rating.isPresent())
            ratingRO = new RatingRO(rating.get());
        else
            ratingRO = new RatingRO("There is no Rating object present with given id: "+ratingId);
        return ratingRO;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movie/{movieId}")
    public RatingsRO getRatingsByMovieId(@PathVariable String movieId) {
        return ratingService.getRatingsByMovieId(movieId, true);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    public RatingsRO getRatingsByUserId(@RequestParam(value = "userId", required = true) String userId) {
        return ratingService.getRatingsByUserId(userId, true);
    }

    @RequestMapping(method = RequestMethod.POST, value = "")
    public RatingRO getAddRating(@RequestBody() RatingRO ratingRO) {
        return ratingService.addRating(ratingRO);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ratingId}")
    public Boolean deleteRating(@PathVariable String ratingId) {
        return ratingService.deleteRating(ratingId);
    }


}
