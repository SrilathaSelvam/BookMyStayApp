/**
 * UseCase2RoomInitialization
 *
 * This class demonstrates object modeling using abstraction,
 * inheritance, polymorphism, and encapsulation in the
 * Book My Stay Hotel Booking Management System.
 *
 * Version 2.1 – Refactored Domain Modeling
 *
 * @author Sri
 * @version 2.1
 */
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("==============================================");
        System.out.println("        Welcome to Book My Stay");
        System.out.println("  Hotel Booking Management System v2.1");
        System.out.println("==============================================");

        // Polymorphism: Referencing child objects using parent type
        Room singleRoom = new SingleRoom(1, 180.0, 2500.00);
        Room doubleRoom = new DoubleRoom(2, 250.0, 4000.00);
        Room suiteRoom  = new SuiteRoom(3, 450.0, 7500.00);

        // Static availability representation (simple variables)
        int singleRoomAvailability = 10;
        int doubleRoomAvailability = 5;
        int suiteRoomAvailability  = 2;

        System.out.println("\nAvailable Room Types:\n");

        singleRoom.displayRoomDetails();
        System.out.println("Available Units: " + singleRoomAvailability);
        System.out.println("----------------------------------------------");

        doubleRoom.displayRoomDetails();
        System.out.println("Available Units: " + doubleRoomAvailability);
        System.out.println("----------------------------------------------");

        suiteRoom.displayRoomDetails();
        System.out.println("Available Units: " + suiteRoomAvailability);
        System.out.println("----------------------------------------------");

        System.out.println("Application terminated successfully.");
    }
}


/**
 * Abstract class representing a generic Room.
 * This class cannot be instantiated directly.
 */
abstract class Room {

    // Encapsulated attributes
    private int numberOfBeds;
    private double sizeInSqFt;
    private double pricePerNight;

    // Constructor
    public Room(int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        this.numberOfBeds = numberOfBeds;
        this.sizeInSqFt = sizeInSqFt;
        this.pricePerNight = pricePerNight;
    }

    // Getters (Encapsulation)
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public double getSizeInSqFt() {
        return sizeInSqFt;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    // Abstract method (forces subclasses to define room type)
    public abstract String getRoomType();

    // Common behavior
    public void displayRoomDetails() {
        System.out.println("Room Type: " + getRoomType());
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Size (sq ft): " + sizeInSqFt);
        System.out.println("Price per Night: ₹" + pricePerNight);
    }
}


/**
 * Concrete class representing a Single Room.
 */
class SingleRoom extends Room {

    public SingleRoom(int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        super(numberOfBeds, sizeInSqFt, pricePerNight);
    }

    @Override
    public String getRoomType() {
        return "Single Room";
    }
}


/**
 * Concrete class representing a Double Room.
 */
class DoubleRoom extends Room {

    public DoubleRoom(int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        super(numberOfBeds, sizeInSqFt, pricePerNight);
    }

    @Override
    public String getRoomType() {
        return "Double Room";
    }
}


/**
 * Concrete class representing a Suite Room.
 */
class SuiteRoom extends Room {

    public SuiteRoom(int numberOfBeds, double sizeInSqFt, double pricePerNight) {
        super(numberOfBeds, sizeInSqFt, pricePerNight);
    }

    @Override
    public String getRoomType() {
        return "Suite Room";
    }
}