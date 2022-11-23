package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
     * This starts up the Restaurant system, loading the csv files and creating classes from them
     *
     * @throws FileNotFoundException if the .csv file cannot be found.
     */
    public RestaurantSystem() throws FileNotFoundException {
        in = new Scanner(System.in);
        ArrayList<String[]> csvContents = new ArrayList<>();
        //Load CSV Contents
        LoadRestaurants(csvContents);
        for (Restaurant restaurant : restaurants) {
            LoadReservations(csvContents, restaurant);
            LoadMenu(csvContents, restaurant);
            LoadRecords(csvContents, restaurant);
        }
    }

    /**
     * This loads <b>all</b> the restaurants, its table and its capacity.
     *
     * @param csvContents the ArrayList used for loading CSV files.
     */
    private void LoadRestaurants(ArrayList<String[]> csvContents) {
        System.out.println("Loading Restaurants");
        while (resScan.hasNextLine()) {
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
     *
     * @param csvContents the arrayList used to store the lines from the CSV
     * @param restaurant  the restaurant the reservation is for
     */
    private void LoadReservations(ArrayList<String[]> csvContents, Restaurant restaurant) throws FileNotFoundException {

        System.out.println("Loading Reservation for " + restaurant);
        csvContents.clear();
        resV = new FileInputStream(restaurant.getId().toLowerCase() + "_reservations.csv");
        Scanner resVScan = new Scanner(resV);
        while (resVScan.hasNextLine()) {
            String temp = resVScan.nextLine();
            //System.out.println(temp);
            String[] splitter = temp.split(",");
            csvContents.add(splitter);
        }

        for (String[] csvContent : csvContents) {
            String reservationID = csvContent[0];
            int numberOfPeople = Integer.parseInt(csvContent[1]);
            String[] dateTemp = csvContent[2].split("-");
            LocalDate date = LocalDate.of((Integer.parseInt(dateTemp[0])), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]));
            String[] timeTemp = csvContent[3].split(":");
            String minutesTemp = timeTemp[1].substring(0, 1);
            LocalTime time = LocalTime.of(Integer.parseInt(timeTemp[0]), Integer.parseInt(minutesTemp));
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
     * This loads the <b>Income Records</b> for the Restaurant
     * @param csvContents Arraylist for CSV loading
     * @param restaurant the restaurant to load the CSV for
     * @throws FileNotFoundException If the CSV cannot be found
     */
    private void LoadRecords(ArrayList<String[]> csvContents, Restaurant restaurant) throws FileNotFoundException {

        System.out.println("Loading Records for " + restaurant);
        csvContents.clear();
        ArrayList<Bill> bills = new ArrayList<>();
        resV = new FileInputStream(restaurant.getId().toLowerCase() + "_income.csv");
        Scanner resVScan = new Scanner(resV);
        while (resVScan.hasNextLine()) {
            String temp = resVScan.nextLine();
            //System.out.println(temp);
            String[] splitter = temp.split(",");
            csvContents.add(splitter);
        }

        for (String[] csvContent : csvContents) {
            String[] dateTemp = csvContent[0].split("-");
            LocalDate date = LocalDate.of((Integer.parseInt(dateTemp[0])), Integer.parseInt(dateTemp[1]), Integer.parseInt(dateTemp[2]));
            String[] timeTemp = csvContent[1].split(":");
            String minutesTemp = timeTemp[1].substring(0, 1);
            LocalTime time = LocalTime.of(Integer.parseInt(timeTemp[0]), Integer.parseInt(minutesTemp));
            int customerID = Integer.parseInt(csvContent[2]);
            int billID = Integer.parseInt(csvContent[3]);
            double price = Double.parseDouble(csvContent[4]);
            String paymentMethod = csvContent[5];
            double tip = Double.parseDouble(csvContent[6]);

            Bill bill = new Bill(price, paymentMethod, tip, date, time, customerID, billID);
            bills.add(bill);
            System.out.println("Loaded Bill:\n" + bill.toString());

        }
        IncomeRecords incomeRecords = new IncomeRecords(bills);
        restaurant.setRecords(incomeRecords);
        System.out.println("Loaded");
        resVScan.close();
    }

    /**
     * This loads the menu, categories and items of the restaurant.
     *
     * @param csvContents
     * @param restaurant  the restaurant the menu is for
     * @throws FileNotFoundException
     */
    private void LoadMenu(ArrayList<String[]> csvContents, Restaurant restaurant) throws FileNotFoundException {

        System.out.println("Loading Menu for " + restaurant);
        csvContents.clear();
        ArrayList<Category> categories = new ArrayList<>();
        Menu menu = new Menu();
        resV = new FileInputStream(restaurant.getId().toLowerCase() + "_menu.csv");
        Scanner resVScan = new Scanner(resV);
        while (resVScan.hasNextLine()) {
            String temp = resVScan.nextLine();
            //System.out.println(temp);
            String[] splitter = temp.split(",");
            csvContents.add(splitter);
        }

        for (String[] csvContent : csvContents) {
            String category = csvContent[0];
            String item = csvContent[1];
            double price = Double.parseDouble(csvContent[2]);
            if (csvContent.length > 3) {
                String content1 = csvContent[3];
                String content2 = csvContent[4];
                String content3 = csvContent[5];
            }

            if (!exists(category, categories)) {
                categories.add(new Category(category));
                System.out.println("\tCreated new category " + category);
            }

            for (Category category1 : categories) {
                if (category1.getName().contentEquals(category)) {
                    category1.addItem(item, price);
                }

            }
            menu.setItems(categories);
            restaurant.setMenu(menu);
        }
        System.out.println("Loaded");
        resVScan.close();
    }

    /**
     * This runs the menu of the program, serving as the text interface
     */
    public void run() throws StringIndexOutOfBoundsException {
        boolean running = true;
        while (running) {
            System.out.println("\nEnter the number of your desired option:");
            System.out.println("\t<1>: Book a Reservation\n\t<2>: Check Reservations\n\t<3>: Your Reservations\n\t<4>: Staff Options");
            String command = String.valueOf(in.nextLine().charAt(0));
            switch (command) {
                case ("1") -> bookAReservation();
                case ("2") -> CheckAllReservations();
                case ("3") -> checkYourReservations();
                case ("4") -> staffOptions();
                default -> running = exitMenu();
            }
        }
    }

    /**
     * Exits the menu if any option that isn't shown is selected
     *
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
    private void staffOptions(){
        System.out.println("Select your branch:");
        Restaurant restaurant = chooseRestaurants(restaurants);
        System.out.println("What would you like to do?:\n\t<1>: Check Income Records\n\t<2>: Add Menu Item\n");
        String command = String.valueOf(in.nextLine().charAt(0));
        switch (command) {
            //case ("1") -> CheckRecords();
            case ("2") -> AddItem(restaurant);
            default -> {
                break;
            }
        }
    }
    public void AddItem(Restaurant restaurant){
        Menu menu = restaurant.getMenu();
        System.out.println("Choose a category for the item you are adding");
        Category category = chooseCategory(menu.getItems());
        System.out.println("Insert a name: E.g. Large Fries");
        String name = in.nextLine();
        System.out.println("Insert the price: E.g. 19.46");
        double price = Double.parseDouble(in.nextLine());
        category.addItem(name, price);
        System.out.println("Done!");

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
            if (!currentReservations.isEmpty()) {
                System.out.println(restaurant.getId() + ": " + currentReservations);
            }
        }
    }

    /**
     * This code lets you select a restaurant and date and book a reservation.
     */
    private void bookAReservation() {
        System.out.println("Select a Restaurant");
        Restaurant restaurant = chooseRestaurants(restaurants);
        System.out.println("Choose a date you want to book for: YYYY-MM-DD");
        LocalDate date = LocalDate.parse(in.nextLine());
        Table table = chooseTable(restaurant.pullTables(true));
        System.out.println("Please supply the following information:\n\tNumber of People\tTime\t");
        System.out.println("Example: 2 15:30");
        String info = in.nextLine();
        String[] bits = info.split(" ");
        int numberofPeople = Integer.parseInt(bits[0]);
        if (numberofPeople > table.getSeats()) {
            System.out.println("You have too many people. Please consider booking seperately.");
        } else {
            LocalTime time = LocalTime.parse(bits[1]);
            int temp = (restaurant.getReservations().size() + 1);
            String id = Integer.toString(temp);
            Reservation reserve = new Reservation(id, (int) ((Math.random() * 90) + 1000), numberofPeople, table.getTableNo(), date, time);
            System.out.println("Are you okay with this booking?\n" + reserve + "\n<Y>\t\t\t<N>");
            String option = in.nextLine();
            if (option.contentEquals("Y")) {
                restaurant.setReservations(reserve);
                System.out.println("Your booking has been confirmed!\tReservation ID:\t\t\t" + reserve.getReservationID());
            } else System.out.println("Your booking has been cancelled");
        }

    }

    private void checkYourReservations() {
        System.out.println("Enter your customer ID:");
        String id = in.nextLine();
        ArrayList<Object> yourReservations = new ArrayList<>();
        for (Restaurant restaurant : restaurants) {
            for (Reservation reservation : restaurant.getReservations()) {
                if (reservation.getCustomerID() == Integer.parseInt(id)) {
                    yourReservations.add(reservation);
                }
            }
        }
        if (!yourReservations.isEmpty()) {
            System.out.println("You have the following reservations coming up!\n\tSelect the number to view");
            System.out.println("\tID\tCustomerID\tT\tSeats\tDate\tTime");
            Reservation reservation = (Reservation) choose(yourReservations);
            System.out.println("You have selected Reservation " + reservation.getReservationID() + "\t in: " + restaurants.get(param));
            System.out.println("What would you like to do with this reservation?\n\t<1>\tView Details\n\t<2>\tChange Date\n\t<3>\tCancel Reservation\n\t<4>\tMake Order\n");
            char opt = in.nextLine().charAt(0);
            switch (opt) {
                case ('1') -> {
                    System.out.println("\tID\tCustomerID\tT\tSeats\tDate\tTime");
                    System.out.println(reservation);
                }
                case ('2') -> {
                    System.out.println(reservation.getDate() + " is the current date. What would you like to change it to? (YYYY-MM-DD)");
                    LocalDate temp = LocalDate.parse(in.nextLine());
                    System.out.println(("Changing date from " + reservation.getDate() + " to " + temp));
                    restaurants.get(param).editDate(temp, reservation);
                    System.out.println("Done!");
                }
                case ('3') -> {
                    System.out.println("Please enter the reservation ID to confirm cancellation");
                    String confirm = in.nextLine();
                    if (reservation.getReservationID().contentEquals(confirm)) {
                        restaurants.get(param).cancelReservation(reservation);
                        System.out.println("Reservation cancelled");
                    }
                }
                case ('4') -> {
                    Restaurant activeBranch = new Restaurant();
                    for (int i = 0; i < restaurants.size(); i++) {
                        for (Reservation reservation1 : restaurants.get(i).getReservations()) {
                            if (reservation1.getCustomerID() == reservation.getCustomerID()) {
                                activeBranch = restaurants.get(i);
                                break;
                            }
                        }
                    }
                    makeOrder(activeBranch, reservation);
                }
                default -> {
                }
            }
        } else System.out.println("You have no reservations coming up. Want to make one?");
    }

    /**
     * Checks if something containing this string exists
     *
     * @param str the string to compare
     * @return boolean whether it exists or not
     */
    public boolean exists(String str) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().contains(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean exists(String str, ArrayList<Category> categories) {
        for (Category category : categories) {
            if (category.getName().contains(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * A non-specific choice machine. An arraylist of any object should be turned into a selection through this.
     * Unfortunately it doesn't work so there is like five dupes of this.
     *
     * @param choices the arraylist to grab options from
     * @return Object to choose.
     */
    private Object choose(ArrayList<Object> choices) {
        if (choices.isEmpty()) return null;
        while (true) {
            int opt = 1;
            for (Object choice : choices) {
                System.out.println(opt + ">\t" + choice.toString());
                opt++;
            }
            param = Integer.parseInt(in.nextLine()) - 1;
            if (0 <= param && param < choices.size()) {
                return choices.get(param);
            }
        }
    }
    /**
     * A non-specific choice machine. An arraylist of any object can be turned into a selection through this
     *
     * @param choices the arraylist to grab options from
     * @return Object to choose.
     */
    private Category chooseCategory(ArrayList<Category> choices) {
        if (choices.isEmpty()) return null;
        while (true) {
            int opt = 1;
            for (Object choice : choices) {
                System.out.println(opt + ">\t" + choice.toString());
                opt++;
            }
            param = Integer.parseInt(in.nextLine()) - 1;
            if (0 <= param && param < choices.size()) {
                return choices.get(param);
            }
        }
    }
    /**
     * A non-specific choice machine. An arraylist of any object can be turned into a selection through this
     *
     * @param choices the arraylist to grab options from
     * @return Item to choose.
     */
    private Item chooseItem(ArrayList<Item> choices) {
        if (choices.isEmpty()) return null;
        while (true) {
            int opt = 1;
            for (Object choice : choices) {
                System.out.println(opt + ">\t" + choice.toString());
                opt++;
            }
            param = Integer.parseInt(in.nextLine()) - 1;
            if (0 <= param && param < choices.size()) {
                return choices.get(param);
            }
        }
    }


    private Table chooseTable(ArrayList<Table> choices) {
        if (choices.isEmpty()) return null;
        while (true) {
            int opt = 1;
            for (Object choice : choices) {
                System.out.println(opt + ">\t" + choice.toString());
                opt++;
            }
            param = Integer.parseInt(in.nextLine()) - 1;
            if (0 <= param && param < choices.size()) {
                return choices.get(param);
            }
        }
    }

    /**
     * @param choices the arraylist to choose from
     * @return the chosen restaurant
     * @see Object choose(ArrayList<Object> choices)
     */
    private Restaurant chooseRestaurants(ArrayList<Restaurant> choices) {
        if (choices.isEmpty()) return null;
        while (true) {
            int opt = 1;
            for (Object choice : choices) {
                System.out.println(opt + ">\t" + choice.toString());
                opt++;
            }
            param = Integer.parseInt(in.nextLine()) - 1;
            if (0 <= param && param < choices.size()) {
                return choices.get(param);
            }
        }
    }

    /**
     * This is how customers order their meal after viewing their reservation. The parameters are autofill in context
     * @param rest the Restaurant they are ordering from
     * @param resv their Reservation
     */
    public void makeOrder(Restaurant rest, Reservation resv) {
        Menu menu = rest.getMenu();
        ArrayList<Item> itemsToOrder = new ArrayList<>();
        Double currentPrice = 0.0;
        int index = 0;
        while (index < resv.NoOfPeople){
            for (int i = 0; i < resv.NoOfPeople; i++){
                System.out.println("__________Menu:_________");
                Category chosenCategory = chooseCategory(menu.getItems());
                System.out.println(chosenCategory.getName() + "\t$" + currentPrice);
                Item order = chooseItem(chosenCategory.getItems());
                currentPrice += order.getPrice();
                itemsToOrder.add(order);
                index++;
            }
        }
        System.out.println("Thank you for Ordering! Finalizing Order");
        Order order = new Order(itemsToOrder);
        System.out.printf("Thank you for ordering! That will be: %6.2f%n", order.computeTotalCost());
        System.out.println(order);
        System.out.println("How would you like to pay?\t <1>: Cash\t <2>: Card");
        String paymentMethod = new String("");
        String command = String.valueOf(in.nextLine().charAt(0));
        switch (command) {
            case ("1") -> paymentMethod = new String("Cash");
            case ("2") -> paymentMethod = new String("Card");
            default -> {
                System.out.println("You have to pay, you know!");
                return;
            }
        }
        System.out.println("Please enter the cost to confirm you can pay:");
        Double temp = Double.parseDouble(in.nextLine());
        Double tip = 0.0;
        if (temp == order.computeTotalCost()){
            System.out.println("Do you want to tip?\t\tY\tN");
            if (in.nextLine().toUpperCase().contentEquals("Y")){
                System.out.println("How much do you want to tip?");
                tip = Double.parseDouble(in.nextLine());
            }
            Bill bill = new Bill(order.computeTotalCost(), paymentMethod, tip);
            System.out.println("Bill printed:\n" + bill.toString());
        } else return;
        System.out.println("Thank you for Ordering, enjoy your food!\n");
        //ArrayList<Object> menuObjects = (ArrayList<Object>) menu.getItems();

    }

    private void SaveAll() throws RuntimeException {
        try (PrintWriter outRest = new PrintWriter("restaurants.csv")) {
            for (Restaurant restaurant : restaurants) {
                outRest.print(restaurant.toCSV());
                try (PrintWriter outResv = new PrintWriter(restaurant.getId().toLowerCase() + "_reservations.csv")) {
                    for (Reservation reservation : restaurant.getReservations()) {
                        outResv.print(reservation.toCSV());
                    }
                }
                try (PrintWriter outResv = new PrintWriter(restaurant.getId().toLowerCase() + "_menu.csv")) {
                    outResv.print(restaurant.getMenu().toCSV());
                }
                try (PrintWriter outResv = new PrintWriter(restaurant.getId().toLowerCase() + "_income.csv")) {
                    outResv.print(restaurant.getRecords().toCSV());
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    /**
     * This is the program's starting code.
     * @throws IOException In case the Scanner receives an unknown input
     */
    public static void main(String[] args) throws IOException {
        RestaurantSystem system = new RestaurantSystem();
        system.run();
    }
}
