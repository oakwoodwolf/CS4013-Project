package src;

import java.util.ArrayList;

public class Meal {

    private ArrayList<Item> items;

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


    public Meal(ArrayList<Item> items){
        this.items = items;
    }

}
