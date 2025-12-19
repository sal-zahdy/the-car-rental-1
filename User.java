/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheCarRental;

/**
 *
 * @author Alzah
 */
import java.util.ArrayList;

public class User extends Person {

    private int currentRentsCount;
    private int totalRentsCount;

    private ArrayList<Booking> rentalHistory = new ArrayList<>();
    private ArrayList<Booking> currentBookings = new ArrayList<>();

    public User(String name, String id, String phone_number, String email) {
        super(name, id, phone_number, email);
        this.currentRentsCount = 0;
        this.totalRentsCount = 0;
    }

    // Rent: creates a Booking and updates state
    public void rentVehicle(Vehicle v, int days, int hours) throws VehicleNotAvailableException, IllegalStateException {
        if (!v.isAvailable()) {
            throw new VehicleNotAvailableException("Vehicle is not available.");
        }
        if (currentRentsCount >= 3) {
            throw new IllegalStateException("You reached the limit of 3 concurrent rentals.");
        }

        // book the vehicle
        v.setAvailable(false);
        v.setStatus("Booked");

        Booking b = Booking.createBooking(this, v, days, hours);
        currentBookings.add(b);
        rentalHistory.add(b);

        currentRentsCount++;
        totalRentsCount++;
        System.out.println("Booking successful. Booking ID: " + b.getBookingId());
        System.out.println("Price: " + b.getTotalPrice() + " EGP | Rent date: " + b.getRentDate() + " | Return date: " + b.getReturnDate());
    }

    // Return a vehicle by selecting a current booking
    public void returnVehicle(int bookingIndex) {
        if (bookingIndex < 0 || bookingIndex >= currentBookings.size()) {
            System.out.println("Invalid booking index.");
            return;
        }
        Booking b = currentBookings.remove(bookingIndex);
        Vehicle v = b.getVehicle();
        v.setAvailable(true);
        v.setStatus("Available");

        currentRentsCount--;
        System.out.println("Vehicle returned successfully. Booking ID: " + b.getBookingId());
    }

    public ArrayList<Booking> getCurrentBookings() {
        return currentBookings;
    }

    public ArrayList<Booking> getRentalHistory() {
        return rentalHistory;
    }

    public int getCurrentRentsCount() {
        return currentRentsCount;
    }
}
