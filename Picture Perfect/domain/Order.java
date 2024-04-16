package domain;
import java.time.LocalTime;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;


public class Order { 
    @Expose private String orderId;
    @Expose private LocalDateTime orderDate;
    @Expose private LocalTime productionTime;
    @Expose private LocalDateTime pickUpDate;
    @Expose private double orderTotal;
    @Expose private Customer customer;
    @Expose private ShoppingBasket shoppingBasket;

    public Order(String orderId, LocalDateTime orderDate, LocalTime productionTime, LocalDateTime pickUpDate, double orderTotal, Customer customer, ShoppingBasket shoppingBasket) { //constructor
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.productionTime = productionTime;
        this.pickUpDate = pickUpDate;
        this.orderTotal = orderTotal; //make reference to calculation
        this.customer = customer;
        this.shoppingBasket = shoppingBasket;
    }

    public Order(Order source) { //constructor to modify
        this.orderId = source.orderId;
        this.orderDate = source.orderDate;
        this.productionTime = source.productionTime;
        this.pickUpDate = source.pickUpDate;
        this.orderTotal = source.orderTotal; 
        this.customer = source.customer;
        this.shoppingBasket = source.shoppingBasket;
    }

    public String getOrderId() { //getter Order ID
        return this.orderId;
    }

    public void setOrderId(String orderId) { //setter Order Id
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() { //getter order date
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) { //setter order date
        this.orderDate = orderDate;
    }

    public LocalTime getProductionTime() { //getter production time
        return this.productionTime;
    }

    public void setProductionTime(LocalTime productionTime) { //setter production time
        this.productionTime = productionTime;
    }

    public LocalDateTime getPickUpDate() { //getter pick up date
        return this.pickUpDate;
    }

    public void setPickUpDate(LocalDateTime pickUpDate) { //setter pick up date
        this.pickUpDate = pickUpDate;
    }

    public double getOrderTotal() { //getter order total
        return this.orderTotal;
    }

    public void setOrderTotal(double orderTotal) { //setter order total
        this.orderTotal = orderTotal;
    }

    public Customer getCustomer() { //getter customer
        return this.customer;
    }

    public void setCustomer(Customer customer) { //setter customer
        this.customer = customer;
    }

    public ShoppingBasket getShoppingBasket() { //getter shoppingbasket
        return this.shoppingBasket;
    }

    public void setShoppingBasket(ShoppingBasket shoppingBasket) { //setter shoppingbasket
        this.shoppingBasket = shoppingBasket;
    }

    

    @Override
    public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Order ID: ").append(orderId).append("\n");
    sb.append("Order Date: ").append(orderDate).append("\n");
    sb.append("Production Time: ").append(productionTime).append("\n");
    sb.append("Pick up date: ").append(pickUpDate).append("\n");
    sb.append("Order Total: ").append(orderTotal).append("\n");
    if (customer != null) {
        sb.append("Customer: ").append(customer.toString()).append("\n");
    } else {
        sb.append("Customer: ").append("Unknown").append("\n");
    }
    sb.append("Shopping Basket: ").append(shoppingBasket).append("\n");
    return sb.toString();
}


}
