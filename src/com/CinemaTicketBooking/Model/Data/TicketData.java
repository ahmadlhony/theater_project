package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.SaveData;
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

    private SaveData<Ticket> ticketListSaveData = new SaveData<>("files/tickets.txt");
    private SaveData<Ticket> userTicketsSaveData = new SaveData<>("files/userTickets.txt");

    public void fetchAndSetTicketList(){
        ticketList = ticketListSaveData.openList();
        fetchTicketId();
    }

    public void fetchAndSetUserTicket(){
        userTickets = userTicketsSaveData.openMap();
    }

    private void fetchTicketId(){
        Ticket_ID_GENERATOR= new AtomicInteger(ticketList.size());
    }


    public boolean addTicket(int theaterId,String showTime, int seatId,char row,int column, Movie movie,String userName){
        if (!userTickets.containsKey(userName)){
            userTickets.put(userName,new ArrayList<>());
        }
        Ticket ticket = new Ticket(Ticket_ID_GENERATOR.getAndIncrement(),theaterId,showTime,seatId,row,column,movie);
        userTickets.get(userName).add(ticket);
        ticketList.add(ticket);
        return ticketListSaveData.saveListToFile(ticketList)
                && userTicketsSaveData.saveMapToFile(userTickets);

    }

    public boolean removeAllTicketForUser(String userName){
        userTickets.remove(userName);
        return userTicketsSaveData.saveMapToFile(userTickets);
    }

    public List<Ticket> getTicketList() {
        fetchAndSetTicketList();
        return ticketList;
    }

    public Map<String, List<Ticket>> getUserTickets() {
        fetchAndSetUserTicket();
        return userTickets;
    }
}
