package src;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private final String reservationID;
    private LocalDate LocalDate;
    private final LocalTime LocalTime;

    public int CustomerId;
    public int NoOfPeople; // Number of people
    private final int TableId; // table number

    public Reservation(String Name, int CustomerID, int noOfPeople, int tableId, LocalDate date, LocalTime time) {
        this.reservationID = Name;
        this.CustomerId = CustomerID;
        this.NoOfPeople = noOfPeople;
        this.TableId = tableId;
        LocalDate = date;
        LocalTime = time;
    }

    public String getReservationID() {
        return reservationID;
    }

    public int getCustomerID() {
        return this.CustomerId;
    }

    public LocalDate getDate() {
        return LocalDate;
    }

    public void setLocalDate(java.time.LocalDate localDate) {
        LocalDate = localDate;
    }

    public LocalTime getTime() {
        return LocalTime;
    }

    public String toString() {
        return reservationID + "\t" + CustomerId + "\t" + TableId + "\t" + NoOfPeople + "\t" + LocalDate + "\t" + LocalTime;
    }

    public String toCSV() {
        return reservationID + "," + NoOfPeople + "," + LocalDate + "," + LocalTime + "," + TableId + "," + CustomerId + "\n";
    }

    public int getTableId() {
        return TableId;
    }
}