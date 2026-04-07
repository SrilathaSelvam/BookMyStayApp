import java.util.*;

/**
 * Use Case 4: Room Search & Availability Check
 * Version 4.0
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v4.0 =====");

        // Step 1: Initialize Inventory (Centralized State)
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 10);
        inventory.addRoom("Double Room", 0); // unavailable
        inventory.addRoom("Suite Room", 3);

        // Step 2: Create Room Objects (Domain Model)
        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom(1, 200, 2500));
        rooms.add(new DoubleRoom(2, 300, 4000));
        rooms.add(new SuiteRoom(3, 500, 7000));

        // Step 3: Perform Search (Read-Only)
        RoomSearchService search = new RoomSearchService();
        search.showAvailableRooms(rooms, inventory);
    }
}

/* ================= INVENTORY CLASS ================= */

class RoomInventory {

    private HashMap<String, Integer> map = new HashMap<>();

    // Add room type and availability
    public void addRoom(String type, int count) {
        map.put(type, count);
    }

    // Get availability (Read-only)
    public int getAvailability(String type) {
        return map.getOrDefault(type, 0);
    }
}

/* ================= SEARCH SERVICE ================= */

class RoomSearchService {

    // Read-only method (NO modification)
    public void showAvailableRooms(List<Room> rooms, RoomInventory inventory) {

        System.out.println("\nAvailable Rooms:\n");

        for (Room r : rooms) {

            int available = inventory.getAvailability(r.getType());

            // Filter unavailable rooms
            if (available > 0) {
                r.display();
                System.out.println("Available: " + available);
                System.out.println("------------------------");
            }
        }
    }
}

/* ================= ABSTRACT ROOM ================= */

abstract class Room {

    private int beds;
    private int size;
    private double price;

    public Room(int beds, int size, double price) {
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    public int getBeds() {
        return beds;
    }

    public int getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getType();

    public void display() {
        System.out.println("Room Type: " + getType());
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sq ft");
        System.out.println("Price: ₹" + price);
    }
}

/* ================= ROOM TYPES ================= */

class SingleRoom extends Room {
    public SingleRoom(int beds, int size, double price) {
        super(beds, size, price);
    }

    public String getType() {
        return "Single Room";
    }
}

class DoubleRoom extends Room {
    public DoubleRoom(int beds, int size, double price) {
        super(beds, size, price);
    }

    public String getType() {
        return "Double Room";
    }
}

class SuiteRoom extends Room {
    public SuiteRoom(int beds, int size, double price) {
        super(beds, size, price);
    }

    public String getType() {
        return "Suite Room";
    }
}