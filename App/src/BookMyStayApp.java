import java.util.*;

/**
 * Use Case 5: Booking Request Queue (FIFO)
 * Version 5.0
 *
 * Demonstrates fair handling of booking requests using Queue (FIFO).
 */
public class UseCase5BookingQueue {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v5.0 =====");

        // Initialize Booking Queue
        BookingQueue queue = new BookingQueue();

        // Guest submits booking requests
        queue.addRequest(new Reservation("R001", "Alice", "Single Room"));
        queue.addRequest(new Reservation("R002", "Bob", "Double Room"));
        queue.addRequest(new Reservation("R003", "Charlie", "Suite Room"));

        // Display queued requests
        System.out.println("\nBooking Requests in Queue:");
        queue.displayQueue();
    }
}

/* ================= RESERVATION ================= */

class Reservation {

    private String requestId;
    private String guestName;
    private String roomType;

    public Reservation(String requestId, String guestName, String roomType) {
        this.requestId = requestId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Request ID: " + requestId +
                ", Guest: " + guestName +
                ", Room: " + roomType);
    }
}

/* ================= BOOKING QUEUE ================= */

class BookingQueue {

    // FIFO Queue
    private Queue<Reservation> queue = new LinkedList<>();

    // Add request to queue
    public void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request Added: " + r.getRequestId());
    }

    // Display all requests (without removing)
    public void displayQueue() {
        for (Reservation r : queue) {
            r.display();
        }
    }
}