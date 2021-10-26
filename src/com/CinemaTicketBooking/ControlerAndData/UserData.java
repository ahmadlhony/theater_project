package com.CinemaTicketBooking.ControlerAndData;

import com.CinemaTicketBooking.Model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class UserData {
    private static AtomicInteger USER_ID_GENERATOR;
    private static User authenticatedUser;
    private SaveData<User> userSaveData = new SaveData<>("files/users/");

    private static List<User> users = new ArrayList<>();

    public void fetchAndSetUsers(){
        users = userSaveData.open();
        fetchUserId();
    }

    private void fetchUserId(){
        USER_ID_GENERATOR = new AtomicInteger(users.get(users.size()-1).getUserId()+1);
    }


    public boolean addUser(String userName, boolean isAdmin){
        if (isUserExist(userName)){
            System.out.println("sorry this UserName is already available");
            return false;
        }

        var user = new User(3, userName.toLowerCase(), isAdmin);

        users.add(user);
        System.out.println("Successfully user Added");
        return userSaveData.add(user,"user_"+user.getUserId());
    }

    public boolean authenticateUser(String userName){
        for (User user : users){
            if (user.getUserName().equals(userName.toLowerCase())){
                System.out.println("Successful Authenticated user from UserData Class");
                authenticatedUser = user;
                return true;
            }
        }
        System.out.println("UserName is not exist.");
        return false;
    }

    public List<User> getUsers() {
        return users;
    }

    public boolean isUserExist(String userName){
        for(User user:users){
            if (user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public static User getAuthUser(){
        return authenticatedUser;
    }

}
