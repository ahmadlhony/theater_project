package com.CinemaTicketBooking.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Packet implements Serializable {
    private List<String> item;
    private int message;
    private String messageString;
    private boolean isAdmin;
    private String userName;
    private int theaterId;
    private String showTime;
    private String movieName;
    private int seatId;
    private Map<String,Boolean> user;

    public Packet(int message){
        this.message=message;
    }

    public Map<String, Boolean> getUser() {
        return user;
    }

    public void setUser(Map<String, Boolean> user) {
        this.user = user;
    }

    public List<String> getItem() {
        return item;
    }

    public void setItem(List<String> item) {
        this.item = item;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }
}
