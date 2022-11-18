package src;
import java.util.ArrayList;

public class Category {

    private String name;
    private String side;
    private String main;
    private String drink;

    private double price;

    private boolean newItem;


    //ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<ArrayList<Item>> items;

    //ArrayList<Meal> meals = new ArrayList<Meal>();
    private ArrayList<Meal> meals;
    private ArrayList<Item> item;

    public void makeMeal(String name, String side, String main, String drink){
        this.name = name;
        this.side = side;
        this.main = main;
        this.drink = drink;

    }

    public void addItem(String name, double price){
        Item newItem = new Item( name, price);
        item.add(newItem);
    }

    public void removeItem(){
        item.clear();
    }


}
