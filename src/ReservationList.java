package src;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationList {
    private ArrayList<Reservation> reservations;
    public ReservationList(){
        reservations = new ArrayList<>();
    }

    /**
     *
     *
     * @param date the date to check for reservations if the param is "d"
     * @return the Reservations available under these requirements
     */
    public ArrayList<Reservation> getReservations(LocalDate date){
     ArrayList<Reservation> reserves = new ArrayList<>();
     if (reservations != null){
         for (int i = 0; i < reservations.size(); i++){
             LocalDate temp = reservations.get(i).getDate();
             if (temp.isEqual(date)){
                 reserves.add(reservations.get(i));
             }
         }
     }
     return reserves;
    }

}
