package com.CinemaTicketBooking.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserTicket implements Serializable {
    private String userName;
    private List<Ticket> tickets = new ArrayList<>();

    public UserTicket(String userName) {
        this.userName = userName;
    }

    public boolean addTicket(Ticket ticket){
        if(tickets.contains(ticket)){
            System.out.println("Ticket already exist #User*addTicket()");
            return false;
        }
        tickets.add(ticket);
        return true;
    }

    public boolean remove(Ticket ticket){
        tickets = tickets.stream()
                .filter(m -> m.getTicketNo()!=ticket.getTicketNo())
                .collect(Collectors.toList());
        return true;
    }

    public void removeAllTicket() {
        tickets.clear();
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public String getUserName() {
        return userName;
    }


}
