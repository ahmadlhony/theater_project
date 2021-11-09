package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Controler.UserController;
import com.CinemaTicketBooking.Model.Packet;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PayingBills {

    public boolean start(String userName){
        int total=0;


        Packet request = new Packet(7);
        request.setUserName(UserController.getAuthUser());
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        if (response.getItem().isEmpty()){
            System.out.println("Bills is Empty ");
            return false;
        }

        System.out.println("        *Cinema Ticket Booking System*      ");
        System.out.println("Date: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "         "+
                "Admin: "+ UserController.getAuthUser());
        System.out.println("---------------------");

        //get ticket String from server and multiply total with list.size
        response.getItem().forEach(System.out::println);
        System.out.println();
        total = response.getItem().size()*5;

        System.out.println("---------------------");
        System.out.println();
        System.out.println("Your total is: "+ total+"$");
        System.out.println("        *Have a great show*");
        System.out.println();


        //add username to post it to  server instead of removeTickets
        return true;

    }

}
