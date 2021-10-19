package com.CinemaTicketBooking.datas;

import com.CinemaTicketBooking.model.Movie;
import com.CinemaTicketBooking.model.Ticket;
import com.CinemaTicketBooking.model.User;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatTicket {

    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static AtomicInteger ID_GENERATOR1 = new AtomicInteger(0);
    private static List<Ticket> ticketLists = new ArrayList<>();



    //add how many ticket a user(use userName) wants, then when user payed or after the show remove the ticket
    private static Map<String, List<Ticket>> userTickets  = new HashMap<>();

    public static boolean addTicket(int theaterId,String showTime, int seatId,char row,int column, Movie movie){

        if (!userTickets.containsKey(UserData.getAuthUser().getUserName())){
            userTickets.put(UserData.getAuthUser().getUserName(),new ArrayList<>());
        }
        Cinema.bookSeat(theaterId, showTime, seatId);
        Ticket ticket = new Ticket(ID_GENERATOR1.getAndIncrement(),theaterId,showTime,seatId,row,column,movie);
        userTickets.get(UserData.getAuthUser().getUserName()).add(ticket);
        ticketLists.add(ticket);
        try{
            FileOutputStream f = new FileOutputStream("files\\tickets\\ticket_"+ticket.getTicketNo()+".txt");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(ticket);
            o.close();
            f.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found Exception. #MovieData*addMovie");
            return false;
        } catch (IOException e){
            System.out.println("Error initializing stream. #MovieData*addMovie");
            return false;
        }
        return true;

    }

    public static boolean removeTicket(String userName, int ticketId){
        UserData users = new UserData();
        if (!users.isUserExist(userName)){
            System.out.println("user not exist from SeatTicket class");
            return false;
        }
        int theaterId = ticketLists.get(ticketId).getTheaterId();
        int seatId = ticketLists.get(ticketId).getSeatId();
        String showTime = ticketLists.get(ticketId).getShowTime();
        Cinema.unBookSeat(theaterId,showTime,seatId);
        userTickets.get(userName).remove(ticketLists.get(ticketId));
        return true;
    }

    public static boolean removeAllTicketForUser(String userName){
        UserData users = new UserData();
        if (!users.isUserExist(userName)){
            System.out.println("user not exist from SeatTicket class");
            return false;
        }
        if (userTickets.get(userName)==null){
            System.out.println("null #SeatTicket*removeAllTicketForUser");
            throw new IllegalStateException();
        }
        for (var ticket:userTickets.get(userName)){
            int theaterId = ticketLists.get(ticket.getTicketNo()).getTheaterId();
            int seatId = ticketLists.get(ticket.getTicketNo()).getSeatId();
            String showTime = ticketLists.get(ticket.getTicketNo()).getShowTime();
            Cinema.unBookSeat(theaterId,showTime,seatId);
        }
        userTickets.remove(userName);
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
        if (userTickets.get(userName)==null){
            return bucket;
        }
        return userTickets.get(userName);
    }




}
