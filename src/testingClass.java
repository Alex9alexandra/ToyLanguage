import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class testingClass extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(new Label("Javafx works!"),300,200));
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}
