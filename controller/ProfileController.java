package com.example.carrentalba2a.controller;

import com.example.carrentalba2a.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class ProfileController {
    @FXML private TextArea profileInfoArea;
    @FXML private Label statsLabel;
    @FXML private TextField nameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;

    private User currentUser;
    private HashMap<String, User> users;
    private ArrayList<Vehicle> vehicles;

    public void setUser(User user) {
        this.currentUser = user;
        profileInfoArea.setText(user.displayInfo());
        statsLabel.setText("Current Rentals: " + user.getCurrentRentsCount() + "\n" +
                "Total Rentals: " + user.getTotalRentsCount());
        nameField.setText(user.getName());
        phoneField.setText(user.getPhoneNumber());
        emailField.setText(user.getEmail());
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @FXML
    private void handleSave() {
        currentUser.setName(nameField.getText());
        currentUser.setPhoneNumber(phoneField.getText());
        currentUser.setEmail(emailField.getText());
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            DashboardController controller = loader.getController();
            controller.setUser(currentUser);
            controller.setUsers(users);
            controller.setVehicles(vehicles);
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}