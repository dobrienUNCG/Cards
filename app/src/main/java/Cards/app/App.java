package Cards.app;
/**
 * Last Updated: 10/28/2020
 * Starting point of the application
 * and sets up the logger.
 *
 * @AUTHOR Devin M. O'Brien
 */

import Cards.views.MainMenuView;

import static Cards.models.CardLogger.logg;
import static Cards.models.CardLogger.loggerSetup;

public class App {

    public static void main(String[] args) {

        loggerSetup();
        logg.fine("Logger has been setup");

        MainMenuView menu = new MainMenuView();
        menu.show();

        logg.exiting("App", "main");
    }
}
