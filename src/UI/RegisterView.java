package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.scene.Scene;
import javafx.stage.Stage;

import database.UserDAO;
import model.User;

public class RegisterView {

    private Stage stage;

    private VBox root;

    private TextField fullNameField;
    private TextField emailField;
    private TextField phoneField;
    private TextField usernameField;

    private PasswordField passwordField;
    private PasswordField confirmPasswordField;

    private Button registerButton;
    private Button backButton;

    public RegisterView(Stage stage) {

        this.stage = stage;

        root = new VBox(10);

        root.setPadding(new Insets(20));

        root.setAlignment(Pos.CENTER);

        Label title =
                new Label("Create Account");

        title.setStyle(
                "-fx-font-size:24;" +
                "-fx-font-weight:bold;"
        );

        fullNameField = new TextField();
        fullNameField.setPromptText("Full Name");

        emailField = new TextField();
        emailField.setPromptText("Email");

        phoneField = new TextField();
        phoneField.setPromptText("Phone Number");

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm Password");

        registerButton = new Button("Register");

        backButton = new Button("Back to Login");

       registerButton.setOnAction(e -> {

    if (!passwordField.getText().equals(confirmPasswordField.getText())) {

        new Alert(Alert.AlertType.ERROR,
                "Passwords do not match.")
                .show();

        return;
    }

    User user = new User(

            fullNameField.getText(),

            emailField.getText(),

            phoneField.getText(),

            usernameField.getText(),

            passwordField.getText()

    );

    UserDAO dao = new UserDAO();

    if (dao.register(user)) {

        new Alert(Alert.AlertType.INFORMATION,
                "Registration successful!")
                .showAndWait();

        LoginView login =
                new LoginView(stage);

        stage.setScene(
                new Scene(login.getView(), 450, 500)
        );

    } else {

        new Alert(Alert.AlertType.ERROR,
                "Registration failed.")
                .show();

    }

});

        backButton.setOnAction(e -> {

    LoginView loginView =
            new LoginView(stage);

    Scene scene =
            new Scene(
                    loginView.getView(),
                    450,
                    500
            );

    stage.setScene(scene);

});

        root.getChildren().addAll(

                title,

                fullNameField,

                emailField,

                phoneField,

                usernameField,

                passwordField,

                confirmPasswordField,

                registerButton,

                backButton

        );

    }

    public Parent getView() {

        return root;

    }

}