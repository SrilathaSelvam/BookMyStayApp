import java.util.*;

/**
 * Use Case 11: Concurrent Booking with Synchronization
 * Version 11.0
 *
 * Demonstrates thread safety and prevention of race conditions.
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v11.0 =====");

        // Shared Inventory
        RoomInventory inventory = new RoomInventory();
        inventory.addRoom("Single Room", 2);

        // Shared Booking Queue
        Queue<Reservation> queue = new LinkedList<>();

        // Simulate multiple guests (requests)
        queue.add(new Reservation("R001", "Alice", "Single Room"));
        queue.add(new Reservation("R002", "Bob", "Single Room"));
        queue.add(new Reservation("R003", "Charlie", "Single Room"));

        // Create threads (Concurrent Processing)
        Thread t1 = new Thread(new BookingProcessor(queue, inventory));
        Thread t2 = new Thread(new BookingProcessor(queue, inventory));

        t1.start();
        t2.start();
    }
}

/* ================= RESERVATION ================= */

class Reservation {

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

/* ================= INVENTORY ================= */

class RoomInventory {

    private Map<String, Integer> map = new HashMap<>();

    public void addRoom(String type, int count) {
        map.put(type, count);
    }

    // Synchronized critical section
    public synchronized boolean bookRoom(String type) {

        int available = map.getOrDefault(type, 0);

        if (available > 0) {
            map.put(type, available - 1);
            return true;
        }

        return false;
    }
}

/* ================= BOOKING PROCESSOR ================= */

class BookingProcessor implements Runnable {

    private Queue<Reservation> queue;
    private RoomInventory inventory;

    public BookingProcessor(Queue<Reservation> queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation r;

            // Synchronize queue access
            synchronized (queue) {
                if (queue.isEmpty()) break;
                r = queue.poll();
            }

            // Process booking (critical section inside inventory)
            boolean success = inventory.bookRoom(r.getRoomType());

            if (success) {
                System.out.println(Thread.currentThread().getName() +
                        " booked " + r.getId() + " for " + r.getGuest());
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " FAILED booking for " + r.getGuest());
            }
        }
    }
}