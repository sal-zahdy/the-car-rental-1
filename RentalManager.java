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
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RentalManager {
    private final ArrayList<Vehicle> vehicles;
    private final User user;
    private final Scanner sc;

    public RentalManager(ArrayList<Vehicle> vehicles, User user) {
        this.vehicles = vehicles;
        this.user = user;
        this.sc = new Scanner(System.in);
    }

    public void start() {
        int choice = -1;
        do {
            System.out.println("\n=== CAR RENTAL SYSTEM ===");
            System.out.println("1. List Vehicles");
            System.out.println("2. Sorting Menu");
            System.out.println("3. Rent Vehicle");
            System.out.println("4. Return Vehicle");
            System.out.println("5. View Current Rentals");
            System.out.println("6. View Rental History");
            System.out.println("7. Edit Vehicle (no add/remove/search)");
            System.out.println("8. Exit");
            System.out.print("Choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                switch (choice) {
                    case 1 -> listVehiclesInteractive();
                    case 2 -> sortingMenu();
                    case 3 -> rentFlow();
                    case 4 -> returnFlow();
                    case 5 -> showCurrentRentals();
                    case 6 -> showRentalHistory();
                    case 7 -> editVehicle();
                    case 8 -> System.out.println("Goodbye!");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers only.");
                sc.nextLine(); // clear
            }
        } while (choice != 8);
    }

    // LIST VEHICLES: offers sorting choices inline
    private void listVehiclesInteractive() {
        System.out.println("\n-- List Vehicles --");
        System.out.println("1. List as-is");
        System.out.println("2. Sort by Model (default)");
        System.out.println("3. Sort by Company");
        System.out.println("4. Sort by Class");
        System.out.println("5. Sort by Availability");
        System.out.println("6. Sort by Price per Day");
        System.out.println("7. Sort by Price per Hour");
        System.out.println("8. Cancel");
        System.out.print("Choice: ");
        int c = sc.nextInt();
        sc.nextLine();

        ArrayList<Vehicle> copy = new ArrayList<>(vehicles);

        switch (c) {
            case 1 -> { /* no sorting */ }
            case 2 -> Collections.sort(copy); // uses compareTo -> model
            case 3 -> copy.sort(VehicleComparators.BY_COMPANY);
            case 4 -> copy.sort(VehicleComparators.BY_CLASS);
            case 5 -> copy.sort(VehicleComparators.BY_AVAILABILITY);
            case 6 -> copy.sort(VehicleComparators.BY_PRICE_DAY);
            case 7 -> copy.sort(VehicleComparators.BY_PRICE_HOUR);
            default -> {
                System.out.println("Cancelled or invalid.");
                return;
            }
        }

        printVehicleList(copy);
    }

    // Separate sorting menu (Option C requirement)
    private void sortingMenu() {
        System.out.println("\n-- Sorting Menu --");
        System.out.println("1. By Model");
        System.out.println("2. By Company");
        System.out.println("3. By Class");
        System.out.println("4. By Availability");
        System.out.println("5. By Price per Day");
        System.out.println("6. By Price per Hour");
        System.out.println("7. By Price per Month");
        System.out.println("8. Cancel");
        System.out.print("Choice: ");
        int c = sc.nextInt();
        sc.nextLine();

        ArrayList<Vehicle> copy = new ArrayList<>(vehicles);
        switch (c) {
            case 1 -> Collections.sort(copy);
            case 2 -> copy.sort(VehicleComparators.BY_COMPANY);
            case 3 -> copy.sort(VehicleComparators.BY_CLASS);
            case 4 -> copy.sort(VehicleComparators.BY_AVAILABILITY);
            case 5 -> copy.sort(VehicleComparators.BY_PRICE_DAY);
            case 6 -> copy.sort(VehicleComparators.BY_PRICE_HOUR);
            case 7 -> copy.sort(VehicleComparators.BY_PRICE_MONTH);
            default -> {
                System.out.println("Cancelled or invalid.");
                return;
            }
        }
        printVehicleList(copy);
    }

    private void printVehicleList(ArrayList<Vehicle> list) {
        System.out.println("\n--- Vehicles ---");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d) %s%n", i, list.get(i).toString());
        }
    }

    // Renting flow
    private void rentFlow() {
        printVehicleList(vehicles);
        System.out.print("Enter vehicle index to rent: ");
        try {
            int idx = sc.nextInt();
            sc.nextLine();
            if (idx < 0 || idx >= vehicles.size()) {
                System.out.println("Invalid index.");
                return;
            }
            Vehicle v = vehicles.get(idx);
            System.out.print("Enter number of days: ");
            int days = sc.nextInt();
            System.out.print("Enter number of hours: ");
            int hours = sc.nextInt();
            sc.nextLine();

            user.rentVehicle(v, days, hours);

        } catch (InputMismatchException e) {
            System.out.println("Please enter numbers only.");
            sc.nextLine();
        } catch (VehicleNotAvailableException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    // Return flow
    private void returnFlow() {
        showCurrentRentals();
        if (user.getCurrentBookings().isEmpty()) {
            return;
        }
        System.out.print("Enter booking index to return (as shown above): ");
        try {
            int idx = sc.nextInt();
            sc.nextLine();
            user.returnVehicle(idx);
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number.");
            sc.nextLine();
        }
    }

    private void showCurrentRentals() {
        System.out.println("\n--- Current Rentals ---");
        ArrayList<Booking> current = user.getCurrentBookings();
        if (current.isEmpty()) {
            System.out.println("No current rentals.");
            return;
        }
        for (int i = 0; i < current.size(); i++) {
            System.out.printf("%d) %s%n", i, current.get(i).toString());
        }
    }

    private void showRentalHistory() {
        System.out.println("\n--- Rental History ---");
        ArrayList<Booking> hist = user.getRentalHistory();
        if (hist.isEmpty()) {
            System.out.println("No history.");
            return;
        }
        for (int i = 0; i < hist.size(); i++) {
            System.out.printf("%d) %s%n", i, hist.get(i).toString());
        }
    }

    // Edit vehicle: allow update of color and prices and status (no add/remove)
    private void editVehicle() {
        printVehicleList(vehicles);
        System.out.print("Enter vehicle index to edit: ");
        try {
            int idx = sc.nextInt();
            sc.nextLine();
            if (idx < 0 || idx >= vehicles.size()) {
                System.out.println("Invalid index.");
                return;
            }
            Vehicle v = vehicles.get(idx);
            System.out.println("Selected: " + v);

            System.out.println("1. Change color");
            System.out.println("2. Change price per day");
            System.out.println("3. Change price per hour");
            System.out.println("4. Change status (Available/Booked)");
            System.out.println("5. Cancel");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1 -> {
                    System.out.print("New color: ");
                    String col = sc.nextLine();
                    v.setColor(col);
                    System.out.println("Color updated.");
                }
                case 2 -> {
                    System.out.print("New price per day: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    v.setPricePerDay(p);
                    System.out.println("Price per day updated.");
                }
                case 3 -> {
                    System.out.print("New price per hour: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    v.setPricePerHour(p);
                    System.out.println("Price per hour updated.");
                }
                case 4 -> {
                    System.out.print("Enter status (Available/Booked): ");
                    String st = sc.nextLine();
                    v.setStatus(st);
                    v.setAvailable(st.equalsIgnoreCase("Available"));
                    System.out.println("Status updated.");
                }
                default -> System.out.println("Cancelled or invalid.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            sc.nextLine();
        }
    }
}
