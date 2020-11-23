package Cards.app;
/**
 * Last Updated: 10/28/2020 (Need to Update)
 * Starting point of the application
 * and sets up the logger.
 *
 * @AUTHOR Devin M. O'Brien
 */

import Cards.models.settings.CardSettings;

import static Cards.models.CardLogger.logg;
import static Cards.models.CardLogger.loggerSetup;
import static Cards.models.settings.CardSettings.save_settings;


public class App {
    public static void main(String[] args) {
       // CardSettings settings = new CardSettings();
       //  save_settings();
        loggerSetup();
        logg.fine("Logger has been setup");
        AppModel appModel = new AppModel();
        appModel.launcher();
        save_settings();
        logg.exiting("App", "main");
    }
}
