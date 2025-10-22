import java.util.Scanner;

public class InputManager {
    Scanner input = new Scanner(System.in);

    public int mainMenuPick() {
        int userPick = -1;

        // check if user input is valid
        do {
            if (input.hasNextInt()) {
                userPick = input.nextInt();
                if (userPick >= 1 && userPick <= 5) {
                    break;
                } else {
                    System.out.println("Invalid input, choose between 1-5.");
                }
            } else {
                System.out.println("Invalid input, please enter a number.");
            }
        } while (true);
        return userPick;
    }


    public String getNameInput(){
        String nameInput;

        // check if name isn't too short or too long
        while(true){
            System.out.println("Name: ");
            nameInput = input.nextLine();

            if (nameInput.length() >= 2 && nameInput.length() <= 50) break;
            System.out.println("Invalid input, please try again.");
        }
        return nameInput;
    }


    public String getEmailInput(){
        String emailInput;

      // check if email isn't too long
      while(true){
        System.out.println("Email: ");
        emailInput = input.nextLine();

          if (emailInput.length() <= 50) break;
          System.out.println("Invalid input, please try again.");
    }
        return emailInput;
    }


    public String getPhoneInput(){
        String phoneNumberInput;

        while(true){
            System.out.println("Phone: ");
            phoneNumberInput = input.nextLine();

            if (phoneNumberInput.length() >= 10 && phoneNumberInput.length() <= 15)  break;
            System.out.println("Invalid input, please try again");
        }
        return phoneNumberInput;
    }
}
