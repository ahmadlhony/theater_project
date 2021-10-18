package com.CinemaTicketBooking.datas;

import com.CinemaTicketBooking.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


public class UserData {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static User authenticatedUser;




    private static ArrayList<User> users = new ArrayList<>(Arrays.asList(new User(ID_GENERATOR.getAndIncrement(),"ahmad",true),
            new User(ID_GENERATOR.getAndIncrement(),"taman",true),
            new User(ID_GENERATOR.getAndIncrement(),"zina",true),
            new User(ID_GENERATOR.getAndIncrement(),"ala",false)
    ));


    public boolean addUser(String userName, boolean isAdmin){
        if (isUserExist(userName)){
            System.out.println("sorry this UserName is already available");
            return false;
        }

        var user = new User(ID_GENERATOR.getAndIncrement(), userName.toLowerCase(), isAdmin);
        users.add(user);
        System.out.println("Successfully user Added");
        return true;
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

    public ArrayList<User> getUsers() {
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
