package Cards.views;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;

import javafx.fxml.FXMLLoader;

import static Cards.models.CardLogger.logg;

// TODO Break Into Two Classes (View and Controller) (In-progress)

public class CardView {



    public void setBody(String x){

    }
    public CardView(){

    }
    public CardView(String x) {
        logg.entering(this.getClass().getName(), "CardViewController()");

        logg.exiting(this.getClass().getName(), "CardViewController()");
    }




    public Scene get_a_card() throws Exception{
        logg.entering(this.getClass().getName(), "start");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Template.fxml"));
        Parent root = loader.load();


        Scene scene = new Scene(root);

        logg.exiting(this.getClass().getName(), "start");
        return scene;
    }



}
