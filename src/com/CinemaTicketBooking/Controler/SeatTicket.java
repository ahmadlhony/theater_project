package com.CinemaTicketBooking.Controler;



import com.CinemaTicketBooking.Model.Packet;

import java.util.ArrayList;
import java.util.List;

public class SeatTicket {



    //dont pass row and column make server calculate it
    public boolean addTicket(int theaterId,String showTime, int seatId){
        Packet request = new Packet(6);
        request.setTheaterId(theaterId);
        request.setShowTime(showTime);
        request.setSeatId(seatId);
        request.setUserName(UserController.getAuthUser());
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        return response.getMessage()==1;
    }




}
