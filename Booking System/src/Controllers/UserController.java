package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class UserController {

    @FXML private AnchorPane userRoot;
    @FXML private AnchorPane userSideMenuContainer;
    @FXML private Button bookAnAppointment;
    @FXML private Button cancelAppointmentBtn;
    @FXML private Button viewBookingsBtn;
    @FXML private Button userLogoutBtn;

    @FXML public void initialize(){


        
    }


    private void setBookAnAppointment(){
        bookAnAppointment.setOnAction(e ->{

        });
    }


}
