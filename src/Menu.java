package src;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Menu {


    //ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Category> items;

    public Menu(ArrayList<Category> items) throws FileNotFoundException {
        this.items = items;
    }

    public Menu() {

    }

    public void setItems(ArrayList<Category> items) {
        this.items = items;
    }

    public ArrayList<Category> getItems() {
        return items;
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




