import java.util.*;

/**
 * Use Case 8: Booking History & Reporting
 * Version 8.0
 *
 * Demonstrates storing confirmed bookings and generating reports
 * without modifying stored data.
 */
public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v8.0 =====");

        // Initialize Booking History
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.addBooking(new Reservation("R001", "Alice", "Single Room"));
        history.addBooking(new Reservation("R002", "Bob", "Double Room"));
        history.addBooking(new Reservation("R003", "Charlie", "Suite Room"));

        // Admin views booking history
        System.out.println("\nAll Confirmed Bookings:");
        history.displayBookings();

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateSummary(history);
    }
}

/* ================= RESERVATION ================= */

class Reservation {

    private String id;
    private String guestName;
    private String roomType;

    public Reservation(String id, String guestName, String roomType) {
        this.id = id;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getId() {
        return id;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("ID: " + id +
                ", Guest: " + guestName +
                ", Room: " + roomType);
    }
}

/* ================= BOOKING HISTORY ================= */

class BookingHistory {

    // List to maintain insertion order
    private List<Reservation> bookings = new ArrayList<>();

    // Add confirmed booking
    public void addBooking(Reservation r) {
        bookings.add(r);
        System.out.println("Booking Confirmed: " + r.getId());
    }

    // Retrieve all bookings
    public List<Reservation> getBookings() {
        return bookings;
    }

    // Display bookings
    public void displayBookings() {
        for (Reservation r : bookings) {
            r.display();
        }
    }
}

/* ================= REPORT SERVICE ================= */

class BookingReportService {

    // Generate summary report (read-only)
    public void generateSummary(BookingHistory history) {

        List<Reservation> list = history.getBookings();

        System.out.println("\n===== Booking Summary Report =====");

        System.out.println("Total Bookings: " + list.size());

        // Count bookings per room type
        Map<String, Integer> countMap = new HashMap<>();

        for (Reservation r : list) {
            String type = r.getRoomType();
            countMap.put(type, countMap.getOrDefault(type, 0) + 1);
        }

        System.out.println("\nBookings by Room Type:");
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}