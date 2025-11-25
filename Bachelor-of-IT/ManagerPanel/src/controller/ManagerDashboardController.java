package controller;

import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.application.League;
import javafx.scene.image.Image;


public class ManagerDashboardController extends au.edu.uts.ap.javafx.Controller<model.application.Manager>{

     @FXML ImageView jerseyImg;
     @FXML Label managerName;
     @FXML Label teamName;
     @FXML Button swapteam;
     @FXML Button withdrawteam;
     @FXML Button manage;
    
     

    @FXML
    private void initialize() {
        managerName.setText(model.toString());

        if (model.getTeam() != null){
            teamName.setText(model.getTeam().toString());
            withdrawteam.setDisable(false);
            manage.setDisable(false);
            jerseyImg.setImage(new Image(getClass().getResourceAsStream("/view/image/" + model.getTeam().getTeamName().toLowerCase() + ".png")));
        }

        else{
            teamName.setText("No team");
            jerseyImg.setImage(new Image(getClass().getResourceAsStream("/view/image/none.png")));
            withdrawteam.setDisable(true);
            manage.setDisable(true);

        }

      

        model.teamProperty().addListener((obs, ot, nt) -> {
                if (model.getTeam() != null){
                    teamName.setText(model.getTeam().toString());
                    withdrawteam.setDisable(false);
                    manage.setDisable(false);
                    jerseyImg.setImage(new Image(getClass().getResourceAsStream("/view/image/" + model.getTeam().getTeamName().toLowerCase() + ".png")));
                }

                else{
                    teamName.setText("No team");
                    jerseyImg.setImage(new Image(getClass().getResourceAsStream("/view/image/none.png")));
                    withdrawteam.setDisable(true);
                    manage.setDisable(true);

                }
               

            });

    }

    @FXML
    public void handleSwap(){
        ViewLoader.showStage(League.getInstance(), "/view/SwapView.fxml", "Swap", new Stage());
    }

    @FXML
    public void handleWithdraw(){
        if (model.getTeam() == null){
            return;
        }

        else{
            League.getInstance().withdrawManagerfromTeam(model);
        }

       
    }

    @FXML
    public void handleManage(){

       if  (model.getTeam() == null){
            return;
       }

       else{
            ViewLoader.showStage(model.getTeam(), "/view/TeamDashboardView.fxml", "Team Dashboard", stage);

       }

       
    }

    @FXML
    public void handleclose(){
        stage.close();
    }



}
