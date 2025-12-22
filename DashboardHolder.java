package com.example.carrentalba2a;

import com.example.carrentalba2a.model.Vehicle;

import java.util.ArrayList;

// Helper class to pass vehicles between controllers
    public class DashboardHolder {
    private static ArrayList<Vehicle> vehicles;

    public static void setVehicles(ArrayList<Vehicle> v) {
        vehicles = v;
    }

    public static ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
}
