/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TheCarRental;

/**
 *
 * @author Alzah
 */
import java.util.Comparator;

public class VehicleComparators {

    // Sort by company (A -> Z)
    public static final Comparator<Vehicle> BY_COMPANY = (v1, v2) -> {
        String a = v1.getCompany() == null ? "" : v1.getCompany();
        String b = v2.getCompany() == null ? "" : v2.getCompany();
        return a.compareToIgnoreCase(b);
    };

    // Sort by class (A, B, C, ...). If same class, use model.
    public static final Comparator<Vehicle> BY_CLASS = (v1, v2) -> {
        int c = Character.compare(v1.getVehicleClass(), v2.getVehicleClass());
        if (c == 0) return v1.getModel().compareToIgnoreCase(v2.getModel());
        return c;
    };

    // Sort by availability (available first)
    public static final Comparator<Vehicle> BY_AVAILABILITY = (v1, v2) -> {
        // available true -> should come first
        return Boolean.compare(!v1.isAvailable(), !v2.isAvailable()); // false < true => available first
    };

    // Sort by price per day
    public static final Comparator<Vehicle> BY_PRICE_DAY = (v1, v2) -> Integer.compare(v1.getPricePerDay(), v2.getPricePerDay());

    // Sort by price per hour
    public static final Comparator<Vehicle> BY_PRICE_HOUR = (v1, v2) -> Integer.compare(v1.getPricePerHour(), v2.getPricePerHour());

    // Sort by price per month
    public static final Comparator<Vehicle> BY_PRICE_MONTH = (v1, v2) -> Integer.compare(v1.getPricePerMonth(), v2.getPricePerMonth());
}

