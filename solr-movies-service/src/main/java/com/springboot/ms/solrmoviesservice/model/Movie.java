package com.springboot.ms.solrmoviesservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "movies_search_index")
public class Movie {

    @Id
    @Indexed(name = "id", type = "string")
    private String id;

    @Indexed(name = "title", type = "string")
    private String title;

    @Indexed(name = "overview", type = "string")
    private String overview;

    public Movie() {
    }

    public Movie(String id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;
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
}
