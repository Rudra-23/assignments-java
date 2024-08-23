package com.assignments.movies.services;

import com.assignments.movies.models.Page;
import com.assignments.movies.models.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MovieService {

    private RestTemplate restTemplate;

    private final String URL = "https://jsonmock.hackerrank.com/api/movies";

    @Autowired
    public MovieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public int getPages() {
        return Objects.requireNonNull(restTemplate.getForObject(URL, Page.class)).total_pages;
    }

    private int getPageMoviesCount(int page, String year) {
         Page p = Objects.requireNonNull(restTemplate.getForObject(URL + "?page=" + page, Page.class));
         return (int) p.data.stream().filter((d) -> d.Year.equals(year)).count();
    }

    public ResponseData getMoviesofYear(String year, int numOfPages) {
        ResponseData responseData = new ResponseData();
        responseData.setYear(year);


        AtomicInteger count = new AtomicInteger(0);
        List<CompletableFuture<?>> futures = new ArrayList<>();
        for(int page = 1; page <= numOfPages; page++) {

            int finalPage = page;
            CompletableFuture<?> future = CompletableFuture.supplyAsync(() -> getPageMoviesCount(finalPage, year));
            future.thenAccept((c) -> count.getAndAdd((int) c));
            futures.add(future);

        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        responseData.setCount(count.get());
        return responseData;
    }

}
