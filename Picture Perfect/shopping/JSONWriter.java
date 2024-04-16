package shopping;

import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDateTime;
import java.time.LocalTime;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import domain.Order;


public class JSONWriter {

    public static void saveToJSON(Order order, String filePath) {
        
        // Create Gson instance
        Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
        .registerTypeAdapter(LocalTime.class, new LocalTimeAdapter())
        .setPrettyPrinting()
        .excludeFieldsWithoutExposeAnnotation()
        .create();
    
        // Convert object to JSON and write to file
        try (FileWriter writer = new FileWriter(filePath, true)) {
            if (JSONReader.isFileEmpty("business/invoice.json")) {
                writer.write("["); // Start of array if file is empty 
                writer.write(System.lineSeparator());

            } else {
                removeClosingBracket(filePath);  // Remove the closing bracket
                writer.write(","); // Add comma to separate from previous content
            }
            writer.write(gson.toJson(order)); // Write JSON data to file
            writer.write(System.lineSeparator()); // Add newline after each order
            System.out.println("Data saved to JSON file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void removeClosingBracket(String filePath) { // Method to remove the closing bracket from the JSON file
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            long length = file.length();
            if (length > 0) {
                file.setLength(length - 1); // Truncate the file by 1 byte
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeJSONFile(String filePath) {
        try (RandomAccessFile file = new RandomAccessFile(filePath, "rw")) {
            long length = file.length();
            if (length > 0) {
                file.seek(length - 1);
                byte lastByte = file.readByte();
                if (lastByte != ']') {
                    file.writeBytes("]"); // End of JSON array
                    System.out.println("JSON file closed successfully.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }
