package UI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import UI.AddExpenseDialog;
import model.Expense;

public class DashboardView {

    private BorderPane root;

    /* Balance */

    private Label balanceLabel;

    private TextField balanceField;

    private Button updateBalanceButton;

    private double balance = 0;

    private ListView<String> recentExpenses;

    /* Expense Table */

    private TableView<Expense> expenseTable;

    private ObservableList<Expense> expenses =
            FXCollections.observableArrayList();

    /* Weekly Chart */

    private BarChart<String, Number> chart;

    private XYChart.Series<String, Number> weeklySeries;

    public DashboardView() {

        root = new BorderPane();

        root.setPadding(new Insets(15));

        //-------------------------------------------------------
        // TOP SECTION
        //-------------------------------------------------------

        VBox topSection = new VBox(10);

        Label welcome = new Label("Welcome");

        welcome.setStyle(
                "-fx-font-size:22px;" +
                "-fx-font-weight:bold;"
        );

        balanceLabel = new Label("Current Balance : KES 0.00");

        balanceLabel.setStyle(
                "-fx-font-size:18px;"
        );

        balanceField = new TextField();

        balanceField.setPromptText(
                "Enter Current Balance"
        );

        updateBalanceButton =
                new Button("Update Balance");

        updateBalanceButton.setMaxWidth(
                Double.MAX_VALUE
        );

        updateBalanceButton.setOnAction(e -> {

            try {

                balance =
                        Double.parseDouble(
                                balanceField.getText()
                        );

                updateBalance();

            }

            catch(NumberFormatException ex){

                Alert alert =
                        new Alert(
                                Alert.AlertType.ERROR
                        );

                alert.setHeaderText(
                        "Invalid Balance"
                );

                alert.setContentText(
                        "Please enter numbers only."
                );

                alert.showAndWait();

            }

        });

        topSection.getChildren().addAll(

                welcome,

                balanceLabel,

                balanceField,

                updateBalanceButton

        );

        root.setTop(topSection);

        //-------------------------------------------------------
        // CHART
        //-------------------------------------------------------

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        chart =
                new BarChart<>(xAxis,yAxis);

        chart.setTitle(
                "Weekly Spending"
        );

        chart.setLegendVisible(false);

        weeklySeries =
                new XYChart.Series<>();

        chart.getData().add(
                weeklySeries
        );

        VBox actions = new VBox(10);

        Button addExpense =
                new Button("Add Expense");

        addExpense.setOnAction(e -> {

            AddExpenseDialog dialog =
                    new AddExpenseDialog();

            dialog.show();

            Expense expense =
                    dialog.getExpense();

            if(expense != null) {

                recentExpenses.getItems().add(
                        expense.toString()
                );

                balance -= expense.getAmount();

                balanceLabel.setText(
                        "Balance: KES " + balance
                );
            }
        });

        Button viewExpenses =
                new Button("View Expenses");

        Button reports =
                new Button("Reports");

        Button advice =
                new Button("Financial Advice");

        addExpense.setMaxWidth(
                Double.MAX_VALUE);

        viewExpenses.setMaxWidth(
                Double.MAX_VALUE);

        reports.setMaxWidth(
                Double.MAX_VALUE);

        advice.setMaxWidth(
                Double.MAX_VALUE);

        actions.getChildren().addAll(
                addExpense,
                viewExpenses,
                reports,
                advice
        );

        HBox center =
                new HBox(15);

        center.getChildren().addAll(
                chart,
                actions
        );

        root.setCenter(center);

        recentExpenses =
                new ListView<>();

        recentExpenses.getItems().addAll(
                "Food - KES 500",
                "Transport - KES 200",
                "Shopping - KES 1200"
        );

        root.setBottom(recentExpenses);
    }

    private void updateBalance() {
        balanceLabel.setText(
                "Current Balance : KES " + String.format("%.2f", balance)
        );
    }

    public Parent getView() {
        return root;
    }
}