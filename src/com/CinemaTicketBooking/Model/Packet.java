package com.CinemaTicketBooking.Model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Packet<T> implements Serializable {
    private List<T> item;
    private Map<String,List<T>> map;
    private int message;

    public Packet(int message){
        this.message=message;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }

    public Map<String, List<T>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<T>> map) {
        this.map = map;
    }

    public int getMessage() {
        return message;
    }
}
