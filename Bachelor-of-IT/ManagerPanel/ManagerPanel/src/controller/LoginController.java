package controller;

import au.edu.uts.ap.javafx.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.application.*;
import model.exception.UnauthorisedAccessException;
import javafx.stage.Stage;


public class LoginController extends Controller<League> {

     @FXML TextField ManagerID;
     @FXML Button Login;
     @FXML Button Exit;
     

    @FXML
    private void initialize() {

            Login.setDisable(true);
        
            ManagerID.textProperty().addListener((obs, ot, nt) -> {
                
               
                
                if (nt == null || nt.trim().length() == 0) {
                    Login.setDisable(true);

                }

                else{
                    Login.setDisable(false);
                }


            });

           
    }


    public void handleLogin(){
     
       String b = ManagerID.getText().trim();

       if (b.length() == 0){
            return;
       }

       int c;
       try{
            c = Integer.parseInt(b);
       }

       catch (NumberFormatException e) {
            ViewLoader.showStage(new ErrorData("UnauthorisedAccessException", "Incorrect format for manager id"), "/view/ErrorView.fxml", "Error", new Stage());
            return;
       }

       League league;
       Manager manager;
       try {
            league = League.getInstance();
            manager = league.validateManager(c);
       }

       catch (UnauthorisedAccessException e) {
            ViewLoader.showStage(new ErrorData("UnauthorisedAccessException", "Invalid login credentials"), "/view/ErrorView.fxml", "Error", new Stage());
            return;
       }



       league.setLoggedInManager(manager);

       ViewLoader.showStage(manager, "/view/ManagerDashboardView.fxml", "Manager Dashboard", stage);


    }

    public void handleExit(){
        stage.close();

    }

}
