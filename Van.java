package TheCarRental;

/**
 *
 * @author Alzah
 */
import java.util.Date;

public class Van extends Vehicle {
    private int cargoCapacity;
    private int numberOfSeats;
    private boolean refrigerated;

    public Van(String model, String company, Date date, Date entryDate, String color,
               String status, char vehicleClass, int pricePerDay, int pricePerHour, int pricePerMonth,
               int cargoCapacity, int numberOfSeats, boolean refrigerated) {
        
        super(model, company, date, entryDate, color, status, vehicleClass, 
              pricePerDay, pricePerHour, pricePerMonth);
        this.cargoCapacity = cargoCapacity;
        this.numberOfSeats = numberOfSeats;
        this.refrigerated = refrigerated;
    }

    // abstract methods implementation
    @Override
    public int calculateSpecificPrice(int days, int hours) {
        // Van-specific pricing: refrigerated vans cost more
        int basePrice = calculateDefaultPrice(days, hours);
        if (refrigerated) {
            return (int)(basePrice * 1.2); // 20% premium for refrigerated
        }
        return basePrice;
    }
    
    @Override
    public String getVehicleCategory() {
        return "Commercial Van (" + vehicleClass + " Class)";
    }
    
    @Override
    public String getSpecifications() {
        return String.format("Cargo: %dkg | Seats: %d | Refrigerated: %s",
                cargoCapacity, numberOfSeats, refrigerated ? "Yes" : "No");
    }

    @Override
    public String toString() {
        return "Van " + super.toString() + String.format(" Cargo:%dkg Seats:%d Refrigerated:%s",
                cargoCapacity, numberOfSeats, refrigerated ? "Yes" : "No");
    }
}