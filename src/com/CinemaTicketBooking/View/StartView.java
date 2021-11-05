package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.Controler.FetchAndSetData;
import com.CinemaTicketBooking.Controler.UserController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class StartView {

    public static void start(){
        FetchAndSetData.fetchAndSetUserData();
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Cinema Ticket Booking");
        while(true){
            System.out.println("Do you have account?");
            System.out.println("1.Yes \n2.No create an account \nany other key to quit");
            int accountAns = console.nextInt();
            if (accountAns==1){
                if (AuthenticateUser.AuthUser()){
                    if (UserController.getAuthUser().isAdmin()) {
                        AdminView.start();
                    } else {
                        CustomerView.start();
                    }
                    break;
                }
                System.out.println("NotAuth #Main");
                break;
            }else if(accountAns==2){
                if (AddingUser.addUser()){
                    if (AuthenticateUser.AuthUser()){
                        if (UserController.getAuthUser().isAdmin()) {
                            AdminView.start();
                        } else {
                            CustomerView.start();
                        }
                        break;
                    }
                    System.out.println("NotAuth #Main2");
                    break;
                }
            }else{
                System.out.println("Bye");
                ClientServerController.stopConnection();
                break;
            }
        }
        FetchAndSetData.fetchAndSetAllData();
    }
}
