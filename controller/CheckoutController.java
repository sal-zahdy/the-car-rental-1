package com.example.carrentalba2a.controller;

import com.example.carrentalba2a.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class CheckoutController {
    @FXML private Label titleLabel;
    @FXML private Label vehicleInfoLabel;
    @FXML private TextField daysField;
    @FXML private TextField hoursField;
    @FXML private Label priceLabel;

    private User currentUser;
    private HashMap<String, User> users;
    private ArrayList<Vehicle> vehicles;
    private Vehicle selectedVehicle;

    public void setUser(User user) {
        this.currentUser = user;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setVehicle(Vehicle vehicle) {
        this.selectedVehicle = vehicle;
        titleLabel.setText("Checkout - " + vehicle.getModel());
        vehicleInfoLabel.setText(vehicle.toString());
    }

    @FXML
    private void handleCalculate() {
        try {
            int days = Integer.parseInt(daysField.getText());
            int hours = Integer.parseInt(hoursField.getText());
            int price = selectedVehicle.calculatePrice(days, hours);
            priceLabel.setText("Total Price: " + price + " EGP");
        } catch (NumberFormatException e) {}
    }

    @FXML
    private void handleConfirm() {
        try {
            int days = Integer.parseInt(daysField.getText());
            int hours = Integer.parseInt(hoursField.getText());
            currentUser.rentVehicle(selectedVehicle, days, hours);
            loadDashboard();
        } catch (Exception e) {}
    }

    @FXML
    private void handleBack() {
        loadDashboard();
    }

    private void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            DashboardController controller = loader.getController();
            controller.setUser(currentUser);
            controller.setUsers(users);
            controller.setVehicles(vehicles);
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}