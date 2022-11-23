package src;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;

public class Bill {
    double price;
    String paymentMethod;
    double tip;
    double totalPrice;
    LocalDateTime dateTime = LocalDateTime.now();

    Bill(double price, String paymentMethod, double tip) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.tip = tip;
        totalPrice = price + tip;
    }

    public String toString() {
        return "Price: " + price + "\nPayment Method: " + paymentMethod + "\nTip: " + tip + "\nTotal Price:" + totalPrice + "\nDate: " + dateTime.toLocalDate();
    }
}
