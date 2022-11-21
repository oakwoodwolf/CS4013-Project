package src;
import java.util.ArrayList;

public class Category {

    private String name;
    private boolean newItem;
    private ArrayList<Item> items = new ArrayList<>();


    public Category(String name){
        this.name = name;
    }


    public void makeMeal(String name, String side, String main, String drink, double price){
        Meal meal = new Meal(name, price, main, side, drink);
    }

    public String getName() {
        return name;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void addItem(String name, double price){
        Item newItem = new Item(name,price);
        System.out.println("\t\tAdding " + newItem.getName() + " to " + this.getName());
        items.add(newItem);
    }

    public void removeItem(){
        items.clear();
    }


}
