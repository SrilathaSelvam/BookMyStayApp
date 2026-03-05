import java.util.HashMap;
import java.util.Map;

/**
 * UseCase3InventorySetup
 *
 * This class demonstrates centralized room inventory management
 * using a HashMap as a single source of truth.
 *
 * Version 3.1 – Refactored Inventory Management
 *
 * @author Sri
 * @version 3.1
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("        Welcome to Book My Stay");
        System.out.println("  Hotel Booking Management System v3.1");
        System.out.println("==============================================");

        // Initialize centralized inventory
        RoomInventory inventory = new RoomInventory();

        // Register room types with availability
        inventory.addRoomType("Single Room", 10);
        inventory.addRoomType("Double Room", 5);
        inventory.addRoomType("Suite Room", 2);

        // Display current inventory
        System.out.println("\nCurrent Room Inventory:");
        inventory.displayInventory();

        // Simulate update (e.g., booking 1 Single Room)
        System.out.println("\nUpdating Inventory: Booking 1 Single Room...");
        inventory.updateAvailability("Single Room", -1);

        // Display updated inventory
        System.out.println("\nUpdated Room Inventory:");
        inventory.displayInventory();

        System.out.println("\nApplication terminated successfully.");
    }
}


/**
 * RoomInventory
 *
 * Encapsulates all inventory-related operations.
 * Acts as a centralized manager for room availability.
 */
class RoomInventory {

    // Single source of truth
    private Map<String, Integer> availabilityMap;

    /**
     * Constructor initializes the HashMap.
     */
    public RoomInventory() {
        availabilityMap = new HashMap<>();
    }

    /**
     * Registers a new room type with initial availability.
     */
    public void addRoomType(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    /**
     * Retrieves availability for a specific room type.
     */
    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }

    /**
     * Updates availability in a controlled manner.
     * Positive value increases availability.
     * Negative value decreases availability.
     */
    public void updateAvailability(String roomType, int change) {
        int current = getAvailability(roomType);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("Error: Cannot reduce availability below zero.");
            return;
        }

        availabilityMap.put(roomType, updated);
    }

    /**
     * Displays the current inventory state.
     */
    public void displayInventory() {
        for (Map.Entry<String, Integer> entry : availabilityMap.entrySet()) {
            System.out.println(entry.getKey() + " -> Available Units: " + entry.getValue());
        }
    }
}