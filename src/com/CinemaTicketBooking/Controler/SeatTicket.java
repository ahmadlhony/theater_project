package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.TicketData;
import com.CinemaTicketBooking.Model.Movie;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class SeatTicket {
   private static TicketData ticketData = new TicketData();

    //dont pass row and column make server calculate it
    //pass movieName and call getMovieByName in server
    //getUserName from client
    public Packet addTicket(int theaterId,String showTime, int seatId,String userName){
        Cinema cinema = new Cinema();
        Packet packet = new Packet(0);
        String message = "";
        if(!cinema.bookSeat(theaterId, showTime, seatId)){
            message = "Error #Server*SeatTicket*addTicket";
            System.out.println(message);
            packet.setMessageString(message);
            return packet;
        }

        Movie movie = cinema.getBookedTheater(theaterId,showTime).getMovie();
        if (!MovieController.isMovieExist(movie.getMovieName())){
            message = "Movie Not Available #Server*SeatTicket*addTicket";
            packet.setMessageString(message);
            return packet;
        }
        if(ticketData.addTicket(theaterId, showTime, seatId, rowCalculate(seatId)
                , columnCalculate(seatId),movie ,userName)){
            message = "Ticket wont be added #Server*SeatTicket*addTicket";
            packet.setMessageString(message);
            return packet;
        }
        packet.setMessage(1);
        return packet;
    }

    public Packet removeAllTicketForUser(String userName){
        UserController users = new UserController();
        Cinema cinema =new Cinema();
        Packet packet = new Packet(0);
        String message = "";
        if (users.isUserExist(userName).getMessage()!=1){
            message = "User not exist. #SeatTicket*removeAllTicket";
            System.out.println(message);
            packet.setMessageString(message);
            return packet;

        }
        var tickets = getUserTickets(userName);
        if (tickets.getItem()==null){
            System.out.println("NullPointerException #SeatTicket*RemoveAllTicketForUser");
            return packet;
        }
        if(tickets.getItem().isEmpty()){
            System.out.println(userName+", don't have reservation. #SeatTicket*removeAllTicketForUser");
            return packet;
        }
        ticketData.getUserTickets().get(userName).forEach(
                m -> cinema.unBookSeat(m.getTheaterId(), m.getShowTime(), m.getSeatId())
        );
        BillController.addBill((tickets.getItem().size()*5),ticketData.getUserTickets().get(userName));
        if(!ticketData.removeAllTicketForUser(userName)){
            message = "User not exist. #SeatTicket*removeAllTicket";
            System.out.println(message);
            packet.setMessageString(message);
            return packet;
        }

        packet.setMessage(1);
        packet.setItem(tickets.getItem());
        return packet;
    }

    public static Packet getUserTickets(String userName){
        List<String> bucket = new ArrayList<>();
        Packet packet = new Packet(0);
        String message = "";
        if(ticketData.getUserTickets()==null){
            message = "userTickets is empty #Server*SeatTicket*getUserTicket";
            System.out.println(message);
            packet.setMessageString(message);
            return packet;
        }
        if (!ticketData.getUserTickets().containsKey(userName)){
            message = userName+ ", don't have reservation  #Server*SeatTicket*getUserTicket";
            System.out.println(message);
            packet.setMessageString(message);
            return packet;
        }

        for (Ticket ticket : ticketData.getUserTickets().get(userName)) {
            bucket.add("SeatId: " + ticket.getSeatId() + "    Seat Position: " + ticket.getSeatRow() + ticket.getSeatColumn() +
                    "       Movie : " + ticket.getMovie().getMovieName() +
                    "\nTheater: " + ticket.getTheaterId() + "       Show Time: " + ticket.getShowTime() + "    Price: 5$");

        }

        packet.setMessage(1);
        packet.setItem(bucket);
        return packet;
    }

    private static int columnCalculate(int seatId){
        return ((seatId-1)%14)+1;
    }
    private static char rowCalculate(int seatId){
        if ((seatId-1)/14==0){
            return 'A';
        }else if((seatId-1)/14==1){
            return 'B';
        }else if((seatId-1)/14==2){
            return 'C';
        }else if((seatId-1)/14==3){
            return 'D';
        }
        return ' ';
    }



}
