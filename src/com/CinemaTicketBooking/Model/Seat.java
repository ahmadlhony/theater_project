package com.CinemaTicketBooking.Model;

import java.io.Serializable;

public class Seat implements Serializable {
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
        if ((seatId-1)/14==0){
            return 'A';
        }else if((seatId-1)/14==1){
            return 'B';
        }else if((seatId-1)/14==2){
            return 'C';
        }else if((seatId-1)/14==3){
            return 'D';
        }
        return ' ';
    }

    private int getSeatIdByRowAndColumn(char row,int column){
        if (column < 14 || column <1){
            System.out.println("Enter an available column");
            return -2;
        }
        int seatId;
        int temp;
        if (row=='A' || row=='a'){
            temp = 0;

        }else if (row=='B' || row=='b'){
            temp = 14;

        }else if (row=='C' || row=='c'){
            temp = 28;
        }else if (row=='D' || row=='d'){
            temp = 42;
        }else{
            System.out.println("Enter an available row");
            return -1;
        }
        return temp+column;
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

    public boolean bookSeat() {
        if (isBooked()){
            System.out.println("Seat already booked");
            return false;
        }
        System.out.println("Seat Successfully booked");
        isBooked = true;
        return true;
    }

    public boolean unBookSeat() {
        if (isBooked == false){
            System.out.println("Seat is not booked");
            return false;
        }

        isBooked = false;
        return true;
    }

    @Override
    public String toString() {
        return " {" + "seatId: "+seatId+", seat position: "+row+column + "} ";
    }
}


