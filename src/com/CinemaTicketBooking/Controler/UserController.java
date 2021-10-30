package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.UserData;
import com.CinemaTicketBooking.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class UserController {
    UserData userData = new UserData();
    private static User authenticatedUser;
    public boolean addUser(String userName, boolean isAdmin){
        if (isUserExist(userName)){
            System.out.println("sorry this UserName is already available #UserController*addUser");
            return false;
        }
        return userData.addUser(userName, isAdmin);
    }

    public boolean authenticateUser(String userName){
        for (User user : userData.getUsers()){
            if (user.getUserName().equals(userName.toLowerCase())){
                System.out.println("Successful Authenticated user from UserController Class");
                authenticatedUser = user;
                return true;
            }
        }
//        users.stream().anyMatch(user -> user.getUserName().equals(userName.toLowerCase()));
        System.out.println("UserName is not exist.");
        return false;
    }

    public boolean isUserExist(String userName){
        return userData.getUsers().stream().anyMatch(user -> user.getUserName().equals(userName));
    }

    public static User getAuthUser(){
        return authenticatedUser;
    }

}
