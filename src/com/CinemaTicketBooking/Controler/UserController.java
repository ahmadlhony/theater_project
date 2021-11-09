package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.UserData;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class UserController {
    UserData userData = new UserData();
    public Packet addUser(String userName, boolean isAdmin){
        Packet packet = new Packet(0);
        if (isUserExist(userName).getMessage()==1){
            String massage = "sorry this UserName is already available #UserController*addUser";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }
        if(!userData.addUser(userName.toLowerCase(), isAdmin)){
            String massage = "failed userData.addUser #UserController*addUser";
            System.out.println(massage);
            packet.setMessageString(massage);
            return packet;
        }
        packet.setMessage(1);
        return packet;
    }

    public Packet isUserExist(String userName){
        Packet packet = new Packet(0);
        if(!userData.getUsers().stream().anyMatch(user -> user.getUserName().equals(userName.toLowerCase()))){
            packet.setMessageString("User not Exist #Server*UserController*isUserExist");
            return packet;
        }
        User userr = null;
        for (User user : userData.getUsers()){
            if (user.getUserName().equalsIgnoreCase(userName)){
                System.out.println("Successful Authenticated user from UserController Class");
                userr=user;
            }
        }
        packet.setUserName(userr.getUserName());
        Map<String,Boolean> mapp = new HashMap<>();
        mapp.put(userr.getUserName(),userr.isAdmin());
        packet.setUser(mapp);
        packet.setMessage(1);
        return packet;
    }




}
