package com.CinemaTicketBooking.ControlerAndData;

import com.CinemaTicketBooking.Model.Bill;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Ticket;
import com.CinemaTicketBooking.Model.UserTicket;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatTicket {

    private static AtomicInteger Ticket_ID_GENERATOR= new AtomicInteger(0);
    private static List<Ticket> ticketList = new ArrayList<>();
    private static  List<UserTicket> userTickets = new ArrayList<>();

    private static SaveData<Ticket> ticketListSaveData = new SaveData<>("files/tickets/");
    private static SaveData<UserTicket> userTicketsSaveData = new SaveData<>("files/userTickets/");


    public void fetchAndSetTicketList(){
        ticketList = ticketListSaveData.open();
        fetchTicketId();
    }

    public void fetchAndSetUserTicket(){
        userTickets = userTicketsSaveData.open();
    }

    private void fetchTicketId(){
//        Ticket_ID_GENERATOR= new AtomicInteger(ticketList.get(ticketList.size()-1).getTicketNo()+1);
        Ticket_ID_GENERATOR= new AtomicInteger(ticketList.size());

    }

    public static boolean addTicket(int theaterId,String showTime, int seatId,char row,int column, Movie movie){
        Cinema cinema = new Cinema();

        if (!containsUser(UserData.getAuthUser().getUserName())){
            userTickets.add(new UserTicket(UserData.getAuthUser().getUserName()));
        }
        cinema.bookSeat(theaterId, showTime, seatId);
        Ticket ticket = new Ticket(Ticket_ID_GENERATOR.getAndIncrement(),theaterId,showTime,seatId,row,column,movie);
        userTickets.get(ticket.getTicketNo()).addTicket(ticket);
        ticketList.add(ticket);

        return ticketListSaveData.add(ticket,"ticket_"+ticket.getTicketNo())
                && userTicketsSaveData.add(userTickets.get(ticket.getTicketNo()),"userTicket_"+ticket.getTicketNo());

    }

    public static boolean removeAllTicketForUser(String userName){
        UserData users = new UserData();
        Cinema cinema =new Cinema();
        if (!users.isUserExist(userName)){
            System.out.println("user not exist from SeatTicket class");
            return false;
        }
        if (!containsUser(userName)){
            System.out.println("User not Exist , null #SeatTicket*removeAllTicketForUser");
            throw new IllegalStateException();
        }

        List<Ticket> userTicket = getUserTicketsByUsername(userName).get().getTickets();
        userTicket.stream()
                .forEach(m->{
                    cinema.unBookSeat(m.getTheaterId(), m.getShowTime(), m.getSeatId());
                });

        userTickets.remove(userTicket);
        //implement remove file for this method

        return true;
    }

    //for billing
    public static List<Ticket> getUserTickets(String userName){
        UserData users = new UserData();
        List<Ticket> bucket = new ArrayList<>();
        if (!users.isUserExist(userName)){
            System.out.println("user not exist from SeatTicket class");
            return bucket;
        }

        return getUserTicketsByUsername(userName).get().getTickets();
    }

    private static boolean containsUser(String username){
        return userTickets.stream()
                .anyMatch(m ->m.getUserName().equals(username));
    }

    private static Optional<UserTicket> getUserTicketsByUsername(String userName){
        return userTickets.stream()
                .filter(m->m.getUserName().equals(userName))
                .findFirst();
    }







}
