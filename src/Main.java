import com.CinemaTicketBooking.DosInterface.AddingUser;
import com.CinemaTicketBooking.DosInterface.AdminInterface;
import com.CinemaTicketBooking.DosInterface.AuthenticateUser;
import com.CinemaTicketBooking.DosInterface.CustomerInterface;
import com.CinemaTicketBooking.classes.BookingSeats;
import com.CinemaTicketBooking.datas.Cinema;
import com.CinemaTicketBooking.datas.MovieData;
import com.CinemaTicketBooking.datas.SeatTicket;
import com.CinemaTicketBooking.datas.UserData;
import org.w3c.dom.ls.LSOutput;

import javax.crypto.spec.PSource;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to Cinema Ticket Booking");
        while(true){
            System.out.println("Do you have account?");
            System.out.println("1.Yes \n2.No create an account \nany other key to quit");
            int accountAns = console.nextInt();
            if (accountAns==1){
                if (AuthenticateUser.AuthUser()){
                    if (UserData.getAuthUser().isAdmin()) {
                        AdminInterface.start();
                    } else {
                        CustomerInterface.start();
                    }
                    break;
                }
                System.out.println("NotAuth #Main");
                break;
            }else if(accountAns==2){
                if (AddingUser.addUser()){
                    if (AuthenticateUser.AuthUser()){
                        if (UserData.getAuthUser().isAdmin()) {
                            AdminInterface.start();
                        } else {
                            CustomerInterface.start();
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

//        Cinema cinema = new Cinema();
//        MovieData movieData = new MovieData();
//        cinema.bookShow(1,"2:00","Avengers");
//        cinema.bookShow(2,"8:00","Endgame");
//        AuthenticateUser.AuthUser();
//
//        SeatTicket.addTicket(1,"2:00",1,rowCalculate(1),columnCalculate(1),movieData.getMovieByName("Avengers"));
//        SeatTicket.addTicket(1,"2:00",2,rowCalculate(2),columnCalculate(2),movieData.getMovieByName("Avengers"));
//        SeatTicket.addTicket(1,"2:00",3,rowCalculate(3),columnCalculate(3),movieData.getMovieByName("Avengers"));
//
//
//        BookingSeats.cancelReservation();
//        for (var ticket:SeatTicket.getUserTickets(UserData.getAuthUser().getUserName())){
//            System.out.println(ticket.getSeatId()+ "   " +ticket.getTicketNo());
//        }
//        BookingSeats.showAvailableSeat(1,"2:00");


    }
    private static int columnCalculate(int seatId){
        return ((seatId-1)%14)+1;
    }

    private static char rowCalculate(int seatId){
        if ((seatId-1)/14==0){
            return 'A';
        }else if((seatId-1)/14==1){
            return 'B';
        }else if((seatId-1)/14==2){
            return 'C';
        }else if((seatId-1)/14==3){
            return 'D';
        }
        return ' ';
    }
}
