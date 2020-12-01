package Cards.translators.io;
/*
 Date: 11/28/2020
 Centralized for loading all the views.
 @author: Devin M. O'Brien
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

import static Cards.models.CardLogger.logg;

public class ViewIO {

    public enum View {
        CARD, MAINMENU, HELP, CALENDAR, SETTINGS, PERSONAL, EVENT
    }

//=================  GETTERS ===============

    /**
     * Gets the parent node of the calendar screen.
     *
     * @return Parent node of the calendar screen, or null if failed
     */
    public Parent getCalendarScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/CalendarNew.fxml"));
            return loader.load();
        } catch (IOException _e) {
            logg.severe(_e.toString());
        }
        return null;
    }

    /**
     * Gets the parent node of the card editor.
     *
     * @return Parent node of the card editor screen, or null if failed.
     */
    public Parent getCardEditor() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Template.fxml"));
            Parent parent = loader.load();
            parent.setUserData(loader);
            parent.getStylesheets().add(this.getClass().getResource("/Card.css").toExternalForm());
            return parent;
        } catch (IOException _e) {
            logg.severe(_e.toString());
        }
        return null;
    }

    /**
     * Gets the parent node of the event creator.
     *
     * @return Parent node of the settings screen, or null if failed
     */
    public Parent getEventCreator() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/EventCreationDialog.fxml"));
            Parent parent = loader.load();
            parent.setUserData(loader);
            return parent;
        } catch (IOException _e) {
            logg.severe(_e.toString());
        }
        return null;
    }

    /**
     * Gets the parent node of the help screen.
     *
     * @return Parent node of the help screen, or null if failed.
     */
    public Parent getHelpScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Help.fxml"));
            return loader.load();
        } catch (IOException _e) {
            logg.severe(_e.toString());
        }
        return null;
    }

    /**
     * Gets the parent node of the main menu.
     *
     * @return Parent node of the main menu screen, or null if failed.
     */
    public Parent getMainMenu() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Menu.fxml"));
            Parent parentMainMenu = loader.load();
            parentMainMenu.getStylesheets().add("/Main_Menu.css");
            return parentMainMenu;
        } catch (IOException _e) {
            logg.severe(_e.toString());
        }
        return null;
    }

    /**
     * Gets parent node for the settings screen.
     *
     * @return Parent node of the settings screen, or null if failed
     */
    public Parent getSettingsScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("/Settings.fxml"));
            Parent settingsParent = loader.load();
            return settingsParent;
        } catch (Exception _e) {
            logg.severe(_e.toString());
        }
        return null;
    }

}
