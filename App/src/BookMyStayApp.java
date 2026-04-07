import java.util.*;

/**
 * Use Case 10: Booking Cancellation & State Reversal
 * Version 10.0
 *
 * Demonstrates safe cancellation using Stack (LIFO)
 * and proper rollback of inventory state.
 */
public class BookMyStayApp{

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v10.0 =====");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 2);

        BookingHistory history = new BookingHistory();
        CancellationService cancelService = new CancellationService(inventory, history);

        // Confirm bookings
        history.addBooking(new Reservation("R001", "Alice", "Single Room"));
        history.addBooking(new Reservation("R002", "Bob", "Single Room"));

        // Reduce inventory manually (simulate allocation)
        inventory.updateRoom("Single Room", -2);

        // Perform cancellation
        cancelService.cancelBooking("R002");

        // Try invalid cancellation
        cancelService.cancelBooking("R999");

        System.out.println("\nFinal Inventory:");
        inventory.display();
    }
}

/* ================= RESERVATION ================= */

class Reservation {

    private String id;
    private String guest;
    private String roomType;
    private boolean active = true;

    public Reservation(String id, String guest, String roomType) {
        this.id = id;
        this.guest = guest;
        this.roomType = roomType;
    }

    public String getId() {
        return id;
    }

    public String getRoomType() {
        return roomType;
    }

    public boolean isActive() {
        return active;
    }

    public void cancel() {
        active = false;
    }
}

/* ================= BOOKING HISTORY ================= */

class BookingHistory {

    private List<Reservation> bookings = new ArrayList<>();

    public void addBooking(Reservation r) {
        bookings.add(r);
        System.out.println("Booking Confirmed: " + r.getId());
    }

    public Reservation findBooking(String id) {
        for (Reservation r : bookings) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }
}

/* ================= INVENTORY ================= */

class RoomInventory {

    private Map<String, Integer> map = new HashMap<>();

    public void addRoom(String type, int count) {
        map.put(type, count);
    }

    public void updateRoom(String type, int change) {
        map.put(type, map.getOrDefault(type, 0) + change);
    }

    public void display() {
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
    }
}

/* ================= CANCELLATION SERVICE ================= */

class CancellationService {

    private RoomInventory inventory;
    private BookingHistory history;

    // Stack for rollback (LIFO)
    private Stack<String> rollbackStack = new Stack<>();

    public CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    public void cancelBooking(String bookingId) {

        Reservation r = history.findBooking(bookingId);

        // Validation
        if (r == null) {
            System.out.println("Error: Booking not found.");
            return;
        }

        if (!r.isActive()) {
            System.out.println("Error: Booking already cancelled.");
            return;
        }

        // Push to rollback stack
        rollbackStack.push(bookingId);

        // Restore inventory
        inventory.updateRoom(r.getRoomType(), 1);

        // Mark as cancelled
        r.cancel();

        System.out.println("Booking Cancelled: " + bookingId);
    }
}