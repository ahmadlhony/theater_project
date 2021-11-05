package com.CinemaTicketBooking.Controler.Server;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveData<T> {
    String path ;
//    T obj ;

    public SaveData(String path){
        this.path=path;
    }


    @SuppressWarnings("unchecked")
    public List<T> openList() {
        List<T> list = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream get = new ObjectInputStream(file);
            list = (List<T>)get.readObject();

            file.close();
            get.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + path);
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean saveListToFile(List<T> items){
        try{
            FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(items);
            o.close();
            f.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found Exception. #SaveData*addMovie"+ path);
            return false;
        } catch (IOException e){
            System.out.println("Error initializing stream. #SaveData*addMovie"+ path);
            return false;
        }
        return true;
    }


    @SuppressWarnings("unchecked")
    public Map<String,List<T>> openMap(){
        Map<String,List<T>> map = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream get = new ObjectInputStream(file);
            map = (Map<String,List<T>>) get.readObject();
            file.close();
            get.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found "+ path);
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage()+path);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean saveMapToFile(Map<String,List<T>> map){
        try{
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(map);
            System.out.println("Map saved to FIle #SaveData*saveMapToFile");
            objectOut.close();
            fileOut.close();

        } catch (FileNotFoundException e){
            System.out.println("File not found Exception. #SaveData*addMovie");
            return false;
        } catch (IOException e){
            System.out.println("Error initializing stream. #SaveData*addMovie");
            return false;
        }
        return true;

    }

}
