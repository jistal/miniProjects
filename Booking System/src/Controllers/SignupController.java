package Controllers;
import Dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import java.io.IOException;
import java.sql.SQLException;

public class SignupController {

    @FXML private AnchorPane signupRoot;
    @FXML private AnchorPane signUpContainer;
    @FXML private TextField signUpUsernameField;
    @FXML private TextField signUpPasswordField;
    @FXML private Label signUpAppTitle;
    @FXML private Hyperlink goToLoginPage;

    String username, password;

    // ref to scene manager and user dao
    private SceneManager sceneManager;
    private UserDao userDao;


  @FXML public void initialize(){
      setGoToLoginPage();
      setSignUpPasswordField();
    }


// go to login page
    private void setGoToLoginPage(){
        goToLoginPage.setOnAction(e -> {

            sceneManager = new SceneManager();
            sceneManager.setSignupController(this);
            // get current stage
            Stage stage = (Stage) goToLoginPage.getScene().getWindow();

            try {
                sceneManager.switchScenes(stage, "Login.fxml");
            } catch (IOException ex) {
                System.err.println("Could not switch scenes! (Signup -> Login)");
            }
        });
    }


    private void setSignUpPasswordField(){
      signUpPasswordField.setOnAction(e -> {
           username = signUpUsernameField.getText();
           password = signUpPasswordField.getText();

         boolean areFieldsFilled = areFieldsFilled(username, password);
          if (areFieldsFilled) {
              sceneManager = new SceneManager();
              sceneManager.setSignupController(this);
              Stage stage = (Stage) goToLoginPage.getScene().getWindow();


              try { sceneManager.switchScenes(stage, "User.fxml");
              } catch (IOException ex) {
                  System.out.println("Something went wrong, please try again later");
              }


              signUpPasswordField.clear();
              signUpUsernameField.clear();
              try {
                  createNewUser();
              } catch (SQLException ex) {
                  throw new RuntimeException(ex);
              }
          }

      });
    }


    private void createNewUser() throws SQLException {
      // create user model
      User newUser = new User(username, password, "USER");
      // send it to use dao

      userDao = new UserDao();
      userDao.insertNewUser(newUser);
    }



    // check if user has entered both username and password, so they may press enter and continue
    private boolean areFieldsFilled(String username, String password){
      boolean areFieldsFilled = false;

        if (password != null && !(password.isEmpty()) && password.matches("[a-zA-z\\d]+")
            && username != null && !(username.isEmpty()) && username.matches("[a-zA-z\\d]+")) {
            areFieldsFilled = true;
        }
        return areFieldsFilled;
    }






}
