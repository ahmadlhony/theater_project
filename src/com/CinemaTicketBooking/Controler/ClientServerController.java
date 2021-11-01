package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.Request;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    @SuppressWarnings("unchecked")
    public List<T> openList(Packet<T> packet) {
        List<T> list = new ArrayList<>();
        try (Socket socket = new Socket("localhost",5000)){
//            socket.setSoTimeout(1500);
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
            Request request = new Request(packet.getMessage(), packet);
            objectOut.writeObject(request);
            Packet<T> response = (Packet<T>) objectIn.readObject();

            int message = response.getMessage();
            if(message==1){
                list = response.getItem();
            }else{
                System.out.println("Error: " + message);
            }
            objectOut.close();
            objectIn.close();
            socket.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found " + packet.getMessage());
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean saveListToFile(Packet<T> packet){
        try(Socket socket = new Socket("localhost",5000)) {
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            Request request = new Request(packet.getMessage(), packet);
            objectOut.writeObject(request);
            Packet<T> response = (Packet<T>) objectIn.readObject();

            int message = response.getMessage();

            objectOut.close();
            objectIn.close();
            socket.close();
            return message == 1;

        } catch (FileNotFoundException e){
            System.out.println("File not found Exception. #ClientServerController*addMovie"+ packet.getMessage());
            return false;
        } catch (IOException e){
            System.out.println("Error initializing stream. #ClientServerController*addMovie"+ packet.getMessage());
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    public Map<String,List<T>> openMap(Packet<T> packet){
        Map<String,List<T>> map = new HashMap<>();

        try (Socket socket = new Socket("localhost",5000)){
//            socket.setSoTimeout(2000);

            ObjectInputStream objectIn = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOut = new ObjectOutputStream(socket.getOutputStream());
            Request request = new Request(packet.getMessage(), packet);
            objectOut.writeObject(request);
            Packet<T> response = (Packet<T>) objectIn.readObject();

            int message = response.getMessage();
            if(message==1){
                map = response.getMap();
            }else{
                System.out.println("Error: " + message);
            }
            objectOut.close();
            objectIn.close();
            socket.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found " + packet.getMessage());
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

//    public boolean saveMapToFile(Map<String,List<T>> map){
//        try{
//            FileOutputStream f = new FileOutputStream(path);
//            ObjectOutputStream o = new ObjectOutputStream(f);
//            o.writeObject(map);
//            o.close();
//            f.close();
//
//        } catch (FileNotFoundException e){
//            System.out.println("File not found Exception. #ClientServerController*addMovie");
//            return false;
//        } catch (IOException e){
//            System.out.println("Error initializing stream. #ClientServerController*addMovie");
//            return false;
//        }
//        return true;
//
//    }

}
