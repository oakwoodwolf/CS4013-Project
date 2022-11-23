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




