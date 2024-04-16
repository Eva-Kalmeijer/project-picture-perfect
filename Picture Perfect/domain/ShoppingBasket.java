package domain;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

import java.util.Iterator;

public class ShoppingBasket {
    @Expose private Customer customer;
    @Expose private List<Item> orderItemList;


    public ShoppingBasket(Customer customer) { //constructor
        this.orderItemList = new ArrayList<>();
        this.customer = customer;
    }
    
    public ShoppingBasket(ShoppingBasket source) { //constructor for modifying
        this.orderItemList = source.orderItemList;
    }
    public List<Item> getOrderItemList() { //getter item list for the whole list
        return this.orderItemList;
    }

    public Item getOrderItemList(int index) { //getter item list for individual items
        return new Item (orderItemList.get(index));
    }

    public void setOrderItemList(int index, Item item) { //setter item list
        this.orderItemList.set(index, new Item(item));
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addItemToShoppingBasket(Item item) { //add Item to shopping basket
        this.orderItemList.add(new Item(item));
    }


    public boolean removeItemFromShoppingBasket(String photoIdToRemove) { //function to remove Item from shopping basket, boolean because of construction in second function
        Iterator<Item> iterator = orderItemList.iterator(); //using Iterator
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item.getPhotoId().equals(photoIdToRemove)) { //checks if item in basket
                iterator.remove();
                System.out.println("Item removed from the Shopping Basket.");
                return true; // Exit the method after removing the item
            }
        }
        System.out.println("Item with the specified Photo ID not found in the Shopping Basket");
        return false;
    }

    public double getTotalCost() { //function to calculate total cost
        double totalCost = 0.0;
        for (Item item : orderItemList) { //each item in the order item list
            totalCost += item.getPrice(); //adds the price to total
        }
        return totalCost;
    }

    public String toString() { //to string function
        String temp = "";
        for (Item item : orderItemList) {
            temp += item.toString() + "\n";
        }
        return temp;

    }

}
