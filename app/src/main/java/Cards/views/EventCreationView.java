package Cards.views;
/***
 * Date: 11/25/2020
 * Event Creation Dialog
 *
 * @author Devin M. O'Brien
 */
import Cards.models.UID;
import Cards.translators.api.TaskEvent;
import com.google.api.client.util.DateTime;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.DateTimeStringConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

public class EventCreationView {
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
    void initialize(){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            startTime.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));
            endTime.setTextFormatter(new TextFormatter<>(new DateTimeStringConverter(format), format.parse("00:00")));

        }catch(Exception e){
            // TODO Change to Logger
            System.err.println(e);
        }
    }

    @FXML
    TaskEvent createEvent(){
        // TODO Setup
        try {
            return new TaskEvent(eventTitle.getText(), DateTime.parseRfc3339(startDate.getValue().toString() + "T" + startTime.getText()), DateTime.parseRfc3339(endDate.getValue().toString() + "T" + endTime.getText()),
                    description.getText(), DateTime.parseRfc3339(LocalDateTime.now().toString()), null, new UID().toString(), allDay.isSelected());
        } catch(Exception error){
            // TODO Change to logger
            System.err.println(error);
        }
        return null;
    }

    @Override
    public String toString() {
        return "EventCreationView{" +
                "description=" + description.getText() +
                ", eventTitle=" + eventTitle.getText() +
                ", startDate=" + startDate.getValue().toString() +
                ", endDate=" + endDate.getValue().toString() +
                ", startTime=" + startTime.getText() +
                ", endTime=" + endTime.getText() +
                ", allDay=" + allDay.isSelected() +
                ", completed=" + completed.isSelected() +
                '}';
    }
}
