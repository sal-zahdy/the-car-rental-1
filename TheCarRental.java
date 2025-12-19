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

        vehicles.add(new Car("Civic", "Honda", new Date(), new Date(), "Red", "Available", 'A', 300, 40, 6000, 5, "Petrol"));
        vehicles.add(new Car("Corolla", "Toyota", new Date(), new Date(), "Black", "Available", 'A', 280, 35, 5800, 5, "Diesel"));
        vehicles.add(new Van("HiAce", "Toyota", new Date(), new Date(), "White", "Available", 'C', 400, 60, 8000, 1500, 2, false));
        vehicles.add(new Motorcycle("Ninja", "Kawasaki", new Date(), new Date(), "Green", "Available", 'M', 150, 20, 2500, "Petrol", "Manual"));

        // Demo user
        User demoUser = new User("John Doe", "U101", "0100000000", "john@example.com");

        RentalManager manager = new RentalManager(vehicles, demoUser);
        manager.start();
    }
}
