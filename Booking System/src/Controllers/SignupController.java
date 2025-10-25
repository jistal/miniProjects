package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

public class SignupController {


    @FXML private AnchorPane signupRoot;
    @FXML private AnchorPane signUpContainer;
    @FXML private TextField signUpUsernameField;
    @FXML private TextField signUpPasswordField;
    @FXML private Label signUpAppTitle;
    @FXML private Hyperlink goToLoginPage;




private SceneController sceneController;




  @FXML public void initialize(){

setGoToLoginPage();
    }



    private void setGoToLoginPage(){
        goToLoginPage.setOnAction(e -> {

           sceneController = new SceneController();
           sceneController.setSignupController(this);
            // get current stage
            Stage stage = (Stage) goToLoginPage.getScene().getWindow();

            try {
                sceneController.switchScenes(stage, "Login.fxml");
            } catch (IOException ex) {
                System.err.println("Could not switch scenes! (Signup -> Login)");
            }


        });
    }





}
