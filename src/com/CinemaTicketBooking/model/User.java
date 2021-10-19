package com.CinemaTicketBooking.model;
import java.io.*;


public class User implements Serializable{
    private int userId;
    private String userName;
    private boolean isAdmin;

    //you can add booked seats for user


    public User(int userId, String userName, boolean isAdmin) {
        this.userId = userId;
        this.userName = userName;
        this.isAdmin = isAdmin;
    }



    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return isAdmin;
    }



    @Override
    public String toString() {
        return userName + ", in position " + (isAdmin ? "Admin" : "user");
    }
}
