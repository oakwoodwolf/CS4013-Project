package src;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;

public class Bill {
    double price;
    String paymentMethod;
    double tip;
    int customerID = 0;
    int billID = 0;
    double totalPrice;
    LocalDate dateTime = LocalDate.now();

    Bill(double price, String paymentMethod, double tip) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.tip = tip;
        totalPrice = price + tip;

    }
    Bill(double price, String paymentMethod, double tip, LocalDate dateTime, int customerID, int billID) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.tip = tip;
        this.dateTime = dateTime;
        this.customerID = customerID;
        this.billID = billID;
        totalPrice = price + tip;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public String toString() {
        return "Price: " + price + "\nPayment Method: " + paymentMethod + "\nTip: " + tip + "\nTotal Price:" + totalPrice + "\nDate: " + dateTime;
    }
}
