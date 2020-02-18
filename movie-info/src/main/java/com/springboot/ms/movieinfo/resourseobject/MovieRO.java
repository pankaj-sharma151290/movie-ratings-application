package com.springboot.ms.movieinfo.resourseobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.ms.movieinfo.model.Movie;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MovieRO {

    private String id;
    private String title;
    private String overview;
    private String servicePort;

    public MovieRO() {
    }

    public MovieRO(String id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
    }

    public MovieRO(Movie movie) {
        this.id = movie.getId();
        this.title = movie.getTitle();
        this.overview = movie.getOverview();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

}
