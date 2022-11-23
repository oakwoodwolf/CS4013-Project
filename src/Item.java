package src;

import java.util.ArrayList;

public class Item {

    private String name;
    private double price = 0.0;

    private ArrayList<Item> items;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double aPrice) {
        price = aPrice;
    }

    public void removeItem() {
        items.clear();
    }

    public String toString() {
        return name + "£" + price;
    }

}





