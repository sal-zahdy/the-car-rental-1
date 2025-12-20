/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package TheCarRental;

/**
 *
 * @author Alzah
 */

import java.util.ArrayList;
import java.util.Date;

public class TheCarRental {
    public static void main(String[] args) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Car("Civic", "Honda", new Date(), new Date(), "Red", 
                           "Available", 'A', 300, 40, 6000, 5, "Petrol"));
        vehicles.add(new Car("Corolla", "Toyota", new Date(), new Date(), "Black", 
                           "Available", 'A', 280, 35, 5800, 5, "Diesel"));
        vehicles.add(new Van("HiAce", "Toyota", new Date(), new Date(), "White", 
                           "Available", 'C', 400, 60, 8000, 1500, 2, false));
        vehicles.add(new Motorcycle("Ninja", "Kawasaki", new Date(), new Date(), "Green", 
                           "Available", 'M', 150, 20, 2500, "Petrol", "Manual"));
        
        // Economy Cars (Class A)
        vehicles.add(new Car("Accent", "Hyundai", new Date(), new Date(), "Silver", 
                           "Available", 'A', 250, 30, 5000, 5, "Petrol"));
        vehicles.add(new Car("Sunny", "Nissan", new Date(), new Date(), "Blue", 
                           "Available", 'A', 260, 32, 5200, 5, "Petrol"));
        vehicles.add(new Car("Lancer", "Mitsubishi", new Date(), new Date(), "Gray", 
                           "Available", 'A', 270, 35, 5400, 5, "Petrol"));
        
        // Mid-size Sedans (Class B)
        vehicles.add(new Car("Camry", "Toyota", new Date(), new Date(), "White", 
                           "Available", 'B', 350, 45, 7000, 5, "Petrol"));
        vehicles.add(new Car("Accord", "Honda", new Date(), new Date(), "Black", 
                           "Available", 'B', 360, 46, 7200, 5, "Petrol"));
        vehicles.add(new Car("Mazda6", "Mazda", new Date(), new Date(), "Red", 
                           "Available", 'B', 340, 44, 6800, 5, "Petrol"));
        
        // Luxury Cars (Class L)
        vehicles.add(new Car("Mercedes E-Class", "Mercedes", new Date(), new Date(), "Black", 
                           "Available", 'L', 800, 100, 16000, 5, "Petrol"));
        vehicles.add(new Car("BMW 5 Series", "BMW", new Date(), new Date(), "Blue", 
                           "Available", 'L', 780, 98, 15600, 5, "Petrol"));
        vehicles.add(new Car("Audi A6", "Audi", new Date(), new Date(), "Silver", 
                           "Available", 'L', 750, 95, 15000, 5, "Petrol"));
        
        // SUV and 4x4 Vehicles (Class S)
        vehicles.add(new Car("Prado", "Toyota", new Date(), new Date(), "White", 
                           "Available", 'S', 600, 75, 12000, 7, "Diesel"));
        vehicles.add(new Car("Pathfinder", "Nissan", new Date(), new Date(), "Gray", 
                           "Available", 'S', 550, 70, 11000, 7, "Petrol"));
        vehicles.add(new Car("Fortuner", "Toyota", new Date(), new Date(), "Black", 
                           "Available", 'S', 580, 72, 11600, 7, "Diesel"));
        
        // Electric/Hybrid Cars (Class E)
        vehicles.add(new Car("Tesla Model 3", "Tesla", new Date(), new Date(), "White", 
                           "Available", 'E', 500, 65, 10000, 5, "Electric"));
        vehicles.add(new Car("Prius", "Toyota", new Date(), new Date(), "Blue", 
                           "Available", 'E', 320, 40, 6400, 5, "Hybrid"));
        
        // ============= ADD MORE VANS =============
        
        // Small Vans
        vehicles.add(new Van("NV200", "Nissan", new Date(), new Date(), "White", 
                           "Available", 'C', 350, 50, 7000, 800, 3, false));
        vehicles.add(new Van("Express", "Chevrolet", new Date(), new Date(), "Red", 
                           "Available", 'C', 380, 55, 7600, 1000, 2, false));
        
        // Large Vans
        vehicles.add(new Van("Sprinter", "Mercedes", new Date(), new Date(), "White", 
                           "Available", 'D', 550, 80, 11000, 2500, 3, false));
        vehicles.add(new Van("Transit", "Ford", new Date(), new Date(), "Blue", 
                           "Available", 'D', 500, 75, 10000, 2000, 2, false));
        
        // Refrigerated Vans
        vehicles.add(new Van("Cold Truck", "Isuzu", new Date(), new Date(), "White", 
                           "Available", 'R', 600, 90, 12000, 1500, 2, true));
        vehicles.add(new Van("Refrigerated Van", "Mitsubishi", new Date(), new Date(), "White", 
                           "Available", 'R', 580, 85, 11600, 1200, 2, true));
        
        // Passenger Vans
        vehicles.add(new Van("Hiace Commuter", "Toyota", new Date(), new Date(), "White", 
                           "Available", 'P', 450, 65, 9000, 500, 12, false));
        
        
        // Sport Bikes
        vehicles.add(new Motorcycle("R1", "Yamaha", new Date(), new Date(), "Blue", 
                           "Available", 'M', 200, 25, 4000, "Petrol", "Manual"));
        vehicles.add(new Motorcycle("CBR1000RR", "Honda", new Date(), new Date(), "Red", 
                           "Available", 'M', 220, 28, 4400, "Petrol", "Manual"));
        vehicles.add(new Motorcycle("GSX-R1000", "Suzuki", new Date(), new Date(), "Blue/White", 
                           "Available", 'M', 210, 26, 4200, "Petrol", "Manual"));
        
        // Cruiser Bikes
        vehicles.add(new Motorcycle("Harley Sportster", "Harley Davidson", new Date(), new Date(), "Black", 
                           "Available", 'C', 300, 40, 6000, "Petrol", "Manual"));
        vehicles.add(new Motorcycle("Vulcan", "Kawasaki", new Date(), new Date(), "Black", 
                           "Available", 'C', 250, 35, 5000, "Petrol", "Manual"));
        
        // Scooters
        vehicles.add(new Motorcycle("Vespa", "Piaggio", new Date(), new Date(), "Red", 
                           "Available", 'S', 100, 15, 2000, "Petrol", "Automatic"));
        vehicles.add(new Motorcycle("Burgman", "Suzuki", new Date(), new Date(), "Silver", 
                           "Available", 'S', 120, 18, 2400, "Petrol", "Automatic"));
        
        // Adventure/Touring Bikes
        vehicles.add(new Motorcycle("GS1250", "BMW", new Date(), new Date(), "Gray", 
                           "Available", 'T', 350, 45, 7000, "Petrol", "Manual"));
        
        // Electric Bikes
        vehicles.add(new Motorcycle("Zero SR", "Zero Motorcycles", new Date(), new Date(), "Black", 
                           "Available", 'E', 180, 22, 3600, "Electric", "Automatic"));

        // Demo user
        User demoUser = new User("John Doe", "U101", "0100000000", "john@example.com");

        RentalManager manager = new RentalManager(vehicles, demoUser);
        manager.start();
    }
}
