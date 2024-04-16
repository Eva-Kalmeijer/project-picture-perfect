package domain;

import com.google.gson.annotations.Expose;

public class Customer {
    
    @Expose private String customerId;
    @Expose private String customerName;
    @Expose private String emailAddress;
    @Expose private String phoneNumber;
    @Expose private Address address;


    public Customer(String customerId, String customerName, String emailAddress, String phoneNumber, Address address) { //constructor
        this.customerId = customerId;
        this.customerName = customerName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public Customer(Customer source) { //constructor for modifying
        this.customerId = source.customerId;
        this.customerName = source.customerName;
        this.emailAddress = source.emailAddress;
        this.phoneNumber = source.phoneNumber;
        this.address = source.address;
    }


    public String getCustomerId() { //getter customer ID
        return this.customerId;
    }

    public void setCustomerId(String customerId) { //setter customer ID
        this.customerId = customerId;
    }

    public String getCustomerName() { //getter customer name
        return this.customerName;
    }

    public void setCustomerName(String customerName) { //setter customer name
        this.customerName = customerName;
    }

    public String getEmailAddress() { //getter email address
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) { //setter email address
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() { //getter phone number
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) { //setter phone number
        this.phoneNumber = phoneNumber;
    }

    public Address getAddress() { //getter address
        return this.address;
    }

    public void setAddress(Address address) { //setter address
        this.address = address;
    }

    @Override
    public String toString() { //to string function
        return "Customer ID: " + customerId + "\nName: " + customerName + "\nEmail: " + emailAddress + "\nPhone Number:" + phoneNumber + "\nAddress:" + address.toString();
    }

}

