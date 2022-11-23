package src;
/** Represents an Item in menu.
 */
import java.util.ArrayList;

public class Item {

    private String name;
    private double price = 0.0;

    private ArrayList<Item> items;

    /** Creates an item with the specified params.
     * @param name name of the item.
     * @param price cost of the item
     */
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Gets the name of the item
     * @return A String representing the name of item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item
     * @param aName name of the item
     */
    public void setName(String aName) {
        this.name = aName;
    }

    /**
     * Gets the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the item
     */
    public void setPrice(double aPrice) {
        price = aPrice;
    }

    /**
     * Removes the item
     */
    public void removeItem() {
        items.clear();
    }

    /**
     * Gets the current state of the Item object
     * @return A String representation of the state of the Item object
     */
    public String toString() {
        return name + " £" + price;
    }

}





