import domain.Address;
import domain.Customer;
import domain.Item;
import domain.ItemList;
import domain.OpeningHour;
import domain.OpeningHours;
import domain.Order;
import domain.ShoppingBasket;
import shopping.CustomerIDGenerator;
import shopping.JSONWriter;
import shopping.OrderProcessor;
import shopping.ReaderProcessor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;




public class Main {
    static ItemList catalog = new ItemList(); //calling new item list
    static OpeningHours schedule = new OpeningHours(); //calling new opening hours
    static CustomerIDGenerator customerIDGenerator = new CustomerIDGenerator(); //calling new random customer id
    static Address address = new Address("Shalomstraat 69", "Kuzec", "2500CM"); //calling new address
    static Customer customer = new Customer("105", "Andrey Bogatov", "andreybogatov@codeplswork.com", "696969", address); //calling new customer
    static ShoppingBasket shopping = new ShoppingBasket(customer); //calling new shopping basket
    static JSONWriter jsonWriter = new JSONWriter(); //calling new JSON writer
    static CustomerIDGenerator generator = new CustomerIDGenerator(); //calling new ID generator
    static OrderProcessor processor = new OrderProcessor(); //calling new order processor
    static Order order = new Order("2525", LocalDateTime.of(2024, 03, 27, 10, 30,00), LocalTime.of(15, 00, 00), LocalDateTime.of(2024, 03, 28, 10, 30,00), 90.00, customer, shopping); //calling new order

    public static void main(String[] args) {
        try {
            loadItemList("business/items.csv");
            loadOpeningHours("business/openinghours.csv");
            System.out.println("Hello, welcome to the Photo Store Picture Perfect!");
            printItemList();

            Scanner scanner = new Scanner(System.in);
            
            int userAnswer = getUserInput();
            optionMenu(userAnswer);
            
            scanner.close();
        
            
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void loadItemList(String fileName) throws FileNotFoundException { // function for loading the item list
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);

        while (scanFile.hasNextLine()) {
           String line = scanFile.nextLine();
           String[] words = line.split(";");
           catalog.addItem(new Item(words[0], words[1], Double.parseDouble(words[2]), LocalTime.parse(words[3])));
        }
        scanFile.close();
    } 

    public static void loadOpeningHours(String fileName) throws FileNotFoundException { // function for loading the opening Hours
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanFile = new Scanner(fis);

        while (scanFile.hasNextLine()) {
           String line = scanFile.nextLine();
           String[] words = line.split(";");
           schedule.addOpeningHour(new OpeningHour(Integer.parseInt(words[0]), DayOfWeek.valueOf(words[1].toUpperCase()), LocalTime.parse(words[2]), LocalTime.parse(words[3])));
        }
        scanFile.close();
    } 

    public static int getUserInput() { //function for the user input used in option menu
        int userAnswer = 0;
        Scanner scanner = new Scanner(System.in);
        while (userAnswer > 4 || userAnswer < 1) {
            System.out.println( "Where can I help you with? Type one of the following numbers:");
            System.out.println("1. Create an order");
            System.out.println("2. Review existing orders");
            System.out.println("3. Review opening times");
            System.out.println("4. Exit");
            userAnswer = scanner.nextInt(); 
             
        }     
        return userAnswer;
    }

    public static void optionMenu(int userAnswer) { //function for the switch with menu options with menu options in switch
        Scanner scanner = new Scanner(System.in);
        switch (userAnswer) {
            case 1: //refering to function to make a new order and after completion starts with adding customer info
            newOrder(shopping, catalog, processor, jsonWriter); 
                break;
            case 2:  //refering to function that let's user retrieve old orders
            ReaderProcessor.retrievingOrders();
            case 3: //prints the opening hours
            System.out.println("The Opening Hours are:");
            System.out.println(schedule);
            break;
            case 4: //closes the program
            System.out.println("Thank you for your visit!");
            System.exit(0);
            default: System.out.println("This is not a valid option. Please try again");
                break;
        }
        scanner.close();
    } 

    public static void newOrder(ShoppingBasket shoppingBasket, ItemList itemList, OrderProcessor orderProcessor, JSONWriter jsonWriter) { //function for a new order
        Scanner scanner = new Scanner(System.in); 
        boolean continueAddingItems = true;

        try{
        while (continueAddingItems) { //asks user to either add or delete an item or complete the order
            System.out.println("Type the Photo ID to add it to the Shopping Basket, 'remove' to remove an item or type 'complete' to finish the order:");
            
            String userInput = scanner.nextLine();
            
            if (userInput.equalsIgnoreCase("complete")) {
                addCustomerInfo(generator, shopping, customer, address); //refering to function to add customer info
                Order order = orderProcessor.completeOrder(customer, shopping); // Call completeOrder() method of OrderProcessor
                System.out.println(order);
                JSONWriter.saveToJSON(order, "business/invoice.json"); // Save shopping basket and customer to JSON file
                JSONWriter.closeJSONFile("business/invoice.json");
                continueAddingItems = false; // Exit the loop
                } else if(userInput.equalsIgnoreCase("remove")) { //refering to function that will remove an item from shopping basket
                    removeItemFromShoppingBasket(scanner, shoppingBasket);
                } else {
                addItemToShoppingBasket(userInput, shoppingBasket, itemList);  //refering to function that will add an item to shopping basket
            }
        }
    } finally {
        scanner.close();
    }
    }
    
    private static void addItemToShoppingBasket(String userInput, ShoppingBasket shoppingBasket, ItemList itemList) { //function to add to shopping basket
        try {
            int itemIdentifier = Integer.parseInt(userInput); //makes an int from user input
            Item itemToAdd = null;

        for (Item currentItem : itemList.getAllItems()) { //to add the item of the same id in the shopping basket
            if (currentItem.getPhotoId().equals(String.valueOf(itemIdentifier))) {
            itemToAdd = currentItem;
            break;
        }
        }

        if (itemToAdd != null) {
            shoppingBasket.addItemToShoppingBasket(itemToAdd); //add the item to shopping basket
            System.out.println("Item added to the Shopping Basket.");
        } else {
            System.out.println("Item with the specified Photo ID not found."); //not found so again back to new order
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid Photo ID or 'complete'."); //exception
        }
    }

    private static void removeItemFromShoppingBasket(Scanner scanner, ShoppingBasket shoppingBasket) {
        System.out.println("Enter the Photo ID of the item you want to remove:");
        String photoIdToRemove = scanner.nextLine();
        boolean removed = shoppingBasket.removeItemFromShoppingBasket(photoIdToRemove); 
 
        // Call a method to remove the item from the shopping basket
        if (removed) {
            System.out.println("Item removed from the Shopping Basket.");
        } else {
            System.out.println("Item with the specified Photo ID not found in the Shopping Basket.");
        }
    }


    public static void addCustomerInfo(CustomerIDGenerator customerIDGenerator, ShoppingBasket shoppingBasket, Customer customer, Address address) { //function to add customer info and address info
        
        Scanner scanner = new Scanner(System.in);
        String newCustomerId = CustomerIDGenerator.generateCustomerID(); //should generate a new customer ID
        customer.setCustomerId(newCustomerId); //refering to setter

        System.out.println("Enter customer name:"); //customer name
        String newCustomerName = scanner.nextLine();
        customer.setCustomerName(newCustomerName); //refering to setter
        while (newCustomerName.isBlank()) { //checks if name is not blank
            System.out.println("Sorry that is an invalid name. Please try again");  
            newCustomerName = scanner.nextLine();
        }
        customer.setCustomerName(newCustomerName); //refering to setter
        
        System.out.println("Enter email address:"); //emailadress
        String newEmailAddress = scanner.nextLine();
        while (newEmailAddress.isBlank()) {
            System.out.println("Sorry that is an invalid email address. Please try again");
            newEmailAddress = scanner.nextLine();
        }
        customer.setEmailAddress(newEmailAddress);

        System.out.println("Enter phone number:"); //phone number
        String newPhoneNumber = scanner.nextLine();
        while (newPhoneNumber.isBlank()) {
            System.out.println("Sorry that is an invalid phone number. Please try again");
            newPhoneNumber = scanner.nextLine();
        }
        customer.setPhoneNumber(newPhoneNumber);

        System.out.println("Enter address line:"); //address line
        String newAddressLine = scanner.nextLine();
        while (newAddressLine.isBlank()) {
            System.out.println("Sorry that is an invalid address line. Please try again");
            newAddressLine = scanner.nextLine();
        }
        address.setAddressLine(newAddressLine);

        System.out.println("Enter city:"); //city
        String newCity = scanner.nextLine();
        while (newCity.isBlank()) {
            System.out.println("Sorry that is an invalid city. Please try again");
            newCity = scanner.nextLine();
        }
        address.setCity(newCity);

        System.out.println("Enter postal code:"); //postal code
        String newPostalCode = scanner.nextLine();
        while (newPostalCode.isBlank()) {
            System.out.println("Sorry that is an invalid postal code. Please try again");
            newPostalCode = scanner.nextLine();
        }
        address.setPostalCode(newPostalCode);
        System.out.println("Customer information added to the order."); //to add to order
        scanner.close();
    }
    

    public static void printItemList() { //print itemlist
        System.out.println("********************************PHOTO STORE PICTURE PERFECT*******************************");
        System.out.println("Photo ID \t Item name \t\t Price \t Working Hours ");
        System.out.println(catalog);
    }
}
