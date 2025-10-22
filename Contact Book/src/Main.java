import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        UiManager uiManager = new UiManager();
        InputManager inputManager = new InputManager();

        uiManager.MainMenu();
        int userPick = inputManager.mainMenuPick();

        switch(userPick){
            case 1 -> {
                uiManager.addNewContact();
            }
            case 2 -> {
                uiManager.viewAllContacts();
            }
            case 3 -> {
                uiManager.searchContact();
            }
            case 4 -> {
                uiManager.deleteContact();
            }
            case 5 -> {
                System.exit(0);
            }
        }



















    }
}