package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.BillData;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.List;

public class BillController {
    private static BillData billData = new BillData();


    public boolean addBill(int total,List<Ticket> tickets){
        return billData.addBill(total, tickets);

    }

    public List<Ticket> getUserTicketForBilling(String userName){

        UserController userController = new UserController();
        if (!userController.isUserExist(userName)){
            System.out.println("Sorry this User does not exist.");
            return null;
        }
        var tickets = SeatTicket.getUserTickets(userName);
        if (tickets.isEmpty()){
            System.out.println("You dont have reservation");
            return null;
        }

        return tickets;
    }

    public boolean removeTickets(String userName){
        SeatTicket seatTicket = new SeatTicket();
        return seatTicket.removeAllTicketForUser(userName);
    }


}
