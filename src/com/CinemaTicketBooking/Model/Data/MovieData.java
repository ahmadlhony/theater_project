package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Packet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieData {
    private static AtomicInteger MOVIE_ID_GENERATOR ;
    private static ClientServerController<Movie> movieClientServerController = new ClientServerController<>();
    private static List<Movie> movies = new ArrayList<>();

    public void fetchAndSetMovies(){
        Packet<Movie> moviePacket = new Packet<>(4);
        try {
            movies = movieClientServerController.get(moviePacket).getItem();
        }catch (NullPointerException e){
            System.out.println("Movies is null #MovieData*fetchAndSetMovies");
        }
        fetchMovieId();
    }

    private void fetchMovieId(){
        MOVIE_ID_GENERATOR = new AtomicInteger(movies.size());
    }

    public boolean addMovie(String movieName){
        Movie movie = new Movie(MOVIE_ID_GENERATOR.getAndIncrement(),movieName);
        movies.add(movie);
        Packet<Movie> moviePacket = new Packet<>(3);
        moviePacket.setItem(movies);
        return movieClientServerController.post(moviePacket);
    }

    public List<Movie> getMovies(){
        return movies;
    }
}
