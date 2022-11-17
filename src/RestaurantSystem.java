package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class RestaurantSystem {
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    FileInputStream res = new FileInputStream("restaurants.csv");

    FileInputStream resV;
    Scanner resScan = new Scanner(res);
    Scanner in;

    int param;

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
            String[] dateTemp = csvContent[2].split("-");
            LocalDate date = LocalDate.of(2000+(Integer.parseInt(dateTemp[0])),Integer.parseInt(dateTemp[1]),Integer.parseInt(dateTemp[2]));
            String[] timeTemp = csvContent[3].split(":");
            String minutesTemp = timeTemp[1].substring(0,1);
            LocalTime time = LocalTime.of(Integer.parseInt(timeTemp[0]),Integer.parseInt(minutesTemp));
            int tableNo = Integer.parseInt(csvContent[4]);
            int customerID = Integer.parseInt(csvContent[5]);
            Reservation reservation = new Reservation(reservationID, customerID, numberOfPeople, tableNo, date, time);

            restaurant.setReservations(reservation);
            restaurant.setTaken(tableNo);
        }
        System.out.println("Loaded");
        resVScan.close();
    }

    /**
     * This runs the menu of the program, serving as the text interface
     */
    public void run() throws StringIndexOutOfBoundsException
    {
        boolean running = true;
        ArrayList<Restaurant> newlist = getRestaurants();
        while (running)
        {
            System.out.println("\nEnter the number of your desired option:");
            System.out.println("\t<1>: Book a Reservation\n\t<2>: Check Reservations\n\t<3>: Your Reservations");
            String command = String.valueOf(in.nextLine().charAt(0));
            switch (command) {
                case ("1") -> bookAReservation();
                case ("2") -> CheckAllReservations();
                case ("3") -> checkYourReservations();
                default -> running = exitMenu();
            }
        }
    }

    /**
     * Exits the menu if any option that isn't shown is selected
     * @return a boolean to tell the program the menu has been exited
     */
    private boolean exitMenu() {
        boolean running;
        running = false;
        System.out.println("Saving.");
        SaveAll();
        System.out.println("Exiting. Thank you for visiting Yum!");
        resScan.close();
        in.close();
        return running;
    }

    /**
     * Checks all reservations in every restaurant by day
     */
    private void CheckAllReservations() {
        System.out.println("Please enter the day: YYYY-MM-DD");
        String line = in.nextLine();
        LocalDate date;
        if (!line.contentEquals("")) {
            String[] lineSplit = line.split("-");
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
    private void bookAReservation(){

    }
    private void checkYourReservations() {
        System.out.println("Enter your customer ID:");
        String id = in.nextLine();
        ArrayList<Object> yourReservations = new ArrayList<>();
        ArrayList<Restaurant> chosenRestaurants = new ArrayList<>();
        for (Restaurant restaurant: restaurants){
            for (Reservation reservation: restaurant.getReservations()){
                if (reservation.getCustomerID() == Integer.parseInt(id)){
                    yourReservations.add(reservation);
                    chosenRestaurants.add(restaurant);
                }
            }
        }
        if (!yourReservations.isEmpty()){
            System.out.println("You have the following reservations coming up!\n\tSelect the number to view");
            System.out.println("\tID\tCustomerID\tT\tSeats\tDate\tTime");
            Reservation reservation = (Reservation) choose(yourReservations);
            restaurants.get(param);
            System.out.println("You have selected Reservation " + reservation.getReservationID() + "\t in: " + restaurants.get(param));
            System.out.println("What would you like to do with this reservation?\n\t<1>\tView Details\n\t<2>\tChange Date\n\t<3>\tCancel Reservation\n");
            char opt = in.nextLine().charAt(0);
            switch (opt){
                case ('1'):
                    System.out.println("\tID\tCustomerID\tT\tSeats\tDate\tTime");
                    System.out.println(reservation);
                    break;
                case ('2'):
                    System.out.println(reservation.getDate() + " is the current date. What would you like to change it to? (YYYY-MM-DD)");
                    LocalDate temp = LocalDate.parse(in.nextLine());
                    System.out.println(("Changing date from " + reservation.getDate() + " to " + temp));
                    restaurants.get(param).editDate(temp, reservation);
                    System.out.println("Done!");
                    break;

                case ('3'):
                    System.out.println("Please enter the reservation ID to confirm cancellation");
                    String confirm = in.nextLine();
                    if (reservation.getReservationID().contentEquals(confirm)){
                        restaurants.get(param).cancelReservation(reservation);
                        System.out.println("Reservation cancelled");
                    }
                    break;
                default:
                    break;

            }
        } else System.out.println("You have no reservations coming up. Want to make one?");
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

    /**
     * A non-specific choice machine. An arraylist of any object can be turned into a selection through this
     * @param choices the arraylist to grab options from
     * @return Object to choose.
     */
    private Object choose(ArrayList<Object> choices){
        if (choices.isEmpty()) return null;
        while (true){
            int opt = 1;
            for (Object choice : choices){
                System.out.println(opt + ">\t" + choice.toString());
                opt++;
            }
            param = Integer.parseInt(in.nextLine()) - 1;
            if (0 <= param  && param < choices.size()){
                return choices.get(param);
            }
        }
    }
    private void SaveAll() throws RuntimeException{
        try (PrintWriter outRest = new PrintWriter("restaurants.csv")){
            for (Restaurant restaurant: restaurants){
                outRest.print(restaurant.toCSV());
                try (PrintWriter outResv = new PrintWriter(restaurant.getId().toLowerCase()+"_reservations.csv")){
                    for (Reservation reservation: restaurant.getReservations()){
                        outResv.print(reservation.toCSV());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
}
