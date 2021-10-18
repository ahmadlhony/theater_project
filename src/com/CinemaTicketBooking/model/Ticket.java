package com.CinemaTicketBooking.model;

public class Ticket{
    private int ticketNo;
    private int theaterId;
    private String showTime;
    private int seatId;
    private char seatRow;
    private int seatColumn;
    private Movie movie;

    public Ticket(int ticketNo, int theaterId, String showTime, int seatId, char seatRow, int seatColumn, Movie movie) {
        this.ticketNo = ticketNo;
        this.theaterId = theaterId;
        this.showTime = showTime;
        this.seatId = seatId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.movie= movie;
    }

    public int getTicketNo() {
        return ticketNo;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getSeatId() {
        return seatId;
    }

    public char getSeatRow() {
        return seatRow;
    }

    public int getSeatColumn() {
        return seatColumn;
    }

    public Movie getMovie() {
        return movie;
    }
}