package src;

import java.time.LocalDate;
import java.util.ArrayList;

public class Restaurant {
    public String id;
    public int capacity;
//    private int currentCapacity;

    private Object menu = new Object();
    private ArrayList<Table> tables = new ArrayList<Table>();
    private ArrayList<Reservation> reservations;
    private int seatsPerTable = 6;

    /*** No-args constructor for Restaurant. This sets the
     * default values for the Restaurant.
     */
    public Restaurant(){
        id = "YUM00";
        capacity = 6;
        reservations = new ArrayList<>();
//        currentCapacity = capacity;
    }

    /**
     * The default constructor for Restaurant
     * @param id the unique identifier for this restaurant
     * @param capacity the amount of chairs the restaurant can hold.
     */
    public Restaurant(String id, int capacity){
        this.id = id;
        this.capacity = capacity;
        reservations = new ArrayList<>();
        //currentCapacity = capacity;
    }
    public void addTable(int no, int capacity){
        Table table = new Table(no, capacity);
        tables.add(table);
    }

    /**
     * This fetches the Restaurant's ID
     * @return id: The unique identifier for this restaurant
     */
    public String getId() {
        return id;
    }

    /**
     * This fetches the capacity of the restaurant
     * @return capacity: how much the restaurant can contain
     */
    public int getCapacity() {
        return capacity;
    }

    @Override
    /**
     * This returns the Restaurant class in String form.
     * @return the Restaurant Summary as a String
     */
    public String toString()
    {
        int capacity = 0;
        for (Table table : tables) {
            if (!table.isTaken()) {
                capacity += table.getSeats();
            }
        }
        return "Branch:\t" + id + "\tCapacity:\t" + capacity;
    }
    public void setTables(int tableNo, boolean taken){
        int temp = tableNo-1;
        tables.get(temp).setIsTaken(taken);
    }

    /**
     * This lists the tables in the restaurant.
     * @param hideBooked if true, this will only show Tables that are not taken.
     */
    public void listTables(boolean hideBooked){
        if (hideBooked){
            for (Table table : tables) {
                if (!table.isTaken()) System.out.println(table);
            }
        } else {
            for (Table table : tables) {
                System.out.println(table);
            }
        }
    }

    /**
     * This is ListTables but instead outputs an ArrayList
     * @param hideBooked if true, hides booked Tables
     * @return a list of free tables
     */
    public ArrayList<Table> pullTables(boolean hideBooked){
        ArrayList<Table> out = new ArrayList<>();
        if (hideBooked){
            for (Table table : tables) {
                if (!table.isTaken()) out.add(table);
            }
        } else {
            out.addAll(tables);
        }
        return out;
    }


    /**
     *This checks all the reservations for a given day.
     *
     * @param date the date to check for reservations
     * @return the Reservations available under these requirements
     */
    public ArrayList<Reservation> getReservations(LocalDate date){
        ArrayList<Reservation> reserves = new ArrayList<>();
        if (reservations != null){
            for (int i = 0; i < reservations.size(); i++){
                LocalDate temp = reservations.get(i).getDate();
                if (temp.isEqual(date)){
                    reserves.add(reservations.get(i));
                }
            }
        }
        return reserves;
    }
    public ArrayList<Reservation> getReservations(){
        return reservations;
    }
    /**This lets you add a reservation to the ArrayList of this restaurant
     * @param reservation the reservation to add
     */
    public void setReservations(Reservation reservation){
        reservations.add(reservation);
    }

    /**
     * This cancels a reservation after confirmation. It also frees the table.
     * @param reservation the reservation to cancel
     */
    public void cancelReservation(Reservation reservation){
        for (Table table: tables){
            if (table.getTableNo() == reservation.getTableId()){
             table.setIsTaken(false);
            }
        }
        for (int i = 0; i < reservations.size(); i++){
            if (reservations.get(i).getReservationID() == reservation.getReservationID()){
                reservations.remove(i);
            }
        }
    }
    public void editDate(LocalDate date, Reservation in){
        for (Reservation reservation: reservations){
            if (in.getReservationID() == reservation.getReservationID()){
                reservation.setLocalDate(date);
            }
        }
    }

    public void setTaken(int tableNo){
        for (Table table: tables){
            if (table.getTableNo() == tableNo){
                table.setIsTaken(true);
            }
        }
    }
    /**
     * This makes a csv-ready ArrayList, with first value being restaurant id, 2nd being table no, and 3rd being seat count.
     * @return A line for the arraylist
     */
    public String toCSV(){
        StringBuilder temp = new StringBuilder(new String());
        for (Table table : tables){
            String tableID = (id + ","+ table.getTableNo()+","+table.getSeats() + "\n");
            temp.append(tableID);
        }
        return temp.toString();
    }

}