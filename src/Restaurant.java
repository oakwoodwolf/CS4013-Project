package src;

import java.io.*;
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
        for (int l = 0; l < tables.size(); l++){
            capacity += tables.get(l).getSeats();
        }
        return "Restaurant Branch:\t" + id + "\tCapacity:\t" + capacity;
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
    /**
     *
     */
    public void setReservations(Reservation reservation){
        reservations.add(reservation);
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
/**
 * an old test class to test outputting to CSV
 * */
// class Test {
//    public static void main (String[] args){
//        File file = new File("restaurants.csv");
//        try (FileReader reader = new FileReader(file);){
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        Restaurant YumPortumna = new Restaurant("YumPr5", 6);
//        System.out.print(YumPortumna.toCSV());
//        try (PrintWriter out = new PrintWriter("restaurants.csv")){
//            out.println(YumPortumna.toCSV());
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
