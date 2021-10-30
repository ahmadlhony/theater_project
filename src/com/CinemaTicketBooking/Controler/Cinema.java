package com.CinemaTicketBooking.Controler;
import com.CinemaTicketBooking.Model.Data.MovieData;
import com.CinemaTicketBooking.Model.Data.TheaterData;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Seat;
import com.CinemaTicketBooking.Model.Theater;

import java.util.*;
import java.util.stream.Collectors;

public class Cinema {



    public boolean bookShow(int theaterId, String showTime,String movieName){
        TheaterData theaterData = new TheaterData();
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            System.out.println("Showtime is not available. #Cinema*bookShow");
            return false;
        }
        if (!MovieController.isMovieExist(movieName)){
            System.out.println("movie name in not exist. #Cinema*bookShow");
            return false;
        }

        int theaterItem = theaterItemInList(theaterId,showTime);
        Movie movie  = MovieController.getMovieByName(movieName).get();
        return theaterData.bookShow(theaterItem,movie);
    }

    public boolean UnBookShow(int theaterId, String showTime){
        TheaterData theaterData = new TheaterData();
        var bookedShows = theaterData.getBookedShows();
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            System.out.println("Showtime is not available.");
            return false;
        }
        int theaterItem = theaterItemInList(theaterId,showTime);
        return theaterData.UnBookShow(theaterItem);
    }

    public Theater getBookedTheater(int theaterId,String showTime){
        TheaterData theaterData = new TheaterData();
        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!theaterData.getTheaters().get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked");
            return null;
        }
        return theaterData.getTheaters().get(theaterItem);
    }

    public boolean showAvailableSeat(int theaterId,String showTime) {

        System.out.println("\n*available Seats are for theater: "+theaterId+", at: "+showTime  +"*");

        List<Seat> seats;
        try {
            seats=getBookedTheater(theaterId,showTime).availableSeats();
        }catch (NullPointerException e){
            System.out.println("null pointer exception");
            return false;
        }

        seats.forEach(seat -> {
            System.out.print(seat);
            if (seat.getSeatId()==14 || seat.getSeatId()==28 || seat.getSeatId()==42)
                System.out.println();
        });
        return true;
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
        TheaterData theaterData = new TheaterData();
        return theaterData.availableShowTime();
    }

    public void availableShows(){
        availableShowTime()
                .forEach(theater ->  System.out.println("Theater: " + theater.getTheaterId()+"  is Available at: "+theater.getShowTime()));
    }

    public void availableMovieIsInShow(){
        TheaterData theaterData = new TheaterData();
        var bookedShows = theaterData.getBookedShows();
        System.out.println("Available Movie Shows: ");
        bookedShows.forEach((k,v) -> System.out.println(theaterData.getTheaters().get(k)));
    }

    public String showIndexToShowTime(int index){
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
        TheaterData theaterData = new TheaterData();
        return theaterData.bookSeat(theaterItemInList(theaterId,showTime),seatId);
    }

    public boolean unBookSeat(int theaterId,String showTime,int seatId){
        TheaterData theaterData = new TheaterData();
        return theaterData.unBookSeat(theaterItemInList(theaterId,showTime),seatId);
    }

}
