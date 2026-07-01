package UI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

import model.Expense;

public class AddExpenseDialog {

    private Expense expense;

    public Expense getExpense() {
        return expense;
    }

    public void show() {

        Stage stage = new Stage();

        stage.setTitle("Add Expense");

        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Date

        Label dateLabel = new Label("Date");

        DatePicker datePicker = new DatePicker();

        datePicker.setValue(LocalDate.now());

        // Category

        Label categoryLabel = new Label("Category");

        ComboBox<String> categoryBox = new ComboBox<>();

        categoryBox.getItems().addAll(
                "Food",
                "Transport",
                "Shopping",
                "Rent",
                "Entertainment",
                "Bills",
                "Health",
                "Education",
                "Other"
        );

        categoryBox.setValue("Food");

        // Description

        Label descriptionLabel = new Label("Description");

        TextField descriptionField = new TextField();

        descriptionField.setPromptText(
                "Enter description"
        );

        // Amount

        Label amountLabel = new Label("Amount (KES)");

        TextField amountField = new TextField();

        amountField.setPromptText(
                "Enter amount"
        );

        // Save Button

        Button saveButton = new Button("Save Expense");

        saveButton.setMaxWidth(Double.MAX_VALUE);

        saveButton.setOnAction(e -> {

            try {

                LocalDate date =
                        datePicker.getValue();

                String category =
                        categoryBox.getValue();

                String description =
                        descriptionField.getText();

                if(description.isBlank()){

                    throw new IllegalArgumentException(
                            "Description cannot be empty."
                    );

                }

                double amount =
                        Double.parseDouble(
                                amountField.getText()
                        );

                if(amount <= 0){

                    throw new IllegalArgumentException(
                            "Amount must be greater than zero."
                    );

                }

                expense = new Expense(

                        date,

                        category,

                        description,

                        amount

                );

                stage.close();

            }

            catch(NumberFormatException ex){

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setTitle("Invalid Amount");

                alert.setHeaderText(null);

                alert.setContentText(
                        "Please enter a valid numeric amount."
                );

                alert.showAndWait();

            }

            catch(IllegalArgumentException ex){

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setTitle("Input Error");

                alert.setHeaderText(null);

                alert.setContentText(
                        ex.getMessage()
                );

                alert.showAndWait();

            }

        });

        root.getChildren().addAll(

                dateLabel,
                datePicker,

                categoryLabel,
                categoryBox,

                descriptionLabel,
                descriptionField,

                amountLabel,
                amountField,

                saveButton

        );

        Scene scene =
                new Scene(root, 350, 380);

        stage.setScene(scene);

        stage.showAndWait();

    }

}