package src;

import java.util.ArrayList;

public class Order {
  ArrayList<Item> orderedMeals = new ArrayList<Item>();
  double totalCost;
  char status;

  public Bill createBill(){
    
  }

  public double getTotalCost(){
    return totalCost;
  }

  public void setStatus(char st){
    status = st;
  }

  public String toString(){
  }
}