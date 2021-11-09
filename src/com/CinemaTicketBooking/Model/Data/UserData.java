package com.CinemaTicketBooking.Model.Data;

import com.CinemaTicketBooking.Controler.SaveData;
import com.CinemaTicketBooking.Model.Packet;
import com.CinemaTicketBooking.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class UserData {
    private static AtomicInteger USER_ID_GENERATOR;
    private static SaveData<User> userSaveData = new SaveData<>("files/users.txt");

    private static List<User> users = new ArrayList<>();

    public void fetchAndSetUsers(){
        users = userSaveData.openList();
        fetchUserId();
    }

    private void fetchUserId(){
        USER_ID_GENERATOR = new AtomicInteger(users.size());
    }

    public boolean addUser(String userName, boolean isAdmin){
        var user = new User(USER_ID_GENERATOR.getAndIncrement(), userName.toLowerCase(), isAdmin);
        users.add(user);
        System.out.println("Successfully user Added #UserController*addUser");
        return userSaveData.saveListToFile(users);
    }

    public List<User> getUsers() {
        fetchAndSetUsers();
        return users;
    }


}
