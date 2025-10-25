package Controllers;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneController {


    // link to login controller
    private LoginController loginController;
    public void setLoginController(LoginController loginController){
        this.loginController = loginController;
    }

    // link to sign up controller
    private SignupController signupController;
    public void setSignupController(SignupController signupController){
        this.signupController = signupController;
    }


        public void switchScenes(Stage stage, String fxmlPath) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + fxmlPath));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.setTitle("Bookify");
            stage.setResizable(false);
            stage.show();
    }





}
