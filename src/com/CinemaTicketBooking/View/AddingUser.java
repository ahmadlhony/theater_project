package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.ControlerAndData.UserData;

import java.util.Scanner;

public class AddingUser {
    public static boolean addUser(){
        UserData userData = new UserData();
        while (true){
            Scanner console = new Scanner(System.in);
            System.out.println("Enter a UserName");
            String userName = console.next();
            System.out.println("Are you admin? \n1.yes \nAny other key for no");
            int admin = console.nextInt();
            boolean isAdmin = false;
            if (admin==1)
                isAdmin=true;

            return userData.addUser(userName, isAdmin);

        }
    }
}
