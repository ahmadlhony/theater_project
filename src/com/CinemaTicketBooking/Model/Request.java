package com.CinemaTicketBooking.Model;

import java.io.Serializable;

public class Request implements Serializable {
    int message;
    Packet packet;

    public Request(int message, Packet packet) {
        this.message = message;
        this.packet = packet;
    }

    public int getMessage() {
        return message;
    }

    public Packet getPacket() {
        return packet;
    }
}
