package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

public class Menu {


    //ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Category> items;

    public Menu(ArrayList<Category> items) throws FileNotFoundException {
        this.items = items;
    }
    public Menu(){

    }

    public void setItems(ArrayList<Category> items) {
        this.items = items;
    }
    //    String path = "\"C:\\Users\\keith\\Downloads\\Menu.csv\"";
//    String line = "";
//
//    try{
//    BufferedReader reader = new BufferedReader(new FileReader(path));
//
//
//
//    }



    }




