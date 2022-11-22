package src;

import java.util.ArrayList;

public class Order {
  ArrayList<Meal> orderedMeals;
  char status;

  public Order(ArrayList<Meal> orderedMeals){
    this.orderedMeals = orderedMeals;
  }

//  public Bill createBill(){
//
//    return new Bill(totalCost, "Card", 0);
//  }

  public double computeTotalCost(){
    double totalCost = 0;
    for (int i = 0; i < this.orderedMeals.size(); i++) {
      totalCost = totalCost + this.orderedMeals.get(i).computePrice();
    }
    return totalCost;
  }

  public void setStatus(char st){
    status = st;
  }

  public String toString(){
    StringBuilder meals = new StringBuilder();
    for (int i = 0; i < this.orderedMeals.size(); i++) {
      meals.append(this.orderedMeals.get(i).toString());
      meals.append("\n");
    }
    return "Order \n Status:" + status + "\n total price:" + computeTotalCost() + "\n Meals: \n" + meals;
  }
}