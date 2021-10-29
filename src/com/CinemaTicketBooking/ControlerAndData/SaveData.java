package com.CinemaTicketBooking.ControlerAndData;

import java.io.*;
import java.util.*;

public class SaveData<T> {
    String path ;
    T obj ;



//    List<T> previousList;

    //folder path for files
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

            //open and be ready for output


        } catch (FileNotFoundException e) {
            System.out.println("File not found " + path);
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;



//        File dir = new File(path);
//        File[] directoryListing = dir.listFiles();
//        if (directoryListing != null) {
//            for (File child : directoryListing) {
//                try {
//                    String str = child.getCanonicalPath();
//
//                    FileInputStream file = new FileInputStream(str);
//                    ObjectInputStream get = new ObjectInputStream(file);
//                    obj = (T)get.readObject();
//                    list.add(obj);
//
//                    file.close();
//                    get.close();
//
//                    //open and be ready for output
//
//
//                } catch (FileNotFoundException e) {
//                    System.out.println("File not found");
//                } catch (IOException e) {
//                    System.out.println("Error initializing stream"+e.getMessage());
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

//        return list;
    }


//    public boolean add(List<T> item){
//        try{
//            FileOutputStream f = new FileOutputStream(path);
//            ObjectOutputStream o = new ObjectOutputStream(f);
//            o.writeObject(item);
//            o.close();
//            f.close();
//
//        } catch (FileNotFoundException e){
//            System.out.println("File not found Exception. #SaveData*addMovie");
//            return false;
//        } catch (IOException e){
//            System.out.println("Error initializing stream. #SaveData*addMovie");
//            return false;
//        }
//        return true;
//    }



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

            //open and be ready for output


        } catch (FileNotFoundException e) {
            System.out.println("File not found "+ path);
        } catch (IOException e) {
            System.out.println("Error initializing stream"+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return map;
    }

    public boolean saveMapToFile(Map<String,List<T>> map){
        try{
            FileOutputStream f = new FileOutputStream(path);
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(map);
            o.close();
            f.close();

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
