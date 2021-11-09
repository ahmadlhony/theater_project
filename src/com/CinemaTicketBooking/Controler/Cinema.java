package com.CinemaTicketBooking.Controler;
import com.CinemaTicketBooking.Model.Data.MovieData;
import com.CinemaTicketBooking.Model.Data.TheaterData;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Seat;
import com.CinemaTicketBooking.Model.Theater;

import java.util.*;
import java.util.stream.Collectors;

public class Cinema {

    public Packet bookShow(int theaterId, String showTime, String movieName){
        TheaterData theaterData = new TheaterData();
        Packet packet = new Packet(0);
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            String massage = "Showtime is not available. #Server*Cinema*bookShow";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }
        if (!MovieController.isMovieExist(movieName)){
            String massage ="movie name in not exist. #Server*Cinema*bookShow";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }

        int theaterItem = theaterItemInList(theaterId,showTime);
        Movie movie  = MovieController.getMovieByName(movieName).get();
        if(!theaterData.bookShow(theaterItem,movie)){
            String massage ="bookShowFailed. #Server*Cinema*bookShow";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }
        String message = "Show Booked Successfully";
        packet.setMessage(1);
        packet.setMessageString(message);
        return packet;
    }

    public Packet UnBookShow(int theaterId, String showTime){
        TheaterData theaterData = new TheaterData();
        Packet packet = new Packet(0);
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            String massage = "Showtime is not available. #Server*Cinema*bookShow";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }
        int theaterItem = theaterItemInList(theaterId,showTime);
        if(!theaterData.UnBookShow(theaterItem)){
            String massage = "Unbook SHow unsuccessful. #Server*Cinema*bookShow";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }
        String message = "Show UnBooked Successfully";
        packet.setMessage(1);
        packet.setMessageString(message);
        return packet;
    }

    //server
    public Theater getBookedTheater(int theaterId,String showTime){
        TheaterData theaterData = new TheaterData();
        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!theaterData.getTheaters().get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked");
            return null;
        }
        return theaterData.getTheaters().get(theaterItem);
    }

    public Packet showAvailableSeat(int theaterId,String showTime) {
        Packet packet = new Packet(0);

        System.out.println("\n*available Seats are for theater: "+theaterId+", at: "+showTime  +"*");

        List<Seat> seats;
        List<String> items = new ArrayList<>(56);
        try {
            seats=getBookedTheater(theaterId,showTime).availableSeats();
        }catch (NullPointerException e){
            String message = "No Seats available #Server*Cinema*showAvailableSeat";
            System.out.println(message);
            packet.setMessageString(message);
            return packet;
        }

        seats.forEach(seat -> {
            items.add(seat.toString());
        });
        packet.setItem(items);
        packet.setMessage(1);
        return packet;
    }

    //server
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

    //server
    public List<Theater> availableShowTime() {
        TheaterData theaterData = new TheaterData();
        return theaterData.getTheaters().stream()
                .filter(theater ->!theaterData.getBookedShows().containsKey(theaterItemInList(theater.getTheaterId(),theater.getShowTime())))
                .collect(Collectors.toList());
    }

    public Packet availableShows(){
        Packet packet = new Packet(1);
        List<String> items= new ArrayList<>();
        availableShowTime()
                .forEach(theater ->  items.add("Theater: " + theater.getTheaterId()+"  is Available at: "+theater.getShowTime()));
        packet.setItem(items);
        return packet;
    }

    public Packet availableMovieIsInShow(){
        TheaterData theaterData = new TheaterData();
        var bookedShows = theaterData.getBookedShows();
        var packet = new Packet(0);
        if(bookedShows.isEmpty()){
            String message = "No Show Available. #Cinema*availableMovieInShow";
            System.out.println(message);
            packet.setMessageString(message);
            return packet;
        }
        List<String> items = new ArrayList<>(9);
        bookedShows.forEach((k,v) -> items.add(theaterData.getTheaters().get(k).toString()));
        packet.setMessage(1);
        packet.setItem(items);
        return packet;
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

    //server get called by SeatTicket*addTicket()
    public boolean bookSeat(int theaterId,String showTime,int seatId){
        TheaterData theaterData = new TheaterData();
        return theaterData.bookSeat(theaterItemInList(theaterId,showTime),seatId);
    }

    //server get called by SeatTicket*removeAllTicket()
    public boolean unBookSeat(int theaterId,String showTime,int seatId){
        TheaterData theaterData = new TheaterData();
        return theaterData.unBookSeat(theaterItemInList(theaterId,showTime),seatId);
    }

}
