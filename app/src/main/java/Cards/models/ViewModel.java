package Cards.models;

import Cards.translators.io.CardFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Cards.models.CardLogger.logg;

public class ViewModel {

    public enum View{
        CARD, MAINMENU, HELP, CALENDAR, SETTINGS, PERSONAL;
    }

    public Scene getMainMenu(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            scene.getStylesheets().add("/Main_Menu.css");
            return scene;
        }catch(IOException error){
            System.err.println("Couldn't Load Menu Files");
            System.err.println(error.toString());
        }
        return null;
    }
    public Scene getCardEditor(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Template.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/Card.css").toExternalForm());
            return scene;
        }catch(IOException error){
            System.err.println("Couldn't Load Card Editor Files");
            System.err.println(error.toString());
        }
        return null;
    }

    public Parent getHelpScreen(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Help.fxml"));
            Parent p = loader.load();
            return p;
        }catch(Exception e){
            System.err.println("Couldn't Load Help Files");
            System.err.println(e);
        }
        return null;
    }
    public Node getCalendarScreen(){
        //TODO Add Calendar
       return null;
    }

}
