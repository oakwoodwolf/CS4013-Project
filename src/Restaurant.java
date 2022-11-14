package src;

import java.io.*;
import java.util.ArrayList;

public class Restaurant {
    public String id;
    public int capacity;
//    private int currentCapacity;

    private Object menu = new Object();
    private ArrayList<Table> tables = new ArrayList<Table>();
    private int seatsPerTable = 6;

    /*** No-args constructor for Restaurant. This sets the
     * default values for the Restaurant.
     */
    public Restaurant(){
        id = "YUM00";
        capacity = 6;
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

 class Test {
    public static void main (String[] args){
        File file = new File("restaurants.csv");
        try (FileReader reader = new FileReader(file);){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Restaurant YumPortumna = new Restaurant("YumPr5", 6);
        System.out.print(YumPortumna.toCSV());
        try (PrintWriter out = new PrintWriter("restaurants.csv")){
            out.println(YumPortumna.toCSV());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
