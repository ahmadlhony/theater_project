package com.CinemaTicketBooking.model;

import java.util.Map;

public class Seat {
    private int seatId;
    private boolean isBooked = false;
    private int column;
    private char row;



    public Seat(int seatId) {
        this.seatId = seatId;
        this.row = rowCalculate(seatId);
        this.column=columnCalculate(seatId);
    }


//    int seatNum = seatId;


    private int columnCalculate(int seatId){
        return ((seatId-1)%14)+1;
    }

    private char rowCalculate(int seatId){
        if (seatId/14==0){
            return 'A';
        }else if(seatId/14==1){
            return 'B';
        }else if(seatId/14==2){
            return 'C';
        }else if(seatId/14==3){
            return 'D';
        }
        return ' ';
    }

    public int getSeatId() {
        return seatId;
    }

    public int getColumn() {
        return column;
    }

    public char getRow() {
        return row;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        if (isBooked()){
            System.out.println("Seat already booked");
            throw new IllegalStateException();
        }
        isBooked = true;
    }

    public void unBookSeat() {
        if (isBooked == false){
            System.out.println("Seat is not booked");
            throw new IllegalStateException();
        }
        isBooked = true;
    }

    @Override
    public String toString() {
        return '{' + "Your seatId is: "+seatId+", And your seat number is: "+row+column + '}';
    }
}


