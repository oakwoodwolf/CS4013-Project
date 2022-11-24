package src;
/**
 * Represents a Category in menu
 */

import java.util.ArrayList;

public class Category {

    private final String name;
    private boolean newItem;
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Creates a category with specified params.
     * @param name name of the item
     */
    public Category(String name) {
        this.name = name;
    }

    /**
     * Creates a meal with specified params.
     * @param name name of the item
     * @param side side item
     * @param main main item
     * @param drink drink item
     * @param price cost of the item
     */
    public void makeMeal(String name, String side, String main, String drink, double price) {
        Meal meal = new Meal(name, price, main, side, drink);
    }

    /**
     * Uses name to get the name of the item
     * @return A String representing the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the item value
     * @param items An Arraylist of items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     * This adds items to the order
     * @param name sets item of specified name
     * @param price sets item of specified price
     */
    public void addItem(String name, double price) {
        Item newItem = new Item(name, price);
        System.out.println("\t\tAdding " + newItem.getName() + " to " + this.getName());
        items.add(newItem);
    }

    /**
     * This removes items from the order
     */
    public void removeItem() {
        items.clear();
    }

    /**
     * Gets the current state of Category object
     * @return A String representation of the state of the Category object
     */
    public String toString(){
        return "Category: " + name + " " + newItem;
    }

    /**
     * Gets the items from the menu
     * @return An Arraylist of items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
}
