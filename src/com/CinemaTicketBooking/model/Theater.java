package com.CinemaTicketBooking.model;

import java.util.Arrays;
import java.util.List;

public class Theater {
    public Theater() {

    }

    private int theaterId;
    private String showTime;
    private boolean isBookedFor2=false;
    private boolean isBookedFor5=false;
    private boolean isBookedFor8=false;



    public Theater(int theaterId, String showTime) {
        this.theaterId = theaterId;
        this.showTime=showTime;
    }

    private List<Theater> theaters = Arrays.asList(
            new Theater(1,"2:00PM"),
            new Theater(2,"2:00PM"),
            new Theater(3,"2:00PM"),
            new Theater(1,"4:00PM"),
            new Theater(2,"4:00PM"),
            new Theater(3,"4:00PM"),
            new Theater(1,"8:00PM"),
            new Theater(2,"8:00PM"),
            new Theater(3,"8:00PM"));


    //admins can book showtime for availabel times

    private final List<Seat> seatsList = Arrays.asList(new Seat(1), new Seat(2), new Seat(3), new Seat(4), new Seat(5),
            new Seat(6), new Seat(7), new Seat(8), new Seat(9), new Seat(10),
            new Seat(11), new Seat(12), new Seat(13), new Seat(14), new Seat(15),
            new Seat(16), new Seat(17), new Seat(18), new Seat(19), new Seat(20),
            new Seat(21), new Seat(22), new Seat(23), new Seat(24), new Seat(25),
            new Seat(26), new Seat(27), new Seat(28), new Seat(29), new Seat(30),
            new Seat(31), new Seat(32), new Seat(33), new Seat(34), new Seat(35),
            new Seat(36), new Seat(37), new Seat(38), new Seat(39), new Seat(40),
            new Seat(41), new Seat(42), new Seat(43), new Seat(44), new Seat(45),
            new Seat(46), new Seat(47), new Seat(48), new Seat(49), new Seat(50),
            new Seat(51), new Seat(52), new Seat(53), new Seat(54), new Seat(55), new Seat(56));


    public Theater getTheaters(int index, int timeIndex) {
        Theater theater = null;
        if (timeIndex == 1) {
            theater= theaters.get(index-1);
        } else if (timeIndex == 2) {
            theater= theaters.get((index+3)-1);
        } else if (timeIndex == 3) {
            theater= theaters.get((index+6)-1);
        }

        return theater;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void bookSeat(int seatId) {
        seatsList.get(seatId).bookSeat();
    }

    public List<Seat> getSeatsList() {
        return seatsList;
    }

    public boolean isBookedFor2() {
        return isBookedFor2;
    }

    public boolean isBookedFor5() {
        return isBookedFor5;
    }

    public boolean isBookedFor8() {
        return isBookedFor8;
    }

    public void bookTheater2() {
        isBookedFor2 = true;
    }

    public void bookTheater5() {
        isBookedFor5 = true;
    }

    public void bookTheater8() {
        isBookedFor8 = true;
    }

    public void unbookedTheater2() {
        isBookedFor2 = false;
    }

    public void unbookedTheater5() {
        isBookedFor5 = false;
    }

    public void unbookedTheater8() {
        isBookedFor8 = false;
    }





    @Override
    public String toString() {
        return "Theater is: " + theaterId;
    }


}
