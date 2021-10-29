package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.ControlerAndData.Cinema;
import com.CinemaTicketBooking.ControlerAndData.MovieData;
import com.CinemaTicketBooking.ControlerAndData.SeatTicket;
import com.CinemaTicketBooking.ControlerAndData.UserData;

import java.util.Scanner;

public class BookingSeats {
    public static boolean startBookingSeat(){
        while (true) {
            UserData userData = new UserData();
            MovieData movieData = new MovieData();
            Cinema cinema = new Cinema();
            Scanner console = new Scanner(System.in);

            System.out.println("*Booking Seat*");
            Cinema.availableMovieIsInShow();
            System.out.println("\n Enter theater you want: ");
            int selectedTheaterId = console.nextInt();
            System.out.println("Which Time ? " +
                    "1.2:00 \n2.5:00 \n3.8:00");
            int showIndex = console.nextInt();
            String selectedShowTime = Cinema.showIndexToShowTime(showIndex);
            showAvailableSeat(selectedTheaterId, selectedShowTime);
            System.out.println("\nEnter a SeatId: ");
            int selectedSeatId = console.nextInt();


            return SeatTicket.addTicket(selectedTheaterId,selectedShowTime,selectedSeatId,rowCalculate(selectedSeatId),columnCalculate(selectedSeatId),cinema.getBookedTheater(selectedTheaterId,selectedShowTime).getMovie());
        }
    }

    public static boolean cancelReservation(){
        UserData userData = new UserData();
        Scanner console = new Scanner(System.in);
        System.out.println("*Cancel Reservation*");
            System.out.println("Please Enter your username: ");
            String userName = console.next();
            if (userData.isUserExist(userName)) {
                if(!SeatTicket.removeAllTicketForUser(userName)){
                    System.out.println("Reservation canceling failed. #BookingSeat*cancelReservation");
                    return false;
                }
                return true;
            } else {
                System.out.println("Username is not exist.");
                return false;
            }
    }

    public static void showAvailableSeat(int theaterId,String showTime) {
        Cinema cinema = new Cinema();
        System.out.println("\n*available Seats are for theater: "+theaterId+", at: "+showTime  +"*");
        var seats = cinema.getBookedTheater(theaterId,showTime).availableSeats();
        seats.forEach(seat -> {
                    System.out.print(seat);
                    if (seat.getSeatId()==14 || seat.getSeatId()==28 || seat.getSeatId()==42)
                        System.out.println();
                });

//

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
