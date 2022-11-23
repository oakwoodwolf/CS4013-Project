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



    public String toCSV() {
        String IncomeRecords = new String("");
        for (Bill bill : Bills) {
            IncomeRecords = new String(IncomeRecords + bill.getDateTime() + "," + bill.getTime() + "," + bill.getCustomerID() + "," + bill.getBillID() + ","   + bill.getPrice()+ ","   + bill.getPaymentMethod()+ ","   + bill.getTip() + "\n");
        }
        return IncomeRecords;
    }
}
