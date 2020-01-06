package com.springboot.ms.movieratings.resourceobject;

import com.springboot.ms.movieratings.model.Rating;

import java.util.List;

public class RatingsRO {

    private List<Rating> ratings;
    private String servicePort;

    public RatingsRO() {
    }

    public RatingsRO(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }
}
