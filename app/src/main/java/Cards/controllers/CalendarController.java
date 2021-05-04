package Cards.controllers;
/*
  Last Updated: 05/04/2021
  Controller for Main Menu View

  @authors Jake Keels and Devin M. O'Brien
 */

import Cards.models.Calendar;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalendarController {

    @FXML
    Label CalendarOutput;

    /**
     * Sets up cards
     */
    @FXML
    public void initialize() {
        Calendar calendar = new Calendar();
        this.CalendarOutput.setText(calendar.calendarSetup());

    }
}
