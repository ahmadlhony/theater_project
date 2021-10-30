package com.CinemaTicketBooking.View;

import com.CinemaTicketBooking.Controler.Cinema;
import com.CinemaTicketBooking.Controler.MovieController;
import com.CinemaTicketBooking.Controler.UserController;

import java.util.Scanner;

public class AdminView {
    public static void start(){
        UserController userController = new UserController();
        MovieController movieController = new MovieController();
        Cinema cinema = new Cinema();
        PayingBills payingBills = new PayingBills();
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome "+ userController.getAuthUser());
        System.out.println();
        while (true){
            System.out.println("Admin menu: " +
                    "\n1.Pay for Reservation. " +
                    "\n2.Show available movies " +
                    "\n3.Add Movie " +
                    "\n4.Book Show for a movie " +
                    "\n5.Cancel Show " +
                    "\n6.Cancel Reservation " +
                    "\n0.Quit");
            System.out.println();
            int menuAns = console.nextInt();
            if (menuAns==1){
                while (true) {
                    System.out.println("Have you reserved seat ? " +
                            "\n1.Yes" +
                            "\n2.No" +
                            "\n0.Back");
                    int reservedSeatAns = console.nextInt();
                    if (reservedSeatAns == 1) {
                        System.out.println("Please Enter your Username: ");
                        String userName = console.next();
                        if(payingBills.start(userName)){
                            System.out.println("Complete #AdminView*payingBill1");
                            break;
                        }


                    } else if (reservedSeatAns == 2) {
                        System.out.println("Please reserve your seat. ");
                        BookingSeats.startBookingSeat();
                    }else if (reservedSeatAns==0){
                        break;
                    }
                }

            }else if(menuAns==2){
                BookingSeats.startBookingSeat();


            }else if(menuAns==3){
                System.out.println("\n Enter Movie Name: ");
                String movieName = console.next();
//                console.nextLine();

                if(movieController.addMovie(movieName)){
                    System.out.println("Added successfully AdminView\n");
                }


            }else if(menuAns==4){
                System.out.println();
                movieController.availableMovies();
                System.out.println("Enter Movie Name: ");
                String movieName = console.next();
//                console.nextLine();
                cinema.availableShows();
                System.out.println("Enter an available TheaterId: ");
                int theaterId = console.nextInt();
                System.out.println("Which Time ? "+
                "1.2:00 \n2.5:00 \n3.8:00");
                int showIndex = console.nextInt();
                String showTime = cinema.showIndexToShowTime(showIndex);

                if(cinema.bookShow(theaterId,showTime,movieName)){
                    System.out.println("Show Successfully booked AdminView");
                }else{
                    System.out.println("Show not booked AdminView");

                }


            }else if(menuAns==5){
                System.out.println("\n*Cancel Show*");
                cinema.availableMovieIsInShow();
                System.out.println("Which show you want to Delete, Enter theaterId");
                int theaterId = console.nextInt();
                System.out.println("Which Time ? "+
                        "1.2:00 \n2.5:00 \n3.8:00");
                int showIndex = console.nextInt();
                String showTime = cinema.showIndexToShowTime(showIndex);
                if (cinema.UnBookShow(theaterId,showTime)){
                    System.out.println("Successful from AdminView");

                }

            }else if(menuAns==6){
                BookingSeats.cancelReservation();

            }else if(menuAns==0){
                return;
            }


        }

    }




}
