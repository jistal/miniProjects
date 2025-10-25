import javafx.application.Application;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Main extends Application {

    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
        System.out.println(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Bookify");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.show();
    }

}