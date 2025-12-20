package TheCarRental;

import java.util.ArrayList;

public class User implements Person {
    
    // Fields from the original abstract Person class
    private String name;
    private String id;
    private String phoneNumber;  // Changed from phone_number for consistency
    private String email;
    
    // User-specific fields (unchanged from your original)
    private int currentRentsCount;
    private int totalRentsCount;
    private ArrayList<Booking> rentalHistory = new ArrayList<>();
    private ArrayList<Booking> currentBookings = new ArrayList<>();
    
    // Constructor (same parameters as before)
    public User(String name, String id, String phoneNumber, String email) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.currentRentsCount = 0;
        this.totalRentsCount = 0;
    }
    
    // ============= PERSON INTERFACE IMPLEMENTATION =============
    @Override
    public String getName() { 
        return name; 
    }
    
    @Override
    public String getId() { 
        return id; 
    }
    
    @Override
    public String getPhoneNumber() { 
        return phoneNumber; 
    }
    
    @Override
    public String getEmail() { 
        return email; 
    }
    
    @Override
    public void setName(String name) { 
        this.name = name; 
    }
    
    @Override
    public void setPhoneNumber(String phoneNumber) { 
        this.phoneNumber = phoneNumber; 
    }
    
    @Override
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    @Override
    public String displayInfo() {
        return String.format("User: %s (ID: %s)%nPhone: %s%nEmail: %s", 
                name, id, phoneNumber, email);
    }
    
    @Override
    public boolean validateData() {
        // Simple validation - can be expanded
        return name != null && !name.trim().isEmpty() &&
               id != null && !id.trim().isEmpty() &&
               phoneNumber != null && !phoneNumber.trim().isEmpty() &&
               email != null && email.contains("@");
    }
    
    @Override
    public String toString() {
        // Keep the same toString format as before
        return name + " (" + id + ")";
    }
    // ===========================================================
    
    // ============= ALL ORIGINAL USER METHODS (UNCHANGED) =============
    public void rentVehicle(Vehicle v, int days, int hours) 
            throws VehicleNotAvailableException, IllegalStateException {
        if (!v.isAvailable()) {
            throw new VehicleNotAvailableException("Vehicle is not available.");
        }
        if (currentRentsCount >= 3) {
            throw new IllegalStateException("You reached the limit of 3 concurrent rentals.");
        }

        v.setAvailable(false);
        v.setStatus("Booked");

        Booking b = Booking.createBooking(this, v, days, hours);
        currentBookings.add(b);
        rentalHistory.add(b);

        currentRentsCount++;
        totalRentsCount++;
        System.out.println("Booking successful. Booking ID: " + b.getBookingId());
        System.out.println("Price: " + b.getTotalPrice() + " EGP | Rent date: " + 
                b.getRentDate() + " | Return date: " + b.getReturnDate());
    }

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

    // Getters (unchanged)
    public ArrayList<Booking> getCurrentBookings() { 
        return currentBookings; 
    }
    
    public ArrayList<Booking> getRentalHistory() { 
        return rentalHistory; 
    }
    
    public int getCurrentRentsCount() { 
        return currentRentsCount; 
    }
    
    public int getTotalRentsCount() { 
        return totalRentsCount; 
    }
}