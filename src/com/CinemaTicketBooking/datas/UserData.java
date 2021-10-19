package com.CinemaTicketBooking.datas;

import com.CinemaTicketBooking.model.Movie;
import com.CinemaTicketBooking.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class UserData {
    private static AtomicInteger ID_GENERATOR = new AtomicInteger(0);
    private static User authenticatedUser;
    private static ArrayList<User> ioUsers = new ArrayList<>();




    private static ArrayList<User> users = new ArrayList<>(Arrays.asList(new User(ID_GENERATOR.getAndIncrement(),"ahmad",true),
            new User(ID_GENERATOR.getAndIncrement(),"taman",true),
            new User(ID_GENERATOR.getAndIncrement(),"zina",true),
            new User(ID_GENERATOR.getAndIncrement(),"ala",false)
    ));


    public boolean addUser(String userName, boolean isAdmin){
        if (isUserExist(userName)){
            System.out.println("sorry this UserName is already available");
            return false;
        }

        var user = new User(ID_GENERATOR.getAndIncrement(), userName.toLowerCase(), isAdmin);
        try{
            FileOutputStream f = new FileOutputStream("files\\users\\users_"+user.getUserId()+".txt");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(user);
            o.close();
            f.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found Exception. #MovieData*addMovie");
            return false;
        } catch (IOException e){
            System.out.println("Error initializing stream. #MovieData*addMovie");
            return false;
        }
        users.add(user);
        System.out.println("Successfully user Added");
        return true;
    }

    public boolean authenticateUser(String userName){
        for (User user : users){
            if (user.getUserName().equals(userName.toLowerCase())){
                System.out.println("Successful Authenticated user from UserData Class");
                authenticatedUser = user;
                return true;
            }
        }
        System.out.println("UserName is not exist.");
        return false;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

//    public static ArrayList<User> getIOUsers() {
//
//
//        try{
//            FileInputStream f = new FileInputStream("files\\users.txt");
//            ObjectInputStream o = new ObjectInputStream(f);
//
//            while (true){
//                try{
//                    User user = (User) o.readObject();
//                    ioUsers.add(user);
//                    System.out.println("readed");
//
//                }catch(EOFException e){
//                    break;
//                }
//            }
//
//
//
//            o.close();
//            f.close();
//
//        } catch (FileNotFoundException e){
//            System.out.println("File not found Exception. #User*getIOUsers");
//            return ioUsers;
//        } catch (IOException e){
//            System.out.println("Error initializing stream. #UserData*getIOUsers");
//            return ioUsers;
//        }catch (ClassNotFoundException e){
//            System.out.println("class not found exception #User*getIOUsers");
//        }
//        return ioUsers;
//    }


    public boolean isUserExist(String userName){
        for(User user:users){
            if (user.getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public static User getAuthUser(){
        return authenticatedUser;
    }

}
