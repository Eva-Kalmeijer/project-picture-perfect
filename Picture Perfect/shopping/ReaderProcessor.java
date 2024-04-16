package shopping;

import java.util.List;
import java.util.Scanner;

import domain.Order;

public class ReaderProcessor {
    
public static void retrievingOrders() { //function for retrieving orders

    boolean continueSearchingOrders = true;
    Scanner scanner = new Scanner(System.in);

    while (continueSearchingOrders) {
        System.out.println("How would you like to search the order?");
        System.out.println("Type 'order ID', 'customer ID', 'customer name' or type 'all' to see all the orders");
        String searchMethod = scanner.nextLine();

    if (searchMethod.equalsIgnoreCase("order ID")) { //search by orderId
        System.out.println("Type the order ID");
        String requestedOrderId = scanner.nextLine();
        List<Order> orders = JSONReader.readOrdersFromJSON("business/invoice.json");
        Order foundOrder = JSONReader.searchOrderByOrderId(orders, requestedOrderId);
        if (foundOrder != null) {
            System.out.println("Found order: " + foundOrder);
        } else {
            System.out.println("No orders found for the provided order ID. Please try again."); //shouldn't here be a true
            continueSearchingOrders = false;
        }

    } else if (searchMethod.equalsIgnoreCase("customer ID")) { //search by customerId
        System.out.println("Type the customer ID");
        String requestedCustomerId = scanner.nextLine();
        List<Order> orders = JSONReader.readOrdersFromJSON("business/invoice.json");
        List<Order> customerOrders = JSONReader.searchOrdersByCustomerId(orders, requestedCustomerId);
        if (!customerOrders.isEmpty()) {
            for (Order order : customerOrders) {
            System.out.println(order);
            }
            continueSearchingOrders = false;
        } else {
        System.out.println("No orders found for the provided customer ID. Please try again.");
        }
        

    } else if (searchMethod.equalsIgnoreCase("customer name")) { //search by customerName
        System.out.println("Type the customer name");
        String requestedCustomerName = scanner.nextLine();
        List<Order> orders = JSONReader.readOrdersFromJSON("business/invoice.json");
        List<Order> ordersByCustomerName = JSONReader.searchOrdersByCustomerName(orders, requestedCustomerName);
        if (ordersByCustomerName.isEmpty()) {
            for (Order order : ordersByCustomerName) {
            System.out.println(order);    
            } 
            continueSearchingOrders = false;
        } else {
        System.out.println("No orders found for the provided customer name. Please try again.");  
        }
        
    } else if (searchMethod.equalsIgnoreCase("all")) { //search for all orders
        List<Order> orders = JSONReader.readOrdersFromJSON("business/invoice.json");
        if (!orders.isEmpty()) {
            for (Order order : orders) {
                System.out.println(order);
            }
            continueSearchingOrders = false;
        } else {
            System.out.println("No orders found.");
        }
        
    } else { // invalid option
        System.out.println("Invalid option, please try again");
        searchMethod = scanner.nextLine();
    }
    }
    scanner.close();

}
}
