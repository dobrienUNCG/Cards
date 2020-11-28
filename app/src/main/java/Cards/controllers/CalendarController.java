package Cards.controllers;
/*
  Last Updated: 11/26/2020
  Controller for Main Menu View

  @author Devin M. O'Brien
 */


import Cards.app.AppModel;

import Cards.models.cards.CardList;
import Cards.models.settings.CardSettings;
import Cards.translators.io.HTMLTranslator;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import Cards.models.Calendar;


public class CalendarController {
    @FXML
    Label CalendarOutput;
    /**
     * Sets up cards
     */
    @FXML
    public void initialize() {
        Calendar calendar = new Calendar();
        CalendarOutput.setText(calendar.calendarSetup());

    }
}