package com.CinemaTicketBooking.classes;

import com.CinemaTicketBooking.ControlerAndData.SaveData;
import com.CinemaTicketBooking.ControlerAndData.SeatTicket;
import com.CinemaTicketBooking.ControlerAndData.UserData;
import com.CinemaTicketBooking.Model.Bill;
import com.CinemaTicketBooking.Model.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class PayingBills {
    private static AtomicInteger BILL_ID_GENERATOR;
    private static List<Bill> billList = new ArrayList<>();
    private SaveData<Bill> billSaveData = new SaveData<>("files/bills/");

    //have you booked seat ? payBills : booking seats
    public boolean start(String userName){
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
        Bill bill = new Bill(BILL_ID_GENERATOR.getAndIncrement(),total,tickets);
        billList.add(bill);
        if(!SeatTicket.removeAllTicketForUser(userName))
            return false;
        return billSaveData.add(bill,"bill_"+bill.getBillId());

    }

    public void fetchId(){
//        BILL_ID_GENERATOR = new AtomicInteger(billList.get(billList.size()-1).getBillId()+1);
        BILL_ID_GENERATOR = new AtomicInteger(billList.size()+1);

    }

    public void fetchAndSetBillList(){
        billList = billSaveData.open();
        fetchId();
    }

    public void addBill(Bill bill){
        String extendedPath = "bill_"+bill.getBillId();
        billSaveData.add(bill,extendedPath);
    }


}
