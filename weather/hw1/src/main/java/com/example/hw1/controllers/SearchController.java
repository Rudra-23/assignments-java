package com.example.hw1.controllers;

import com.example.hw1.models.University;
import com.example.hw1.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    // http://localhost:8080/search/all
    @GetMapping("/search/all")
    public University[] search() {
            return searchService.searchAll();
    }

    // http://localhost:8080/search/countries?country=United+States&country=China&country=India
    @GetMapping("/search/countries")
    public University[] search(@RequestParam(value = "country", required = false) List<String> countries) {
        if(countries == null) {
            return new University[] {};
        }
        return searchService.searchCountries(countries);
    }
}
