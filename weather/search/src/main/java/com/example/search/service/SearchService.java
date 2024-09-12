package com.example.search.service;

import com.example.search.config.RestTemplateConfig;
import com.example.search.models.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class SearchService {

    private String detailsUrl = "http://DETAILS/details/port";
    private String hw3Url = "http://HW3/students/details/port";

    private RestTemplate restTemplate;

    public SearchService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private DataModel getDetailsInfo() throws ResourceAccessException {
        try {
            ResponseEntity<DataModel> response = restTemplate.getForEntity(detailsUrl, DataModel.class);
            return response.getBody();
        }
        catch (Exception e) {
            throw new ResourceAccessException("Cannot access details");
        }

    }

    private String getHw3Info() throws ResourceAccessException {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(hw3Url, String.class);
            return response.getBody();
        }
        catch (Exception e) {
            throw new ResourceAccessException("Cannot access hw3");
        }

    }

    public List<String> getData() throws ResourceAccessException {
        List<String> list = new ArrayList<>();
        list.add(getDetailsInfo().toString());
        list.add(getHw3Info());
        return list;
    }

}
