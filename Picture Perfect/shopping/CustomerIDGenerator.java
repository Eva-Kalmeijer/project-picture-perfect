package shopping;
import java.util.Random;

public class CustomerIDGenerator {
    private static final int ID_LENGTH = 6; // Length of the generated customer ID

    public static String generateCustomerID() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // Generate random digits to form the customer ID
        for (int i = 0; i < ID_LENGTH; i++) {
            int digit = random.nextInt(10); // Generate a random digit (0-9)
            sb.append(digit);
        }

        return sb.toString();
}
}