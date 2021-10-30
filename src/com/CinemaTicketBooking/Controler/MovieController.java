package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.MovieData;
import com.CinemaTicketBooking.Model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieController {


    public static boolean addMovie(String movieName){
        MovieData movieData = new MovieData();
        if (getMovieByName(movieName).isPresent()){
            System.out.println("Movie is already Available #MovieData*addMovie");
            return false;
        }
        return movieData.addMovie(movieName);
    }

    public static boolean isMovieExist(String movieName){
        MovieData movieData = new MovieData();
        return movieData.getMovies().stream().anyMatch(m -> m.getMovieName().equals(movieName));
    }

    public static Optional<Movie> getMovieByName(String movieName){
        MovieData movieData = new MovieData();
        return movieData.getMovies().stream().filter(movie -> movie.getMovieName().equals(movieName)).findAny();
    }

    public void availableMovies(){
        MovieData movieData = new MovieData();
        System.out.println("Available Movies: ");
        movieData.getMovies().forEach(m -> System.out.println(m.getMovieName() + "."));
        System.out.println();
    }

}
