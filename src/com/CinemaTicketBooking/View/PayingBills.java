package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.Controler.BillController;
import com.CinemaTicketBooking.Controler.SeatTicket;
import com.CinemaTicketBooking.Controler.UserController;
import com.CinemaTicketBooking.Model.Data.BillData;
import com.CinemaTicketBooking.Model.Ticket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class PayingBills {
    private BillController billController = new BillController();


    public boolean start(String userName){
        List<Ticket> tickets;
        int total=0;


        tickets= billController.getUserTicketForBilling(userName);
        if (tickets==null){
            System.out.println("Null pointer #PayingBills*start");
            return false;
        }

        System.out.println("        *Cinema Ticket Booking System*      ");
        System.out.println("Date: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "         "+
                "Admin: "+ UserController.getAuthUser());
        System.out.println("---------------------");

        for (Ticket ticket : tickets) {
            System.out.println("SeatId: " + ticket.getSeatId() + "    Seat Position: " + ticket.getSeatRow() + ticket.getSeatColumn() +
                    "       Movie : " + ticket.getMovie().getMovieName() +
                    "\nTheater: " + ticket.getTheaterId() + "       Show Time: " + ticket.getShowTime() + "    Price: 5$");
            System.out.println();
            total += 5;
        }

        System.out.println("---------------------");
        System.out.println();
        System.out.println("Your total is: "+ total+"$");
        System.out.println("        *Have a great show*");
        System.out.println();

        if(!billController.removeTickets(userName))
            return false;
        return billController.addBill(total,tickets);

    }

}
