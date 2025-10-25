package Controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController {


    private LoginController loginController;
    public void setSceneController(LoginController loginController){
        this.loginController = loginController;
    }


        public void switchScenes(Stage stage, String fxmlPath) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Bookify");
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
    }





}
