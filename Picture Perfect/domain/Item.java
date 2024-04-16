package domain;

import com.google.gson.annotations.Expose;

import java.time.LocalTime;

public class Item { //is this a parent class? probably not
    @Expose private String photoId;
    @Expose private String itemName;
    @Expose private double price;
    @Expose private LocalTime workingHours;


    public Item(String photoId, String itemName, double price, LocalTime workingHours) { //constructor
        this.photoId = photoId;
        this.itemName = itemName;
        this.price = price;
        this.workingHours = workingHours;
    }

    public Item(Item source) { //constructor for modifying
        this.photoId = source.photoId;
        this.itemName = source.itemName;
        this.price = source.price;
        this.workingHours = source.workingHours;
    }

    public String getPhotoId() { //getter photo ID
        return this.photoId;
    }

    public void setPhotoId(String photoId) { //setter phote ID
        this.photoId = photoId;
    }

    public String getItemName() { // getter Item name
        return this.itemName;
    }

    public void setItemName(String itemName) { //setter Item Name
        this.itemName = itemName;
    }

    public double getPrice() { //getter price
        return this.price;
    }

    public void setPrice(double price) { //setter price
        this.price = price;
    }

    public LocalTime getWorkingHours() { //getter working Hours
        return this.workingHours;
    }

    public void setWorkingHours(LocalTime workingHours) { //setter working Hours
        this.workingHours = workingHours;
    }

    public String toString() { //to string
        return this.photoId + "\t" + this.itemName + " \t$" + this.price + "\t\t " + this.workingHours + " hours";
    }  

}
