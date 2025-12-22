package com.example.carrentalba2a.controller;

import com.example.carrentalba2a.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class DashboardController {
    @FXML private Label welcomeLabel;
    @FXML private Label currentRentsLabel;
    @FXML private Label totalRentsLabel;

    private User currentUser;
    private HashMap<String, User> users;
    private ArrayList<Vehicle> vehicles;

    public void setUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Welcome, " + user.getName());
        currentRentsLabel.setText("Current Rentals: " + user.getCurrentRentsCount());
        totalRentsLabel.setText("Total Rentals: " + user.getTotalRentsCount());
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @FXML
    private void handleRentCar() {
        loadRentalPage("Car");
    }

    @FXML
    private void handleRentVan() {
        loadRentalPage("Van");
    }

    @FXML
    private void handleRentMotorcycle() {
        loadRentalPage("Motorcycle");
    }

    @FXML
    private void handleMyRentals() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/myrentals.fxml"));
            Scene scene = new Scene(loader.load());
            MyRentalsController controller = loader.getController();
            controller.setUser(currentUser);
            controller.setUsers(users);
            controller.setVehicles(vehicles);
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleProfile() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/profile.fxml"));
            Scene scene = new Scene(loader.load());
            ProfileController controller = loader.getController();
            controller.setUser(currentUser);
            controller.setUsers(users);
            controller.setVehicles(vehicles);
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleLogout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load());
            LoginController controller = loader.getController();
            controller.setUsers(users);
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRentalPage(String vehicleType) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/rental.fxml"));
            Scene scene = new Scene(loader.load());
            RentalController controller = loader.getController();
            controller.setUser(currentUser);
            controller.setUsers(users);
            controller.setVehicles(vehicles);
            controller.setVehicleType(vehicleType);
            Stage stage = (Stage) welcomeLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
