package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.DBconnection;

import java.io.IOException;
import java.sql.Connection;

public class LoginController {


    // ref to scene controller
    private SceneManager sceneManager;

   @FXML private AnchorPane rootContainer;
   @FXML private AnchorPane loginContainer;
   @FXML private TextField loginUsernameField;
   @FXML private TextField loginPasswordField;
   @FXML private Label loginAppTitle;
   @FXML private Hyperlink goToSignUpPage;



    @FXML public void initialize(){



        setGoToSignUpPage();


    }

    private void setGoToSignUpPage(){
        goToSignUpPage.setOnAction(e->{

            sceneManager = new SceneManager();
            sceneManager.setLoginController(this);

            // get the current stage
           Stage stage = (Stage) goToSignUpPage.getScene().getWindow();
           try {
               sceneManager.switchScenes(stage, "Signup.fxml");
           } catch (IOException ex) {
               System.err.println("Could not switch scenes! (Login -> Signup)");
           }
        });

    }




}
