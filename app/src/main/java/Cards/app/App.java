package Cards.app;
/**
 * Last Updated: 11/25/2020
 * Starting point of the application.
 *
 * @AUTHOR Devin M. O'Brien
 * @apiNote
 */

import Cards.models.settings.CardSettings;
import static Cards.models.CardLogger.logg;
import static Cards.models.CardLogger.loggerSetup;
import static Cards.models.settings.CardSettings.save_settings;


public class App {
    public static void main(String[] args) {
        AppModel appModel = new AppModel();
        appModel.launcher();
    }
}
