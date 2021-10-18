package com.CinemaTicketBooking.datas;

import com.CinemaTicketBooking.model.Movie;
import com.CinemaTicketBooking.model.Theater;

import java.util.*;

public class Cinema {
    //list of theaters by given time
    private static final List<Theater> theaters = new ArrayList<>(Arrays.asList(
            new Theater(1,"2:00"),
            new Theater(2,"2:00"),
            new Theater(3,"2:00"),
            new Theater(1,"5:00"),
            new Theater(2,"5:00"),
            new Theater(3,"5:00"),
            new Theater(1,"8:00"),
            new Theater(2,"8:00"),
            new Theater(3,"8:00")
    ));

    private static Map<Integer, Movie> bookedShows = new HashMap<>();
    MovieData movieData = new MovieData();



    public boolean bookShow(int theaterId, String showTime,String movieName){
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            System.out.println("Showtime is not available.");
            return false;
        }

        int theaterItem = theaterItemInList(theaterId,showTime);
        if (bookedShows.containsKey(theaterItem)){
            System.out.println("sorry This show is booked");
            return false;
        }
        Movie movie = movieData.getMovieByName(movieName);
        bookedShows.put(theaterItem,movie);
        if(!movieData.putMovieToShow(movie.getMovieName(),theaterId,showTime)){
            System.out.println("Movie not added to show Cinema#40");
        }

        return theaters.get(theaterItem).bookTheater(movie);


    }

    public boolean UnBookShow(int theaterId, String showTime){
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            System.out.println("Showtime is not available.");
            return false;
        }

        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!bookedShows.containsKey(theaterItem)){
            System.out.println("This show is not booked");
            return false;
        }
        movieData.removeMovieShow(bookedShows.get(theaterItem).getMovieName(),theaterId,showTime);
        bookedShows.remove(theaterItem);

        return theaters.get(theaterItem).unBookTheater();


    }

    public static Theater getTheater(int theaterId,String showTime){
        if (!theaters.get(theaterItemInList(theaterId, showTime)).isTheaterBooked()){
            System.out.println("Theater is not Booked");
            return null;
        }
        return theaters.get(theaterItemInList(theaterId, showTime));
    }

    private static int theaterItemInList(int theaterId, String showTime){
        if (theaterId<1 || theaterId>3){
            System.out.println("Theater Id not available.");
            return -1;

        }
        int temp=-10;
        if (showTime.equals("2:00")){
            temp=0;
        }else if (showTime.equals("5:00")){
            temp = 3;
        }else if (showTime.equals("8:00")){
            temp=6;

        }
        return theaterId+temp;
    }

    public List<Theater> GetTheaters(){
        return theaters;
    }



    public List<String> moviesInShow() {
        List<String> myList = new ArrayList<>();
        for (Movie movie:bookedShows.values()){
            myList.add(movie.getMovieName());
        }
        return myList;
    }

    public List<Theater> availableShowTime() {
        List<Theater> myList = new ArrayList<>(10);
        for(var theater:theaters){
            if(!bookedShows.containsKey(theaterItemInList(theater.getTheaterId(),theater.getShowTime()))){
                myList.add(theater);
            }
        }
        return myList;
    }

    public static void availableShows(){
        Cinema cinema = new Cinema();
        System.out.println("Available Shows: ");
        for(var theater: cinema.availableShowTime()){

            System.out.println("Theater: " + theater.getTheaterId()+"  is Available at: "+theater.getShowTime());
        }
        System.out.println();

    }

    public static void availableMovies(MovieData movieData){
        System.out.println("Available Movies: ");
        for(var movie: movieData.getMovies()){
            System.out.println(movie.getMovieName() + (movie.isShowing() ? " is already Showing at: " : " is not showing"));
            movie.getMovieShowTimes().forEach((k, v) -> {
                if (movie.isShowing()) {
                    System.out.println("Theater: " + k + " in: " + v.toString());
                }
            });
        }
        System.out.println();
    }

    public static void availableMoviesShows(){
        MovieData movieData = new MovieData();
        System.out.println("Available Movie Shows: ");
        for(var movie: movieData.getMovies()){
            if(bookedShows.containsValue(movie)) {
                //Error showing movie
                if (movie.isShowing()) {
                    System.out.println(movie.getMovieName() + " is already Showing at: ");
                }
                movieData.getMovieByName(movie.getMovieName()).getMovieShowTimes().forEach((k, v) -> {
                    if (movie.isShowing()) {
                        System.out.println("Theater: " + k + " in: " + v.toString());
                    }
                });
            }
        }

    }

    public static Movie movieForTheaterAndShowTime(int theaterId,String showTime){
        for (var theater:theaters){
            if (theater.getTheaterId()==theaterId && theater.getShowTime().equals(showTime)){
                return theater.getMovie();
            }
        }
        System.out.println("Movie not found #Cinema*movieForTheaterAndShowTime");
        return null;
    }





    public static String showIndexToShowTime(int index){
        if (index==1)
            return "2:00";
        else if (index==2)
            return "5:00";
        else if (index==3)
            return "8:00";
        else{
            System.out.println("Show index not available AdminInterface#106");
            return null;
        }
    }

    public static boolean bookSeat(int theaterId,String showTime,int seatId){
        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cienma#bookSeat");
            return false;
        }
        return theaters.get(theaterItem).bookSeat(seatId);

    }

    public static boolean unBookSeat(int theaterId,String showTime,int seatId){
        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cienma#bookSeat");
            return false;
        }
        if (!theaters.get(theaterItem).unBookSeat(seatId)){
            System.out.println("Unbooking seat failed #Cinema*unBookSeat");
            return false;
        }
        return true;

    }


}
