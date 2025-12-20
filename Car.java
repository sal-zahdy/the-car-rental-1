package TheCarRental;

import java.util.Date;

public class Car extends Vehicle {
    private int numberOfSeats;
    private String fuelType;

    public Car(String model, String company, Date date, Date entryDate, String color,
               String status, char vehicleClass, int pricePerDay, int pricePerHour, int pricePerMonth,
               int numberOfSeats, String fuelType) {
        
        super(model, company, date, entryDate, color, status, vehicleClass, 
              pricePerDay, pricePerHour, pricePerMonth);
        this.numberOfSeats = numberOfSeats;
        this.fuelType = fuelType;
    }

    // abstract methods implementation
    @Override
    public int calculateSpecificPrice(int days, int hours) {
        // FIXED: Use getters instead of direct field access
        if (hours > 0 && days == 0) {
            return Math.max(getPricePerDay(), hours * getPricePerHour());
        }
        return calculateDefaultPrice(days, hours);
    }
    
    @Override
    public String getVehicleCategory() {
        return "Passenger Car (" + vehicleClass + " Class)";
    }
    
    @Override
    public String getSpecifications() {
        return String.format("Seats: %d | Fuel: %s", numberOfSeats, fuelType);
    }

    // addition of specific data fields
    
    public int getNumberOfSeats() { return numberOfSeats; }
    public String getFuelType() { return fuelType; }

    @Override
    public String toString() {
        return "Car " + super.toString() + String.format(" Seats:%d Fuel:%s", numberOfSeats, fuelType);
    }
}