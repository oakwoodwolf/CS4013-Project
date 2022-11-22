package src;

public class Table {
    private int seats;
    public int tableNo = 0;
    private boolean isTaken;
    private Order order;

    public Table() {

    }

    public Table(int tableNo, int seats) {
        this.seats = seats;
        this.tableNo = tableNo;
        isTaken = false;
    }

    public void setIsTaken(boolean taken) {
        isTaken = taken;

    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }

    /**
     * This gets the table number of the table
     *
     * @return tableNo the table number.
     */
    public int getTableNo() {
        return tableNo;
    }

    /**
     * This returns whether or not the Table is taken by a reservation
     *
     * @return isTaken
     */
    public boolean isTaken() {
        return isTaken;
    }

    /**
     * This returns the capacity of the table
     *
     * @return seats the capacity of the table
     */
    public int getSeats() {
        return seats;
    }

    /**
     * This outputs the table as a readable String, showing its number, seats available and whether or not it is taken.
     *
     * @return the Table as a string.
     */
    public String toString() {
        return "Table No: " + tableNo + "\tSeats:\t" + seats + "\tTaken:\t" + isTaken;
    }
}
