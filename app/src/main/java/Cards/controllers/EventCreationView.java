package Cards.controllers;
/*
 * Date: 11/25/2020
 * Event Creation Dialog
 *
 * @author Devin M. O'Brien
 */
import Cards.app.AppModel;
import Cards.models.UID;
import Cards.translators.api.TaskEvent;
import com.google.api.client.util.DateTime;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import static Cards.models.CardLogger.logg;
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

        }catch(Exception error){
            // TODO Change to Logger
            logg.warning(Arrays.toString(error.getStackTrace()));
        }
    }

    @FXML
    TaskEvent createEvent(){
        Stage stage =  (Stage) this.endTime.getScene().getWindow();
        stage.close();
        try {
            return new TaskEvent(this.eventTitle.getText(), new DateTime(this.startDate.getValue().toString()+'T'+ this.startTime.getText() + ":00.000-05:00"), new DateTime(this.endDate.getValue().toString() + "T" + endTime.getText() + ":00.000-05:00"),
                    this.description.getText(), DateTime.parseRfc3339(LocalDateTime.now().toString()), null, new UID().toString(), allDay.isSelected());
        } catch(Exception error){
            logg.severe(Arrays.toString(error.getStackTrace()));
        }
        logg.warning("'EventCreationView' method 'createEvent' is returning null");
        {
            AppModel.requestManager.submit();
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
