package Controllers;
import Dao.CategoryDAO;
import Dao.SlotDao;
import Services.Services;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminController {

    private SceneManager sceneController;

    @FXML private AnchorPane adminSideMenuContainer;
    @FXML private Button adminManageCategoriesBtn;
    @FXML private Button adminManageSlotsBtn;
    @FXML private Button adminViewBookingsBtn;
    @FXML private Button adminLogoutBtn;
    @FXML private TextArea adminCategoriesTextArea;
    @FXML private TextArea adminSlotsTextArea;
    @FXML private TextField adminTextField;
    @FXML private HBox createAndRemoveBtnContainer;
    @FXML private Button createBtn;
    @FXML private Button removeBtn;
    @FXML private VBox categoriesContainer;


    private enum Type{
        CATEGORY,
        SLOT
    } Type type = Type.CATEGORY;


    private CategoryDAO categoryDao;
    private SlotDao slotDao;
    private final SceneManager sceneManager = new SceneManager();
    private Services service;

    ArrayList<String> storedCategories = new ArrayList<>();
    ArrayList<String> storedSlots = new ArrayList<>();

    String selectedCategory = null;
    int categoryID;

    @FXML public void initialize() throws SQLException {

        categoryDao = new CategoryDAO();
        slotDao = new SlotDao();
        service = new Services();

        displayCategoriesAndListenForInput();
        setCreateBtn(type);
        setRemoveBtn(type);
        setAdminLogoutBtn();
    }

    public void displayCategoriesAndListenForInput() throws SQLException {
        // get the stored categories
        storedCategories = categoryDao.displayCategory();

        // display them and wait for if user clicks any of them
        for(String s :  storedCategories) {
            Button button = new Button(s);

            button.setOnAction(e -> {
                // get category name that was clicked
                selectedCategory = button.getText();
                type = Type.SLOT;

                try {
                    setCreateBtn(type);
                    setRemoveBtn(type);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                // display the slots available for that category
                try {
                    displaySlots();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });
            categoriesContainer.getChildren().add(button);
        }
    }

    public void displaySlots() throws SQLException{
        // get the category id (used for foreign key in slots)
         categoryID =  categoryDao.getCategoryID(selectedCategory);

        // display all the slots found in that category
        storedSlots = slotDao.viewAllSlots(categoryID);
        categoriesContainer.getChildren().clear();
        for (String s : storedSlots){
            Label label = new Label(s);
            categoriesContainer.getChildren().add(label);
        }
    }

    // CHECKKKKKKKKKKKKK
    public boolean checkSlotAvailability() throws SQLException {
        Boolean isBooked = slotDao.checkSlotAvailability(categoryID);
        return isBooked;
    }



    private void setCreateBtn(Type type){
        createBtn.setOnAction(e->{
            // get user input

            String userInput = adminTextField.getText();
            adminTextField.clear();

            // check if we are creating a category or slot
            try {
            switch (type){
                case CATEGORY -> service.addNewCategory(userInput);
                case SLOT -> service.addNewSlot(selectedCategory, userInput);
            }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


    private void setRemoveBtn(Type type) throws SQLException{
        removeBtn.setOnAction(e ->{
            // get user input
            String userInput = adminTextField.getText();
            adminTextField.clear();

            //check if we are removing a category or slot
            try {
                switch(type){
                    case CATEGORY -> service.removeCategory(userInput);
                    case SLOT -> service.removeSlot(selectedCategory,userInput);
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }


    // log out button
    private void setAdminLogoutBtn(){
        adminLogoutBtn.setOnAction(e ->{
            Stage stage = (Stage) adminLogoutBtn.getScene().getWindow();
            try {
                sceneManager.switchScenes(stage, "Login.fxml");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }




}
