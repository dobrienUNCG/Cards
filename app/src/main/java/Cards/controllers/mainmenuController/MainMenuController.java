package Cards.controllers.mainmenuController;
/**
 * Last Updated: 10/28/2020
 * Controller for Main Menu View
 *
 * @author Devin M. O'Brien
 */

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

    @FXML
    Button home_button;
    @FXML
    Stage stage;

    public void create_card() throws Exception {
        Stage appStage;
        Parent root;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Template.fxml"));
        appStage = (Stage) this.home_button.getScene().getWindow();
        root = loader.load();
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }
}
