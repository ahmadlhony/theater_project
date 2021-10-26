package com.CinemaTicketBooking.ControlerAndData;

import com.CinemaTicketBooking.Model.Bill;
import com.CinemaTicketBooking.Model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieData {
    private static AtomicInteger MOVIE_ID_GENERATOR ;
    private SaveData<Movie> movieSaveData = new SaveData<>("files/movies/");


    private static List<Movie> movies = new ArrayList<>();


    public void fetchAndSetMovies(){
        movies = movieSaveData.open();
        fetchMovieId();
    }

    private void fetchMovieId(){
        MOVIE_ID_GENERATOR = new AtomicInteger(movies.get(movies.size()-1).getMovieId()+1);
    }

    public boolean addMovie(String movieName){
        if (getMovieByName(movieName)!=null){
            System.out.println("Movie is already Available");
            return false;
        }
        Movie movie = new Movie(MOVIE_ID_GENERATOR.getAndIncrement(),movieName);
        movies.add(movie);

        return movieSaveData.add(movie,"movies_"+movie.getMovieId());
    }

    public Movie getMovieByName(String movieName){
        for(Movie movie:movies){
            if (movie.getMovieName().equals(movieName))
                return movie;
        }
        System.out.println("MovieName not exist");
        return null;
    }

    public boolean isMovieExist(String movieName){
        return movies.stream()
                .anyMatch(m -> m.getMovieName().equals(movieName));

    }

    public List<Movie> getMovies(){
        return movies;
    }

}
