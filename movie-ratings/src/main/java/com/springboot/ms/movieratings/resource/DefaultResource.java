package com.springboot.ms.movieratings.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class DefaultResource {

    @Value("${config.server.test.data}")
    private String configServerTestData;

    @RequestMapping("/")
    public String getStatus(){
        return "Movie-Ratings is up and running - "+configServerTestData;
    }
}
