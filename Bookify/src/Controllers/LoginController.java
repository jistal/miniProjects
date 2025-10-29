package Controllers;

import Dao.UserDao;
import Services.Services;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Model.Session;
import Model.User;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController {

    // ref to scene controller
    private SceneManager sceneManager;
    private UserDao userDao;
    private User user;
    private Services services;

    String username, password;

    @FXML private AnchorPane rootContainer;
    @FXML private AnchorPane loginContainer;
    @FXML private TextField loginUsernameField;
    @FXML private TextField loginPasswordField;
    @FXML private Label loginAppTitle;
    @FXML private Hyperlink goToSignUpPage;

    @FXML public void initialize() {
        setGoToSignUpPage();
        setLoginPasswordField();
        userDao = new UserDao();
        services = new Services();
        sceneManager = new SceneManager();
    }

    private void setGoToSignUpPage() {
        goToSignUpPage.setOnAction(e -> {
            sceneManager.setLoginController(this);

            // get the current stage
            Stage stage = (Stage) goToSignUpPage.getScene().getWindow();
            try {
                sceneManager.switchScenes(stage, "Signup.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void setLoginPasswordField() {
        loginPasswordField.setOnAction(e -> {
            username = loginUsernameField.getText();
            password = loginPasswordField.getText();

            boolean areFieldsFilled = services.areFieldsFilled(username, password);
            user = new User(username, password, "USER");

            // load dashboard
            try {
                if (areFieldsFilled && isUsernameValid() && isPasswordCorrect()) {
                    loginUsernameField.clear();
                    loginPasswordField.clear();
                    sceneManager.setLoginController(this);

                    Stage stage = (Stage) goToSignUpPage.getScene().getWindow();

                    // if user is admin load admin dashboard
                    if (isUserAdmin()) {
                        sceneManager.switchScenes(stage, "Admin.fxml");
                    } else {
                        // else load user dashboard
                        sceneManager.switchScenes(stage, "User.fxml");
                        Session.setUserLoggedIn(username);
                    }
                }
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private boolean isUsernameValid() throws SQLException {
        return userDao.isUsernameValid(user);
    }

    private boolean isPasswordCorrect() throws SQLException {
        return userDao.isPasswordValid(user);
    }

    private boolean isUserAdmin() throws SQLException {
        return userDao.isUserAdmin(user);
    }
}
