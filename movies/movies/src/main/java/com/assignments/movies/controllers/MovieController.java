package com.assignments.movies.controllers;

import com.assignments.movies.models.ResponseData;
import com.assignments.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseData getMovies(@RequestParam(value = "year", required = false) String year) {
        int numOfpages = movieService.getPages();

        ResponseData responseData = movieService.getMoviesofYear(year, numOfpages);
        return responseData;
    }
}
