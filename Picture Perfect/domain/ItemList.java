package domain;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


public class ItemList {
    
    @Expose private List<Item> catalogItemList;


    public ItemList() { //constructor
        this.catalogItemList = new ArrayList<>();
    }
    public ItemList (ItemList source) {
        this.catalogItemList = source.catalogItemList;
    }

    public Item getItem(int index) { //getter for item
        return new Item(catalogItemList.get(index));
    }

    public void setItem(int index, Item item) { //setter for item
        this.catalogItemList.set(index, new Item(item));
    }

    public void addItem(Item item) { //add item to Item list
        this.catalogItemList.add(new Item(item));
    }

    public String toString() { //to string function
        String temp = "";
        for (Item item : catalogItemList) {
            temp += item.toString() + "\n";
        }
        return temp;
    }
   
    public List<Item> getAllItems() { //get all Items to be able to use forEach
        return catalogItemList;
    }
    
}
