package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Controler.FetchAndSetData;

import java.util.Scanner;

public class CustomerView {
    public static void start(){
        Scanner console = new Scanner(System.in);
        System.out.println("*Customer Interface*");
        while (true){
            System.out.println("Menu: " +
                    "\n1.book a seat" +
                    "\n2.cancel a reservation" +
                    "\n0.Quit");
            int select = console.nextInt();
            if (select==0){

                ClientServerController.stopConnection();
                break;
            }else if (select==1){
                FetchAndSetData.fetchAndSetAllData();
                BookingSeats.startBookingSeat();
            }else if(select==2){
                FetchAndSetData.fetchAndSetAllData();
                BookingSeats.cancelReservation();
            }

        }
    }
}
