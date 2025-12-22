package com.example.carrentalba2a.controller;

import com.example.carrentalba2a.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.HashMap;

public class SignUpController {
    @FXML private TextField nameField;
    @FXML private TextField usernameField;
    @FXML private TextField phoneField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    private HashMap<String, User> users;

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    @FXML
    private void handleSignUp() {
        User newUser = new User(nameField.getText(), usernameField.getText(),
                phoneField.getText(), emailField.getText());
        users.put(usernameField.getText(), newUser);
        loadDashboard(newUser);
    }

    @FXML
    private void handleBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load());
            LoginController controller = loader.getController();
            controller.setUsers(users);
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadDashboard(User user) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
            Scene scene = new Scene(loader.load());
            DashboardController controller = loader.getController();
            controller.setUser(user);
            controller.setUsers(users);
            controller.setVehicles(com.example.carrentalba2a.DashboardHolder.getVehicles());
            Stage stage = (Stage) nameField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
