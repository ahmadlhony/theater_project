package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Packet;

public class MovieController {


    public static boolean addMovie(String movieName){
        Packet request = new Packet(3);
        request.setMovieName(movieName);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        return response.getMessage()==1;
    }

    public void availableMovies(){
        Packet request = new Packet(4);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return;
        }
        if (response.getItem().isEmpty())
            return;
        System.out.println("Available Movies: ");
        response.getItem().forEach(System.out::println);
        System.out.println();
    }

}
