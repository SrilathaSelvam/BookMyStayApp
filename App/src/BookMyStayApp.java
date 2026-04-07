import java.util.*;

/**
 * Use Case 4: Room Search & Availability Check
 * Version 4.0
 * 
 * Resolved Merge Conflict (UC4 kept, UC3 removed)
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v4.0 =====");

        // Initialize Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 10);
        inventory.addRoom("Double Room", 0); // unavailable
        inventory.addRoom("Suite Room", 3);

        // Create Room Objects
        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom(1, 200, 2500));
        rooms.add(new DoubleRoom(2, 300, 4000));
        rooms.add(new SuiteRoom(3, 500, 7000));

        // Perform Search (READ-ONLY)
        RoomSearchService search = new RoomSearchService();
        search.showAvailableRooms(rooms, inventory);
    }
}

/* ================= INVENTORY ================= */

class RoomInventory {

    private HashMap<String, Integer> map = new HashMap<>();

    public void addRoom(String type, int count) {
        map.put(type, count);
    }

    public int getAvailability(String type) {
        return map.getOrDefault(type, 0);
    }
}

/* ================= SEARCH SERVICE ================= */

class RoomSearchService {

    public void showAvailableRooms(List<Room> rooms, RoomInventory inventory) {

        System.out.println("\nAvailable Rooms:\n");

        for (Room r : rooms) {

            int available = inventory.getAvailability(r.getType());

            // Show only available rooms
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