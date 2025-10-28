package Controllers;

import Dao.UserDao;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.User;
import utils.DBconnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LoginController {

    // ref to scene controller
    private SceneManager sceneManager;
    private UserDao userDao;
    private User user;

    String username, password;
    boolean isPasswordCorrect = false;
    boolean isUsernameValid = false;
    boolean isUserAdmin = false;

    @FXML
    private AnchorPane rootContainer;
    @FXML
    private AnchorPane loginContainer;
    @FXML
    private TextField loginUsernameField;
    @FXML
    private TextField loginPasswordField;
    @FXML
    private Label loginAppTitle;
    @FXML
    private Hyperlink goToSignUpPage;

    @FXML
    public void initialize() {
        setGoToSignUpPage();
        setLoginPasswordField();
    }

    private void setGoToSignUpPage() {
        goToSignUpPage.setOnAction(e -> {
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

    private void setLoginPasswordField() {
        loginPasswordField.setOnAction(e -> {
            username = loginUsernameField.getText();
            password = loginPasswordField.getText();
            boolean areFieldsFilled = areFieldsFilled(username, password);

            user = new User(username, password, "USER");
            userDao = new UserDao();

            // check if username exists
            try {
                isUsernameValid = checkIfUserExits();


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // check if password is correct
            try {
                isPasswordCorrect = checkIfPasswordIsCorrect();

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            // load dashboard
            if (areFieldsFilled && isUsernameValid && isPasswordCorrect) {
                loginUsernameField.clear();
                loginPasswordField.clear();
                sceneManager = new SceneManager();
                sceneManager.setLoginController(this);

                Stage stage = (Stage) goToSignUpPage.getScene().getWindow();
                try {
                    // if user is admin load admin dashboard
                    if (isUserAdmin()) {
                        sceneManager.switchScenes(stage, "Admin.fxml");
                    } else {
                        // else load user dashboard
                        sceneManager.switchScenes(stage, "User.fxml");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private boolean checkIfUserExits() throws SQLException {
        isUsernameValid = userDao.isUsernameValid(user);
        return isUsernameValid;
    }

    private boolean checkIfPasswordIsCorrect() throws SQLException {
        isPasswordCorrect = userDao.isPasswordValid(user);
        return isPasswordCorrect;
    }

    private boolean isUserAdmin() throws SQLException {
        isUserAdmin = userDao.isUserAdmin(user);
        return isUserAdmin;
    }




    // check if user has entered both username and password, so they may press enter and continue
    private boolean areFieldsFilled(String username, String password) {
        boolean areFieldsFilled = false;

        if (password != null && !(password.isEmpty()) && password.matches("[a-zA-z\\d]+")
                && username != null && !(username.isEmpty()) && username.matches("[a-zA-z\\d]+")) {
            areFieldsFilled = true;
        }
        return areFieldsFilled;
    }


}
