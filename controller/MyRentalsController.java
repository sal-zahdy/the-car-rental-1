package com.example.carrentalba2a.controller;

import com.example.carrentalba2a.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class MyRentalsController {
    @FXML private Label statsLabel;
    @FXML private ListView<Booking> currentListView;
    @FXML private ListView<Booking> historyListView;

    private User currentUser;
    private HashMap<String, User> users;
    private ArrayList<Vehicle> vehicles;

    public void setUser(User user) {
        this.currentUser = user;

        int totalSpent = 0;
        for (Booking b : user.getRentalHistory()) {
            totalSpent += b.getTotalPrice();
        }
        statsLabel.setText("Total Rentals: " + user.getRentalHistory().size() +
                " | Total Spent: " + totalSpent + " EGP");

        currentListView.getItems().addAll(user.getCurrentBookings());
        currentListView.setCellFactory(param -> new ListCell<Booking>() {
            @Override
            protected void updateItem(Booking booking, boolean empty) {
                super.updateItem(booking, empty);
                setText(empty || booking == null ? null : booking.toString());
            }
        });

        historyListView.getItems().addAll(user.getRentalHistory());
        historyListView.setCellFactory(param -> new ListCell<Booking>() {
            @Override
            protected void updateItem(Booking booking, boolean empty) {
                super.updateItem(booking, empty);
                setText(empty || booking == null ? null : booking.toString());
            }
        });
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @FXML
    private void handleReturn() {
        int selectedIndex = currentListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            currentUser.returnVehicle(selectedIndex);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/myrentals.fxml"));
                Scene scene = new Scene(loader.load());
                MyRentalsController controller = loader.getController();
                controller.setUser(currentUser);
                controller.setUsers(users);
                controller.setVehicles(vehicles);
                Stage stage = (Stage) statsLabel.getScene().getWindow();
                stage.setScene(scene);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
            Stage stage = (Stage) statsLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
