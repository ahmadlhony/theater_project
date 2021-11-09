package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Packet;

import java.io.*;

public class ClientServerController{


    @SuppressWarnings("unchecked")
    public static Packet get(Packet packet) {
        try{
            ClientServerConnection.objectOut.writeObject(packet);
            ClientServerConnection.objectOut.flush();
            Packet response = (Packet) ClientServerConnection.objectIn.readObject();
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

//    public static boolean post(Packet packet){
//        try {
//            ClientServerConnection.objectOut.writeObject(packet);
//            ClientServerConnection.objectOut.flush();
//            Packet response = (Packet) ClientServerConnection.objectIn.readObject();
//
//            int message = response.getMessage();
//            return message == 1;
//
//        } catch (FileNotFoundException e){
//            System.out.println("File not found Exception. #ClientServerController*addMovie"+ packet.getMessage());
//            return false;
//        } catch (IOException e){
//            System.out.println("Error initializing stream. #ClientServerController*addMovie"+ packet.getMessage());
//            return false;
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }catch (NullPointerException e){
//            System.out.println("NullPointerException #ClientServerController*saveListToFile");
//        }
//        return true;
//    }

}
