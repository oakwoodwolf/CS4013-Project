package src;
/** Represents a Bill.
 */
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

    /** Creates a bill with the specified params.
     * @param price cost of the Order.
     * @param paymentMethod the method used to make payment
     * @param tip tip left by the customer
     */
    Bill(double price, String paymentMethod, double tip) {
        this.price = price;
        this.paymentMethod = paymentMethod;
        this.tip = tip;
        totalPrice = price + tip;

    }
    /** Creates a bill with the specified params.
     * @param price cost of the Order.
     * @param paymentMethod the method used to make payment
     * @param tip tip left by the customer
     * @param dateTime date of the bill paid
     * @param time time of the bill paid
     * @param customerID id of the customer who paid the bill
     * @param billID id of the bill
     */
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

    /**
     * Gets the total price of the order
     * @return A double representing the cost of the order.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Gets the date of the bill
     * @return A LocalDate representing the date of the bill.
     */
    public LocalDate getDateTime() {
        return dateTime;
    }

    /**
     * Gets the price of the order without the tip
     * @return A double representing the price of the bill.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the id of the bill
     * @return An int representing the id of the bill.
     */
    public int getBillID() {
        return billID;
    }

    /**
     * Gets the tip
     * @return A double representing the tip.
     */
    public double getTip() {
        return tip;
    }

    /**
     * Gets the customer id
     * @return An int representing the customer id.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Gets the time of the bill
     * @return A LocalTime representing the time of the bill.
     */
    public LocalTime getTime() {
        return time;
    }

    /**
     * Gets the current state of the Bill object
     * @return A String representation of the state of the Bill object
     */
    public String toString() {
        return "Price: " + price + "\nPayment Method: " + paymentMethod + "\nTip: " + tip + "\nTotal Price:" + totalPrice + "\nDate: " + dateTime;
    }

    /**
     * Gets the current state of the Order object
     * @return A String representation of the state of the Order object but in comma separated form
     */
    public String toCSV(){
        return dateTime + "," + time+ ","+ customerID + "," + billID + "," +price + "," + paymentMethod + "," +tip + "\n";
    }
}
