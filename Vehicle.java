/*
 * Abstract Vehicle class for TheCarRental system
 * Provides common functionality for all vehicle types
 */
package TheCarRental;

/**
 *
 * @author Alzah
 */
import java.util.Date;

public abstract class Vehicle implements Comparable<Vehicle> {
    
    protected String model;
    protected String company;
    protected Date date;
    protected Date entryDate;
    protected String color;
    protected String status;
    protected boolean available = true;
    protected char vehicleClass;
    protected int pricePerDay;
    protected int pricePerHour;
    protected int pricePerMonth;

    
    protected Vehicle() {}

    protected Vehicle(String model, String company, Date date, Date entryDate, String color,
                      String status, char vehicleClass, int pricePerDay, int pricePerHour, 
                      int pricePerMonth) {
        this.model = model;
        this.company = company;
        this.date = date;
        this.entryDate = entryDate;
        this.color = color;
        this.status = status;
        this.vehicleClass = vehicleClass;
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.pricePerMonth = pricePerMonth;
    }
    //abstract methods
    public abstract int calculateSpecificPrice(int days, int hours);
    
    public abstract String getVehicleCategory();
    
    public abstract String getSpecifications();
    

    // concrete methods
    
    // price calculation for all but can be ovverriden for a vehicle that has smth special
    public int calculatePrice(int days, int hours) {
        return calculateSpecificPrice(days, hours);
    }
    protected int calculateDefaultPrice(int days, int hours) {
        return days * pricePerDay + hours * pricePerHour;
    }
    
    // Comparable default: by model (A -> Z)
    @Override
    public int compareTo(Vehicle other) {
        if (this.model == null && other.model == null) return 0;
        if (this.model == null) return -1;
        if (other.model == null) return 1;
        return this.model.compareToIgnoreCase(other.model);
    }
    
    // displaying the vehicle info
    @Override
    public String toString() {
        return String.format("[%s | %s] Color:%s Status:%s Avail:%s Class:%c Price/day:%d Price/hr:%d",
                model, company, color, status, available ? "Yes" : "No", 
                vehicleClass, pricePerDay, pricePerHour);
    }
    
    // setters and getters
    public String getModel() { return model; }
    public String getCompany() { return company; }
    public Date getDate() { return date; }
    public Date getEntryDate() { return entryDate; }
    public String getColor() { return color; }
    public String getStatus() { return status; }
    public boolean isAvailable() { return available; }
    public char getVehicleClass() { return vehicleClass; }
    public int getPricePerDay() { return pricePerDay; }
    public int getPricePerHour() { return pricePerHour; }
    public int getPricePerMonth() { return pricePerMonth; }

    public void setStatus(String status) { this.status = status; }
    public void setAvailable(boolean available) { this.available = available; }
    public void setPricePerDay(int pricePerDay) { this.pricePerDay = pricePerDay; }
    public void setPricePerHour(int pricePerHour) { this.pricePerHour = pricePerHour; }
    public void setPricePerMonth(int pricePerMonth) { this.pricePerMonth = pricePerMonth; }
    public void setColor(String color) { this.color = color; }
}