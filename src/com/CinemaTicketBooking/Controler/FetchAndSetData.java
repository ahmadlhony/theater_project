package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.*;

public class FetchAndSetData {
    public static void fetchAndSeatData(){
        BillData billData = new BillData();
        TheaterData theaterData = new TheaterData();
        MovieData movieData = new MovieData();
        TicketData ticketData = new TicketData();
        UserData userData = new UserData();

        userData.fetchAndSetUsers();
        movieData.fetchAndSetMovies();
        theaterData.fetchAndSetTheaters();
        ticketData.fetchAndSetTicketList();
        ticketData.fetchAndSetUserTicket();
        billData.fetchAndSetBillList();
    }
}
