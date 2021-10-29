package com.CinemaTicketBooking.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Theater implements Serializable {
    private int theaterId;
    private String showTime;
    private Movie movie;
    private boolean isBooked= false;




    public Theater(int theaterId,String showTime) {
        this.theaterId = theaterId;
        this.showTime = showTime;

    }


    private final List<Seat> seatsList = Arrays.asList(new Seat(1), new Seat(2), new Seat(3), new Seat(4), new Seat(5),
            new Seat(6), new Seat(7), new Seat(8), new Seat(9), new Seat(10),
            new Seat(11), new Seat(12), new Seat(13), new Seat(14),
            new Seat(15), new Seat(16), new Seat(17), new Seat(18), new Seat(19), new Seat(20), new Seat(21), new Seat(22), new Seat(23), new Seat(24), new Seat(25),
            new Seat(26), new Seat(27), new Seat(28), new Seat(29), new Seat(30),
            new Seat(31), new Seat(32), new Seat(33), new Seat(34), new Seat(35),
            new Seat(36), new Seat(37), new Seat(38), new Seat(39), new Seat(40),
            new Seat(41), new Seat(42), new Seat(43), new Seat(44), new Seat(45),
            new Seat(46), new Seat(47), new Seat(48), new Seat(49), new Seat(50),
            new Seat(51), new Seat(52), new Seat(53), new Seat(54), new Seat(55), new Seat(56));




    public int getTheaterId() {
        return theaterId;
    }

    public boolean bookSeat(int seatId) {
        if (seatId<1 || seatId>56){
            System.out.println("this Seat not available (1-56) #Theater*bookSeat");
            return false;
        }
        return seatsList.get(seatId-1).bookSeat();
    }

    public boolean unBookSeat(int seatId) {
        if (seatId<1 || seatId>56){
            System.out.println("this Seat not available (1-56) #Theater*unBookSeat");
            return false;
        }
        return seatsList.get(seatId-1).unBookSeat();
    }

    public boolean isTheaterBooked(){
        return isBooked;
    }


    public boolean bookTheater(Movie movie){
        if (isBooked){
            System.out.println("sorry the theater is booked for " + this.movie.getMovieName()+", #Theater*bookTheater");
            return false;
        }
        this.movie=movie;
        isBooked=true;
        return true;
    }

    public boolean unBookTheater() {
        if (!isBooked){
            System.out.println("this theater is not booked #Theater*unBookTheater");
            return false;
        }
        this.movie=null;
        isBooked=false;
        return true;
    }

    public List<Seat> availableSeats(){
        if (!isBooked){
            System.out.println("The theater is not booked");
            return null;
        }
        return seatsList.stream().filter(m -> !m.isBooked()).collect(Collectors.toList());
    }

    public String getShowTime() {
        return showTime;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public String toString() {
        return "\nTheater: " + theaterId+(isTheaterBooked() ? (", and is booked for "+movie.getMovieName()+" at "+showTime ) : ", And is not Booked")+ ".";
    }


}
