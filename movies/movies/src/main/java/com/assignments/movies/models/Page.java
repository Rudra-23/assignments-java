package com.assignments.movies.models;

import java.util.List;

public class Page {

    public int page;
    public int per_page;
    public  int total;
    public int total_pages;

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }

    public List<MovieData> data;


}
