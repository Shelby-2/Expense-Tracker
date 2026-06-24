package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Expense;

public class AddExpenseDialog {

    private Expense expense;

    public Expense getExpense() {
        return expense;
    }

    public void show() {

        Stage stage = new Stage();

        VBox root = new VBox(10);

        root.setPadding(new Insets(15));

        ComboBox<String> categoryBox =
                new ComboBox<>();

        categoryBox.getItems().addAll(
                "Food",
                "Transport",
                "Shopping",
                "Rent",
                "Entertainment",
                "Other"
        );

        categoryBox.setValue("Food");

        TextField descriptionField =
                new TextField();

        descriptionField.setPromptText(
                "Description"
        );

        TextField amountField =
                new TextField();

        amountField.setPromptText(
                "Amount"
        );

        Button saveButton =
                new Button("Save");

        saveButton.setOnAction(e -> {

            try {

                String category =
                        categoryBox.getValue();

                String description =
                        descriptionField.getText();

                double amount =
                        Double.parseDouble(
                                amountField.getText()
                        );

                expense =
                        new Expense(
                                category,
                                description,
                                amount
                        );

                stage.close();

            } catch(Exception ex) {

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setContentText(
                        "Invalid Amount"
                );

                alert.showAndWait();
            }
        });

        root.getChildren().addAll(
                new Label("Category"),
                categoryBox,

                new Label("Description"),
                descriptionField,

                new Label("Amount"),
                amountField,

                saveButton
        );

        Scene scene =
                new Scene(root,300,250);

        stage.setTitle(
                "Add Expense"
        );

        stage.setScene(scene);

        stage.showAndWait();
    }
}