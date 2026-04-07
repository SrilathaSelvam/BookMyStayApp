import java.io.*;
import java.util.*;

/**
 * Use Case 12: Data Persistence & System Recovery
 * Version 12.0
 *
 * Demonstrates saving and restoring system state using serialization.
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v12.0 =====");

        String fileName = "system_state.dat";

        PersistenceService service = new PersistenceService();

        // Try loading previous state
        SystemState state = service.loadState(fileName);

        if (state == null) {
            System.out.println("No previous data found. Initializing new state...");

            // Create new state
            state = new SystemState();
            state.getInventory().addRoom("Single Room", 5);
            state.getInventory().addRoom("Double Room", 3);

            state.getHistory().addBooking(new Reservation("R001", "Alice", "Single Room"));
        } else {
            System.out.println("System state restored successfully!");
        }

        // Display current state
        System.out.println("\nInventory:");
        state.getInventory().display();

        System.out.println("\nBooking History:");
        state.getHistory().displayBookings();

        // Save state before shutdown
        service.saveState(state, fileName);

        System.out.println("\nSystem state saved. Restart to test recovery.");
    }
}

/* ================= SYSTEM STATE ================= */

class SystemState implements Serializable {

    private static final long serialVersionUID = 1L;

    private RoomInventory inventory;
    private BookingHistory history;

    public SystemState() {
        inventory = new RoomInventory();
        history = new BookingHistory();
    }

    public RoomInventory getInventory() {
        return inventory;
    }

    public BookingHistory getHistory() {
        return history;
    }
}

/* ================= PERSISTENCE SERVICE ================= */

class PersistenceService {

    // Save state to file
    public void saveState(SystemState state, String fileName) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(fileName))) {

            out.writeObject(state);
            System.out.println("State saved to file.");

        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    // Load state from file
    public SystemState loadState(String fileName) {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(fileName))) {

            return (SystemState) in.readObject();

        } catch (FileNotFoundException e) {
            return null; // No previous file
        } catch (Exception e) {
            System.out.println("Error loading state. Starting fresh.");
            return null;
        }
    }
}

/* ================= INVENTORY ================= */

class RoomInventory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, Integer> map = new HashMap<>();

    public void addRoom(String type, int count) {
        map.put(type, count);
    }

    public void display() {
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}

/* ================= BOOKING HISTORY ================= */

class BookingHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Reservation> bookings = new ArrayList<>();

    public void addBooking(Reservation r) {
        bookings.add(r);
        System.out.println("Booking Saved: " + r.getId());
    }

    public void displayBookings() {
        for (Reservation r : bookings) {
            System.out.println(r.getId() + " | " + r.getGuest() +
                    " | " + r.getRoomType());
        }
    }
}

/* ================= RESERVATION ================= */

class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String guest;
    private String roomType;

    public Reservation(String id, String guest, String roomType) {
        this.id = id;
        this.guest = guest;
        this.roomType = roomType;
    }

    public String getId() {
        return id;
    }

    public String getGuest() {
        return guest;
    }

    public String getRoomType() {
        return roomType;
    }
}