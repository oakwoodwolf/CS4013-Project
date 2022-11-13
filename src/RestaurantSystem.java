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
    public RestaurantSystem() throws FileNotFoundException {
        ArrayList<String[]> csvContents = new ArrayList<>();
        //Load CSV Contents
        while(resScan.hasNextLine()){
            String temp = resScan.nextLine();
            System.out.println(temp);
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
                    if (restaurants.get(i).getId().contains(restaurantID)){
                        restaurants.get(i).addTable(tableNo, capacity);
                    }
                }
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
