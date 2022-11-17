package src;
import java.sql.Time;
import java.time.*;
import java.util.Date;

public class Reservation {
    public String name;
    public int PhoneNumber;
    private LocalDate LocalDate;
    private LocalTime LocalTime;

    public int CustomerId;
    public int NoOfPeople; // Number of people
    int TableId; // table number

    public Reservation(String Name, int CustomerID, int noOfPeople, int tableId, LocalDate date, LocalTime time) {
        this.name = Name;
        this.CustomerId = CustomerID;
        this.NoOfPeople = noOfPeople;
        this.TableId = tableId;
        LocalDate = date;
        LocalTime = time;
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
        return name + "\t" + CustomerId + "\t" + PhoneNumber + "\t" + NoOfPeople + "\t";
    }


}