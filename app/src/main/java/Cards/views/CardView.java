package Cards.views;
/**
 * Last Updated: 10/28/2020
 * Card View
 * @author Devin M. O'Brien
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import static Cards.models.CardLogger.logg;


public class CardView {

    // Required for JavaFx
    public CardView() {

    }

    public CardView(String x) {
        logg.entering(this.getClass().getName(), "CardViewController()");

        logg.exiting(this.getClass().getName(), "CardViewController()");
    }

    public Scene get_a_card() throws Exception {
        logg.entering(this.getClass().getName(), "start");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Template.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        logg.exiting(this.getClass().getName(), "start");
        return scene;
    }

}
