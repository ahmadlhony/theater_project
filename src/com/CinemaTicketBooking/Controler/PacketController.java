package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.*;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;


public class PacketController extends Thread{
    private Socket socket;
    public PacketController(Socket socket){
        this.socket=socket;
    }
    @Override
    @SuppressWarnings("unchecked")
    public void run() {

        try{
            System.out.println("Client Connected");
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            socket.setTcpNoDelay(true);
            Packet packet;

                while(true) {
                    FetchAndSetData.fetchAndSetAllData();
                    System.out.println("1");
                    packet = (Packet) input.readObject();
                    int packetMessage = packet.getMessage();
                    System.out.println("Received Client input: " + packetMessage);
                    if (packetMessage == 0) {
                        packet = new Packet(1);
                        break;
                    } else {
                        packet = processPacket(packet);
                    }
                    System.out.println("Before OutputStream");
                    outStream.writeObject(packet);
                    outStream.flush();
                }
            outStream.writeObject(packet);
            outStream.close();
            input.close();
            socket.close();

        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("Class Not Found Exception: "+e.getMessage());
        }
        System.out.println("Disconnected");

    }

    private Packet processPacket(Packet packet){

        int packetMessage = packet.getMessage();
        Packet response = new Packet(1);

        if (packetMessage==1){
            UserController userController = new UserController();
            response = userController.addUser(packet.getUserName(),packet.isAdmin());
        }else if (packetMessage==2){
            UserController userController = new UserController();
            response= userController.isUserExist(packet.getUserName());
        }else if (packetMessage==3){
            MovieController movieController = new MovieController();
            response = movieController.addMovie(packet.getMovieName());

        }else if (packetMessage==4){
            MovieController movieController = new MovieController();
            response = movieController.availableMovies();

        }
//        else if (packetMessage==5){
//            BillController billController = new BillController();
//            response = billController.addBill();
//
//        }
        else if (packetMessage==6){
            SeatTicket seatTicket = new SeatTicket();
            response=seatTicket.addTicket(packet.getTheaterId(), packet.getShowTime(), packet.getSeatId()
                    , packet.getUserName());

        }else if (packetMessage==7){
            SeatTicket seatTicket = new SeatTicket();
            response=seatTicket.removeAllTicketForUser(packet.getUserName());
        }else if (packetMessage==8){
            Cinema cinema = new Cinema();
            response = cinema.bookShow(packet.getTheaterId(), packet.getShowTime(), packet.getMovieName());

        }else if (packetMessage==9){
            Cinema cinema = new Cinema();
            response = cinema.UnBookShow(packet.getTheaterId(), packet.getShowTime());
        }else if (packetMessage==10){
            Cinema cinema = new Cinema();
            response=cinema.showAvailableSeat(packet.getTheaterId(), packet.getShowTime());
        }else if (packetMessage==11){
            Cinema cinema = new Cinema();
            response = cinema.availableShows();
        }else if (packetMessage==12){
            Cinema cinema = new Cinema();
            response = cinema.availableMovieIsInShow();

        }else {
            response.setMessage(0);
            response.setMessageString("No command found (packet.message)");
        }


        return response;
    }

}
