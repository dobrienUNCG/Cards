package Cards.models;

import java.util.logging.*;


/**
 * Logger for Card Project
 */
public class CardLogger {
    /**
     * This is the logger; import into other classes to use it.
     * Can be *disabled* by changing the Level
     * @see Logger*
     * @since 10/25/2020
     */
    public static final Logger logg = Logger.getLogger(CardLogger.class.getName());

    /**
     * This sets up the logger.
     * Src: https://stackoverflow.com/questions/37713212/java-logger-does-not-print-anything-to-console
     */
    public static void logger_setup(){
        LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.FINEST);
        Handler handlerObj = new ConsoleHandler();
        handlerObj.setLevel(Level.ALL); // Change when release
        logg.addHandler(handlerObj);
        logg.setLevel(Level.ALL); // Change when release
        logg.setUseParentHandlers(false);
    }
}
