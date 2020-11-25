package Cards.views;
/**
 * Updated 10/27/2020
 * Main Menu View
 *
 * @author Devin M. O'Brien
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

import static Cards.models.CardLogger.logg;

public class MainMenuView {


    public Scene mainmenu() throws IOException {
        logg.entering(this.getClass().getName(), "start");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Menu.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        scene.getStylesheets().add("/Main_Menu.css");
        return scene;
    }


}




