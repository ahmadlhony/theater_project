package com.CinemaTicketBooking.model;

import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private String userId;
    private String userName;
    private boolean isAdmin;

    public User(){}

    public User(String userId, String userName, boolean isAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.isAdmin = isAdmin;
    }

    private ArrayList<User> users = new ArrayList<>(Arrays.asList(new User("U001","ahmad",true),
            new User("U002","Shakar",true),
            new User("U003","Dleir",false)
    ));

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void addUser(String userId, String userName, boolean isAdmin){
        var user = new User(userId, userName, isAdmin);
        users.add(user);
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return "UserName: " + userName + ", position " + (isAdmin ? "Admin" : "user") + ", user ID is : " + userId;
    }
}
