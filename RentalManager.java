/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheCarRental;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Alzah
 */
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
            System.out.println("2. Filter Vehicles");
            System.out.println("3. Sort Vehicles");
            System.out.println("4. Rent Vehicle");
            System.out.println("5. Return Vehicle");
            System.out.println("6. View Current Rentals");
            System.out.println("7. View Rental History");
            System.out.println("8. Edit Vehicle (no add/remove/search)");
            System.out.println("9. Exit");
            System.out.print("Choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine(); // consume newline
                switch (choice) {
                    case 1 -> listVehicles();
                    case 2 -> filterVehiclesMenu();
                    case 3 -> sortVehiclesMenu();
                    case 4 -> rentFlow();
                    case 5 -> returnFlow();
                    case 6 -> showCurrentRentals();
                    case 7 -> showRentalHistory();
                    case 8 -> editVehicle();
                    case 9 -> System.out.println("Goodbye!");
                    default -> System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter numbers only.");
                sc.nextLine(); // clear
            }
        } while (choice != 9);
    }

    // ============= LIST VEHICLES (Simple List) =============
    private void listVehicles() {
        System.out.println("\n=== LIST ALL VEHICLES ===");
        printVehicleList(vehicles);
    }

    // ============= FILTER VEHICLES MENU =============
    private void filterVehiclesMenu() {
        System.out.println("\n=== FILTER VEHICLES ===");
        System.out.println("1. By Vehicle Type (Car/Van/Motorcycle)");
        System.out.println("2. By Vehicle Class (A, B, C, L, S, etc.)");
        System.out.println("3. By Availability (Available/Booked)");
        System.out.println("4. By Company/Brand");
        System.out.println("5. By Price Range (per day)");
        System.out.println("6. Cancel");
        System.out.print("Choice: ");
        
        try {
            int choice = sc.nextInt();
            sc.nextLine();
            
            ArrayList<Vehicle> filteredList = new ArrayList<>();
            
            switch (choice) {
                case 1 -> filteredList = filterByType();
                case 2 -> filteredList = filterByClass();
                case 3 -> filteredList = filterByAvailability();
                case 4 -> filteredList = filterByCompany();
                case 5 -> filteredList = filterByPrice();
                case 6 -> {
                    System.out.println("Cancelled.");
                    return;
                }
                default -> {
                    System.out.println("Invalid choice.");
                    return;
                }
            }
            
            if (filteredList.isEmpty()) {
                System.out.println("No vehicles match your filter criteria.");
            } else {
                System.out.println("\n=== FILTERED RESULTS (" + filteredList.size() + " vehicles) ===");
                printVehicleList(filteredList);
                
                // Option to sort the filtered results
                System.out.print("\nSort filtered results? (y/n): ");
                String sortChoice = sc.nextLine();
                if (sortChoice.equalsIgnoreCase("y")) {
                    sortFilteredResults(filteredList);
                }
            }
            
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number.");
            sc.nextLine();
        }
    }
    
    // ============= FILTERING METHODS =============
    
    private ArrayList<Vehicle> filterByType() {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        
        System.out.println("\n=== FILTER BY VEHICLE TYPE ===");
        System.out.println("1. Cars only");
        System.out.println("2. Vans only");
        System.out.println("3. Motorcycles only");
        System.out.println("4. All types (no filter)");
        System.out.print("Choice: ");
        
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1 -> {
                for (Vehicle v : vehicles) {
                    if (v instanceof Car) filtered.add(v);
                }
                System.out.println("Found " + filtered.size() + " cars.");
            }
            case 2 -> {
                for (Vehicle v : vehicles) {
                    if (v instanceof Van) filtered.add(v);
                }
                System.out.println("Found " + filtered.size() + " vans.");
            }
            case 3 -> {
                for (Vehicle v : vehicles) {
                    if (v instanceof Motorcycle) filtered.add(v);
                }
                System.out.println("Found " + filtered.size() + " motorcycles.");
            }
            case 4 -> {
                filtered.addAll(vehicles);
                System.out.println("Showing all " + filtered.size() + " vehicles.");
            }
            default -> System.out.println("Invalid choice. Showing all vehicles.");
        }
        
        return filtered;
    }
    
    private ArrayList<Vehicle> filterByClass() {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        
        System.out.println("\n=== FILTER BY VEHICLE CLASS ===");
        System.out.println("Available Classes:");
        System.out.println("A - Economy Cars");
        System.out.println("B - Mid-size Sedans");
        System.out.println("C - Commercial Vans");
        System.out.println("D - Large Vans");
        System.out.println("L - Luxury Cars");
        System.out.println("S - SUV/4x4 Vehicles");
        System.out.println("M - Sport Motorcycles");
        System.out.println("E - Electric/Hybrid Vehicles");
        System.out.println("T - Touring Motorcycles");
        System.out.println("R - Refrigerated Vans");
        System.out.println("P - Passenger Vans");
        System.out.print("\nEnter class letter (or press Enter for all): ");
        
        String input = sc.nextLine().toUpperCase().trim();
        
        if (input.isEmpty()) {
            filtered.addAll(vehicles);
            System.out.println("Showing all " + filtered.size() + " vehicles.");
        } else if (input.length() == 1) {
            char filterClass = input.charAt(0);
            
            for (Vehicle v : vehicles) {
                if (v.getVehicleClass() == filterClass) {
                    filtered.add(v);
                }
            }
            
            if (filtered.isEmpty()) {
                System.out.println("No vehicles found in class '" + filterClass + "'.");
            } else {
                System.out.println("Found " + filtered.size() + " vehicles in class '" + filterClass + "'.");
            }
        } else {
            System.out.println("Invalid input. Showing all vehicles.");
            filtered.addAll(vehicles);
        }
        
        return filtered;
    }
    
    private ArrayList<Vehicle> filterByAvailability() {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        
        System.out.println("\n=== FILTER BY AVAILABILITY ===");
        System.out.println("1. Available vehicles only");
        System.out.println("2. Booked vehicles only");
        System.out.println("3. All vehicles");
        System.out.print("Choice: ");
        
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1 -> {
                for (Vehicle v : vehicles) {
                    if (v.isAvailable()) filtered.add(v);
                }
                System.out.println("Found " + filtered.size() + " available vehicles.");
            }
            case 2 -> {
                for (Vehicle v : vehicles) {
                    if (!v.isAvailable()) filtered.add(v);
                }
                System.out.println("Found " + filtered.size() + " booked vehicles.");
            }
            case 3 -> {
                filtered.addAll(vehicles);
                System.out.println("Showing all " + filtered.size() + " vehicles.");
            }
            default -> {
                System.out.println("Invalid choice. Showing all vehicles.");
                filtered.addAll(vehicles);
            }
        }
        
        return filtered;
    }
    
    private ArrayList<Vehicle> filterByCompany() {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        
        System.out.println("\n=== FILTER BY COMPANY/BRAND ===");
        System.out.print("Enter company name (or part of name, press Enter for all): ");
        String companyName = sc.nextLine().toLowerCase().trim();
        
        if (companyName.isEmpty()) {
            filtered.addAll(vehicles);
            System.out.println("Showing all " + filtered.size() + " vehicles.");
        } else {
            for (Vehicle v : vehicles) {
                if (v.getCompany().toLowerCase().contains(companyName)) {
                    filtered.add(v);
                }
            }
            
            if (filtered.isEmpty()) {
                System.out.println("No vehicles found from companies containing '" + companyName + "'.");
            } else {
                System.out.println("Found " + filtered.size() + " vehicles from companies containing '" + companyName + "'.");
            }
        }
        
        return filtered;
    }
    
    private ArrayList<Vehicle> filterByPrice() {
        ArrayList<Vehicle> filtered = new ArrayList<>();
        
        System.out.println("\n=== FILTER BY PRICE RANGE ===");
        System.out.println("Filter by daily rental price.");
        System.out.print("Enter minimum price (or 0 for no minimum): ");
        int minPrice = sc.nextInt();
        System.out.print("Enter maximum price (or 0 for no maximum): ");
        int maxPrice = sc.nextInt();
        sc.nextLine();
        
        // Validate input
        if (minPrice < 0 || maxPrice < 0) {
            System.out.println("Price cannot be negative. Showing all vehicles.");
            filtered.addAll(vehicles);
            return filtered;
        }
        
        if (maxPrice > 0 && minPrice > maxPrice) {
            System.out.println("Minimum price cannot be greater than maximum price. Showing all vehicles.");
            filtered.addAll(vehicles);
            return filtered;
        }
        
        for (Vehicle v : vehicles) {
            int price = v.getPricePerDay();
            boolean meetsMin = (minPrice == 0) || (price >= minPrice);
            boolean meetsMax = (maxPrice == 0) || (price <= maxPrice);
            
            if (meetsMin && meetsMax) {
                filtered.add(v);
            }
        }
        
        String rangeText = "";
        if (minPrice > 0 && maxPrice > 0) {
            rangeText = minPrice + " - " + maxPrice;
        } else if (minPrice > 0) {
            rangeText = minPrice + " and above";
        } else if (maxPrice > 0) {
            rangeText = "up to " + maxPrice;
        } else {
            rangeText = "any price";
        }
        
        System.out.println("Found " + filtered.size() + " vehicles in price range: " + rangeText + " per day.");
        
        return filtered;
    }
    
    // ============= SORT VEHICLES MENU =============
    private void sortVehiclesMenu() {
        System.out.println("\n=== SORT VEHICLES ===");
        System.out.println("1. By Model (A to Z)");
        System.out.println("2. By Company/Brand (A to Z)");
        System.out.println("3. By Price per Day (Low to High)");
        System.out.println("4. By Price per Hour (Low to High)");
        System.out.println("5. By Price per Month (Low to High)");
        System.out.println("6. Cancel");
        System.out.print("Choice: ");
        
        try {
            int choice = sc.nextInt();
            sc.nextLine();
            
            ArrayList<Vehicle> sortedList = new ArrayList<>(vehicles);
            
            switch (choice) {
                case 1 -> {
                    Collections.sort(sortedList);
                    System.out.println("\nSorted by Model (A to Z):");
                }
                case 2 -> {
                    sortedList.sort(VehicleComparators.BY_COMPANY);
                    System.out.println("\nSorted by Company (A to Z):");
                }
                case 3 -> {
                    sortedList.sort(VehicleComparators.BY_PRICE_DAY);
                    System.out.println("\nSorted by Price per Day (Low to High):");
                }
                case 4 -> {
                    sortedList.sort(VehicleComparators.BY_PRICE_HOUR);
                    System.out.println("\nSorted by Price per Hour (Low to High):");
                }
                case 5 -> {
                    sortedList.sort(VehicleComparators.BY_PRICE_MONTH);
                    System.out.println("\nSorted by Price per Month (Low to High):");
                }
                case 6 -> {
                    System.out.println("Cancelled.");
                    return;
                }
                default -> {
                    System.out.println("Invalid choice.");
                    return;
                }
            }
            
            printVehicleList(sortedList);
            
        } catch (InputMismatchException e) {
            System.out.println("Please enter a number.");
            sc.nextLine();
        }
    }
    
    // ============= SORT FILTERED RESULTS =============
    private void sortFilteredResults(ArrayList<Vehicle> filteredList) {
        System.out.println("\n=== SORT FILTERED RESULTS ===");
        System.out.println("1. By Model (A to Z)");
        System.out.println("2. By Price per Day (Low to High)");
        System.out.println("3. By Price per Hour (Low to High)");
        System.out.println("4. Cancel (keep current order)");
        System.out.print("Choice: ");
        
        int choice = sc.nextInt();
        sc.nextLine();
        
        switch (choice) {
            case 1 -> {
                Collections.sort(filteredList);
                System.out.println("\nSorted by Model (A to Z):");
            }
            case 2 -> {
                filteredList.sort(VehicleComparators.BY_PRICE_DAY);
                System.out.println("\nSorted by Price per Day (Low to High):");
            }
            case 3 -> {
                filteredList.sort(VehicleComparators.BY_PRICE_HOUR);
                System.out.println("\nSorted by Price per Hour (Low to High):");
            }
            case 4 -> {
                System.out.println("Keeping current order.");
                return;
            }
            default -> {
                System.out.println("Invalid choice. Keeping current order.");
                return;
            }
        }
        
        System.out.println("\n=== SORTED FILTERED RESULTS (" + filteredList.size() + " vehicles) ===");
        for (int i = 0; i < filteredList.size(); i++) {
            System.out.printf("%d) %s%n", i, filteredList.get(i).toString());
        }
    }
    
    // ============= RENT FLOW =============
    private void rentFlow() {
        System.out.println("\n=== RENT A VEHICLE ===");
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

    // ============= RETURN FLOW =============
    private void returnFlow() {
        System.out.println("\n=== RETURN A VEHICLE ===");
        showCurrentRentals();
        if (user.getCurrentBookings().isEmpty()) {
            System.out.println("You have no current rentals to return.");
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

    // ============= SHOW CURRENT RENTALS =============
    private void showCurrentRentals() {
        System.out.println("\n=== YOUR CURRENT RENTALS ===");
        ArrayList<Booking> current = user.getCurrentBookings();
        if (current.isEmpty()) {
            System.out.println("No current rentals.");
            return;
        }
        System.out.println("You have " + current.size() + " active rental(s):");
        for (int i = 0; i < current.size(); i++) {
            System.out.printf("%d) %s%n", i, current.get(i).toString());
        }
    }

    // ============= SHOW RENTAL HISTORY =============
    private void showRentalHistory() {
        System.out.println("\n=== YOUR RENTAL HISTORY ===");
        ArrayList<Booking> hist = user.getRentalHistory();
        if (hist.isEmpty()) {
            System.out.println("No rental history.");
            return;
        }
        System.out.println("Total rentals: " + hist.size());
        int totalSpent = 0;
        for (Booking b : hist) {
            totalSpent += b.getTotalPrice();
        }
        System.out.println("Total spent: " + totalSpent + " EGP");
        System.out.println("\nAll rentals:");
        for (int i = 0; i < hist.size(); i++) {
            System.out.printf("%d) %s%n", i, hist.get(i).toString());
        }
    }

    // ============= EDIT VEHICLE =============
    private void editVehicle() {
        System.out.println("\n=== EDIT VEHICLE ===");
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

            System.out.println("What would you like to edit?");
            System.out.println("1. Change color");
            System.out.println("2. Change price per day");
            System.out.println("3. Change price per hour");
            System.out.println("4. Change price per month");
            System.out.println("5. Change status (Available/Booked)");
            System.out.println("6. Cancel");
            System.out.print("Choice: ");
            int ch = sc.nextInt();
            sc.nextLine();
            switch (ch) {
                case 1 -> {
                    System.out.print("New color: ");
                    String col = sc.nextLine();
                    v.setColor(col);
                    System.out.println("Color updated to: " + col);
                }
                case 2 -> {
                    System.out.print("New price per day: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    if (p < 0) {
                        System.out.println("Price cannot be negative.");
                    } else {
                        v.setPricePerDay(p);
                        System.out.println("Price per day updated to: " + p + " EGP");
                    }
                }
                case 3 -> {
                    System.out.print("New price per hour: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    if (p < 0) {
                        System.out.println("Price cannot be negative.");
                    } else {
                        v.setPricePerHour(p);
                        System.out.println("Price per hour updated to: " + p + " EGP");
                    }
                }
                case 4 -> {
                    System.out.print("New price per month: ");
                    int p = sc.nextInt();
                    sc.nextLine();
                    if (p < 0) {
                        System.out.println("Price cannot be negative.");
                    } else {
                        v.setPricePerMonth(p);
                        System.out.println("Price per month updated to: " + p + " EGP");
                    }
                }
                case 5 -> {
                    System.out.print("Enter status (Available/Booked): ");
                    String st = sc.nextLine();
                    if (st.equalsIgnoreCase("Available") || st.equalsIgnoreCase("Booked")) {
                        v.setStatus(st);
                        v.setAvailable(st.equalsIgnoreCase("Available"));
                        System.out.println("Status updated to: " + st);
                    } else {
                        System.out.println("Invalid status. Use 'Available' or 'Booked'.");
                    }
                }
                case 6 -> System.out.println("Edit cancelled.");
                default -> System.out.println("Invalid choice.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number.");
            sc.nextLine();
        }
    }
    
    // ============= HELPER METHODS =============
    
    private void printVehicleList(ArrayList<Vehicle> list) {
        if (list.isEmpty()) {
            System.out.println("No vehicles to display.");
            return;
        }
        
        // Count vehicles by type
        int cars = 0, vans = 0, motorcycles = 0;
        int available = 0, booked = 0;
        
        for (Vehicle v : list) {
            if (v instanceof Car) cars++;
            else if (v instanceof Van) vans++;
            else if (v instanceof Motorcycle) motorcycles++;
            
            if (v.isAvailable()) available++;
            else booked++;
        }
        
        System.out.println("\nVehicle Summary:");
        System.out.printf("  Total: %d vehicles%n", list.size());
        System.out.printf("  By Type: Cars: %d | Vans: %d | Motorcycles: %d%n", cars, vans, motorcycles);
        System.out.printf("  By Status: Available: %d | Booked: %d%n", available, booked);
        
        System.out.println("\n=== VEHICLE LIST ===");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%3d) %s%n", i, list.get(i).toString());
        }
        
        // Show price range if sorted by price
        if (list.size() > 0) {
            int minPrice = list.get(0).getPricePerDay();
            int maxPrice = list.get(0).getPricePerDay();
            for (Vehicle v : list) {
                int price = v.getPricePerDay();
                if (price < minPrice) minPrice = price;
                if (price > maxPrice) maxPrice = price;
            }
            System.out.printf("\nDaily Price Range: %d - %d EGP%n", minPrice, maxPrice);
        }
    }
}