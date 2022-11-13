package src;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import static java.lang.System.exit;

public class Interface {
    RestaurantSystem system = new RestaurantSystem();


    public Interface() throws FileNotFoundException {

    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ArrayList<Restaurant> newlist = system.getRestaurants();

        boolean running = true;
        GregorianCalendar calendar = new GregorianCalendar();
        while (running){
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
}
