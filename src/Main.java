import com.CinemaTicketBooking.ControlerAndData.*;

import com.CinemaTicketBooking.View.AddingUser;
import com.CinemaTicketBooking.View.AdminView;
import com.CinemaTicketBooking.View.AuthenticateUser;
import com.CinemaTicketBooking.View.CustomerView;
import com.CinemaTicketBooking.classes.PayingBills;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Cinema cinema = new Cinema();
        fetchAndSeatData();
//        cinema.availableShowTime().forEach(m->System.out.println(m.getTheaterId()+", "+m.getShowTime()));
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Cinema Ticket Booking");
        while(true){
            System.out.println("Do you have account?");
            System.out.println("1.Yes \n2.No create an account \nany other key to quit");
            int accountAns = console.nextInt();
            if (accountAns==1){
                if (AuthenticateUser.AuthUser()){
                    if (UserData.getAuthUser().isAdmin()) {
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
                        if (UserData.getAuthUser().isAdmin()) {
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
                break;
            }

        }





    }

    private static void fetchAndSeatData(){
        PayingBills payingBills  =new PayingBills();
        Cinema cinema = new Cinema();
        MovieData movieData = new MovieData();
        SeatTicket seatTicket = new SeatTicket();
        UserData userData = new UserData();

        userData.fetchAndSetUsers();
        movieData.fetchAndSetMovies();
        cinema.fetchAndSetTheaters();
        seatTicket.fetchAndSetTicketList();
        seatTicket.fetchAndSetUserTicket();
        payingBills.fetchAndSetBillList();


    }

}
