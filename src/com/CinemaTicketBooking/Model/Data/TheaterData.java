package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Theater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TheaterData {
    private static ClientServerController<Theater> cinemaClientServerController = new ClientServerController<>();
    private static List<Theater> theaters = new ArrayList<>();
    private static Map<Integer, String> bookedShows = new HashMap<>();

    public void fetchAndSetTheaters(){
        Packet<Theater> theaterPacket = new Packet<>(6);
        theaters = cinemaClientServerController.openList(theaterPacket);
        fetchAndSetBookedShow();
    }

    private void fetchAndSetBookedShow(){
        theaters.stream()
                .filter(Theater::isTheaterBooked)
                .forEach(m->
                        bookedShows.put(theaterItemInList(m.getTheaterId(),m.getShowTime()),m.getMovie().getMovieName())
                );
    }

    public boolean bookShow(int theaterItem, Movie movie){
        if (bookedShows.containsKey(theaterItem)){
            System.out.println("This show is booked. #TheaterData*bookShow");
            return false;
        }
        if(!theaters.get(theaterItem).bookTheater(movie)) {
            return false;
        }
        fetchAndSetBookedShow();
        Packet<Theater> theaterPacket = new Packet<>(5);
        theaterPacket.setItem(theaters);
        return cinemaClientServerController.saveListToFile(theaterPacket);
    }

    public boolean UnBookShow(int theaterItem){
        if (!bookedShows.containsKey(theaterItem)){
            System.out.println("This show is not booked. #Cinema*UnBookShow");
            return false;
        }

        if(!theaters.get(theaterItem).unBookTheater())
            return false;
        bookedShows.remove(theaterItem);
        Packet<Theater> theaterPacket = new Packet<>(5);
        theaterPacket.setItem(theaters);
        return cinemaClientServerController.saveListToFile(theaterPacket);
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

    //take this to controller
    public List<Theater> availableShowTime() {
        return theaters.stream()
                .filter(theater ->!bookedShows.containsKey(theaterItemInList(theater.getTheaterId(),theater.getShowTime())))
                .collect(Collectors.toList());
    }

    public List<Theater> getTheaters() {
        return theaters;
    }

    public Map<Integer, String> getBookedShows() {
        return bookedShows;
    }

    public boolean bookSeat(int theaterItem, int seatId){
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cinema#bookSeat");
            return false;
        }
        if(!theaters.get(theaterItem).bookSeat(seatId))
            return false;

        Packet<Theater> theaterPacket = new Packet<>(5);
        theaterPacket.setItem(theaters);
        return cinemaClientServerController.saveListToFile(theaterPacket);
    }

    public boolean unBookSeat(int theaterItem,int seatId){
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cinema#unBookSeat");
            return false;
        }

        if (!theaters.get(theaterItem).unBookSeat(seatId)){
            return false;
        }

        Packet<Theater> theaterPacket = new Packet<>(5);
        theaterPacket.setItem(theaters);
        return cinemaClientServerController.saveListToFile(theaterPacket);
    }
}
