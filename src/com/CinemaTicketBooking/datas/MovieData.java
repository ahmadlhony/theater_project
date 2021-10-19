package com.CinemaTicketBooking.datas;

import com.CinemaTicketBooking.model.Movie;

import java.io.*;
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
            System.out.println("Movie is already Available");
            return false;
        }
        Movie movie = new Movie(ID_GENERATOR.getAndIncrement(),movieName);
//        C:\Users\ahmad\IdeaProjects\CinemaTicketBooking\

        try{
            FileOutputStream f = new FileOutputStream("files\\movies\\movies_"+movie.getMovieId()+".txt");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(movie);
            o.close();
            f.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found Exception. #MovieData*addMovie");
            return false;
        } catch (IOException e){
            System.out.println("Error initializing stream. #MovieData*addMovie");
            return false;
        }

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

//    public static List<Movie> readedMovieFromFile(){
//        List<Movie> readedMovie = new ArrayList<>();
//        try{
//            while(true) {
//                FileInputStream f = new FileInputStream("files\\movies\\movies_"+movies.get()+".txt");
//                ObjectInputStream o = new ObjectInputStream(f);
//
//                Movie movie = (Movie) o.readObject();
//                readedMovie.add(movie);
//                System.out.println("readed");
//                o.close();
//                f.close();
//            }
//
//
//
//
//        } catch (FileNotFoundException e){
//            System.out.println("File not found Exception. #MovieData*addMovie");
//            return readedMovie;
//        } catch (IOException e){
//            System.out.println("Error initializing stream. #MovieData*addMovie");
//            return readedMovie;
//        }catch (ClassNotFoundException e){
//            System.out.println("class not found exception #MovieData*readedMovieFromFile");
//        }catch(EOFException e){
//            break;
//        }
//        return readedMovie;
//
//
//    }



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
