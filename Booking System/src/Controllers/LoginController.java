package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {



    private SceneController sceneController;


   @FXML private AnchorPane rootContainer;
   @FXML private AnchorPane loginContainer;
   @FXML private TextField loginUsernameField;
   @FXML private TextField loginPasswordField;
   @FXML private Label loginAppTitle;
   @FXML private Hyperlink goToSignUpPage;


    public void initialize(){

        sceneController = new SceneController();
        sceneController.setSceneController(this);

        setGoToSignUpPage();


    }

    private void setGoToSignUpPage(){
        goToSignUpPage.setOnAction(e->{

            // get current stage
            Stage stage = (Stage) goToSignUpPage.getScene().getWindow();
            try {
                sceneController.switchScenes(stage, "Signup.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }




}
