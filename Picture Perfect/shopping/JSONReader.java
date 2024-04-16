package shopping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import domain.Customer;
import domain.Order;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class JSONReader { //to read information from the JSON file

    public static List<Order> readOrdersFromJSON(String filePath) {
        try {
            Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
            
            File file = new File(filePath);

            // Check if the file is empty
            if (isFileEmpty(filePath)) {
                System.out.println("The JSON file is empty.");
                return null;
            }

            try (Reader reader = new FileReader(file)) {
                Type listType = new TypeToken<List<Order>>() {}.getType();
                List<Order> orders = gson.fromJson(reader, listType);
                return orders;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isFileEmpty(String filePath) {
        return filePath.length() == 0;
    }

    public static List<Order> searchOrdersByCustomerId(List<Order> orders, String customerId) { //searching by customerID
    List<Order> foundOrders = new ArrayList<>();
    for (Order order : orders) {
        Customer customer = order.getCustomer();
        if (customer != null && customer.getCustomerId().equals(customerId)) {
            foundOrders.add(order);
        }
        }
    return foundOrders;
    }


    public static Order searchOrderByOrderId(List<Order> orders, String orderId) { //searching by orderId
        for (Order order : orders) {
            if (order != null && order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null; // Order not found
    }
    public static List<Order> searchOrdersByCustomerName(List<Order> orders, String customerName) { //searching by customerName
        List<Order> matchingOrders = new ArrayList<>();
        for (Order order : orders) {
            Customer customer = order.getCustomer();
            if (customer != null && customer.getCustomerName().equalsIgnoreCase(customerName)) {
                matchingOrders.add(order);
            }
        }
        return matchingOrders;
    }

    /*public static List<Order> searchOrdersByOrderDate(List<Order> orders, LocalDateTime date) { //searching by orderDate, optional to implement
        List<Order> matchingOrders = new ArrayList<>();
        for (Order order : orders) {
            if (order.getOrderDate().equals(date)) {
                matchingOrders.add(order);
            }
        }
        return matchingOrders;
    } */
}

