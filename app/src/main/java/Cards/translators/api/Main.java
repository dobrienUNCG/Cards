package Cards.translators.api;

/**
 * Updated 11/24/2020 - Added deleteTask() in the TESTING ZONE.
 *
 * Contains static methods calendarService() & getCredentials() for basic OAuth
 * access.
 *
 * @author Sage Bonfield
 */
import com.google.api.client.util.DateTime;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main {

    /**
     * Converts a date to a long value.
     *
     * @param _fullDay string of a date in the format yyyy-MM-dd
     * @return long milliseconds
     * @throws ParseException
     */
    static long dateInLong(String _fullDay) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(_fullDay);
        long milliseconds = date.getTime();
        return milliseconds;
    }

    /**
     * Converts a DateTime type to long in milliseconds.
     *
     * @param _dateTime
     * @return
     * @throws ParseException
     */
    static long dateTimeInLong(String _dateTime) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date date = sdf.parse(_dateTime);
        long milliseconds = date.getTime();
        return milliseconds;
    }

    /**
     * Gives full day of the week.
     *
     * @return (Wednesday, Thursday, etc.)
     */
    static String dayOfTheWeek() {

        //Uses Date type and creates and current Date.
        Date now = new Date();

        // Formats now to string of the full day. 'E' returns abbrev.
        SimpleDateFormat dayOfWeek = new SimpleDateFormat("EEEE");
        return dayOfWeek.format(now);
    }

    /**
     * Date and time in RFC3339 format.
     *
     * @return String of RFC3339
     */
    static String dateInRFC() {

        //Uses RFC3339 format for current date and time.
        SimpleDateFormat rfcDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

        //formats RFC3339 to String.
        String dateToday = rfcDate.format(new Date());
        return dateToday;
    }

    /**
     * Gives current date and time.
     *
     * @return DateTime in String form
     */
    static String dateInDateTime() {

        //Uses LocalDateTime class for current Date and Time.
        LocalDateTime localDateTime = LocalDateTime.now();

        //Formats to DateTime.
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        //Formats to String.
        String stringDateTime = localDateTime.format(formatter);
        return stringDateTime;
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException, ParseException {

        /*
        --------------TESTING ZONE BELOW--------------
        To start off fresh, the first thing you should do is un-comment the newCalendar() method.
        Once done, comment it back out to avoid creating duplicate calendars.
        Creating a new calendar specifically for this program will allow the user
        to see tasks specific to the program.
         */
 /*Creates a new calendar for this project*/
        String newCalendar = new GoogleTranslator().newCalendar();
        System.out.println(newCalendar);

        /*
        CalendarView.java testing
         */
 /*Day of the week*/
//        System.out.println(dayOfTheWeek() + " is the day of the week.");
        /*Date in RFC3339 format*/
//        System.out.println(dateInRFC() + " is the current date and time in RFC3339 format.");
        /*Date in Date Time format*/
//        System.out.println(dateInDateTime() + " is the current date and time in DateTime.");
        /* Your Tasker calendar ID */
//        System.out.println(GoogleTranslator.myCalendarId() + " is my Tasker Calendar ID.");
        /*Prints up to int x upcoming tasks on your calendar*/
        GoogleTranslator gerTrand = new GoogleTranslator();
        gerTrand.upcomingTasks(10);
        /*Prints tasks that are due today*/
        System.out.println("---");
        gerTrand.dueToday();

        /*
        Individual Task Info Testing
         */
//        System.out.println(gerTrand.myEventId("[PLACE EVENT NAME/SUMMARY HERE]"));
        /*Get task info. Use the myEventId method ^ to get the event ID for the pieces of info below.*/
//        System.out.println(gerTrand.taskInfoAll("[PLACE EVENT ID HERE]"));
        /*just Date Created*/
//        System.out.println("Date created: " + gerTrand.taskDateCreated("[PLACE EVENT ID HERE]"));
        /*just Description*/
//        System.out.println("Description: " + gerTrand.taskDescription("[PLACE EVENT ID HERE]"));
        /*just Due Date*/
//        System.out.println("Due date: " + gerTrand.taskDueDate("[PLACE EVENT ID HERE]"));
        /*just All-Day check*/
//        System.out.println("All day: " + gerTrand.taskAllDay("[PLACE EVENT ID HERE]"));
        /*
        TaskEvent object testing.
        Uncomment the two lines below to make a new event. Must include time zone shift.
        "-05:00" after Autumnal Equinox, "-04:00" after Spring Equinox.
         */
//        TaskEvent anotherEvent = new TaskEvent("Swab the poop deck", new DateTime("2020-11-22T17:00:00-05:00"), new DateTime("2020-11-22T17:30:00-05:00"), "Argh", null, null, null, false);
//        gerTrand.newEvent(anotherEvent);

        /*
        Update.java testing - Play with the getters and setters
         */
        //This is not an all-day event so set the last parameter as false
//        TaskEvent event = new TaskEvent("Feed the chickens", new DateTime("2020-11-18T06:30:00.000-05:00"), new DateTime("2020-11-18T07:00:00.000-05:00"), "gotta go feeeed the chickenss", null, null, null, false);
//        gerTrand.newEvent(event);
        //SetEventId must come before the getter because of how the object is designed. --> (..., null, null, null)
//        event.setEventId();
//        System.out.println(event.getEventId());
//        System.out.println(event.getSummary());
        //Uncomment to change the summary
//        event.setSummary("chicken time!");
//        System.out.println(event.getBeginDate());
        //Uncomment to change the start DateTime.
//        event.setBeginDateTime(new DateTime("2020-11-18T06:37:30-05:00"));
//        System.out.println(event.getEndDate());
        //Uncomment to change the end DateTime.
//        event.setEndDateTime(new DateTime("2020-11-18T06:49:59-05:00"));
//        System.out.println(event.getDescription());
        //Uncomment to change the description.
//        event.setDescription("mhmhmmmm chickensss");
//        setDateCreated must come before the getter because of how the object is designed. --> (..., null, null, null)
//        event.setDateCreated();
//        System.out.println(event.getDateCreated());
        //tests if event is all day
//      System.out.println("IS IT ALL DAY THO?");
//        System.out.println(event.getAllDay());

        /* Uncomment the 9 lines below to change the event to an All-Day and see the object info*/
//        event.setAllDay("2020-11-18", "2020-11-19");
//        System.out.println("---------");
//        System.out.println(event.getAllDay());
//        System.out.println(event.getEventId());
//        System.out.println(event.getSummary());
//        System.out.println(event.getBeginDate());
//        System.out.println(event.getEndDate());
//        System.out.println(event.getDescription());
//        System.out.println(event.getDateCreated());
        //Making an all-day event (set last param as true)
//        TaskEvent bedTime = new TaskEvent("Sleep day", new DateTime(dateInLong("2020-11-19")), new DateTime(dateInLong("2020-11-21")), "sleep all day", null, null, null, true);
//        gerTrand.newEvent(bedTime);
//        bedTime.setEventId();
//        bedTime.setDateCreated();
//        System.out.println(bedTime.getEventId());
//        System.out.println(bedTime.getSummary());
        //Uncomment to change the summary.
//        bedTime.setSummary("sleeeeepyy day");
//        System.out.println(bedTime.getBeginDate());
//        System.out.println(bedTime.getEndDate());
        //Uncomment to change the start and end date.
//        bedTime.setBeginDateTime(new DateTime(dateInLong("2020-11-20")));
//        bedTime.setEndDateTime(new DateTime(dateInLong("2020-11-22")));
//        System.out.println(bedTime.getDescription());
        //Uncomment to change the description.
//        bedTime.setDescription("sleeeeeeeeeep alll dayyy");
//        System.out.println(bedTime.getDateCreated());
//        System.out.println("IS IT ALL DAY THO?");
//        System.out.println(bedTime.getAllDay());

        /* Uncomment the lines of code below to change this all-day event to a not-all-day event, and see the opject details. */
//        bedTime.setNotAllDay(new DateTime("2020-11-19T07:30:00-05:00"), new DateTime("2020-11-19T08:30:00-05:00"));
//        System.out.println("---------");
//        System.out.println(bedTime.getAllDay());
//        System.out.println(bedTime.getEventId());
//        System.out.println(bedTime.getSummary());
//        System.out.println(bedTime.getBeginDateTime());
//        System.out.println(bedTime.getEndDateTime());
//        System.out.println(bedTime.getDescription());
//        System.out.println(bedTime.getDateCreated());

        //Delete a task
//        gerTrand.deleteTask(bedTime.getEventId());
    }

}
