package com.CinemaTicketBooking.ControlerAndData;

import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatTicket {

    private static AtomicInteger Ticket_ID_GENERATOR;

    private static List<Ticket> ticketList = new ArrayList<>();
    private static Map<String, List<Ticket>> userTickets  = new HashMap<>();

    private static SaveData<Ticket> ticketListSaveData = new SaveData<>("files/tickets.txt");
    private static SaveData<Ticket> userTicketsSaveData = new SaveData<>("files/userTickets.txt");

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

    public static boolean addTicket(int theaterId,String showTime, int seatId,char row,int column, Movie movie){
        Cinema cinema = new Cinema();
        if (!userTickets.containsKey(UserData.getAuthUser().getUserName())){
            userTickets.put(UserData.getAuthUser().getUserName(),new ArrayList<>());
        }

        cinema.bookSeat(theaterId, showTime, seatId);
        Ticket ticket = new Ticket(Ticket_ID_GENERATOR.getAndIncrement(),theaterId,showTime,seatId,row,column,movie);
        userTickets.get(UserData.getAuthUser().getUserName()).add(ticket);
        ticketList.add(ticket);


        return ticketListSaveData.saveListToFile(ticketList)
                && userTicketsSaveData.saveMapToFile(userTickets);

    }

    public static boolean removeAllTicketForUser(String userName){
        UserData users = new UserData();
        Cinema cinema =new Cinema();
        if (!users.isUserExist(userName)){
            System.out.println("User not exist. #SeatTicket*removeAllTicket");
            return false;
        }

        var tickets = userTickets.get(userName);

        if(tickets.isEmpty()){
            System.out.println(userName+", don't have reservation. #SeatTicket*removeAllTicketForUser");
        }
        userTickets.get(userName).forEach(
                m -> cinema.unBookSeat(m.getTheaterId(), m.getShowTime(), m.getSeatId())
        );
        userTickets.remove(userName);
        return userTicketsSaveData.saveMapToFile(userTickets);
    }

    public static List<Ticket> getUserTickets(String userName){
        List<Ticket> bucket = new ArrayList<>();

        if (!userTickets.containsKey(userName)){
            return bucket;
        }
        return userTickets.get(userName);
    }








}
