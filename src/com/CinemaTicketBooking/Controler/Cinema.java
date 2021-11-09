package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Packet;


public class Cinema {
    public boolean bookShow(int theaterId, String showTime,String movieName){
        Packet request = new Packet(8);
        request.setTheaterId(theaterId);
        request.setShowTime(showTime);
        request.setMovieName(movieName);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        return response.getMessage()==1;
    }

    public boolean UnBookShow(int theaterId, String showTime){
        Packet request = new Packet(9);
        request.setTheaterId(theaterId);
        request.setShowTime(showTime);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        return response.getMessage()==1;
    }

    public boolean showAvailableSeat(int theaterId,String showTime) {
        Packet request = new Packet(10);
        request.setTheaterId(theaterId);
        request.setShowTime(showTime);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        response.getItem().forEach(seat -> {
            System.out.print(seat);
            if (seat.contains("14") || seat.contains("28") || seat.contains("42"))
                System.out.println();
        });
        return true;
    }

//    private int theaterItemInList(int theaterId, String showTime){
//        if (theaterId<1 || theaterId>3){
//            System.out.println("Theater Id not available.");
//            return -1;
//        }
//        int temp=-10;
//        if (showTime.equals("2:00")){
//            temp=0;
//        }else if (showTime.equals("5:00")){
//            temp = 3;
//        }else if (showTime.equals("8:00")){
//            temp=6;
//
//        }
//        return (theaterId+temp)-1;
//    }

    public void availableShows(){
        Packet request = new Packet(11);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return;
        }
        response.getItem()
                .forEach(System.out::println);
    }

    public boolean availableMovieIsInShow(){
        Packet request = new Packet(12);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1){
            System.out.println(response.getMessageString());
            return false;
        }
        response.getItem().stream()
                .forEach(System.out::println);
        return true;
    }

    public String showIndexToShowTime(int index){
        if (index==1)
            return "2:00";
        else if (index==2)
            return "5:00";
        else if (index==3)
            return "8:00";
        else{
            System.out.println("Show index not available AdminView#106");
            return null;
        }
    }



}
