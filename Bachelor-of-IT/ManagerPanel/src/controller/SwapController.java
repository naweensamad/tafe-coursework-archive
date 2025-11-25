package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.application.League;
import model.application.Team;



public class SwapController extends au.edu.uts.ap.javafx.Controller<League>{

    
     
     @FXML ListView<Team> teamsLv;
     @FXML Button assign;
     @FXML Button cancel;
    

    @FXML
    private void initialize() {

        teamsLv.setItems(model.getManageableTeams().getTeams());
       
        assign.setDisable(true);

        teamsLv.getSelectionModel().selectedItemProperty().addListener((obs, ot, nt) -> {
              if (nt != null){
                    assign.setDisable(false);
              }  
              
              else{
                assign.setDisable(true);
              }

         });


    }

    @FXML
    public void handleassign(){
        if (teamsLv.getSelectionModel().getSelectedItem() == null){
            return;
        }

        else{
             model.setManagerForTeam(model.getLoggedInManager(), teamsLv.getSelectionModel().getSelectedItem());
             teamsLv.getSelectionModel().clearSelection();

        }
        

       
        
    }

    

    @FXML
    public void handlecancel(){
        stage.close();
    }


}
