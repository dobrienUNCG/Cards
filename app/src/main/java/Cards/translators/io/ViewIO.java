package Cards.models;

import Cards.translators.io.CardFile;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Cards.models.CardLogger.logg;

public class ViewIO {

    public enum View{
        CARD{

        }, MAINMENU, HELP, CALENDAR, SETTINGS, PERSONAL, EVENT;
    }

    public Parent getMainMenu(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Menu.fxml"));
            Parent root = loader.load();
            root.getStylesheets().add("/Main_Menu.css");
            return root;
        }catch(IOException error){
            System.err.println("Couldn't Load Menu Files");
            System.err.println(error.toString());
        }
        return null;
    }
    public Parent getEventCreator(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/EventCreationDialog.fxml"));
            Parent root = loader.load();
            return root;
        }catch(IOException error){
            System.err.println("Couldn't load event creator");
            System.err.println(error.toString());
        }
        return null;
    }

    public Parent getCardEditor(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Template.fxml"));
            Parent root = loader.load();
            root.getStylesheets().add(getClass().getResource("/Card.css").toExternalForm());
            return root;
        }catch(IOException error){
            System.err.println("Couldn't Load Card Editor Files");
            System.err.println(error.toString());
        }
        return null;
    }

    /**
     *
     * @return
     */
    public Parent getSettingsScreen(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Settings.fxml"));
            Parent root = loader.load();
            return root;
        }catch (Exception e){
            System.err.println("Couldn't Load Settings Screen Files");
            System.err.println(e.toString());
        }
        return null;
    }
    public Parent getHelpScreen(){
        // TODO Add Content to Help View
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
    public Parent getCalendarScreen(){
        // TODO Setup Calendar Controller
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/CalendarNew.fxml"));
            Parent p = loader.load();
            return p;
        }catch(Exception e){
            System.err.println(e);
        }
       return null;
    }

}
