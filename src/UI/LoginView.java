package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.scene.Scene;

import database.UserDAO;
import model.User;
import javafx.scene.control.Alert;

public class LoginView {

    private VBox root;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;
    private Button registerButton;

    private Stage stage;

    public LoginView(Stage stage) {

        this.stage = stage;

        root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label title = new Label("Expense Tracker");
        title.setStyle(
                "-fx-font-size: 24px;" +
                "-fx-font-weight: bold;"
        );

        usernameField = new TextField();
        usernameField.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        loginButton = new Button("Login");
        loginButton.setOnAction(e -> {

    String username = usernameField.getText().trim();
    String password = passwordField.getText();

    UserDAO dao = new UserDAO();

    User user = dao.login(username, password);

    if (user != null) {

        DashboardView dashboard = new DashboardView();

        Scene scene = new Scene(
                dashboard.getView(),
                1000,
                700
        );

        stage.setScene(scene);

    } else {

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setHeaderText("Login Failed");

        alert.setContentText("Invalid username or password.");

        alert.showAndWait();
    }

});

        registerButton = new Button("Register");
        registerButton.setOnAction(e -> {
            RegisterView registerView =
                 new RegisterView(stage);
              
        Scene scene = new Scene(
               registerView.getView(),
               500,
               650
        );

        stage.setScene(scene);

        });

        root.getChildren().addAll(
                title,
                usernameField,
                passwordField,
                loginButton,
                registerButton
        );
    }

    public Parent getView() {
        return root;
    }
}