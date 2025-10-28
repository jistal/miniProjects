package Controllers;
import Dao.CategoryDAO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Category;

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




    private Category category;
    private CategoryDAO categoryDao;

    ArrayList<String> storedCategories = new ArrayList<>();

    String categoryPicked = null;

    @FXML public void initialize() throws SQLException {


        fillCategoriesScrollPane();

    }

    public void fillCategoriesScrollPane() throws SQLException {


        categoryDao = new CategoryDAO();
        storedCategories = categoryDao.displayCategory();

        for(String s :  storedCategories) {
            Button button = new Button(s);

            button.setOnAction(e -> {
                categoryPicked = button.getText();
                type = Type.SLOT;
            });

            categoriesContainer.getChildren().add(button);
        }
    }





    private void setCreateBtn(Type type){
        createBtn.setOnAction(e->{

            String userInput = adminTextField.getText();
            adminTextField.clear();

            switch (type){
                case CATEGORY -> {
                    category = new Category(userInput);
                    categoryDao = new CategoryDAO();

                    try {
                        categoryDao.addNewCategory(category);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                case SLOT -> {

                }
            }






        });
    }

    private void setRemoveBtn(Type type){
        removeBtn.setOnAction(e ->{
            String categoryName = adminTextField.getText();

            category = new Category(categoryName);


            try {
                categoryDao.deleteCategory(category);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

            adminTextField.clear();
        });
    }





    private void addSlots(Button button){
        button.setOnAction(e-> {

        });

    }




}
