package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.MovieData;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieController {


    public Packet addMovie(String movieName){
        MovieData movieData = new MovieData();
        Packet packet = new Packet(0);
        if (getMovieByName(movieName).isPresent()){
            String massage = "Movie is already Available #Server*MovieData*addMovie";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }

        if(!movieData.addMovie(movieName)){
            String massage = "movieData.addMovie() failed #Server*MovieData*addMovie";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }
        packet.setMessage(1);
        return packet;
    }

    public static boolean isMovieExist(String movieName){
        MovieData movieData = new MovieData();
        return movieData.getMovies().stream().anyMatch(m -> m.getMovieName().equals(movieName));
    }

    //we dont need it in client
    public static Optional<Movie> getMovieByName(String movieName){
        MovieData movieData = new MovieData();
        return movieData.getMovies().stream().filter(movie -> movie.getMovieName().equals(movieName)).findAny();
    }

    public Packet availableMovies(){
        MovieData movieData = new MovieData();
        Packet packet = new Packet(1);
        List<String> items = new ArrayList<>(30);
        movieData.getMovies().forEach(m -> items.add(m.getMovieName() + "."));
        packet.setItem(items);
        return packet;

    }

}
