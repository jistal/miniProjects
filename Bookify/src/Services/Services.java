package Services;

import Dao.CategoryDAO;
import Dao.SlotDao;
import Dao.UserDao;
import Model.Session;
import Model.Slot;
import Model.User;

import java.sql.SQLException;
public class Services {

   private CategoryDAO categoryDao = new CategoryDAO();
   private SlotDao slotDao = new SlotDao();
   private UserDao userDao = new UserDao();

   // add a category
    public void addNewCategory(String userInput) throws SQLException{
         categoryDao.addNewCategory(userInput);
    }

    // add a slot to a category
    public void addNewSlot(String selectedCategory, String userInput) throws SQLException{

        // get id of category user chose (so it can be used as foreign key for slots)
         int categoryID = categoryDao.getCategoryID(selectedCategory);

        // create the model
         Slot slot = new Slot(userInput, categoryID, false);

        // add the slot
        slotDao.addSlot(slot);
    }


    // remove a category
    public void removeCategory(String userInput) throws SQLException{
        categoryDao.deleteCategory(userInput);
    }


    // remove a slot from a category
    public void removeSlot(String selectedCategory, String userInput) throws SQLException{
        int categoryID = categoryDao.getCategoryID(selectedCategory);

        Slot slot = new Slot(userInput, categoryID, false);
        slotDao.deleteSlot(slot);
    }


    // create new user
    public void createNewUser(String username, String password) throws SQLException {
        User newUser = new User(username, password, "USER");
        userDao = new UserDao();
        userDao.insertNewUser(newUser);
    }

    // check if user has entered both username and password, so they may press enter and continue
    public boolean areFieldsFilled(String username, String password){
        boolean areFieldsFilled = false;

        if (password != null && !(password.isEmpty()) && password.matches("[a-zA-z\\d]+")
                && username != null && !(username.isEmpty()) && username.matches("[a-zA-z\\d]+")) {
            areFieldsFilled = true;
        }
        return areFieldsFilled;
    }

    public void bookSlot(String slotPicked, int categoryID) throws SQLException {
        slotDao.bookSlot(Session.getUsernameLoggedIn(), slotPicked, categoryID);
    }
}
