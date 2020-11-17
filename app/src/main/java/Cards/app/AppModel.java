package Cards.app;
/**
 * App Model
 *
 * This class connects the settings to the program. Technically, this also starts up the
 * program.
 *
 * @Author Devin M. O'Brien
 * @since 11/11/2020
 */

import Cards.models.settings.CardSettings;
import Cards.translators.io.CardFile;
import Cards.views.MainMenuView;
import javafx.application.Application;
import javafx.stage.Stage;

import static Cards.models.settings.CardSettings.save_settings;


public class AppModel extends Application {

    CardSettings cardSettings;
    public static CardFile activeFile = null;

    public AppModel() {

    }

    @Override
    public void start(Stage _stage) throws Exception {
        _stage.titleProperty().set("Cards");
        _stage.setScene(new MainMenuView().mainmenu());
        _stage.show();

    }

    /**
     * Starts JavaFX Application
     */
    public void launcher() {
        launch();
    }

}
