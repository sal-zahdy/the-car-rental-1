package TheCarRental;

/**
 *
 * @author Alzah
 */
import java.util.Date;

public class Motorcycle extends Vehicle {
    private String fuelType;
    private String transmissionType;
    
    // Add constant for short rental threshold
    private static final int SHORT_RENTAL_HOURS = 2;

    public Motorcycle(String model, String company, Date date, Date entryDate, String color,
                      String status, char vehicleClass, int pricePerDay, int pricePerHour, int pricePerMonth,
                      String fuelType, String transmissionType) {
        
        super(model, company, date, entryDate, color, status, vehicleClass, 
              pricePerDay, pricePerHour, pricePerMonth);
        this.fuelType = fuelType;
        this.transmissionType = transmissionType;
    }

    // abstract methods implementation
    @Override
    public int calculateSpecificPrice(int days, int hours) {
        // Motorcycle-specific pricing: hourly rate for short rentals
        // FIXED: Use constant and getter
        if (hours <= SHORT_RENTAL_HOURS && days == 0) {
            return hours * getPricePerHour(); // No daily minimum for short rentals
        }
        return calculateDefaultPrice(days, hours);
    }
    
    @Override
    public String getVehicleCategory() {
        return "Motorcycle (" + vehicleClass + " Class)";
    }
    
    @Override
    public String getSpecifications() {
        return String.format("Fuel: %s | Transmission: %s", fuelType, transmissionType);
    }

    @Override
    public String toString() {
        return "Motorcycle " + super.toString() + String.format(" Fuel:%s Transmission:%s", 
                fuelType, transmissionType);
    }
}