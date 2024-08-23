package com.assignments.movies.models;

public class MovieData {
    @Override
    public String toString() {
        return "MovieData{" +
                "Title='" + Title + '\'' +
                ", Year='" + Year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }

    public String Title;
    public String Year;
    public String imdbID;
}
