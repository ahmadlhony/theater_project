package com.CinemaTicketBooking.Model;

import java.util.*;
import java.io.*;

public class Movie implements Serializable{
    private int movieId;
    private String movieName;
    private boolean isShowing=false;


    public Movie(int movieId, String movieName) {




        this.movieId = movieId;
        this.movieName = movieName;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public boolean isShowing(){
        return isShowing;
    }


    @Override
    public String toString() {
        return "{Movie Name: "+ movieName + ", MovieId: "+movieId + "}" ;
    };

}
