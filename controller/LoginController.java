package com.example.carrentalba2a.controller;

import com.example.carrentalba2a.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.util.HashMap;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    private HashMap<String, User> users;

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        if (users.containsKey(username)) {
            User user = users.get(username);
            loadDashboard(user);
        }
    }

    @FXML
    private void handleSignUp() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/signup.fxml"));
            Scene scene = new Scene(loader.load());
            SignUpController controller = loader.getController();
            controller.setUsers(users);
            Stage stage = (Stage) usernameField.getScene().getWindow();
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
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}