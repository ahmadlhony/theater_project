package com.CinemaTicketBooking.ControlerAndData;
import com.CinemaTicketBooking.Model.Bill;
import com.CinemaTicketBooking.Model.Theater;

import java.util.*;

public class Cinema {
    private static SaveData<Theater> cinemaSaveData = new SaveData<>("files/theaters/");
    //list of theaters by given time
    private static List<Theater> theaters = new ArrayList<>();
    private static Map<Integer, String> bookedShows = new HashMap<>();
    MovieData movieData = new MovieData();

//    new Theater(1,"2:00"),
//            new Theater(2,"2:00"),
//            new Theater(3,"2:00"),
//            new Theater(1,"5:00"),
//            new Theater(2,"5:00"),
//            new Theater(3,"5:00"),
//            new Theater(1,"8:00"),
//            new Theater(2,"8:00"),
//            new Theater(3,"8:00")

    public void fetchAndSetTheaters(){
        theaters = cinemaSaveData.open();
        fetchAndSetBookedShow();
    }

    private void fetchAndSetBookedShow(){
        theaters.stream()
                .filter(Theater::isTheaterBooked)
                .forEach(m->{
                    bookedShows.put(theaterItemInList(m.getTheaterId(),m.getShowTime()),m.getMovie().getMovieName());
                });

    }







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

        if (!movieData.isMovieExist(movieName)){
            return false;
        }

        bookedShows.put(theaterItemInList(theaterId,showTime),movieName);
        if(!theaters.get(theaterItem).bookTheater(movieData.getMovieByName(movieName)))
            return false;
        MovieData movieData = new MovieData();
        theaters.get(theaterItem).bookTheater(movieData.getMovieByName(movieName));
        return cinemaSaveData.add(theaters.get(theaterItem),"theater_"+theaterItem);

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

        bookedShows.remove(theaterItem);

        if(!theaters.get(theaterItem).unBookTheater())
            return false;
        theaters.get(theaterItem).unBookTheater();

        return cinemaSaveData.add(new Theater(theaterId,showTime),"theater_"+theaterItem);


    }

    public Theater getBookedTheater(int theaterId, String showTime){
        if (!theaters.get(theaterItemInList(theaterId, showTime)).isTheaterBooked()){
            System.out.println("Theater is not Booked");
            return null;
        }
        return theaters.get(theaterItemInList(theaterId, showTime));
    }

    private int theaterItemInList(int theaterId, String showTime){
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
        return (theaterId+temp)-1;
    }


    public List<Theater> availableShowTime() {
        List<Theater> myList = new ArrayList<>(9);
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
            System.out.println(movie.getMovieName() + ".");
        }
        System.out.println();
    }

    public static void availableMovieIsInShow(){
        System.out.println("Available Movie Shows: ");
        bookedShows.forEach((k,v) -> System.out.println(theaters.get(k)));
//            for (var show:bookedShows.keySet()){
//                var theater = theaters.get(show);
//                System.out.println(theater);
//            }
//        }


//        movieData.getMovies()
//                 .stream()
//                 .filter(m -> bookedShows.containsValue(m))
//                 .filter(m -> m.isShowing())
//                 .peek(m -> System.out.println(m.getMovieName() + " is Showing at: "))
//                 .map(m->m.getMovieShowTimes())
//                 .forEach(m -> m.forEach((k,v) -> System.out.println("Theater: " + k + " in: " + v.toString())));


//        for(var movie: movieData.getMovies()){
//            if(bookedShows.containsValue(movie)) {
//                //Error showing movie
//                if (movie.isShowing()) {
//                    System.out.println(movie.getMovieName() + " is Showing at: ");
//                }
//                movieData.getMovieByName(movie.getMovieName()).getMovieShowTimes().forEach((k, v) -> {
//                    if (movie.isShowing()) {
//                        System.out.println("Theater: " + k + " in: " + v.toString());
//                    }
//                });
//            }
//        }

    }

    public static String showIndexToShowTime(int index){
        if (index==1)
            return "2:00";
        else if (index==2)
            return "5:00";
        else if (index==3)
            return "8:00";
        else{
            System.out.println("Show index not available AdminView#106");
            return null;
        }
    }

    public boolean bookSeat(int theaterId,String showTime,int seatId){
        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cienma#bookSeat");
            return false;
        }
        if(!theaters.get(theaterItem).bookSeat(seatId))
            return false;
        return cinemaSaveData.add(theaters.get(theaterItem),"theater_"+theaterItem);

    }

    public boolean unBookSeat(int theaterId,String showTime,int seatId){
        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cienma#bookSeat");
            return false;
        }
        if (!theaters.get(theaterItem).unBookSeat(seatId)){
            System.out.println("Unbooking seat failed #Cinema*unBookSeat");
            return false;
        }
        return cinemaSaveData.add(theaters.get(theaterItem),"theater_"+theaterItem);

    }


}
