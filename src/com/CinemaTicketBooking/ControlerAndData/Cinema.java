package com.CinemaTicketBooking.ControlerAndData;
import com.CinemaTicketBooking.Model.Theater;

import java.util.*;
import java.util.stream.Collectors;

public class Cinema {
    private static SaveData<Theater> cinemaSaveData = new SaveData<>("files/theaters.txt");
    //list of theaters by given time
    private static List<Theater> theaters = new ArrayList<>();
    private static Map<Integer, String> bookedShows = new HashMap<>();
    MovieData movieData = new MovieData();

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

    public boolean bookShow(int theaterId, String showTime,String movieName){
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            System.out.println("Showtime is not available. #Cinema*bookShow");
            return false;
        }

        int theaterItem = theaterItemInList(theaterId,showTime);

        if (bookedShows.containsKey(theaterItem)){
            System.out.println("Sorry This show is booked. #Cinema*bookShow");
            return false;
        }

        if (!movieData.isMovieExist(movieName)){
            System.out.println("movie name in not exist. #Cinema*bookShow");
            return false;
        }

        bookedShows.put(theaterItemInList(theaterId,showTime),movieName);

        if(!theaters.get(theaterItem).bookTheater(movieData.getMovieByName(movieName))) {
            return false;
        }

        MovieData movieData = new MovieData();
        theaters.get(theaterItem).bookTheater(movieData.getMovieByName(movieName));

        return cinemaSaveData.saveListToFile(theaters);
    }

    public boolean UnBookShow(int theaterId, String showTime){
        if (!showTime.equals("2:00") && !showTime.equals("5:00") && !showTime.equals("8:00")){
            System.out.println("Showtime is not available.");
            return false;
        }


        int theaterItem = theaterItemInList(theaterId,showTime);
        if (!bookedShows.containsKey(theaterItem)){
            System.out.println("This show is not booked. #Cinema*UnBookShow");
            return false;
        }

        if(!theaters.get(theaterItem).unBookTheater())
            return false;

        bookedShows.remove(theaterItem);
        theaters.get(theaterItem).unBookTheater();
        return cinemaSaveData.saveListToFile(theaters);
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
        return theaters.stream()
                .filter(theater ->!bookedShows.containsKey(theaterItemInList(theater.getTheaterId(),theater.getShowTime())))
                .collect(Collectors.toList());
    }

    public void availableShows(){
        availableShowTime()
                .forEach(theater ->  System.out.println("Theater: " + theater.getTheaterId()+"  is Available at: "+theater.getShowTime()));
    }

    public static void availableMovieIsInShow(){
        System.out.println("Available Movie Shows: ");
        bookedShows.forEach((k,v) -> System.out.println(theaters.get(k)));
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
            System.out.println("Theater is not Booked from Cinema#bookSeat");
            return false;
        }

        if(!theaters.get(theaterItem).bookSeat(seatId))
            return false;

        return cinemaSaveData.saveListToFile(theaters);
    }

    public boolean unBookSeat(int theaterId,String showTime,int seatId){
        int theaterItem = theaterItemInList(theaterId,showTime);
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
