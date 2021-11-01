package com.CinemaTicketBooking.Controler;

import com.CinemaTicketBooking.Model.Data.*;

public class FetchAndSetData {
    public static void fetchAndSetAllData(){
        fetchAndSetUserData();
        fetchAndSetMovieData();
        fetchAndSetTheaterData();
        fetchAndSetTicketData();
        fetchAndSetBillData();
    }

    public static void fetchAndSetUserData(){
        UserData userData = new UserData();
        userData.fetchAndSetUsers();
    }

    public static void fetchAndSetMovieData(){
        MovieData movieData = new MovieData();
        movieData.fetchAndSetMovies();
    }

    public static void fetchAndSetTheaterData(){
        TheaterData theaterData = new TheaterData();
        theaterData.fetchAndSetTheaters();
    }

    public static void fetchAndSetTicketData(){
        TicketData ticketData = new TicketData();
        ticketData.fetchAndSetTicketList();
        ticketData.fetchAndSetUserTicket();
    }

    public static void fetchAndSetBillData(){
        BillData billData = new BillData();
        billData.fetchAndSetBillList();
    }


}
