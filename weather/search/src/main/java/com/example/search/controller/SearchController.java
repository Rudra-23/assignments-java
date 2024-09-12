package com.example.search.controller;

import com.example.search.models.DataModel;
import com.example.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/weather/search")
    public ResponseEntity<?> getDetails() {
        //TODO
        return new ResponseEntity<>("this is search service", HttpStatus.OK);
    }

    public ResponseEntity<?> getDataFallback(Throwable throwable) {
        return new ResponseEntity<>("Service is temporarily unavailable. Please try again later. " + throwable.getMessage(), HttpStatus.OK);
    }

    @GetMapping("/data")
    @HystrixCommand(fallbackMethod = "getDataFallback")
    public ResponseEntity<?> getData() throws Exception {

        try {
            List<String> data = searchService.getData();
            return new ResponseEntity<>(data, HttpStatus.OK);
        }
        catch (ResourceAccessException e) {
            throw new Exception("Details or hw3 Service unavailable");
        }
        catch (Exception e) {
            throw new Exception("Something wrong happened");
        }
    }
}
