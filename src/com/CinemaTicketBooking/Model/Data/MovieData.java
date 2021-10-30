package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.SaveData;
import com.CinemaTicketBooking.Model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieData {
    private static AtomicInteger MOVIE_ID_GENERATOR ;
    private static SaveData<Movie> movieSaveData = new SaveData<>("files/movies.txt");
    private static List<Movie> movies = new ArrayList<>();

    public void fetchAndSetMovies(){
        movies = movieSaveData.openList();
        fetchMovieId();
    }

    private void fetchMovieId(){
        MOVIE_ID_GENERATOR = new AtomicInteger(movies.get(movies.size()-1).getMovieId()+1);
    }

    public boolean addMovie(String movieName){
        Movie movie = new Movie(MOVIE_ID_GENERATOR.getAndIncrement(),movieName);
        movies.add(movie);
        return movieSaveData.saveListToFile(movies);
    }

    public List<Movie> getMovies(){
        return movies;
    }
}
