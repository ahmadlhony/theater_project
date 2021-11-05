package com.CinemaTicketBooking.Controler.Server;

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
                    System.out.println("1");
                    packet = (Packet) input.readObject();
//                    Packet ans;
                    int packetMessage = packet.getMessage();
                    System.out.println("Received Client input: " + packetMessage);
                    if (packetMessage == 0) {
                        packet = new Packet(2);
                        break;
                    } else if (packetMessage == 1) {
                        SaveData<Bill> billSaveData = new SaveData<>("files/bills.txt");
                        if (billSaveData.saveListToFile(packet.getItem())) {
                            packet = new Packet<Bill>(1);
                        } else {
                            packet = new Packet<Bill>(0);
                        }
                    } else if (packetMessage == 2) {
                        SaveData<Bill> billSaveData = new SaveData<>("files/bills.txt");
                        List<Bill> bills = billSaveData.openList();
                        if (bills.isEmpty()) {
                            packet = new Packet(0);
                        } else {
                            packet = new Packet<Bill>(1);
                            packet.setItem(bills);
                        }

                    } else if (packetMessage == 3) {
                        SaveData<Movie> movieSaveData = new SaveData<>("files/movies.txt");
                        if (movieSaveData.saveListToFile(packet.getItem())) {
                            packet = new Packet<Movie>(1);
                        } else {
                            packet = new Packet<Movie>(0);
                        }
                    } else if (packetMessage == 4) {
                        SaveData<Movie> movieSaveData = new SaveData<>("files/movies.txt");
                        List<Movie> movies = movieSaveData.openList();
                        if (movies.isEmpty()) {
                            packet = new Packet(0);
                        } else {
                            packet = new Packet<Movie>(1);
                            packet.setItem(movies);
                        }


                    } else if (packetMessage == 5) {
                        SaveData<Theater> cinemaSaveData = new SaveData<>("files/theaters.txt");
                        if (cinemaSaveData.saveListToFile(packet.getItem())) {
                            packet = new Packet<Theater>(1);
                        } else {
                            packet = new Packet<Theater>(0);
                        }


                    } else if (packetMessage == 6) {
                        SaveData<Theater> cinemaSaveData = new SaveData<>("files/theaters.txt");
                        List<Theater> theaters = cinemaSaveData.openList();
                        if (theaters.isEmpty()) {
                            packet = new Packet(0);
                        } else {
                            packet = new Packet<Theater>(1);
                            packet.setItem(theaters);
                        }

                    } else if (packetMessage == 7) {
                        SaveData<User> userSaveData = new SaveData<>("files/users.txt");
                        if (userSaveData.saveListToFile(packet.getItem())) {
                            packet = new Packet<User>(1);
                        } else {
                            packet = new Packet<User>(0);
                        }
                    } else if (packetMessage == 8) {
                        SaveData<User> userSaveData = new SaveData<>("files/users.txt");
                        List<User> users = userSaveData.openList();
                        if (users.isEmpty()) {
                            packet = new Packet(0);
                        } else {
                            packet = new Packet<User>(1);
                            packet.setItem(users);
                        }


                    } else if (packetMessage == 9) {
                        SaveData<Ticket> ticketListSaveData = new SaveData<>("files/tickets.txt");
                        if (ticketListSaveData.saveListToFile(packet.getItem())) {
                            packet = new Packet<Ticket>(1);
                        } else {
                            packet = new Packet<Ticket>(0);
                        }
                    } else if (packetMessage == 10) {
                        SaveData<Ticket> ticketListSaveData = new SaveData<>("files/tickets.txt");
                        List<Ticket> tickets = ticketListSaveData.openList();
                        if (tickets.isEmpty()) {
                            packet = new Packet(0);
                        } else {
                            packet = new Packet<Ticket>(1);
                            packet.setItem(tickets);
                        }


                    } else if (packetMessage == 11) {
                        SaveData<Ticket> userTicketsSaveData = new SaveData<>("files/userTickets.txt");
                        if (userTicketsSaveData.saveMapToFile(packet.getMap())) {
                            packet = new Packet<Ticket>(1);
                        } else {
                            packet = new Packet<Ticket>(0);
                        }
                    } else if (packetMessage == 12) {
                        SaveData<Ticket> userTicketsSaveData = new SaveData<>("files/userTickets.txt");
                        var userTickets = userTicketsSaveData.openMap();

                        try {
                            if (userTickets.isEmpty()) {
                                packet = new Packet(0);
                                packet.setMap(new HashMap<>());
                            } else {
                                packet = new Packet<Ticket>(1);
                                packet.setMap(userTickets);
                            }
                        } catch (NullPointerException e) {
                            System.out.println("userTicket Null");
                            packet = new Packet(1);
                            packet.setMap(new HashMap<>());

                        }
                    } else {
                        packet = new Packet(3);
                    }

//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        System.out.println("Thread Interrupted");
//                    }

    //                output.println("Echo from server "+ echoString);

                    System.out.println("Before OutputStream");
                    outStream.writeObject(packet);
                    outStream.flush();
                }




//            outStream.close();
//            input.close();

//            input.close();
//            outStream.close();
//            socket.setKeepAlive(true);

        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("Class Not Found Exception: "+e.getMessage());
        }
        System.out.println("Disconnected");

    }

}
