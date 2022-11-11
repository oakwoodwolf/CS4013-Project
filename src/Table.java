package src;

public class Table {
    private int seats;
    public int tableNo = 0;
    private boolean isTaken;
    public Table(){

    }
    public Table(int tableNo, int seats){
        this.seats = seats;
        this.tableNo = tableNo;
        isTaken = false;
    }
    public void setIsTaken(boolean taken){
        isTaken = taken;

    }

    public int getTableNo() {
        return tableNo;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public int getSeats() {
        return seats;
    }
    public String toString(){
        return "Table No: " + tableNo + "\tSeats:\t"+seats+"\tTaken:\t" + isTaken;
    }
}
