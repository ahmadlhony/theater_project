package com.CinemaTicketBooking.model;

import java.util.List;

public class Bill {
    int billId;
    int total;
    List<Ticket> tickets;

    public Bill(int billId, int total, List<Ticket> tickets) {
        this.billId = billId;
        this.total = total;
        this.tickets = tickets;
    }
}
