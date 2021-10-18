package com.CinemaTicketBooking.classes;

import com.CinemaTicketBooking.datas.Cinema;
import com.CinemaTicketBooking.datas.MovieData;
import com.CinemaTicketBooking.datas.SeatTicket;
import com.CinemaTicketBooking.datas.UserData;
import com.CinemaTicketBooking.model.Seat;

import java.util.List;
import java.util.Scanner;

public class BookingSeats {
    public static boolean startBookingSeat(){
        while (true) {
            UserData userData = new UserData();
            MovieData movieData = new MovieData();
            Cinema cinema = new Cinema();
            Scanner console = new Scanner(System.in);

            System.out.println("*Booking Seat*");
            Cinema.availableMoviesShows();
            System.out.println("\n Enter theater you want: ");
            int selectedTheaterId = console.nextInt();
            System.out.println("Which Time ? " +
                    "1.2:00 \n2.5:00 \n3.8:00");
            int showIndex = console.nextInt();
            String selectedShowTime = Cinema.showIndexToShowTime(showIndex);
            showAvailableSeat(selectedTheaterId, selectedShowTime);
            System.out.println("\nEnter a SeatId: ");
            int selectedSeatId = console.nextInt();


            return SeatTicket.addTicket(selectedTheaterId,selectedShowTime,selectedSeatId,rowCalculate(selectedSeatId),columnCalculate(selectedSeatId),Cinema.getTheater(selectedTheaterId,selectedShowTime).getMovie());
        }
    }

    public static boolean cancelReservation(){

        UserData userData = new UserData();
        MovieData movieData = new MovieData();
        Cinema cinema = new Cinema();

        Scanner console = new Scanner(System.in);
        System.out.println("*Cancel Reservation*");
        while (true) {
            System.out.println("Please Enter your username: ");
            String userName = console.next();
            if (userData.isUserExist(userName)) {
                System.out.println("Do you want to cancel all your reservation or just one ticket? " +
                        "\n1.Cancel all Reservation" +
                        "\n2.just one ticket");
                int cancelReservation = console.nextInt();
                if (cancelReservation==1){
                    if(!SeatTicket.removeAllTicketForUser(userName)){
                        System.out.println("Reservation canceling failed. #BookingSeat*cancelReservation");
                        return false;
                    }
                    return true;

                }else if (cancelReservation==2){
                    while (true) {
                        System.out.println("Please enter your ticket ID: ");
                        int ticketId = console.nextInt();
                        if (!SeatTicket.removeTicket(userName, ticketId)) {
                            System.out.println("Ticket removing failed. #BookingSeat*cancelReservation\n");
                        }
                        System.out.println("Do you want to cancel more reservation? " +
                                "\n1.Yes" +
                                "\n2.No");
                        int cancelMore = console.nextInt();
                        if (cancelMore==2){
                            return true;
                        }
                    }

                }
            } else {
                System.out.println("Username is not exist.");
            }
        }




    }

    public static void showAvailableSeat(int theaterId,String showTime) {
        System.out.println("\n*available Seats are for theater: "+theaterId+", at: "+showTime  +"*");
        var seats = Cinema.getTheater(theaterId,showTime).availableSeats();

        for(var seat:seats){
            System.out.print(seat);
            if (seat.getSeatId()==14 || seat.getSeatId()==28 || seat.getSeatId()==42)
                System.out.println();
        }

    }
    private static int columnCalculate(int seatId){
        return ((seatId-1)%14)+1;
    }
    private static char rowCalculate(int seatId){
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


}
