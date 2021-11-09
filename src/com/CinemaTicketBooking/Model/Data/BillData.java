package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.SaveData;
import com.CinemaTicketBooking.Model.Bill;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BillData {
    private static AtomicInteger BILL_ID_GENERATOR;
    private static List<Bill> billList = new ArrayList<>();
    private static SaveData<Bill> billSaveData = new SaveData<>("files/bills.txt");

    public boolean addBill(int total,List<Ticket> tickets){
        billList.add(new Bill(BILL_ID_GENERATOR.getAndIncrement(),total,tickets));
        return billSaveData.saveListToFile(billList);
    }

    public void fetchId(){
        BILL_ID_GENERATOR = new AtomicInteger(billList.size()+1);

    }


    public void fetchAndSetBillList(){
        billList = billSaveData.openList();
        fetchId();
    }
}
