package src;
import java.sql.Time;
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

    public IncomeRecords(double day, double date, ArrayList bills) {
        price = totalPrice(bills);
    }

    public double getTotalPrice() {
        return this.price;
    }
    public double totalPrice(ArrayList<Bill> Bills) {
        double totalPrice = 0;
        for (int i = 0; i < Bills.size(); i++) {
            totalPrice += (Bills.get(i).getTotalPrice());
        }
        return totalPrice;
    }


    public void showIncome(double day, double totalPrice) {
        this.Day = day;
        this.price = totalPrice;
    }

    public void showIncomeHistory(double day, double totalPrice) {
    }

    public String toCSV() {
        StringBuilder temp = new StringBuilder(new String());
        for (Bill bill : Bills) {
            String IncomeRecords = (LocalDate + "," + LocalTime + "," + billId + "," + CustomerId + "," + getTotalPrice() + "/n");
            temp.append(IncomeRecords);
        }
return temp.toString();
    }
}
