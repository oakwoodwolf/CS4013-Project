package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class RestaurantSystem {
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    FileInputStream res = new FileInputStream("restaurants.csv");
    Scanner resScan = new Scanner(res);
    Scanner in;

    /**
     *  This starts up the Restaurant system, loading the csv files and creating classes from them
     * @throws FileNotFoundException if the .csv file cannot be found.
     */
    public RestaurantSystem() throws FileNotFoundException {
        in = new Scanner(System.in);
        ArrayList<String[]> csvContents = new ArrayList<>();
        //Load CSV Contents
        while(resScan.hasNextLine()){
            String temp = resScan.nextLine();
            //System.out.println(temp);
            String[] splitter = temp.split(",");
            csvContents.add(splitter);
        }
        for (String[] csvContent : csvContents) {
            String restaurantID = csvContent[0];
            int tableNo = Integer.parseInt(csvContent[1]);
            int capacity = Integer.parseInt(csvContent[2]);
            if (!exists(restaurantID)) {
                Restaurant res = new Restaurant(restaurantID, capacity);
                res.addTable(tableNo, capacity);
                restaurants.add(res);
            } else {
                for (Restaurant restaurant : restaurants) {
                    if (restaurant.getId().contains(restaurantID)) {
                        restaurant.addTable(tableNo, capacity);
                    }
                }
            }
        }
    }

    /**
     * This runs the menu of the program, serving as the text interface
     */
    public void run()
    {
        boolean running = true;
        GregorianCalendar calendar = new GregorianCalendar();
        ArrayList<Restaurant> newlist = getRestaurants();
        while (running)
        {
            System.out.println("Enter the number of your desired option:");
            System.out.println("<1>: Search For Tables\t<2>: Make Reservation\t<3>: Check Reservations");
            String command = in.nextLine();
            switch (command){
                case ("1") :
                    System.out.println(newlist);
                    break;
                case ("2") :
                    System.out.println("Please enter the day: YYYY/MM/DD");
                    String line = in.nextLine();
                    LocalDate date;
                    if (!line.contentEquals("")){
                        String[] lineSplit = line.split("/");
                        int year =Integer.parseInt(lineSplit[0]) ;
                        int month =Integer.parseInt(lineSplit[1]) ;
                        int day =Integer.parseInt(lineSplit[2]) ;
                        date = LocalDate.of(year, month, day);
                    } else {
                        date = LocalDate.now();
                    }
                    break;
                case ("3") :
                    System.out.println("");
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }

    public boolean exists(String str){
        for (int i = 0; i < restaurants.size(); i++){
            if (restaurants.get(i).getId().contains(str)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
