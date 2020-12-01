package Cards.models;
/**
 * Updated: 11/18/2020
 * This class connects everything together.
 *
 * @Author Devin M. O'Brien
 */

import Cards.controllers.SettingsController;
import Cards.data.request.RequestManager;
import Cards.translators.io.CardFile;
import Cards.translators.io.ViewIO;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppModel extends Application {


    public static SettingsController settingsController = new SettingsController();
    public static RequestManager requestManager = new RequestManager();
    public static final ViewIO viewIO = new ViewIO();


    private static boolean launched;

    public AppModel() {

    }

    /**
     * Starts up the JavaFX Application.
     *
     * @param _stage Application Stage for JavaFX
     * @throws Exception FileIO
     */
    @Override
    public void start(Stage _stage) throws Exception {
        _stage.titleProperty().set("Cards");
        _stage.setScene(new Scene(changeView(ViewIO.View.MAINMENU)));
        _stage.show();

    }

    /**
     * Creates a new window from a scene.
     *
     * @param _scene
     */
    public static void newWindow(Scene _scene) {
        Stage stage = new Stage();
        stage.setScene(_scene);
        stage.show();
    }

    public static void newWindowHold(Scene _scene) {
        Stage stage = new Stage();
        stage.setScene(_scene);
        stage.showAndWait();
    }

    /**
     * @param _view
     * @return JavaFX Parent Element
     */
    public static Parent changeView(ViewIO.View _view) {
        switch (_view) {
            case HELP -> {
                return viewIO.getHelpScreen();
            }
            case CALENDAR -> {
                return viewIO.getCalendarScreen();
            }
            case SETTINGS -> {
                return viewIO.getSettingsScreen();
            }
            case MAINMENU -> {
                return viewIO.getMainMenu();
            }
            case EVENT -> {
                return viewIO.getEventCreator();
            }
            case CARD -> {
                return viewIO.getCardEditor();
            }
        }
        return null;
    }

    /**
     * Starts JavaFX Application
     */
    public final void launcher() {
        if (!launched) {
            launched = true;
            launch();
            settingsController.save();
        }
    }

}
