package UI;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

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

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.time.DayOfWeek;

import model.Expense;

public class DashboardView {

    //----------------------------------------------------
    // Root Layout
    //----------------------------------------------------

    private BorderPane root;

    //----------------------------------------------------
    // Data
    //----------------------------------------------------

    private ObservableList<Expense> expenses =
            FXCollections.observableArrayList();

    private double startingBalance = 0;

    private double currentBalance = 0;

    private double totalExpenses = 0;

    //----------------------------------------------------
    // Summary Labels
    //----------------------------------------------------

    private Label balanceValue;

    private Label expenseValue;

    private Label currentBalanceValue;

    //----------------------------------------------------
    // Balance Controls
    //----------------------------------------------------

    private TextField balanceField;

    private Button updateBalanceButton;

    //----------------------------------------------------
    // Expense Table
    //----------------------------------------------------

    private TableView<Expense> expenseTable;

    //----------------------------------------------------
    // Chart
    //----------------------------------------------------

    private BarChart<String, Number> weeklyChart;

    private XYChart.Series<String, Number> weeklySeries;

    //----------------------------------------------------
    // Buttons
    //----------------------------------------------------

    private Button addExpenseButton;

    //----------------------------------------------------
    // Constructor
    //----------------------------------------------------

    public DashboardView() {

        root = new BorderPane();

        root.setPadding(new Insets(20));

        buildTopSection();

        buildChart();

    }
   private void buildTopSection() {

    Label title =
            new Label("Expense Tracker");

    title.setStyle(
            "-fx-font-size:24px;" +
            "-fx-font-weight:bold;"
    );

    balanceValue =
            new Label("KES 0.00");

    expenseValue =
            new Label("KES 0.00");

    currentBalanceValue =
            new Label("KES 0.00");

    GridPane cards =
            new GridPane();

    cards.setHgap(20);

    cards.add(createCard(
            "Starting Balance",
            balanceValue
    ),0,0);

    cards.add(createCard(
            "Total Expenses",
            expenseValue
    ),1,0);

    cards.add(createCard(
            "Current Balance",
            currentBalanceValue
    ),2,0);

    balanceField =
            new TextField();

    balanceField.setPromptText(
            "Enter Current Balance"
    );

    updateBalanceButton =
            new Button("Add Income");

    updateBalanceButton.setOnAction(e->{

        try{

            double income =
                    Double.parseDouble(
                            balanceField.getText()
                    );

            startingBalance += income;

            currentBalance += income;

            updateBalance();

        }

        catch(NumberFormatException ex){

            Alert alert =
                    new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Invalid Amount");

            alert.setContentText(
                    "Enter a valid number."
            );

            alert.showAndWait();

        }

    });

    HBox balanceBox =
            new HBox(
                    10,
                    balanceField,
                    updateBalanceButton
            );

    VBox top =
            new VBox(
                    15,
                    title,
                    cards,
                    balanceBox
            );

    root.setTop(top);

} 
private VBox createCard(String title, Label value){

    Label heading =
            new Label(title);

    heading.setStyle(
            "-fx-font-weight:bold;"
    );

    value.setStyle(
            "-fx-font-size:18px;"
    );

    VBox card =
            new VBox(
                    8,
                    heading,
                    value
            );

    card.setPadding(
            new Insets(15)
    );

    card.setStyle(
            "-fx-background-color:white;" +
            "-fx-border-color:lightgray;"
    );

    card.setPrefWidth(220);

    return card;
}

private void buildChart(){

    CategoryAxis xAxis =
            new CategoryAxis();

    NumberAxis yAxis =
            new NumberAxis();

    xAxis.setLabel("Day");

    yAxis.setLabel("KES");

    weeklyChart =
            new BarChart<>(
                    xAxis,
                    yAxis
            );

    weeklyChart.setLegendVisible(false);

    weeklyChart.setTitle(
            "Weekly Spending"
    );

    weeklySeries =
            new XYChart.Series<>();

    weeklyChart.getData().add(
            weeklySeries
    );

    VBox center =
            new VBox(20);

    buildExpenseTable();

    center.getChildren().addAll(
        
        weeklyChart,

        new Label("Recent Expenses"),

        expenseTable,

        addExpenseButton
    );

    VBox.setVgrow(
expenseTable,
Priority.ALWAYS

    );

    root.setCenter(center); 
}

private void buildExpenseTable(){

    expenseTable =
            new TableView<>();

    expenseTable.setItems(
            expenses
    );

    //------------------------------------------------
    // Date Column
    //------------------------------------------------

    TableColumn<Expense,String> dateColumn =
            new TableColumn<>("Date");

    dateColumn.setCellValueFactory(cell ->

            new SimpleStringProperty(

                    cell.getValue()
                            .getDate()
                            .toString()

            )
    );

    //------------------------------------------------
    // Category Column
    //------------------------------------------------

    TableColumn<Expense,String> categoryColumn =
            new TableColumn<>("Category");

    categoryColumn.setCellValueFactory(

            new PropertyValueFactory<>(
                    "category"
            )

    );

    //------------------------------------------------
    // Description Column
    //------------------------------------------------

    TableColumn<Expense,String> descriptionColumn =
            new TableColumn<>("Description");

    descriptionColumn.setCellValueFactory(

            new PropertyValueFactory<>(
                    "description"
            )

    );

    //------------------------------------------------
    // Amount Column
    //------------------------------------------------

    TableColumn<Expense,Double> amountColumn =
            new TableColumn<>("Amount");

    amountColumn.setCellValueFactory(

            new PropertyValueFactory<>(
                    "amount"
            )

    );

    //------------------------------------------------
    // Widths
    //------------------------------------------------

    dateColumn.setPrefWidth(120);

    categoryColumn.setPrefWidth(150);

    descriptionColumn.setPrefWidth(250);

    amountColumn.setPrefWidth(120);

    expenseTable.getColumns().addAll(

            dateColumn,

            categoryColumn,

            descriptionColumn,

            amountColumn

    );

    //------------------------------------------------
    // Add Expense Button
    //------------------------------------------------

    addExpenseButton =
            new Button(
                    "Add Expense"
            );

    addExpenseButton.setMaxWidth(
            Double.MAX_VALUE
    );

    addExpenseButton.setOnAction(e -> {

        AddExpenseDialog dialog =
                new AddExpenseDialog();

        dialog.show();

        Expense expense =
                dialog.getExpense();

        if (expense != null) {

    expenses.add(expense);

    totalExpenses += expense.getAmount();

    currentBalance -= expense.getAmount();

    updateBalance();

    refreshChart();
}

    });
}

        private void updateBalance() {

                balanceValue.setText(
                                String.format("KES %.2f", startingBalance)
                );

                expenseValue.setText(
                                String.format("KES %.2f", totalExpenses)
                );

                currentBalanceValue.setText(
                                String.format("KES %.2f", currentBalance)
                );

        }

        private void refreshChart() {

                weeklySeries.getData().clear();

                double monday = 0;
                double tuesday = 0;
                double wednesday = 0;
                double thursday = 0;
                double friday = 0;
                double saturday = 0;
                double sunday = 0;

                for (Expense expense : expenses) {

                        DayOfWeek day =
                                        expense.getDate().getDayOfWeek();

                        switch (day) {

                                case MONDAY:
                                        monday += expense.getAmount();
                                        break;

                                case TUESDAY:
                                        tuesday += expense.getAmount();
                                        break;

                                case WEDNESDAY:
                                        wednesday += expense.getAmount();
                                        break;

                                case THURSDAY:
                                        thursday += expense.getAmount();
                                        break;

                                case FRIDAY:
                                        friday += expense.getAmount();
                                        break;

                                case SATURDAY:
                                        saturday += expense.getAmount();
                                        break;

                                case SUNDAY:
                                        sunday += expense.getAmount();
                                        break;
                        }
                }

                weeklySeries.getData().add(new XYChart.Data<>("Mon", monday));
                weeklySeries.getData().add(new XYChart.Data<>("Tue", tuesday));
                weeklySeries.getData().add(new XYChart.Data<>("Wed", wednesday));
                weeklySeries.getData().add(new XYChart.Data<>("Thu", thursday));
                weeklySeries.getData().add(new XYChart.Data<>("Fri", friday));
                weeklySeries.getData().add(new XYChart.Data<>("Sat", saturday));
                weeklySeries.getData().add(new XYChart.Data<>("Sun", sunday));

        }

        public Parent getView() {

                return root;

        }

}