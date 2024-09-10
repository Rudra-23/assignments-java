package com.example.hw1.services;

import com.example.hw1.models.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Service
public class SearchService {

    private RestTemplate restTemplate;
    private final String URL = "http://universities.hipolabs.com/search";


    @Autowired
    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public University[] searchAll() {
        return restTemplate.getForObject(URL, University[].class);
    }

    private List<University> searchCountry(String country) {
        University[] universities = restTemplate.getForObject(URL + "?country=" + country, University[].class);
        return universities != null? Arrays.asList(universities): Collections.emptyList();
    }

    public University[] searchCountries(List<String> countries) {

        // This is to ensure that users don't create excessive threads.
        Executor executor = Executors.newFixedThreadPool(Math.min(countries.size(), 10));

        List<University> universities = Collections.synchronizedList(new ArrayList<>());
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for(String country: countries) {
            CompletableFuture<List<University>> future = CompletableFuture.supplyAsync(
                    () -> searchCountry(country),
                    executor
            );

            future.thenAccept(universities::addAll);
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        return universities.toArray(new University[0]);
    }
}
