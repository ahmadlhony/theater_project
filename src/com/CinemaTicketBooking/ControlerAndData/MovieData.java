package com.CinemaTicketBooking.ControlerAndData;

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
        if (getMovieByName(movieName).isPresent()){
            System.out.println("Movie is already Available");
            return false;
        }
        Movie movie = new Movie(MOVIE_ID_GENERATOR.getAndIncrement(),movieName);
        movies.add(movie);
        return movieSaveData.saveListToFile(movies);
    }

    public Optional<Movie> getMovieByName(String movieName){
//        for(Movie movie:movies){
//            if (movie.getMovieName().equals(movieName))
//                return movie;
//        }
//
//        System.out.println("MovieName not exist");

        return movies.stream().filter(movie -> movie.getMovieName().equals(movieName)).findAny();
    }

    public boolean isMovieExist(String movieName){
        return movies.stream().anyMatch(m -> m.getMovieName().equals(movieName));
    }

    public void availableMovies(){
        System.out.println("Available Movies: ");
        movies.forEach(m -> System.out.println(m.getMovieName() + "."));
        System.out.println();
    }

    public List<Movie> getMovies(){
        return movies;
    }

}
