package domain;

import com.google.gson.annotations.Expose;

public class Address  { 
    
    @Expose private String addressLine;
    @Expose private String city;
    @Expose private String postalCode;


    public Address(String addressLine, String city, String postalCode) { //constructor
        this.addressLine = addressLine;
        this.city = city;
        this.postalCode = postalCode;
    }

    public Address(Address source) { //constructor source so they can modify
        this.addressLine = source.addressLine;
        this.city = source.city;
        this.postalCode = source.postalCode;
    } 

    public String getAddressLine() { //getter AddressLine
        return this.addressLine;
    }

    public void setAddressLine(String addressLine) { //setter AddressLine
        this.addressLine = addressLine;
    }

    public String getCity() { //getter City
        return this.city;
    }

    public void setCity(String city) { //setter City
        this.city = city;
    }

    public String getPostalCode() { //getter Postal Code
        return this.postalCode;
    }

    public void setPostalCode(String postalCode) { //setter postal code
        this.postalCode = postalCode;
    }

    @Override
    public String toString() { //to string function
        return "\nAddress line: " + addressLine + "\nCity: " + city + "\nPostal code: " + postalCode;
    }

}
