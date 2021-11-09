package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.Controler.*;
import com.CinemaTicketBooking.Model.Packet;

import java.util.Scanner;

public class BookingSeats {
    private static SeatTicket seatTicket = new SeatTicket();
    public static boolean startBookingSeat(){

        while (true) {
            Cinema cinema = new Cinema();
            Scanner console = new Scanner(System.in);
            System.out.println("*Booking Seat*");
            if(!cinema.availableMovieIsInShow()){
                return false;
            }
            System.out.println("\n Enter theater you want: 0 to Quit");
            int selectedTheaterId = console.nextInt();
            if (selectedTheaterId==0){
                break;
            }
            System.out.println("Which Time ? " +
                    "1.2:00 \n2.5:00 \n3.8:00 \n0.Quit");
            int showIndex = console.nextInt();
            if (selectedTheaterId==0 || showIndex ==0){
                break;
            }
            String selectedShowTime = cinema.showIndexToShowTime(showIndex);
            if(!cinema.showAvailableSeat(selectedTheaterId, selectedShowTime)){
                System.out.println("Please enter a valid theater and showtime #BookingSeat*startBookingSeat");
                continue;
            }
            System.out.println("\nEnter a SeatId: ");
            int selectedSeatId = console.nextInt();


            if(seatTicket.addTicket(selectedTheaterId,selectedShowTime,selectedSeatId)){
                System.out.println("Do you want to book more Seat? \n1.Yes \n0.Exit");
                int bookMoreSeatAns = console.nextInt();
                if (bookMoreSeatAns==1){
                    continue;
                }else if(bookMoreSeatAns==0){
                    return true;
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }
        return false;

    }

    public static boolean cancelReservation(){
        UserController userController = new UserController();
        Scanner console = new Scanner(System.in);
        System.out.println("*Cancel Reservation*");
            System.out.println("Please Enter your username: ");
            String userName = console.next();
            if (userController.isUserExist(userName)) {
                Packet request = new Packet(7);
                request.setUserName(UserController.getAuthUser());
                Packet response = ClientServerController.get(request);
                if (response.getMessage() !=1) {
                    System.out.println(response.getMessageString());
                    return false;
                }
                return true;
            } else {
                System.out.println("Username is not exist.");
                return false;
            }
    }





}
