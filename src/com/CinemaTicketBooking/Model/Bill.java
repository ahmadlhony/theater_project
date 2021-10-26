package com.CinemaTicketBooking.Model;

import java.io.Serializable;
import java.util.List;

public class Bill implements Serializable {
    private int billId;
    private int total;
    private List<Ticket> tickets;

    public Bill(int billId, int total, List<Ticket> tickets) {
        this.billId = billId;
        this.total = total;
        this.tickets = tickets;
    }

    public int getBillId() {
        return billId;
    }
}
