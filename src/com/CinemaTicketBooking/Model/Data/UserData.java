package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserData {
    private static AtomicInteger USER_ID_GENERATOR;
    private static ClientServerController<User> userClientServerController = new ClientServerController<>();

    private static List<User> users = new ArrayList<>();

    public void fetchAndSetUsers(){
        Packet<User> userPacket = new Packet<>(8);
        users = userClientServerController.openList(userPacket);
        fetchUserId();
    }

    private void fetchUserId(){
        USER_ID_GENERATOR = new AtomicInteger(users.size());
    }

    public boolean addUser(String userName, boolean isAdmin){
        var user = new User(USER_ID_GENERATOR.getAndIncrement(), userName.toLowerCase(), isAdmin);
        users.add(user);
        System.out.println("Successfully user Added #UserController*addUser");
        Packet<User> userPacket = new Packet<>(7);
        userPacket.setItem(users);
        return userClientServerController.saveListToFile(userPacket);
    }

    public List<User> getUsers() {
        return users;
    }


}
