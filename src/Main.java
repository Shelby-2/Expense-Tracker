import UI.DashboardView;
import UI.LoginView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
DashboardView dashboard =
        new DashboardView();

Scene scene = new Scene(
        dashboard.getView(),
        900,
        600);
        

        stage.setTitle("Expense Tracker");

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}