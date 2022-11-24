package src;
/**
 * A representation of the restaurant's menu.
 */

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Menu {


    //ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Category> items;

    /**
     * Creates a menu with the specified params.
     * @param items List of category objects
     * @throws FileNotFoundException
     */
    public Menu(ArrayList<Category> items) throws FileNotFoundException {
        this.items = items;
    }

    public Menu() {

    }

    /**
     *  Sets the item value
     * @param items an array list of items
     */
    public void setItems(ArrayList<Category> items) {
        this.items = items;
    }

    /**
     * Gets the items from the menu
     * @return A ArrayList of items
     */
    public ArrayList<Category> getItems() {
        return items;
    }

    /**
     * Gets the current state of the Menu object
     * @return String representation of the comma separated value
     */
    public String toCSV() {
        String temp = new String();
        for (Category item : items) {
            for (int j = 0; j < item.getItems().size(); j++) {
                temp = new String(temp + item.getName() + "," + item.getItems().get(j).getName() + "," + item.getItems().get(j).getPrice()+ "\n");
            }
        }
        return temp;
    }
    //    String path = "\"C:\\Users\\keith\\Downloads\\Menu.csv\"";
//    String line = "";
//
//    try{
//    BufferedReader reader = new BufferedReader(new FileReader(path));
//
//
//
//    }


}




