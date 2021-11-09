package com.CinemaTicketBooking.Controler;


import com.CinemaTicketBooking.Model.Packet;


public class UserController {

    private static String authenticatedUserName;
    private static boolean isAdmin;


    public boolean addUser(String userName, boolean isAdmin){
        Packet request = new Packet(1);
        request.setUserName(userName);
        request.setAdmin(isAdmin);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        return response.getMessage()==1;
    }

    public boolean authenticateUser(String userName){
        Packet request = new Packet(2);
        request.setUserName(userName);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        authenticatedUserName =response.getUserName();
        isAdmin=response.getUser().get(response.getUserName());
        return response.getMessage()==1;

    }

    public boolean isUserExist(String userName){
        Packet request = new Packet(2);
        request.setUserName(userName);
        Packet response = ClientServerController.get(request);
        if (response.getMessage() !=1) {
            System.out.println(response.getMessageString());
            return false;
        }
        return response.getMessage()==1;
    }

    public static String getAuthUser(){
        return authenticatedUserName;
    }
    public static boolean isAdmin(){
        return isAdmin;
    }

}
