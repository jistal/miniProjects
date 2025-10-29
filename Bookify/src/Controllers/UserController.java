package Controllers;

import Dao.CategoryDAO;
import Dao.SlotDao;
import Services.Services;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController {

    @FXML private AnchorPane userRoot;
    @FXML private Button userLogoutBtn;
    @FXML private VBox categoriesContainer;


    private CategoryDAO categoryDao;
    private SlotDao slotDao;
    private final SceneManager sceneManager = new SceneManager();
    private Services services;

    ArrayList<String> storedCategories = new ArrayList<>();
    ArrayList<String> storedSlots = new ArrayList<>();
    String selectedCategory = null;


    @FXML public void initialize() throws SQLException {
        categoryDao = new CategoryDAO();
        slotDao = new SlotDao();
        services = new Services();
        displayCategoriesAndListenForInput();
        setUserLogoutBtn();
    }

    // displays categories and checks for input
    private void displayCategoriesAndListenForInput() throws SQLException {
        // read stored categories
        storedCategories = categoryDao.displayCategory();
        for(String s :  storedCategories) {
            Button button = new Button(s);

            button.setOnAction(e -> {
                selectedCategory = button.getText();
                try {
                    displaySlots();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            categoriesContainer.getChildren().add(button);
        }
    }





    private void displaySlots() throws SQLException{
        int categoryID =  categoryDao.getCategoryID(selectedCategory);

        // read all the stored slots in the selected category
        storedSlots = slotDao.viewAllSlots(categoryID);

        // display them
        categoriesContainer.getChildren().clear();
        for (String s : storedSlots){

            Button button = new Button(s);
            button.setOnAction(e->{

                // if a slot is clicked, book it
                try {
                    services.bookSlot(button.getText(), categoryID);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            categoriesContainer.getChildren().add(button);
        }
    }


    // log out button
    private void setUserLogoutBtn(){
        userLogoutBtn.setOnAction(e->{
            Stage stage = (Stage) userLogoutBtn.getScene().getWindow();
            try {
                sceneManager.switchScenes(stage, "Login.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }









}
