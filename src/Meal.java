package src;

import java.util.ArrayList;

public class Meal extends Item {

    private ArrayList<Item> items = new ArrayList<>();

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }


    public Meal(String name, double price, Item main, Item side, Item drink) {
        super(name, price);
        items.add(main);
        items.add(side);
        items.add(drink);
    }

    public Meal(String name, double price, String main, String side, String drink) {
        super(name, price);
        Item mainItem = new Item(main, price);
        items.add(mainItem);
        Item sideItem = new Item(side, price);
        items.add(sideItem);
        Item drinkItem = new Item(drink, price);
        items.add(drinkItem);
    }

    public double computePrice() {
        double price = 0;
        for (Item item : this.items) {
            price += item.getPrice();
        }
        return price;
    }

    public String toString() {
        StringBuilder items = new StringBuilder();
        for (int i = 0; i < this.items.size(); i++) {
            items.append(this.items.get(i).toString()).append("\n");
        }
        return items.toString();
    }

}
