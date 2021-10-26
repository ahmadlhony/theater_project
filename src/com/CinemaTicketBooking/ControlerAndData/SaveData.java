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
    public List<T> open() {

        List<T> list = new ArrayList<>();



        File dir = new File(path);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    String str = child.getCanonicalPath();

                    FileInputStream file = new FileInputStream(str);
                    ObjectInputStream get = new ObjectInputStream(file);
                    obj = (T)get.readObject();
                    list.add(obj);

                    file.close();
                    get.close();

                    //open and be ready for output


                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException e) {
                    System.out.println("Error initializing stream"+e.getMessage());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        return list;
    }


    public boolean add(T item,String extendedPath){
        try{
            FileOutputStream f = new FileOutputStream(path+extendedPath+".txt");
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(item);
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
