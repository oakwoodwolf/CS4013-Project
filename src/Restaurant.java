package src;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Restaurant {
    public String id;
    public int capacity;
    private int currentCapacity;
    private ArrayList<Table> tables = new ArrayList<Table>();
    private int seatsPerTable = 6;

    /*** No-args constructor for Restaurant. This sets the
     * default values for the Restaurant.
     */
    public Restaurant(){
        id = "YUM00";
        capacity = 60;
        currentCapacity = capacity;
        for (int i = 0; i < capacity/seatsPerTable; i++){
            tables.add(new Table(i+1, seatsPerTable));
        }
    }

    /**
     * The default constructor for Restaurant
     * @param id the unique identifier for this restaurant
     * @param capacity the amount of chairs the restaurant can hold.
     */
    public Restaurant(String id, int capacity){
        this.id = id;
        this.capacity = capacity;
        currentCapacity = capacity;
        for (int i = 0; i < capacity/seatsPerTable; i++){
            tables.add(new Table(i, seatsPerTable));
        }
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

    /**
     * This allows the other restaurants to reduce or increase capacity based on the booking
     * @param currentCapacity The number of free chairs available at this restaurant
     */
    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    @Override
    /**
     * This returns the Restaurant class in String form.
     * @return the Restaurant Summary as a String
     */
    public String toString(){
        return "Restaurant Branch:\t" + id + "\tCapacity:\t" + capacity + "\tSeats Free:\t" +currentCapacity;
    }
    public void setTables(int tableNo, boolean taken){
        int temp = tableNo-1;
        tables.get(temp).setIsTaken(taken);
        if (taken = true) {
            currentCapacity -= tables.get(temp).getSeats();
        }
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
}

 class Test {
    public static void main (String[] args){
        Restaurant YumPortumna = new Restaurant("YumPr5", 42);
        System.out.print(YumPortumna.toString());
        YumPortumna.listTables(false);
    }
}
