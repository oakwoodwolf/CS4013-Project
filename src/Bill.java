package src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.GregorianCalendar;

public class Bill {
    double price;
    String paymentMethod;
    double tip;
    int customerID = 0;
    int billID = 0;
    double totalPrice;
    LocalDate dateTime = LocalDate.now();
    LocalTime time = LocalTime.now();

    Bill(double price, String paymentMethod, double tip) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.tip = tip;
        totalPrice = price + tip;

    }
    Bill(double price, String paymentMethod, double tip, LocalDate dateTime, LocalTime time, int customerID, int billID) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.tip = tip;
        this.dateTime = dateTime;
        this.customerID = customerID;
        this.billID = billID;
        this.time = time;
        totalPrice = price + tip;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public double getPrice() {
        return price;
    }

    public int getBillID() {
        return billID;
    }

    public double getTip() {
        return tip;
    }

    public int getCustomerID() {
        return customerID;
    }

    public LocalTime getTime() {
        return time;
    }

    public String toString() {
        return "Price: " + price + "\nPayment Method: " + paymentMethod + "\nTip: " + tip + "\nTotal Price:" + totalPrice + "\nDate: " + dateTime;
    }
}
