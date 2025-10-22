public class UiManager {

    InputManager inputManager = new InputManager();

    public void MainMenu() {
        System.out.println("""
                 =========================
                 📒 CONTACT BOOK MENU
                 =========================
                 1️⃣  Add New Contact
                 2️⃣  View All Contacts
                 3️⃣  Search Contact by ID or Name
                 4️⃣  Delete Contact by ID or Name
                 5️⃣  Save & Exit
                -------------------------
                Select an option (1–5)
                """);
    }

    public void addNewContact(){
        System.out.println("[ ADD NEW CONTACT ]");
        String nameInput = inputManager.getNameInput();
        String emailInput = inputManager.getEmailInput();
        String phoneNumberInput = inputManager.getPhoneInput();
        System.out.println("[ CONTACT ADDED SUCCESSFULLY ]");
    }

    public void viewAllContacts(){

    }

    public void searchContact(){
        String nameInput = inputManager.getNameInput();
    }

    public void deleteContact(){
        String nameInput = inputManager.getNameInput();
    }

}
