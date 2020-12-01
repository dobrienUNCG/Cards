package Cards.controllers;
/*
  Last Updated: 11/26/2020
  Controller for Main Menu View

  @author Devin M. O'Brien
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