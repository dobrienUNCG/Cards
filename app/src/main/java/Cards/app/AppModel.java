package Cards.app;
/**
 * App Model
 * Updated: 11/18/2020
 *
 * This class connects the settings to the program. Technically, this also starts up the
 * program.
 *
 * @Author Devin M. O'Brien
 * @since 11/11/2020
 */

import Cards.models.ViewModel;
import Cards.models.settings.CardSettings;
import Cards.translators.io.CardFile;
import Cards.views.MainMenuView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Cards.models.settings.CardSettings.save_settings;


public class AppModel extends Application {

    CardSettings cardSettings;
    ViewModel viewModel = new ViewModel();
    public static CardFile activeFile = null;
    static boolean launched = false;
    public AppModel() {

    }

    @Override
    public void start(Stage _stage) throws Exception {
        _stage.titleProperty().set("Cards");
        _stage.setScene(viewModel.getMainMenu());
        _stage.show();

    }
     private void newWindow(Scene _scene){
        Stage stage = new Stage();
        stage.setScene(_scene);
        stage.show();
    }
    public static void changeView(ViewModel.View red){

    }


    /**
     * Starts JavaFX Application
     */
    final public void launcher() {
        if(!launched) {
            this.launched = true;
            cardSettings = new CardSettings();;
            launch();
            save_settings();
        }
    }

}
