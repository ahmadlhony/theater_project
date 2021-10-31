package com.CinemaTicketBooking.Controler.Server;

import com.CinemaTicketBooking.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

;

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
            Request request = (Request) input.readObject();
            Packet ans;

            int packetMessage = request.getMessage();
            System.out.println("Received Client input: "+packetMessage);
            if (packetMessage==0){
                ans = new Packet(2);
            }else if(packetMessage==1){
                SaveData<Bill> billSaveData = new SaveData<>("theater_project/files/bills.txt");
                if(billSaveData.saveListToFile(request.getPacket().getItem())){
                    ans = new Packet<Bill>(1);
                }else{
                    ans = new Packet<Bill>(0);
                }
            }else if(packetMessage==2){
                SaveData<Bill> billSaveData = new SaveData<>("theater_project/files/bills.txt");
                List<Bill> bills= billSaveData.openList();
                if (bills.isEmpty()){
                    ans = new Packet(0);
                }else{
                    ans = new Packet<Bill>(1);
                    ans.setItem(bills);
                }

            }else if(packetMessage==3){
                SaveData<Movie> movieSaveData = new SaveData<>("theater_project/files/movies.txt");
                if(movieSaveData.saveListToFile(request.getPacket().getItem())){
                    ans = new Packet<Movie>(1);
                }else{
                    ans = new Packet<Movie>(0);
                }
            }else if(packetMessage==4){
                SaveData<Movie> movieSaveData = new SaveData<>("theater_project/files/movies.txt");
                List<Movie> movies= movieSaveData.openList();
                if (movies.isEmpty()){
                    ans = new Packet(0);
                }else{
                    ans = new Packet<Movie>(1);
                    ans.setItem(movies);
                }


            }else if(packetMessage==5){
                SaveData<Theater> cinemaSaveData = new SaveData<>("theater_project/files/theaters.txt");
                if(cinemaSaveData.saveListToFile(request.getPacket().getItem())){
                    ans = new Packet<Theater>(1);
                }else{
                    ans = new Packet<Theater>(0);
                }


            }else if(packetMessage==6){
                SaveData<Theater> cinemaSaveData = new SaveData<>("theater_project/files/theaters.txt");
                List<Theater> theaters= cinemaSaveData.openList();
                if (theaters.isEmpty()){
                    ans = new Packet(0);
                }else{
                    ans = new Packet<Theater>(1);
                    ans.setItem(theaters);
                }

            }else if(packetMessage==7){
                SaveData<User> userSaveData = new SaveData<>("theater_project/files/users.txt");
                if(userSaveData.saveListToFile(request.getPacket().getItem())){
                    ans = new Packet<User>(1);
                }else{
                    ans = new Packet<User>(0);
                }
            }else if(packetMessage==8){
                SaveData<User> userSaveData = new SaveData<>("theater_project/files/users.txt");
                List<User> users= userSaveData.openList();
                if (users.isEmpty()){
                    ans = new Packet(0);
                }else{
                    ans = new Packet<User>(1);
                    ans.setItem(users);
                }


            }else if(packetMessage==9){
                SaveData<Ticket> ticketListSaveData = new SaveData<>("theater_project/files/tickets.txt");
                if(ticketListSaveData.saveListToFile(request.getPacket().getItem())){
                    ans = new Packet<Ticket>(1);
                }else{
                    ans = new Packet<Ticket>(0);
                }
            }else if(packetMessage==10){
                SaveData<Ticket> ticketListSaveData = new SaveData<>("theater_project/files/tickets.txt");
                List<Ticket> tickets= ticketListSaveData.openList();
                if (tickets.isEmpty()){
                    ans = new Packet(0);
                }else{
                    ans = new Packet<Ticket>(1);
                    ans.setItem(tickets);
                }


            }else if(packetMessage==11){
                SaveData<Ticket> userTicketsSaveData = new SaveData<>("theater_project/files/userTickets.txt");
                if(userTicketsSaveData.saveMapToFile(request.getPacket().getMap())){
                    ans = new Packet<Ticket>(1);
                }else{
                    ans = new Packet<Ticket>(0);
                }
            }else if(packetMessage==12){
                SaveData<Ticket> userTicketsSaveData = new SaveData<>("theater_project/files/userTickets.txt");
                var userTickets= userTicketsSaveData.openMap();

                try {
                    if (userTickets.isEmpty()) {
                        ans = new Packet(0);
                        ans.setMap(new HashMap<>());
                    } else {
                        ans = new Packet<Ticket>(1);
                        ans.setMap(userTickets);
                    }
                }catch (NullPointerException e){
                    System.out.println("userTicket Null");
                    ans = new Packet(1);
                    ans.setMap(new HashMap<>());

                }
            }else{
                ans = new Packet(3);
            }

            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                System.out.println("Thread Interrupted");
            }

//                output.println("Echo from server "+ echoString);


            outStream.writeObject(ans);

            input.close();
            outStream.close();
            socket.close();
        }catch (IOException e){
            System.out.println("IOException: "+e.getMessage());
        }catch (ClassNotFoundException e){
            System.out.println("Class Not Found Exception: "+e.getMessage());
        }
        System.out.println("Disconnected");

    }

}
