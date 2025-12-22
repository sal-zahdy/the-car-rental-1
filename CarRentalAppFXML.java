package com.example.carrentalba2a;

import com.example.carrentalba2a.model.*;
import com.example.carrentalba2a.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CarRentalAppFXML extends Application {

    private ArrayList<Vehicle> vehicles;
    private HashMap<String, User> users;

    @Override
    public void start(Stage stage) {
        this.vehicles = initializeVehicles();
        this.users = new HashMap<>();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load(), 800, 600);

            LoginController controller = loader.getController();
            controller.setUsers(users);

            // Pass vehicles to dashboard via static method or singleton
            DashboardHolder.setVehicles(vehicles);

            stage.setTitle("Car Rental System");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Vehicle> initializeVehicles() {
        ArrayList<Vehicle> list = new ArrayList<>();

        list.add(new Car("Civic", "Honda", new Date(), new Date(), "Red",
                "Available", 'A', 300, 40, 6000, 5, "Petrol"));
        list.add(new Car("Corolla", "Toyota", new Date(), new Date(), "Black",
                "Available", 'A', 280, 35, 5800, 5, "Diesel"));
        list.add(new Car("Accent", "Hyundai", new Date(), new Date(), "Silver",
                "Available", 'A', 250, 30, 5000, 5, "Petrol"));
        list.add(new Car("Camry", "Toyota", new Date(), new Date(), "White",
                "Available", 'B', 350, 45, 7000, 5, "Petrol"));
        list.add(new Car("Accord", "Honda", new Date(), new Date(), "Black",
                "Available", 'B', 360, 46, 7200, 5, "Petrol"));
        list.add(new Car("Mercedes E-Class", "Mercedes", new Date(), new Date(), "Black",
                "Available", 'L', 800, 100, 16000, 5, "Petrol"));
        list.add(new Car("BMW 5 Series", "BMW", new Date(), new Date(), "Blue",
                "Available", 'L', 780, 98, 15600, 5, "Petrol"));
        list.add(new Car("Prado", "Toyota", new Date(), new Date(), "White",
                "Available", 'S', 600, 75, 12000, 7, "Diesel"));
        list.add(new Car("Fortuner", "Toyota", new Date(), new Date(), "Black",
                "Available", 'S', 580, 72, 11600, 7, "Diesel"));

        list.add(new Van("HiAce", "Toyota", new Date(), new Date(), "White",
                "Available", 'C', 400, 60, 8000, 1500, 2, false));
        list.add(new Van("Sprinter", "Mercedes", new Date(), new Date(), "White",
                "Available", 'D', 550, 80, 11000, 2500, 3, false));
        list.add(new Van("Transit", "Ford", new Date(), new Date(), "Blue",
                "Available", 'D', 500, 75, 10000, 2000, 2, false));
        list.add(new Van("Cold Truck", "Isuzu", new Date(), new Date(), "White",
                "Available", 'R', 600, 90, 12000, 1500, 2, true));

        list.add(new Motorcycle("Ninja", "Kawasaki", new Date(), new Date(), "Green",
                "Available", 'M', 150, 20, 2500, "Petrol", "Manual"));
        list.add(new Motorcycle("R1", "Yamaha", new Date(), new Date(), "Blue",
                "Available", 'M', 200, 25, 4000, "Petrol", "Manual"));
        list.add(new Motorcycle("Vespa", "Piaggio", new Date(), new Date(), "Red",
                "Available", 'S', 100, 15, 2000, "Petrol", "Automatic"));
        list.add(new Motorcycle("GS1250", "BMW", new Date(), new Date(), "Gray",
                "Available", 'T', 350, 45, 7000, "Petrol", "Manual"));

        return list;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

