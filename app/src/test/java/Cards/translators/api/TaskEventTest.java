package Cards.translators.api;

import com.google.api.client.util.DateTime;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

class TaskEventTest {

    @Test
    void TaskEvent() throws IOException, GeneralSecurityException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        System.out.println("This is a normal event: "+event.toString());
        event = new TaskEvent("I love events!", new DateTime("2021-10-10"), new DateTime("2021-10-10"), "new Desc", new DateTime("2021-10-10"), null, "newEventId", false);
        System.out.println("This is a normal event: "+event.toString());
        try {
            event = new TaskEvent(null, null, new DateTime("2021-10-10"), "new Desc", new DateTime("2021-10-10"), null, "newEventId", false);
        }catch(Exception _e){
            System.out.println(_e.toString());
        }
        System.out.println("This is an error case: ");
    }


    @Test
    void setEventId() {
        //Does not return any errors
    }

    @Test
    void setSummary() throws IOException, GeneralSecurityException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        System.out.println("This is the base case: " + event.getSummary());
        event.setSummary("This is a summary.");
        System.out.println("This is a normal case: " + event.getSummary());
        event.setSummary("This is another summary.");
        System.out.println("This is another normal case: " + event.getSummary());
        try {
            event.setSummary(null);
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an error case:" + event.getSummary());
        try {
            event.setSummary("");
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an edge case:" + event.getSummary());
    }

    @Test
    void setBeginDate() throws GeneralSecurityException, IOException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        System.out.println("This is the base case: " + event.getBeginDate());
        event.setBeginDate(new DateTime("2020-10-10").getValue());
        System.out.println("This is a normal case: " + event.getBeginDate());
        event.setBeginDate(new DateTime("2020-10-10").getValue());
        System.out.println("This is a normal case: " + event.getBeginDate());
        try {
            event.setBeginDate(new DateTime("2020-11-11").getValue());
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an error case: " + event.getBeginDate());
        try {
            event.setBeginDate(new DateTime("2020-10-10").getValue());
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an edge case:" + event.getBeginDate());
    }

    @Test
    void setEndDate() throws IOException, GeneralSecurityException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        System.out.println("This is the base case: " + event.getEndDate());
        event.setEndDate(new DateTime("2020-10-3").getValue());
        System.out.println("This is a normal case: " + event.getEndDate());
        event.setEndDate(new DateTime("2020-10-4").getValue());
        System.out.println("This is a normal case: " + event.getEndDate());
        try {
            event.setEndDate(new DateTime("2020-9-9").getValue());
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an error case: " + event.getEndDate());
        try {
            event.setEndDate(new DateTime("2020-10-10").getValue());
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an edge case:" + event.getEndDate());
    }

    @Test
    void setBeginDateTime() throws IOException, GeneralSecurityException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        System.out.println("This is the base case: " + event.getBeginDate());
        event.setBeginDateTime(new DateTime("2020-2-2"));
        System.out.println("This is a normal case: " + event.getBeginDate());
        event.setBeginDateTime(new DateTime("2020-1-1"));
        System.out.println("This is a normal case: " + event.getBeginDate());
        try {
            event.setBeginDateTime(new DateTime("2020-12-12"));
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an error case: " + event.getBeginDate());
        try {
            event.setBeginDateTime(new DateTime("2020-10-10"));
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an edge case:" + event.getBeginDate());
    }

    @Test
    void setEndDateTime() throws IOException, GeneralSecurityException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        System.out.println("This is the base case: " + event.getEndDateTime());
        event.setEndDateTime(new DateTime("2020-10-10"));
        System.out.println("This is a normal case: " + event.getSummary());
        event.setEndDateTime(new DateTime("2020-10-10"));
        System.out.println("This is a normal case: " + event.getSummary());
        try {
            event.setEndDateTime(new DateTime("2020-9-9"));
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an error case: " + event.getEndDateTime());
        try {
            event.setEndDateTime(new DateTime("2020-10-10"));
        } catch (Exception _e) {
            System.out.println(_e.toString());
        }
        System.out.println("This is an edge case:" + event.getEndDateTime());
    }

    @Test
    void setDescription() {
        //Does not generate any error cases
    }

    @Test
    void setDateCreated() {
        //Does not generate any error cases
    }

    @Test
    void setAllDay() throws IOException, GeneralSecurityException, ParseException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        System.out.println("this is a base case: " + event.getAllDay());
        try {
            event.setAllDay("2020-10-10", "2020-10-10");
        }catch(Exception _e){
            System.out.println(_e.toString());
        }
        System.out.println("This is an edge/error case as it only checks a boolean value: "+event.getAllDay());
        event.setNotAllDay(new DateTime("2020-10-10"), new DateTime("2020-10-10"));//resets the boolean
        event.setAllDay("2020-10-10", "2020-10-10");
        System.out.println("This is a normal case: "+event.getAllDay());
    }

    @Test
    void setNotAllDay() throws IOException, GeneralSecurityException {
        TaskEvent event = new TaskEvent("Nada here", new DateTime("2020-10-10"), new DateTime("2020-10-10"), "desc", new DateTime("2020-10-10"), null, "eventId", true);
        event.setNotAllDay(new DateTime("2020-10-10"), new DateTime("2020-10-10"));
        System.out.println("This is a normal case: "+ event.getAllDay());
        try{
            event.setNotAllDay(new DateTime("2020-10-10"), new DateTime("2020-10-10"));
        }catch(Exception _e){
            System.out.println(_e.toString());
        }
        System.out.println("This is a error/edge case: "+ event.getAllDay());
    }

    @Test
    void setFileLocation() {
        //This does not return any errors, file location was deprecated
    }
}