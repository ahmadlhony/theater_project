package com.CinemaTicketBooking.Model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable{
    private int userId;
    private String userName;
    private boolean isAdmin;

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
