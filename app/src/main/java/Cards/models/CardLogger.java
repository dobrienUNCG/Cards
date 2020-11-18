package Cards.models;

import java.util.logging.*;

/**
 * Last Updated: 10/28/2020
 * Logger for Card Project
 *
 * @author Devin M. O'Brien
 */
public class CardLogger {
    /**
     * This is the logger; import into other classes to use it.
     * Can be *disabled* by changing the Level
     *
     * @see Logger*
     */
    public static final Logger logg = Logger.getLogger(CardLogger.class.getName());

    /**
     * This sets up the logger.
     * Src: https://stackoverflow.com/questions/37713212/java-logger-does-not-print-anything-to-console
     */
    public static void loggerSetup() {
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINEST);
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL);
        logg.addHandler(handlerObj);
        logg.setLevel(Level.ALL);
        logg.setUseParentHandlers(false);
    }
}
