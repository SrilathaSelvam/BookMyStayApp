import java.util.*;

/**
 * Use Case 6: Bogie Capacity Mapping
 * Version 6.0
 *
 * Demonstrates mapping between bogie type and its capacity
 * using HashMap (key–value structure).
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Train Bogie Capacity Mapping =====");

        // Step 1: Create HashMap
        HashMap<String, Integer> bogieMap = new HashMap<>();

        // Step 2: Insert key–value pairs using put()
        bogieMap.put("Sleeper", 72);
        bogieMap.put("AC Chair", 60);
        bogieMap.put("First Class", 40);

        // Step 3: Iterate using entrySet()
        System.out.println("\nBogie Capacity Details:\n");

        for (Map.Entry<String, Integer> entry : bogieMap.entrySet()) {
            System.out.println("Bogie: " + entry.getKey() +
                    " -> Capacity: " + entry.getValue());
        }

        System.out.println("\nProgram continues...");
    }
}