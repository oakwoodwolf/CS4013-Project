package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class RestaurantSystem {
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    FileInputStream res = new FileInputStream("restaurants.csv");

    FileInputStream resV;
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
        LoadRestaurants(csvContents);
        for (Restaurant restaurant : restaurants){
            LoadReservations(csvContents, restaurant);


        }
    }

    /**
     * This loads <b>all</b> the restaurants, its table and its capacity.
     * @param csvContents the ArrayList used for loading CSV files.
     */
    private void LoadRestaurants(ArrayList<String[]> csvContents) {
        System.out.println("Loading Restaurants");
        while(resScan.hasNextLine()){
            String temp = resScan.nextLine();
            //System.out.println(temp);
            String[] splitter = temp.split(",");
            csvContents.add(splitter);
        }
        //Load Restaurant CSV contents
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
        System.out.println("Loaded!");
    }

    /**
     * This loads Reservations from a CSV file for each Restaurant
     * @param csvContents the arrayList used to store the lines from the CSV
     * @param restaurant the restaurant the reservation is for
     */
    private void LoadReservations(ArrayList<String[]> csvContents, Restaurant restaurant) throws FileNotFoundException {

        System.out.println("Loading Reservation for " + restaurant);
        csvContents.clear();
        resV = new FileInputStream(restaurant.getId().toLowerCase()+"_reservations.csv");
        Scanner resVScan = new Scanner(resV);
        while(resVScan.hasNextLine()){
            String temp = resVScan.nextLine();
            //System.out.println(temp);
            String[] splitter = temp.split(",");
            csvContents.add(splitter);
        }

        for (String[] csvContent : csvContents) {
            String reservationID = csvContent[0];
            int numberOfPeople = Integer.parseInt(csvContent[1]);
            String[] dateTemp = csvContent[2].split("/");
            LocalDate date = LocalDate.of(2000+(Integer.parseInt(dateTemp[2])),Integer.parseInt(dateTemp[1]),Integer.parseInt(dateTemp[0]));
            String[] timeTemp = csvContent[3].split("\\.");
            String minutesTemp = timeTemp[1].substring(0,1);
            LocalTime time = LocalTime.of(Integer.parseInt(timeTemp[0]),Integer.parseInt(minutesTemp));
            int tableNo = Integer.parseInt(csvContent[4]);
            int customerID = Integer.parseInt(csvContent[5]);
            Reservation reservation = new Reservation(restaurant.getId(), customerID, numberOfPeople, tableNo, date, time);

            restaurant.setReservations(reservation);
            restaurant.setTaken(tableNo);
        }
        System.out.println("Loaded");
        resVScan.close();
    }

    /**
     * This runs the menu of the program, serving as the text interface
     */
    public void run()
    {
        boolean running = true;
        ArrayList<Restaurant> newlist = getRestaurants();
        while (running)
        {
            System.out.println("Enter the number of your desired option:");
            System.out.println("<1>: Search For Tables\t<2>: Check Reservations\t<3>: Check Reservations");
            String command = in.nextLine();
            switch (command) {
                case ("1") -> System.out.println(newlist);
                case ("2") -> {
                    System.out.println("Please enter the day: YYYY/MM/DD");
                    String line = in.nextLine();
                    LocalDate date;
                    if (!line.contentEquals("")) {
                        String[] lineSplit = line.split("/");
                        System.out.println(Arrays.toString(lineSplit));
                        int year = Integer.parseInt(lineSplit[0]);
                        int month = Integer.parseInt(lineSplit[1]);
                        int day = Integer.parseInt(lineSplit[2]);
                        date = LocalDate.of(year, month, day);
                    } else {
                        date = LocalDate.now();
                    }
                    for (Restaurant restaurant : restaurants) {
                        ArrayList<Reservation> currentReservations = restaurant.getReservations(date);
                        if (!currentReservations.isEmpty()){
                            System.out.println(restaurant.getId() + ": " + currentReservations);
                        }
                    }
                }
                case ("3") -> System.out.println("");
                default -> {
                    running = false;
                    System.out.println("Exiting. Thank you for visiting Yum!");
                    resScan.close();
                    in.close();
                }
            }
        }
    }

    public boolean exists(String str){
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().contains(str)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }
}
