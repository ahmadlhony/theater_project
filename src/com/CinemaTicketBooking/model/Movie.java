package com.CinemaTicketBooking.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie {
    private int movieId;
    private String movieName;

    public Movie(){

    }

    public Movie(int movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;
    }

    List<Movie> movies = new ArrayList<>(Arrays.asList(new Movie(1,"Avengers"),
            new Movie(2,"Endgame"),
            new Movie(3,"Man of Steel"),
            new Movie(4,"The meg")
    ));

    @Override
    public String toString() {
        return "{Movie Name: "+ movieName + ", MovieId: "+movieId + "}";
    };

}
