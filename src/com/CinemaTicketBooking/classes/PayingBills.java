package com.CinemaTicketBooking.classes;

import com.CinemaTicketBooking.datas.SeatTicket;
import com.CinemaTicketBooking.datas.UserData;
import com.CinemaTicketBooking.model.Bill;
import com.CinemaTicketBooking.model.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PayingBills {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static List<Bill> billList = new ArrayList<>();

    //have you booked seat ? payBills : booking seats
    public static boolean start(String userName){
        int total=0;
        UserData userData = new UserData();
        if (!userData.isUserExist(userName)){
            System.out.println("Sorry this User does not exist.");
            return false;
        }
        var tickets = SeatTicket.getUserTickets(userName);
        if (tickets.isEmpty()){
            System.out.println("You dont have reservation");
        }
        System.out.println("        *Cinema Ticket Booking System*      ");
        System.out.println("Date: "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()) + "         "+
                "Admin: "+ UserData.getAuthUser());
        System.out.println("---------------------");
        for (Ticket ticket:tickets){
            System.out.println("SeatId: "+ticket.getSeatId() + "    Seat Position: " +ticket.getSeatRow()+ticket.getSeatColumn() +
                    "       Movie : " + ticket.getMovie().getMovieName()+
                    "\nTheater: " + ticket.getTheaterId() + "       Show Time: " + ticket.getShowTime()+"    Price: 5$");
            System.out.println();
            total+=5;
        }
        System.out.println("---------------------");
        System.out.println();
        System.out.println("Your total is: "+ total);
        System.out.println("        *Have a great show*");
        System.out.println();
        billList.add(new Bill(ID_GENERATOR.getAndIncrement(),total,tickets));
        return SeatTicket.removeAllTicketForUser(userName);






    }
}
