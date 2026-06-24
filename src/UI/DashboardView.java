package UI;
import UI.AddExpenseDialog;
import model.Expense;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.chart.*;

public class DashboardView {

    private BorderPane root;
        private ListView<String> recentExpenses;
        private Label balanceLabel;
        private double balance = 15000;

    public DashboardView() {

        root = new BorderPane();

        root.setPadding(new Insets(15));

        VBox topSection = new VBox(10);

        Label welcome =
                new Label("Welcome Shelby");

        welcome.setStyle(
                "-fx-font-size: 18px;" +
                "-fx-font-weight: bold;"
        );

        balanceLabel =
                new Label(
                        "Balance: KES " + balance
                );

        balanceLabel.setStyle(
                "-fx-font-size:16px;"
        );

        topSection.getChildren().addAll(
                welcome,
                balanceLabel
        );

        root.setTop(topSection);

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        BarChart<String, Number> chart =
                new BarChart<>(xAxis, yAxis);

        chart.setTitle("Weekly Spending");

        XYChart.Series<String, Number> series =
                new XYChart.Series<>();

        series.getData().add(
                new XYChart.Data<>("Mon", 500));

        series.getData().add(
                new XYChart.Data<>("Tue", 1200));

        series.getData().add(
                new XYChart.Data<>("Wed", 700));

        series.getData().add(
                new XYChart.Data<>("Thu", 1000));

        series.getData().add(
                new XYChart.Data<>("Fri", 300));

        chart.getData().add(series);

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

    public Parent getView() {
        return root;
    }
}