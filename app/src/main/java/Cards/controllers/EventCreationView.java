/**MIT License

Copyright (c) 2021 Devin M. O'Brien

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
**/
package Cards.controllers;
/*
 * Date: 12/1/2020
 * Event Creation Dialog
 *
 * @author Devin M. O'Brien
 */

import Cards.models.UID;
import Cards.models.cards.CardEvent;
import Cards.translators.api.TaskEvent;
import com.google.api.client.util.DateTime;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import static Cards.models.CardLogger.logg;

public class EventCreationView {

    public CardEvent cardEvent;
    @FXML
    TextArea description;
    @FXML
    TextField eventTitle;
    @FXML
    DatePicker startDate;
    @FXML
    DatePicker endDate;
    @FXML
    TextField startTime;
    @FXML
    TextField endTime;
    @FXML
    CheckBox allDay;
    @FXML
    CheckBox completed;
    @FXML
    Button create;
    @FXML
    Button delete;
    boolean deleteEvent;
    String taskid;

    @FXML
    void initialize() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            this.startTime.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
            this.endTime.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));

        } catch (Exception error) {
            // TODO Change to Logger
            logg.warning(Arrays.toString(error.getStackTrace()));
        }


    }

    @FXML
    public TaskEvent createEvent() {
        Stage stage = (Stage) this.endTime.getScene().getWindow();
        stage.close();
        try {
            return new TaskEvent(this.eventTitle.getText(), new DateTime(this.startDate.getValue().toString() + 'T' + this.startTime.getText() + ":00.000-05:00"), new DateTime(this.endDate.getValue() + "T" + this.endTime.getText() + ":00.000-05:00"),
                    this.description.getText(), DateTime.parseRfc3339(LocalDateTime.now().toString()), null, null != this.cardEvent ? this.cardEvent.getTaskEvent().getEventId() : new UID().toString(), this.allDay.isSelected());
        } catch (Exception error) {
            logg.severe(Arrays.toString(error.getStackTrace()));
        }
        logg.warning("'EventCreationView' method 'createEvent' is returning null");

        return null;
    }

    @FXML
    public void deleteEvent() {
        this.deleteEvent = true;
        Stage stage = (Stage) this.endTime.getScene().getWindow();
        stage.close();
    }

    public void initData(CardEvent _event) {
        this.delete.disableProperty().set(false);
        this.delete.opacityProperty().setValue(1);
        this.taskid = _event.getTaskEvent().getEventId();

        this.allDay.disableProperty().set(true);
        this.cardEvent = _event;

        TaskEvent te = _event.getTaskEvent();

        this.description.setText(te.getDescription());
        DateTime beginDate = te.getBeginDate();
        String startingDate = beginDate.toString().replaceAll("(T.*)", "");

        this.startDate.setValue(LocalDate.parse(startingDate));

        this.endDate.setValue(LocalDate.parse(te.getEndDate().toString().replaceAll("T.*", "")));
        DateTime beginDateTime = te.getBeginDateTime();
        String startDateTime = beginDateTime.toString().replaceAll("(.*T)|(:00\\..*)", "");

        this.startTime.setText(startDateTime);
        String endDateTime = (te.getEndDateTime().toString().replaceAll("(.*T)|(:00\\..*)", ""));

        this.endTime.setText(endDateTime);
        this.eventTitle.setText(te.getSummary());
        this.completed.setSelected(_event.isComplete());

    }

    @Override
    public String toString() {
        return "description=\"" + this.description.getText() +
                "\" eventTitle=\"" + this.eventTitle.getText() +
                "\" startDate=\"" + this.startDate.getValue() +
                "\" endDate=\"" + this.endDate.getValue() +
                "\" startTime=\"" + this.startTime.getText() +
                "\" endTime=\"" + this.endTime.getText() +
                "\" allDay=\"" + this.allDay.isSelected() +
                "\" completed=\"" + this.completed.isSelected() +
                '"';
    }
}
