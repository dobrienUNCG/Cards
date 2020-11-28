package Cards.models;
/*
  Updated: 11/18/2020
  This class connects everything together.

  @Author Devin M. O'Brien
 */

import Cards.data.request.RequestManager;

import Cards.models.settings.CardSettings;
import Cards.translators.io.CardFile;
import Cards.translators.io.ViewIO;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static Cards.models.settings.CardSettings.save_settings;

public class AppModel extends Application {
    // TODO Change Acces MTYpe
    public static final ViewIO viewIO = new ViewIO();
    public static CardFile activeFile;
    public static RequestManager requestManager;
    static boolean launched;
    CardSettings cardSettings;

    /**
     * Starts up the JavaFX Application.
     *
     * @param _stage Application Stage for JavaFX
     * @throws Exception FileIO
     */
    @Override
    public void start(Stage _stage) throws Exception {
        _stage.titleProperty().set("Cards");
        _stage.setScene(new Scene(viewIO.getMainMenu()));
        _stage.show();

    }

    /**
     * Creates a new window from a scene.
     * @param _scene JavaFX scene
     */
    public static void newWindow(Scene _scene) {
        Stage stage = new Stage();
        stage.setScene(_scene);
        stage.show();
    }

    /**
     * Creates a window then waits
     * @param _scene JavaFX scene
     */
    public static void newWindowHold(Scene _scene) {
        Stage stage = new Stage();
        stage.setScene(_scene);
        stage.showAndWait();
    }

    /**
     * Gets the parent node for a view
     * @param _view reqyested view type
     * @return JavaFX Parent Element
     */
    public static Parent changeView(ViewIO.View _view) {
        Parent out = null;
        switch (_view) {
            case HELP -> {
                out = viewIO.getHelpScreen();
            }
            case CALENDAR -> {
                out = viewIO.getCalendarScreen();
            }
            case SETTINGS -> {
                out = viewIO.getSettingsScreen();
            }
            case MAINMENU -> {
                out = viewIO.getMainMenu();
            }
            case EVENT -> {
                out = viewIO.getEventCreator();
            }
            case CARD -> {
                out = viewIO.getCardEditor();
            }
        }
        return out;
    }

    /**
     * Starts JavaFX Application
     */
    public final void launcher() {
        if (!launched) {
            launched = true;
            this.cardSettings = new CardSettings();
            requestManager = new RequestManager();
            launch();
            save_settings();
            requestManager.submit();
        }
    }

}
