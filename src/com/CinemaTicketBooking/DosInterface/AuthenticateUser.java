package com.CinemaTicketBooking.DosInterface;

import com.CinemaTicketBooking.datas.UserData;

import java.util.Scanner;

public class AuthenticateUser {
    public static boolean AuthUser(){
        UserData userData = new UserData();
        Scanner console = new Scanner(System.in);

        System.out.println("Authentication");
        while (true){
            System.out.println("Enter your username: ");
            String userName = console.next();

            if (userData.authenticateUser(userName)){
                System.out.println("Successful Authenticated from AuthenticationUser");
                return true;
            }else{
                System.out.println("Incorrect userName do you want to try again? \n1.yes \n2.quit");
                int tryAgain = console.nextInt();
                if (tryAgain==1){
                    continue;
                }else{
                    return false;
                }

            }
        }
    }
}
