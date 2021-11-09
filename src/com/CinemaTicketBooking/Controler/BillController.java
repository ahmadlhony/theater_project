package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.BillData;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.List;

public class BillController {
    private static BillData billData = new BillData();


    public static Packet addBill(int total, List<Ticket> tickets){
        Packet packet = new Packet(0);
        if(!billData.addBill(total, tickets)){
            String message = "addBill failed #Server*BillController*addBill";
            packet.setMessageString(message);
            return packet;
        }
        packet.setMessage(1);
        return packet;
    }
}
