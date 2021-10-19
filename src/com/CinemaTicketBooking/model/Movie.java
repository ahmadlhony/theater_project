package com.CinemaTicketBooking.model;

import java.util.*;
import java.io.*;

public class Movie implements Serializable{
    private int movieId;
    private String movieName;
    private boolean isShowing=false;
    private static Map<Integer,List<String>> showTimes= new HashMap<>();


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

    public void showMovie(int theaterId, String showTime){
        if (theaterId>3 || theaterId < 1)
            return;

        if (!showTimes.containsKey(theaterId))
            showTimes.put(theaterId,new ArrayList<>(3));
        showTimes.get(theaterId).add(showTime);

        isShowing=true;
    }

    public void unShowMovie(int theaterId, String showTime){
        if (!showTimes.get(theaterId).contains(showTime)){
            System.out.println("show time is not booked #Movie");
            return;
        }
        showTimes.get(theaterId).remove(showTime);
        if (showTimes.get(theaterId).isEmpty()){
            showTimes.remove(theaterId);
        }
        if (showTimes.isEmpty())
            isShowing=false;
    }

    public Map<Integer,List<String>> getMovieShowTimes(){
        return showTimes;
    }

    public boolean isShowing(){
        return isShowing;
    }





    @Override
    public String toString() {
        return "{Movie Name: "+ movieName + ", MovieId: "+movieId + "}" ;
    };

}
