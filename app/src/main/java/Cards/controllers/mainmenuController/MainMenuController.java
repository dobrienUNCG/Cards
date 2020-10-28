package Cards.controllers.mainmenuController;

import Cards.views.CardView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
    // TODO Write Class

    @FXML
    Button home_button;
    @FXML
    Stage stage;

    public void create_card() throws Exception {
        Stage appStage;
        Parent root;
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Template.fxml"));
            appStage=(Stage)home_button.getScene().getWindow();
            root= loader.load();
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();

        }
    }


}
