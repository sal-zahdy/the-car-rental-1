package com.example.carrentalba2a.controller;

import com.example.carrentalba2a.model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class RentalController {
    @FXML private Label titleLabel;
    @FXML private ComboBox<String> filterBox;
    @FXML private ComboBox<String> sortBox;
    @FXML private ListView<Vehicle> vehicleListView;

    private User currentUser;
    private HashMap<String, User> users;
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Vehicle> filteredVehicles;
    private String vehicleType;

    public void setUser(User user) {
        this.currentUser = user;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setVehicleType(String type) {
        this.vehicleType = type;
        titleLabel.setText("Rent a " + type);

        filterBox.getItems().addAll("All", "Available Only", "Class A", "Class B", "Class L", "Class S");
        filterBox.setValue("All");

        sortBox.getItems().addAll("By Model", "By Company", "By Price (Day)", "By Price (Hour)");
        sortBox.setValue("By Model");

        filteredVehicles = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (type.equals("Car") && v instanceof Car) {
                filteredVehicles.add(v);
            } else if (type.equals("Van") && v instanceof Van) {
                filteredVehicles.add(v);
            } else if (type.equals("Motorcycle") && v instanceof Motorcycle) {
                filteredVehicles.add(v);
            }
        }

        vehicleListView.getItems().addAll(filteredVehicles);
        vehicleListView.setCellFactory(param -> new ListCell<Vehicle>() {
            @Override
            protected void updateItem(Vehicle vehicle, boolean empty) {
                super.updateItem(vehicle, empty);
                setText(empty || vehicle == null ? null : vehicle.toString());
            }
        });
    }

    @FXML
    private void handleApplyFilter() {
        ArrayList<Vehicle> result = new ArrayList<>(filteredVehicles);

        String filter = filterBox.getValue();
        if (filter.equals("Available Only")) {
            result.removeIf(v -> !v.isAvailable());
        } else if (filter.startsWith("Class")) {
            char classChar = filter.charAt(filter.length() - 1);
            result.removeIf(v -> v.getVehicleClass() != classChar);
        }

        String sort = sortBox.getValue();
        if (sort.equals("By Model")) {
            Collections.sort(result);
        } else if (sort.equals("By Company")) {
            result.sort(VehicleComparators.BY_COMPANY);
        } else if (sort.equals("By Price (Day)")) {
            result.sort(VehicleComparators.BY_PRICE_DAY);
        } else if (sort.equals("By Price (Hour)")) {
            result.sort(VehicleComparators.BY_PRICE_HOUR);
        }

        vehicleListView.getItems().clear();
        vehicleListView.getItems().addAll(result);
    }

    @FXML
    private void handleRent() {
        Vehicle selected = vehicleListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/checkout.fxml"));
                Scene scene = new Scene(loader.load());
                CheckoutController controller = loader.getController();
                controller.setUser(currentUser);
                controller.setUsers(users);
                controller.setVehicles(vehicles);
                controller.setVehicle(selected);
                Stage stage = (Stage) titleLabel.getScene().getWindow();
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
            Stage stage = (Stage) titleLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}