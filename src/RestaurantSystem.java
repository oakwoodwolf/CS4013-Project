package src;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class RestaurantSystem {
    ArrayList<Restaurant> restaurants = new ArrayList<>();
    FileInputStream res = new FileInputStream("restaurants.csv");
    Scanner resScan = new Scanner(res);
    Scanner in;

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
        for (int i = 0; i < csvContents.size(); i++){
            String restaurantID = csvContents.get(i)[0];
            int tableNo = Integer.parseInt(csvContents.get(i)[1]);
            int capacity = Integer.parseInt(csvContents.get(i)[2]);
            if (!exists(restaurantID)){
                Restaurant res = new Restaurant(restaurantID, capacity);
                res.addTable(tableNo,capacity);
                restaurants.add(res);
            } else {
                for (int j = 0; j < restaurants.size(); j++){
                    if (restaurants.get(j).getId().contains(restaurantID)){
                        restaurants.get(j).addTable(tableNo, capacity);
                    }
                }
            }
        }
    }

    public void run()
    {
        boolean running = true;
        GregorianCalendar calendar = new GregorianCalendar();
        ArrayList<Restaurant> newlist = getRestaurants();
        while (running)
        {
            System.out.println("Enter the number of your desired option:");
            System.out.println("<1>: Search For Tables\t<2>: Make Reservation\t<3>: Exit");
            String command = in.nextLine();
            switch (command){
                case ("1") :
                    System.out.println(newlist);
                    break;
                case ("2") :
                    System.out.println("Not implemented yet");
                    break;
                case ("3") :
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
