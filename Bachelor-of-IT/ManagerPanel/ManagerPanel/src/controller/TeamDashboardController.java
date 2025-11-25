package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.application.Player;
import model.application.League;
import javafx.scene.control.Tooltip;

public class TeamDashboardController extends au.edu.uts.ap.javafx.Controller<model.application.Team>{

    
     @FXML Label TeamName;
     @FXML TextField sign;
     @FXML Button signbtn;
     @FXML TableView<Player> t;
     @FXML TableColumn<Player, String> n;
     @FXML TableColumn<Player, String> p;
     @FXML ImageView img1, img2, img3, img4, img5;
     @FXML Button unsign;


    @FXML
    private void initialize() {

        TeamName.setText(model.toString());
       
        n.setCellValueFactory(cell -> cell.getValue().fullNameProperty());
        p.setCellValueFactory(cell -> cell.getValue().positionProperty());

        unsign.disableProperty().bind(t.getSelectionModel().selectedItemProperty().isNull());

        

        signbtn.setDisable(true);

       
        sign.textProperty().addListener((obs, ot, nt) -> {
            if (nt == null || nt.trim().length() == 0){
                signbtn.setDisable(true);
            }

            else{
                 signbtn.setDisable(false);
            }
        });

        
        rj();

        ut();

    }

    public void rj(){

        List<ImageView> c = new ArrayList<>(Arrays.asList(img1, img2, img3, img4, img5));

        List<Player> a = Arrays.asList(model.getCurrentTeam());
        for (int i = 0; i < a.size(); i++){
            ImageView m = c.get(i);

            Player p = a.get(i);
            
            if (p != null){
               m.setImage(new Image(getClass().getResourceAsStream("/view/image/" + model.getTeamName().toLowerCase() + ".png")));
               Tooltip t = new Tooltip(p.getFullName() + " (" + p.positionProperty().get() + ")");
               Tooltip.install(m, t);
            }
           

            else{
                 m.setImage(new Image(getClass().getResourceAsStream("/view/image/none.png")));
                 Tooltip g = new Tooltip("Unallocated");
                 Tooltip.install(m, g);

               
            }
           
        }
    }

    public void ut(){
        List<Player> a = League.getInstance().getPlayers().getPlayers();
        List<Player> b = new ArrayList<>();

        for (int i = 0; i < a.size(); i ++){
            Player p = a.get(i);
            if (p.getTeam() == model){
                b.add(p);
            }
        }

        t.getItems().setAll(b);

        
    }

    @FXML
    public void mc1(){

        int s = 0;

        Player p = t.getSelectionModel().getSelectedItem();

        Player [] a = model.getCurrentTeam();

        if (p != null){
            for (int i = 0; i < a.length; i++){
                if (i != s && a[i] == p){
                    ViewLoader.showStage(new ErrorData("FillException", p.getFullName() + " is already in the active playing team"), "/view/ErrorView.fxml", "Error", new Stage());
                    return;
                }

                
            }

            

            if (a[s] != p){
                a[s] = p;
            }


            
        }

        else if (a[s] != null){
             a[s] = null;
            
        }

        rj();

    }

    @FXML
    public void mc2(){
        int s = 1;

        Player p = t.getSelectionModel().getSelectedItem();

        Player [] a = model.getCurrentTeam();

        if (p != null){
            for (int i = 0; i < a.length; i++){
                if (i != s && a[i] == p){
                    ViewLoader.showStage(new ErrorData("FillException", p.getFullName() + " is already in the active playing team"), "/view/ErrorView.fxml", "Error", new Stage());
                    return;
                }

                
            }

            

            if (a[s] != p){
                a[s] = p;
            }


            
        }

        else if (a[s] != null){
             a[s] = null;
            
        }

        rj();
    }

    @FXML
    public void mc3(){
        int s = 2;

        Player p = t.getSelectionModel().getSelectedItem();

        Player [] a = model.getCurrentTeam();

        if (p != null){
            for (int i = 0; i < a.length; i++){
                if (i != s && a[i] == p){
                    ViewLoader.showStage(new ErrorData("FillException", p.getFullName() + " is already in the active playing team"), "/view/ErrorView.fxml", "Error", new Stage());
                    return;
                }

                
            }

            

            if (a[s] != p){
                a[s] = p;
            }


            
        }

        else if (a[s] != null){
             a[s] = null;
            
        }

        rj();
    }

    @FXML
    public void mc4(){
        int s = 3;

        Player p = t.getSelectionModel().getSelectedItem();

        Player [] a = model.getCurrentTeam();

        if (p != null){
            for (int i = 0; i < a.length; i++){
                if (i != s && a[i] == p){
                    ViewLoader.showStage(new ErrorData("FillException", p.getFullName() + " is already in the active playing team"), "/view/ErrorView.fxml", "Error", new Stage());
                    return;
                }

                
            }

            

            if (a[s] != p){
                a[s] = p;
            }


            
        }

        else if (a[s] != null){
             a[s] = null;
            
        }

        rj();

    }

    @FXML
    public void mc5(){
        int s = 4;

        Player p = t.getSelectionModel().getSelectedItem();

        Player [] a = model.getCurrentTeam();

        if (p != null){
            for (int i = 0; i < a.length; i++){
                if (i != s && a[i] == p){
                    ViewLoader.showStage(new ErrorData("FillException", p.getFullName() + " is already in the active playing team"), "/view/ErrorView.fxml", "Error", new Stage());
                    return;
                }

                
            }

            

            if (a[s] != p){
                a[s] = p;
            }


            
        }

        else if (a[s] != null){
             a[s] = null;
            
        }

        rj();

    }

    @FXML 
    public void handlesign(){
        
        
         String name = sign.getText().trim();
        
         if (name.length() == 0){
            return;
         }
         

         Player p = League.getInstance().getPlayers().player(name);

         if (p == null){
            ViewLoader.showStage(new ErrorData("InvalidSigningException", "Player does not exist within the league"), "/view/ErrorView.fxml", "Error", new Stage());
            return;
         }
       

       else if (p.getTeam() == model){
            ViewLoader.showStage(new ErrorData("InvalidSigningException", p.getFullName() + " is already signed to your team"), "/view/ErrorView.fxml", "Error", new Stage());
            return;
       }

       else if (p.getTeam() != null){
            ViewLoader.showStage(new ErrorData("InvalidSigningException", "Cannot sign " + p.getFullName() + ", player is already signed to " + p.getTeam().toString()), "/view/ErrorView.fxml", "Error", new Stage());
            return;
       }

       else{
            p.setTeam(model);
            rj();

            ut();

       }
      
    }

    @FXML
    public void handleunsign(){

        Player p = t.getSelectionModel().getSelectedItem();

        if (p == null){
            return;
        }



       

        for (int i = 0; i < model.getCurrentTeam().length; i++){
            if (model.getCurrentTeam()[i] == p){
                ViewLoader.showStage(new ErrorData("InvalidSigningException", "Cannot remove " + p.getFullName() + ", player is in the active team"), "/view/ErrorView.fxml", "Error", new Stage());
                return;
    
            }

        }

        p.setTeam(null);
        
      
        ut();

        rj();

      



    }

    public void handleExit(){
        stage.close();

    }
}
