package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Controler.UserController;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketData {
    private static AtomicInteger Ticket_ID_GENERATOR;

    private static List<Ticket> ticketList;
    private static Map<String, List<Ticket>> userTickets;

    private ClientServerController<Ticket> ticketListClientServerController = new ClientServerController<>();
    private ClientServerController<Ticket> userTicketsClientServerController = new ClientServerController<>();

    public void fetchAndSetTicketList(){
        Packet<Ticket> ticketPacket = new Packet<>(10);
        try{

        ticketList = ticketListClientServerController.get(ticketPacket).getItem();
        } catch (NullPointerException e){
            System.out.println("TicketList is null #TicketData*fetchAndSetTicketList");
        }
        fetchTicketId();
    }

    public void fetchAndSetUserTicket(){
        Packet<Ticket> ticketPacket = new Packet<>(12);
        try{

        userTickets = userTicketsClientServerController.get(ticketPacket).getMap();
        } catch (NullPointerException e){
            System.out.println("UserTickets is null #TicketData*fetchAndSetUserTicket");
        }
    }

    private void fetchTicketId(){
        Ticket_ID_GENERATOR= new AtomicInteger(ticketList.size());
    }

    public boolean addTicket(int theaterId,String showTime, int seatId,char row,int column, Movie movie){
        if (!userTickets.containsKey(UserController.getAuthUser().getUserName())){
            userTickets.put(UserController.getAuthUser().getUserName(),new ArrayList<>());
        }

        Ticket ticket = new Ticket(Ticket_ID_GENERATOR.getAndIncrement(),theaterId,showTime,seatId,row,column,movie);
        userTickets.get(UserController.getAuthUser().getUserName()).add(ticket);
        ticketList.add(ticket);
        Packet<Ticket> userTicketPacket = new Packet<>(11);
        userTicketPacket.setMap(userTickets);
        Packet<Ticket> ticketPacket = new Packet<>(9);
        ticketPacket.setItem(ticketList);


        return ticketListClientServerController.post(ticketPacket)
                && userTicketsClientServerController.post(userTicketPacket);

    }

    public boolean removeAllTicketForUser(String userName){
        userTickets.remove(userName);
        Packet<Ticket> ticketPacket = new Packet<>(11);
        return userTicketsClientServerController.post(ticketPacket);
    }



    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public Map<String, List<Ticket>> getUserTickets() {
        return userTickets;
    }
}
