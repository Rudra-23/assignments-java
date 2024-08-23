package com.assignments.movies.models;

public class ResponseData {
    private String year;

    public int getCount() {
        return count;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "year=" + year +
                ", count=" + count +
                '}';
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private int count;
}
