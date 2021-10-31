package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Model.Bill;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BillData {
    private static AtomicInteger BILL_ID_GENERATOR;
    private static List<Bill> billList = new ArrayList<>();
    private static ClientServerController<Bill> billClientServerController = new ClientServerController<>();

    public boolean addBill(int total,List<Ticket> tickets){
        billList.add(new Bill(BILL_ID_GENERATOR.getAndIncrement(),total,tickets));
        Packet<Bill> billPacket = new Packet<>(1);
        billPacket.setItem(billList);
        return billClientServerController.saveListToFile(billPacket);
    }

    public void fetchId(){
        BILL_ID_GENERATOR = new AtomicInteger(billList.size()+1);

    }


    public void fetchAndSetBillList(){
        Packet<Bill> billPacket = new Packet<>(2);
        billList = billClientServerController.openList(billPacket);
        fetchId();
    }
}
