package com.springboot.microservice.moviescatalog.resource;

import com.springboot.microservice.moviescatalog.resourceobject.MovieRatingsCatalogRO;
import com.springboot.microservice.moviescatalog.resourceobject.MovieRatingCatalogRO;
import com.springboot.microservice.moviescatalog.service.CatalogService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/catalog")
public class CatalogResource {

    @Autowired
    Logger logger;

    @Autowired
    CatalogService catalogService;

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public MovieRatingsCatalogRO getMovieRatingsCatalog() {
        return catalogService.getMovieRatingsCatalog();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/user")
    public MovieRatingsCatalogRO getMovieRatingsCatalogForUser(@RequestParam(value = "userId", required = true) String userId) {
        return catalogService.getMovieRatingsCatalogForUser(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/dbmovies/user")
    public MovieRatingCatalogRO getMovieRatingCatalogForUser(@RequestParam(value = "userId", required = true) String userId) {
        return catalogService.getMovieRatingCatalogForUser(userId);
    }

}
