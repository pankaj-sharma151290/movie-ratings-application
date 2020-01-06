package com.springboot.microservice.moviescatalog.resourceobject;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RatingsRO {

    private List<RatingRO> ratings;
    private String servicePort;
    private String error;

    public RatingsRO() {
    }

    public RatingsRO(List<RatingRO> ratings) {
        this.ratings = ratings;
    }

    public RatingsRO(String error) {
        this.error = error;
    }

    public List<RatingRO> getRatings() {
        return ratings;
    }

    public void setRatings(List<RatingRO> ratings) {
        this.ratings = ratings;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
