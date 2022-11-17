package src;
import java.sql.Time;
import java.time.*;
import java.util.Date;

public class Reservation {
    private String reservationID;
    private LocalDate LocalDate;
    private LocalTime LocalTime;

    public int CustomerId;
    public int NoOfPeople; // Number of people
    private int TableId; // table number

    public Reservation(String Name, int CustomerID, int noOfPeople, int tableId, LocalDate date, LocalTime time) {
        this.reservationID = Name;
        this.CustomerId = CustomerID;
        this.NoOfPeople = noOfPeople;
        this.TableId = tableId;
        LocalDate = date;
        LocalTime = time;
    }

    public String getReservationID(){
        return reservationID;
    }
    public int getCustomerID() {
        return this.CustomerId;
    }
    public LocalDate getDate() {
        return LocalDate;
    }
    public LocalTime getTime() {
        return LocalTime;
    }
    public String toString() {
        return reservationID + "\t" + CustomerId + "\t" + NoOfPeople + "\t";
    }

    public int getTableId() {
        return TableId;
    }
}