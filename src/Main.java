import com.CinemaTicketBooking.Controler.ClientServerConnection;
import com.CinemaTicketBooking.Controler.ClientServerController;
import com.CinemaTicketBooking.View.StartView;


public class Main {
    public static void main(String[] args) {
        ClientServerConnection.startConnection();
        StartView.start();
    }
}
