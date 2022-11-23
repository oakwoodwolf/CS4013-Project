package src;
/** Represents an Order.
 */
import java.util.ArrayList;

public class Order {
    ArrayList<Item> orderedMeals;
    char status;

    /** Creates an order with the specified meals.
     * @param orderedMeals List of meals.
     */
    public Order(ArrayList<Item> orderedMeals) {
        this.orderedMeals = orderedMeals;
    }

    /**
     * Gets the total cost of all the meals
     * @return A double representing the cost of all meals.
     */
    public double computeTotalCost() {
        double totalCost = 0;
        for (int i = 0; i < this.orderedMeals.size(); i++) {
            totalCost = totalCost + this.orderedMeals.get(i).getPrice();
        }
        return totalCost;
    }

    /**
     * Sets the status of the Order
     * @param st a char status
     */
    public void setStatus(char st) {
        status = st;
    }

    /**
     * Gets the current state of the Order object
     * @return A String representation of the state of the Order object
     */
    public String toString() {
        StringBuilder meals = new StringBuilder();
        for (int i = 0; i < this.orderedMeals.size(); i++) {
            meals.append(this.orderedMeals.get(i).toString());
            meals.append("\n");
        }
        return "Order \n Status:" + status + "\n total price:" + computeTotalCost() + "\n Meals: \n" + meals;
    }
}