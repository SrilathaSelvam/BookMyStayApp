import java.util.*;

/**
 * Use Case 7: Add-On Services
 * Version 7.0
 *
 * Demonstrates adding optional services to a reservation
 * without modifying core booking logic.
 */
public class BookMyStayApp{

    public static void main(String[] args) {

        System.out.println("===== Book My Stay - v7.0 =====");

        // Create Add-On Service Manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Sample Reservation IDs
        String res1 = "R001";
        String res2 = "R002";

        // Create Services
        Service breakfast = new Service("Breakfast", 500);
        Service wifi = new Service("WiFi", 200);
        Service spa = new Service("Spa", 1500);

        // Add services to reservations
        manager.addService(res1, breakfast);
        manager.addService(res1, wifi);

        manager.addService(res2, spa);

        // Display services for each reservation
        System.out.println("\nServices for Reservation " + res1 + ":");
        manager.displayServices(res1);

        System.out.println("\nServices for Reservation " + res2 + ":");
        manager.displayServices(res2);

        // Calculate additional cost
        System.out.println("\nTotal Add-On Cost for " + res1 + ": ₹" +
                manager.calculateTotalCost(res1));

        System.out.println("Total Add-On Cost for " + res2 + ": ₹" +
                manager.calculateTotalCost(res2));
    }
}

/* ================= SERVICE CLASS ================= */

class Service {

    private String name;
    private double cost;

    public Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }
}

/* ================= ADD-ON SERVICE MANAGER ================= */

class AddOnServiceManager {

    // Map<ReservationID, List of Services>
    private Map<String, List<Service>> serviceMap = new HashMap<>();

    // Add service to a reservation
    public void addService(String reservationId, Service service) {

        // If no list exists, create one
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());

        serviceMap.get(reservationId).add(service);

        System.out.println("Added " + service.getName() +
                " to Reservation " + reservationId);
    }

    // Display services for a reservation
    public void displayServices(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        if (services == null || services.isEmpty()) {
            System.out.println("No services selected.");
            return;
        }

        for (Service s : services) {
            System.out.println("Service: " + s.getName() +
                    " | Cost: ₹" + s.getCost());
        }
    }

    // Calculate total cost of services
    public double calculateTotalCost(String reservationId) {

        List<Service> services = serviceMap.get(reservationId);

        double total = 0;

        if (services != null) {
            for (Service s : services) {
                total += s.getCost();
            }
        }

        return total;
    }
}