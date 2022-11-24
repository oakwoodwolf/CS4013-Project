package src;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class IncomeRecords {

    public double Day;
    public Date LocalDate;
    public Time LocalTime;
    public int billId;
    public int CustomerId;
    public double price;

    ArrayList<Bill> Bills = new ArrayList<Bill>();

    public IncomeRecords(ArrayList bills) {
        this.Bills = bills;
    }

    /**
     *This uses totalPrice to get the price of a specific bill
     * @return double that represents the price of the bill
     */
    public double getTotalPrice() {
        return this.price;
    }

    /**
     * This adds the total prices from each bill in the ArrayList
     * @param Bills uses bills to get the total price
     * @return double that represents total price of all the bills in the ArrayList
     */
    public double totalPrice(ArrayList<Bill> Bills) {
        double totalPrice = 0;
        for (int i = 0; i < Bills.size(); i++) {
            totalPrice += (Bills.get(i).getTotalPrice());
        }
        return totalPrice;
    }

    /**
     *This checks if the given date is equal to the date on the bills and if it is it adds it to an ArrayList
     * @param date the income history on that specific day
     * @return an arrayList of all bills on a given day
     */
    public ArrayList<Bill> showIncomeHistory(LocalDate date) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (Bill bill : Bills) {
            if (date.isEqual(bill.getDateTime())) temp.add(bill);
        }
        return temp;
    }

    /**
     * This uses show income history for specific dates
     * @param start the starting range for dates
     * @param end the end range for dates
     * @return an arrayList of bills from that range
     */
    public ArrayList<Bill> showMultiIncomeHistory(LocalDate start, LocalDate end) {
        ArrayList<Bill> temp = new ArrayList<>();
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)){
            ArrayList<Bill> toAppend = showIncomeHistory(date);
            for (int i = 0; i < toAppend.size(); i++){
                temp.add(toAppend.get(i));
            }
        }
        return temp;
    }


    /**
     *Gets the current state of the incomeRecords object
     * @return string representation of the incomeRecords object but comma separated
     */
    public String toCSV() {
        String IncomeRecords = new String("");
        for (Bill bill : Bills) {
            IncomeRecords = new String(IncomeRecords + bill.getDateTime() + "," + bill.getTime() + "," + bill.getCustomerID() + "," + bill.getBillID() + ","   + bill.getPrice()+ ","   + bill.getPaymentMethod()+ ","   + bill.getTip() + "\n");
        }
        return IncomeRecords;
    }
}
