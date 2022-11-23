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

    /**
     *This gets the unique reservation Id
     * @return string that represents reservation Id
     */
    public String getReservationID() {
        return reservationID;
    }

    /**
     *Gets the Id of the customer who made the reservation
     * @return an int that represents customer Id
     */
    public int getCustomerID() {
        return this.CustomerId;
    }

    /**
     *This  gets the date that the reservation was made for
     * @return LocalDate that represents the date
     */
    public LocalDate getDate() {
        return LocalDate;
    }


    public void setLocalDate(java.time.LocalDate localDate) {
        LocalDate = localDate;
    }

    /**
     *This  gets the time that the reservation was made for
     * @return LocalTime that represents the time
     */
    public LocalTime getTime() {
        return LocalTime;
    }

    /**
     *Gets the current state of the reservation object
     * @return string representation of the reservation object
     */
    public String toString() {
        return reservationID + "\t" + CustomerId + "\t" + TableId + "\t" + NoOfPeople + "\t" + LocalDate + "\t" + LocalTime;
    }

    /**
     * Gets the current state of the reservation object
     * @return string that represents this object but comma separated except CustomerId which is space separated
     */
    public String toCSV() {
        return reservationID + "," + NoOfPeople + "," + LocalDate + "," + LocalTime + "," + TableId + "," + CustomerId + "\n";
    }

    /**
     * gets the id of the table that the booking was made for
     * @return int that represents table id
     */
    public int getTableId() {
        return TableId;
    }
}