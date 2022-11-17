package src;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Reservation {
    public String name;
    public int PhoneNumber;
    public LocalDate LocalDate;
    public Time LocalTime;

    public int CustomerId;
    public int NoOfPeople; // Number of people
    int TableId; // table number

    public Reservation(String Name, int CustomerID, int noOfPeople, int tableId) {
        this.name = Name;
        this.CustomerId = CustomerID;
        this.NoOfPeople = noOfPeople;
        this.TableId = tableId;
    }

    public int getCustomerID() {
        return this.CustomerId;
    }
    public LocalDate getDate() {
        return LocalDate;
    }
    public String toString() {
        return name + "\t" + CustomerId + "\t" + PhoneNumber + "\t" + NoOfPeople + "\t";
    }


}