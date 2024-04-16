package shopping;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import domain.Customer;
import domain.Item;
import domain.Order;
import domain.ShoppingBasket;

public class OrderProcessor {
    public static Order completeOrder(Customer customer, ShoppingBasket shoppingBasket) {
        //Generate orderId
        String orderId = UUID.randomUUID().toString();

        //Set orderDate to current date and time
        LocalDateTime orderDate = LocalDateTime.now(); 

        //Calculate productionTime
        LocalTime productionTime = findMaxWorkingHours(shoppingBasket);

        //Calculate pickUpDate
        LocalDateTime pickUpDate = calculatePickUpDate(orderDate, productionTime);

        //Calculate orderTotal
        double orderTotal = shoppingBasket.getTotalCost(); //refering to getTotalCost method in shoppingBasket class

        // Create a new Order object
        Order order = new Order(orderId, orderDate, productionTime, pickUpDate, orderTotal, customer, shoppingBasket);

        return order;
    }

    private static LocalTime findMaxWorkingHours(ShoppingBasket shoppingBasket) { //function to find max working hours from the basket
        // Initialize variables to hold the maximum working hours
        LocalTime maxWorkingHours = null;
    
        // Iterate through each item in the shopping basket
        for (Item item : shoppingBasket.getOrderItemList()) {
            LocalTime itemWorkingHours = item.getWorkingHours();
    
            // Update the maximum working hours if necessary
            if (maxWorkingHours == null || itemWorkingHours.compareTo(maxWorkingHours) > 0) {
                maxWorkingHours = itemWorkingHours;
            }
        }
    
        return maxWorkingHours;
    }

    private static LocalDateTime calculatePickUpDate(LocalDateTime orderDate, LocalTime productionTime) {
        LocalDateTime lastPickUpDate = getLastPickUpDateTime("business/invoice.json");
        LocalDateTime dateReady;

        if (lastPickUpDate.isAfter(orderDate)) { //checks if lastPickUpDate is after the orderDate
        dateReady = lastPickUpDate;
        } else {
        //dateReady calculates with productionTime what will be the LocalDateTime that the order is ready without considering the opening hours yet
        dateReady = orderDate.plusHours(productionTime.getHour()); 
        }

        IntRange pickupRange = new IntRange(0, 0); //referring to class that defines an IntRange
        switch (dateReady.getDayOfWeek()) { //switch checks day of the week and returns a time range
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY: pickupRange = new IntRange(9, 18); break;
            case FRIDAY: pickupRange = new IntRange(9, 21); break;
            case SATURDAY: pickupRange = new IntRange(9, 16); break;
            case SUNDAY: pickupRange = new IntRange(0, 0); break;
            default: break;
        }

        if (pickupRange.contains(dateReady.getHour())) { //if the time is in the range return the dateReady that was set before
            return dateReady;
        } else { //if not then it will add one day
            int comparisonResult = dateReady.getDayOfWeek().compareTo(DayOfWeek.SUNDAY); //here it checks if the dateReady is a Sunday
            boolean isItSunday = comparisonResult == 0;
    
            if (isItSunday) { //if dateReady is a Sunday it returns next day at 9:00
                dateReady = dateReady.plusDays(1);
                LocalDateTime pickupDateWithUpdatedTime = dateReady
                .withHour(9)
                .withMinute(0);
                return pickupDateWithUpdatedTime;
            }

            if (dateReady.getHour() < 9) { //if it is ready before opening time, it will return the same day at 9:00
                LocalDateTime pickupDateWithUpdatedTime = dateReady
                .withHour(9)
                .withMinute(0);
                return pickupDateWithUpdatedTime;
            }
            
            //if it is neither a Sunday nor the Pick up time is before 9, it creates a newDateReady plus one day at 9:00
            LocalDateTime newDateReady = dateReady.plusDays(1); 
            
            LocalDateTime pickupDateWithUpdatedTime = newDateReady
            .withHour(9)
            .withMinute(0);
            return pickupDateWithUpdatedTime;
        }
    }

    public static LocalDateTime getLastPickUpDateTime(String filePath) { //method to get the last pick up date and time from the order
    try {
            // Read orders from the JSON file
            List<Order> orders = JSONReader.readOrdersFromJSON(filePath);

            // If there are no orders in the file, return null
            if (orders.isEmpty()) {
                return null;
            }

            // Get the last order
            Order lastOrder = orders.get(orders.size() - 1);

            // Return the pick-up date and time from the last order
            return lastOrder.getPickUpDate();
    } finally {} 
} 

  
}