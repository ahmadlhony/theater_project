package com.CinemaTicketBooking.datas;

import com.CinemaTicketBooking.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MovieData {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);

    private static List<Movie> movies = new ArrayList<>(Arrays.asList(new Movie(ID_GENERATOR.getAndIncrement(),"Avengers"),
            new Movie(ID_GENERATOR.getAndIncrement(),"Endgame"),
            new Movie(ID_GENERATOR.getAndIncrement(),"steel"),
            new Movie(ID_GENERATOR.getAndIncrement(),"meg")
    ));

    public boolean addMovie(String movieName){
        if (getMovieByName(movieName)!=null){
            System.out.println("Movie is Available");
            return false;
        }
        Movie movie = new Movie(ID_GENERATOR.getAndIncrement(),movieName);
        movies.add(movie);
        return true;
    }

    public Movie getMovieById(int movieId){
        return movies.get(movieId);
    }

    public Movie getMovieByName(String movieName){
        for(Movie movie:movies){
            if (movie.getMovieName().equals(movieName))
                return movie;
        }
        System.out.println("MovieName not exist");
        return null;
    }



    public List<Movie> getMovies(){
        return movies;
    }

    public boolean putMovieToShow(String movieName, int theaterId, String showTime){
        int movieId = getMovieByName(movieName).getMovieId();
        if (movieId==-1){
            return false;
        }
        movies.get(movieId).showMovie(theaterId,showTime);
        return true;
    }

    public boolean removeMovieShow(String movieName, int theaterId, String showTime){
        int movieId = getMovieByName(movieName).getMovieId();
        if (movieId==-1){
            return false;
        }
        movies.get(movieId).unShowMovie(theaterId,showTime);
        return true;

    }





}
