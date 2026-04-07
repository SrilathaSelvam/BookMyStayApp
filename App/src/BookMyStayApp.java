import java.util.*;

/**
 * Use Case 9: Validation & Error Handling
 * Version 9.0
 *
 * Demonstrates input validation, custom exceptions,
 * and fail-fast error handling.
 */
public class BookMyStayApp{

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v9.0 =====");

        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 2);
        inventory.addRoom("Double Room", 1);

        BookingService service = new BookingService(inventory);

        try {
            // Valid booking
            service.bookRoom("R001", "Alice", "Single Room");

            // Invalid room type
            service.bookRoom("R002", "Bob", "Suite Room");

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            // Overbooking attempt
            service.bookRoom("R003", "Charlie", "Double Room");
            service.bookRoom("R004", "David", "Double Room"); // exceeds

        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\nSystem running safely after errors.");
    }
}

/* ================= CUSTOM EXCEPTION ================= */

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

/* ================= INVENTORY ================= */

class RoomInventory {

    private Map<String, Integer> map = new HashMap<>();

    public void addRoom(String type, int count) {
        map.put(type, count);
    }

    public int getAvailability(String type) {
        return map.getOrDefault(type, -1);
    }

    public void updateRoom(String type, int change) throws InvalidBookingException {

        int current = getAvailability(type);

        if (current == -1) {
            throw new InvalidBookingException("Invalid room type: " + type);
        }

        if (current + change < 0) {
            throw new InvalidBookingException("No rooms available for: " + type);
        }

        map.put(type, current + change);
    }
}

/* ================= BOOKING SERVICE ================= */

class BookingService {

    private RoomInventory inventory;

    public BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void bookRoom(String id, String guest, String roomType)
            throws InvalidBookingException {

        // Validation (Fail-Fast)
        if (roomType == null || roomType.isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }

        // Try updating inventory
        inventory.updateRoom(roomType, -1);

        // If successful
        System.out.println("Booking Confirmed: " + id +
                " | Guest: " + guest +
                " | Room: " + roomType);
    }
}