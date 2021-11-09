package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.SaveData;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Theater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheaterData {
    private static SaveData<Theater> cinemaSaveData = new SaveData<>("files/theaters.txt");
    private static List<Theater> theaters = new ArrayList<>();
    private static Map<Integer, String> bookedShows = new HashMap<>();

    public void fetchAndSetTheaters(){
        theaters = cinemaSaveData.openList();
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
        return cinemaSaveData.saveListToFile(theaters);
    }

    public boolean UnBookShow(int theaterItem){
        if (!bookedShows.containsKey(theaterItem)){
            System.out.println("This show is not booked. #Cinema*UnBookShow");
            return false;
        }
        if(!theaters.get(theaterItem).unBookTheater())
            return false;
        bookedShows.remove(theaterItem);

        return cinemaSaveData.saveListToFile(theaters);
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

    public List<Theater> getTheaters() {
        fetchAndSetTheaters();
        return theaters;
    }

    public Map<Integer, String> getBookedShows() {
        fetchAndSetTheaters();
        return bookedShows;
    }

    public boolean bookSeat(int theaterItem, int seatId){
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cinema#bookSeat");
            return false;
        }
        if(!theaters.get(theaterItem).bookSeat(seatId))
            return false;

        return cinemaSaveData.saveListToFile(theaters);
    }

    public boolean unBookSeat(int theaterItem,int seatId){
        if (!theaters.get(theaterItem).isTheaterBooked()){
            System.out.println("Theater is not Booked from Cinema#unBookSeat");
            return false;
        }

        if (!theaters.get(theaterItem).unBookSeat(seatId)){
            return false;
        }

        return cinemaSaveData.saveListToFile(theaters);
    }
}
