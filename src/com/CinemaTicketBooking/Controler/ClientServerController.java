package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Request;
import com.CinemaTicketBooking.View.StartView;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientServerController<T> {
//    Packet<T> packet ;
//
//    public void setPacket(Packet<T> packet) {
//        this.packet = packet;
//    }
    private static Socket socket;
    private static  ObjectInputStream objectIn;
    private static ObjectOutputStream objectOut;

    public static void startConnection(){
        try{
            socket = new Socket("localhost",5000);
            socket.setTcpNoDelay(true);
            objectIn = new ObjectInputStream(socket.getInputStream());
            objectOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            System.out.println("Connecting to server Successful");
        } catch (FileNotFoundException e) {
            System.out.println("File not found SocketController*");
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage());
        }
    }

    public static void stopConnection() {
        try {
            objectOut.writeObject(new Packet(0));
            objectOut.close();
            objectIn.close();
            socket.close();
        }catch (IOException e){
            System.out.println("IOException ClientServerController*stopConnection");
        }
    }


    @SuppressWarnings("unchecked")
    public Packet<T> get(Packet<T> packet) {
        List<T> list = new ArrayList<>();
        try{
            objectOut.writeObject(packet);
            objectOut.flush();
            Packet<T> response = (Packet<T>) objectIn.readObject();
            int message = response.getMessage();
            if(message!=1){
                System.out.println("Error: " + message);
                return null;
            }
            return response;

        } catch (FileNotFoundException e) {
            System.out.println("File not found " + packet.getMessage());
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("NullPointerException #ClientServerController*openList");
        }
        return null;

    }

    public boolean post(Packet<T> packet){
        try {
            objectOut.writeObject(packet);
            objectOut.flush();
            Packet<T> response = (Packet<T>) objectIn.readObject();

            int message = response.getMessage();
            return message == 1;

        } catch (FileNotFoundException e){
            System.out.println("File not found Exception. #ClientServerController*addMovie"+ packet.getMessage());
            return false;
        } catch (IOException e){
            System.out.println("Error initializing stream. #ClientServerController*addMovie"+ packet.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            System.out.println("NullPointerException #ClientServerController*saveListToFile");
        }
        return true;
    }


//    @SuppressWarnings("unchecked")
//    public Map<String,List<T>> openMap(Packet<T> packet){
//        Map<String,List<T>> map = new HashMap<>();
//
//        try{
//            objectOut.writeObject(packet);
//            objectOut.flush();
//            Packet<T> response = (Packet<T>) objectIn.readObject();
//            int message = response.getMessage();
//            if(message==1){
//                map = response.getMap();
//            }else{
//                System.out.println("Error: " + message);
//            }
//
//
//
//        } catch (FileNotFoundException e) {
//            System.out.println("File not found " + packet.getMessage());
//        } catch (IOException e) {
//            System.out.println("Error initializing stream"+e.getMessage());
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }catch (NullPointerException e){
//            System.out.println("NullPointerException #ClientServerController*openMap");
//        }
//        return map;
//    }

}
