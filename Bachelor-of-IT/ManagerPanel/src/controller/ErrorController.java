package controller;

import au.edu.uts.ap.javafx.Controller; 
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class ErrorController extends Controller<ErrorData> {


   @FXML ImageView errorImg;
   @FXML Label errorTitleLbl;
   @FXML Label errorMsgLbl;
   @FXML Button closeBtn;


   @FXML
   private void initialize() {
      Image image = new Image(getClass().getResourceAsStream("/view/image/error.png"));

      errorImg.setImage(image);

      errorTitleLbl.setText(model.getTitle());
      errorMsgLbl.setText(model.getMessage());
      
   }

   public void close(){
       stage.close();
   }


}


 class ErrorData{
      private String title;
      private String message;

      public ErrorData(String title, String message){
         this.title = title;
         this.message = message;
      }

      public String getTitle(){
         return title;
      }

      public String getMessage(){
         return message;
      }
}



