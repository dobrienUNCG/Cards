package Cards.controllers;
/**
 * Date: 12/1/2020
 * Card settings controller
 * @author Devin M. O'Brien
 */

import Cards.models.settings.CardSettings;
import Cards.translators.io.CardFile;

import java.util.ArrayList;

public class SettingsController {

    private final CardSettings cardSettings = new CardSettings();

    public void removeCardFile(CardFile _cardFile) {
        this.cardSettings.removeCardFile(_cardFile);
    }

    public void addCardFile(CardFile _cardFile) {
        this.cardSettings.add_file(_cardFile);
    }

    public void save() {
        this.cardSettings.save_settings();
    }

    public void movetoFront(CardFile _cardFile) {
        this.cardSettings.moveToFront(_cardFile);
    }

    public void calendarCreated() {
        this.cardSettings.setCalendarCreated(true);
    }

    //=================  GETTERS ===============
    public ArrayList<CardFile> getCardFiles() {
        return this.cardSettings.getRecentFiles();
    }

    public boolean isCalendarCreated() {
        return this.cardSettings.isCalendarCreated();
    }


}
