package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.TicketData;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SeatTicket {
   private static TicketData ticketData = new TicketData();

    public boolean addTicket(int theaterId,String showTime, int seatId,char row,int column, Movie movie){
        Cinema cinema = new Cinema();
        if(!cinema.bookSeat(theaterId, showTime, seatId)){
            System.out.println("Error SeatTicket*addTicket");
            return false;
        }
        return ticketData.addTicket(theaterId, showTime, seatId, row, column, movie);
    }

    public boolean removeAllTicketForUser(String userName){
        UserController users = new UserController();
        Cinema cinema =new Cinema();
        if (!users.isUserExist(userName)){
            System.out.println("User not exist. #SeatTicket*removeAllTicket");
            return false;
        }

        var tickets = getUserTickets(userName);
        if (tickets==null){
            System.out.println("NullPointerException #SeatTicket*RemoveAllTicketForUser");
            return false;
        }

        if(tickets.isEmpty()){
            System.out.println(userName+", don't have reservation. #SeatTicket*removeAllTicketForUser");
            return false;
        }
        ticketData.getUserTickets().get(userName).forEach(
                m -> cinema.unBookSeat(m.getTheaterId(), m.getShowTime(), m.getSeatId())
        );
        return ticketData.removeAllTicketForUser(userName);
    }

    public static List<Ticket> getUserTickets(String userName){
        List<Ticket> bucket = new ArrayList<>();
        if(ticketData.getUserTickets()==null){
//            System.out.println("NullPointerException #SeatTicket*RemoveAllTicketForUser");
            return null;
        }

        if (!ticketData.getUserTickets().containsKey(userName)){
            return bucket;
        }
        return ticketData.getUserTickets().get(userName);
    }



}
