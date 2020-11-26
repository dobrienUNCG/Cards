package Cards.translators.api;

/**
 * Updated 11/24/20 - Added deleteTask().
 *
 * Contains all of the methods that will connect to the Google Calendar API.
 *
 * @author Sage Bonfield
 * @author Jake Keels
 */
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Sage Bonfield
 * @author Jake Keels
 */
public class GoogleTranslator implements TranslatorInterface {

    protected static final String APPLICATION_NAME = "Tasker";
    protected static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    protected static final String TOKENS_DIRECTORY_PATH = "tokens";
    protected static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    /* Put your own credentials file path in CREDENTIALS_FILE_PATH
     * Credentials go into a seperate package called ResourcePackage.
     * Goto README.md for directions on creating credentials.json and new package.*/
    protected static final String CREDENTIALS_FILE_PATH = "/credentials.json" ;
    protected static final String CALENDAR_NAME = "My Tasker Calendar";

    /**
     * Uses the API to find the event in a calendar and return the eventId.
     *
     * @param _eventSummary the name associated with the event
     * @return event ID associated with the event name
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public String myEventId(String _eventSummary) throws GeneralSecurityException, IOException {

        // Iterate over the events in the specified calendar.
        String pageToken = null;
        String myEventId = null;
        do {
            Events events = calendarService().events().list(myCalendarId())
                    .setPageToken(pageToken)
                    .execute();

            //searches through a list to match the summary and find ID.
            List<Event> items = events.getItems();
            for (Event event : items) {
                if (event.getSummary().equals(_eventSummary)) {
                    myEventId = event.getId();
                }
                pageToken = events.getNextPageToken();
            }
        } while (pageToken != null);

        return myEventId;
    }

    /**
     * Returns the event's summary, date created, date end, description, event
     * ID, and status.
     *
     * @param _eventId Uses this to find the event summary
     * @return some task information as String
     * @throws java.io.IOException
     * @throws java.security.GeneralSecurityException
     */
    @Override
    public String taskInfoAll(String _eventId) throws IOException, GeneralSecurityException {

        // Retrieve a task.
        Event event = calendarService().events().get(myCalendarId(), _eventId)
                .execute();

        //task summary
        String thisSummary = event.getSummary();

        //gets date task was created.
        DateTime thisDateTimeCreated = event.getCreated();
        String thisDateTime = thisDateTimeCreated.toString();

        //task ending time
        EventDateTime eventEndDateTime = event.getEnd();
        String endDateTime = eventEndDateTime.toString();

        //task is complete?
        String isConfirmed = event.getStatus();

        //task beginning time
        EventDateTime eventBeginEventDateTime = event.getStart();
        String beginDateTime = eventBeginEventDateTime.toString();

        //is all day? checks for a certain character to signify whether or not All-Day.
        boolean isAllDay = false;
        if (beginDateTime.charAt(20) == '}') {
            isAllDay = true;
        }

        //task description
        String descrip;
        if (event.getDescription() == null) {
            descrip = null;
        } else {
            descrip = event.getDescription();
        }
        return "'Summary':" + thisSummary + "\n"
                + "'Begin Date':" + beginDateTime + "\n"
                + "'End Date':" + endDateTime + "\n"
                + "'Task Description':" + descrip + "\n"
                + "'Date Created':" + thisDateTime + "\n"
                + "'Task ID':" + _eventId + "\n"
                + "'All Day':" + isAllDay + "\n"
                + "'Status':" + isConfirmed + "\n";
    }

    /**
     * Gives the end date of the task.
     *
     * @param _eventId
     * @return task end date
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public String taskDueDate(String _eventId) throws GeneralSecurityException, IOException {

        // Retrieve a task.
        Event event = calendarService().events().get(myCalendarId(), _eventId)
                .execute();

        return event.getEnd().toString();
    }

    /**
     * Gives the task's description or null if no description available.
     *
     * @param _eventId Google-created ID associated with this event
     * @return description assigned with certain task
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public String taskDescription(String _eventId) throws GeneralSecurityException, IOException {

        //Retrieve a task.
        Event event = calendarService().events().get(myCalendarId(), _eventId)
                .execute();

        //the description can be absent from a task.
        String descrip;
        if (event.getDescription() == null) {
            descrip = null;
        } else {
            descrip = event.getDescription();
        }
        return descrip;
    }

    /**
     * Gives the date that the task was created.
     *
     * @param _eventId Google-created ID associated with this task
     * @return date task was created in DateTime
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public String taskDateCreated(String _eventId) throws GeneralSecurityException, IOException {

        //Retrieve a task.
        Event event = calendarService().events().get(myCalendarId(), _eventId)
                .execute();

        //Date created is DateTime type.
        DateTime thisDateTimeCreated = event.getCreated();
        String thisDateTime = thisDateTimeCreated.toString();
        return thisDateTime;
    }

    /**
     * Checks whether or not a task is set to All-Day.
     *
     * @param _eventId Google-Created ID associated with this event
     * @return Boolean
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public boolean taskAllDay(String _eventId) throws GeneralSecurityException, IOException {

        //Retrieve a task.
        Event event = calendarService().events().get(myCalendarId(), _eventId)
                .execute();

        //All All-Day events have the timestamp below in their start and end times.
        String tester = "00:00:00";
        EventDateTime beginEventDateTime = event.getStart();
        EventDateTime endEventDateTime = event.getEnd();
        String beginDateTime = beginEventDateTime.toString();
        String endDateTime = endEventDateTime.toString();

        boolean isAllDay = false;
        //Checks for a certain characters to signify whether or not All-Day.
        if (beginDateTime.substring(24, 32).equals(tester) && endDateTime.substring(24, 32).equals(tester)) {
            isAllDay = true;
        }
        return isAllDay;
    }

    /**
     * Shows tasks that are due today from the Tasker Calendar.
     *
     * @return
     * @throws java.security.GeneralSecurityException
     * @throws java.io.IOException
     * @throws java.text.ParseException
     */
    @Override
    public List<TaskEvent> dueToday() throws GeneralSecurityException, IOException, ParseException {

        List<TaskEvent> taskList = new ArrayList<>();
        Date now = new Date();
        DateTime todayDateTime = new DateTime(now);
        //Determines tomorrow's date to test all-day tasks.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(java.util.Calendar.DATE, 1);
        String tomorrowDate = dateFormat.format(calendar.getTime());
        //Uses google calendar api to list current events.
        Events events = calendarService().events().list(myCalendarId())
                .setTimeMin(todayDateTime)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();
        String todayDueCheck;
        String thisEventEndDate;
        String thisEventEndDateTime;
        //Creates list of events
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No tasks due today.");
        } else {
            for (Event event : items) {
                todayDueCheck = todayDateTime.toString().substring(0, 10);
                thisEventEndDate = null;
                thisEventEndDateTime = null;
                String eventEndString = event.getEnd().toString();
                TaskEvent thisEvent;
                DateTime eventStart;
                DateTime eventEnd;
                boolean allDay = false;
                //Is it all-day?
                if (eventEndString.charAt(20) == '}') {
                    //Yes, parse from Date type and change today checker to tomorrow.
                    todayDueCheck = tomorrowDate;
                    thisEventEndDate = eventEndString.substring(9, 19);
                } else {
                    //No, parse from DateTime type. Keep today checker the same.
                    thisEventEndDateTime = eventEndString.substring(13, 23);
                }
                //is it due today?
                if (todayDueCheck.equals(thisEventEndDate)) {
                    eventStart = new DateTime(event.getStart().getDate().getValue());
                    eventEnd = new DateTime(event.getEnd().getDate().getValue());
                    thisEvent = new TaskEvent(event.getSummary(), eventStart, eventEnd, event.getDescription(), event.getCreated(), null, event.getId(), allDay);
                    taskList.add(thisEvent);
                } else if (todayDueCheck.equals(thisEventEndDateTime)) {
                    eventStart = event.getStart().getDateTime();
                    eventEnd = event.getEnd().getDateTime();
                    thisEvent = new TaskEvent(event.getSummary(), eventStart, eventEnd, event.getDescription(), event.getCreated(), null, event.getId(), allDay);
                    taskList.add(thisEvent);
                }
            }
        }
        return taskList;
    }

    /**
     * Prints upcoming tasks from Tasker Calendar.
     *
     * @param _quantity
     * @return
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public List<TaskEvent> upcomingTasks(int _quantity) throws GeneralSecurityException, IOException {

        DateTime now = new DateTime(System.currentTimeMillis());
        List<TaskEvent> taskList = new ArrayList<>();
        String allDayTester = "00:00:00";

        //Uses google calendar api to list current events.
        Events events = calendarService().events().list(myCalendarId())
                .setMaxResults(_quantity)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();

        //Creates list of events.
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming tasks found.");
        } else {
            for (Event event : items) {
                DateTime eventStart = null;
                DateTime eventEnd = null;
                boolean allDay = false;
                TaskEvent thisEvent = new TaskEvent(event.getSummary(), eventStart, eventEnd, event.getDescription(), event.getCreated(), null, event.getId(), allDay);
                eventStart = event.getStart().getDateTime();
                eventEnd = event.getEnd().getDateTime();
                if (eventStart == null) {
                    eventStart = event.getStart().getDate();
                    eventEnd = event.getEnd().getDate();
                    allDay = true;
                }
                thisEvent = new TaskEvent(event.getSummary(), eventStart, eventEnd, event.getDescription(), event.getCreated(), null, event.getId(), allDay);
                taskList.add(thisEvent);
            }
        }
        return taskList;
    }

    /**
     * Inserts a new calendar for a user. Should only be used once per person.
     *
     * @return null if successful
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public String newCalendar() throws GeneralSecurityException, IOException {

        // Create a new calendar.
        com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(CALENDAR_NAME);
        calendar.setTimeZone("America/New_York");

        // Insert the new calendar.
        calendarService().calendars().insert(calendar).execute();

        return calendar.getId();
    }

    /**
     * Inserts a new TaskEvent into Google Calendar.
     *
     * @param _event
     * @throws IOException
     * @throws GeneralSecurityException
     */
    @Override
    public void newEvent(TaskEvent _event) throws IOException, GeneralSecurityException {

        //Creates an event and adds the summary and description.
        Event event = new Event().setSummary(_event.getSummary());
        event.setDescription(_event.getDescription());

        //Sets the beginning of the event.
        EventDateTime start = new EventDateTime().setDateTime(_event.getBeginDate());
        event.setStart(start);

        //Sets the end of the event.
        EventDateTime end = new EventDateTime().setDateTime(_event.getEndDate());
        event.setEnd(end);

        //Uploads the event to the calendar.
        calendarService().events().insert(myCalendarId(), event).execute();
    }

    /**
     * Helper method for getting Tasker Calendar ID. ------
     *
     * @return calendar ID as String
     * @throws IOException
     * @throws GeneralSecurityException
     */
    @Override
    public String myCalendarId() throws IOException, GeneralSecurityException {

        // Iterate through entries in calendar list.
        String pageToken = null;
        String myCalendarId = null;
        do {
            CalendarList calendarList = calendarService().calendarList().list().setPageToken(pageToken).execute();
            List<CalendarListEntry> items = calendarList.getItems();

            //Searches for the calendar in Google Calendar with a matching summary "My Tasker Calendar".
            for (CalendarListEntry calendarListEntry : items) {
                if (calendarListEntry.getSummary().equals("My Tasker Calendar")) {
                    myCalendarId = calendarListEntry.getId();
                }
            }
            pageToken = calendarList.getNextPageToken();
        } while (pageToken != null);
        return myCalendarId;
    }

    /**
     * Initializes a service calendar with valid OAuth credentials.
     *
     * @return valid calendar
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public Calendar calendarService() throws GeneralSecurityException, IOException {

        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

        // Initialize Calendar service with valid OAuth credentials.
        Calendar service = new Calendar.Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();

        return service;
    }

    /**
     * Uses credentials file path for valid OAuth credentials.
     *
     * @param _httpTransport
     * @return valid credentials
     * @throws IOException
     */
    @Override
    public Credential getCredentials(final NetHttpTransport _httpTransport) throws IOException {
        // Load client secrets.
        InputStream input = Main.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (input == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(input));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                _httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    /**
     * Changes the summary of the event in Google Calendar.
     *
     * @param _oldSummary
     * @param _newSummary
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public void editTaskSummary(String _oldSummary, String _newSummary) throws GeneralSecurityException, IOException {
        // Retrieve the event from the API.
        Event event = calendarService().events().get(myCalendarId(), myEventId(_oldSummary)).execute();
        // Make a change.
        event.setSummary(_newSummary);
        // Update the event.
        Event updatedEvent = calendarService().events().update(myCalendarId(), myEventId(_oldSummary), event).execute();
        System.out.println("Changing " + _oldSummary + " to be called " + _newSummary + " in Google Calendar...");
    }

    /**
     * Sets a new start date for an all-day event. IMPORTANT: 400 Error will
     * occur if the start time is ahead of the end time or the same as end time.
     * (i.e. start: 2020-11-18, end: 2020-11-19 = 12am November 18 to 12am
     * November 19)
     *
     * @param _newBeginDate
     * @param _eventSummary
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public void editBeginDate(String _eventSummary, DateTime _newBeginDate) throws GeneralSecurityException, IOException {

        EventDateTime thisNewBeginDate = new EventDateTime().setDateTime(_newBeginDate);
        // Retrieve the event from the API
        Event event = calendarService().events().get(myCalendarId(), myEventId(_eventSummary)).execute();
        // Make a change
        event.setStart(thisNewBeginDate);
        //Update in Google
        Event updatedEvent = calendarService().events().update(myCalendarId(), myEventId(_eventSummary), event).execute();
        System.out.println("Start date and time changed to " + _newBeginDate.toString() + " in Google Calendar.");
    }

    /**
     * Sets a new end date for an all-day event. IMPORTANT: 400 Error will occur
     * if the start time is ahead of the end time or the same as end time. (i.e.
     * start: 2020-11-18, end: 2020-11-19 = 12am November 18 to 12am November
     * 19)
     *
     * @param _newEndDate
     * @param _eventSummary
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public void editEndDate(String _eventSummary, DateTime _newEndDate) throws GeneralSecurityException, IOException {

        EventDateTime thisNewEndDate = new EventDateTime().setDateTime(_newEndDate);
        // Retrieve the event from the API
        Event event = calendarService().events().get(myCalendarId(), myEventId(_eventSummary)).execute();
        // Make a change
        event.setEnd(thisNewEndDate);
        //Update in Google
        Event updatedEvent = calendarService().events().update(myCalendarId(), myEventId(_eventSummary), event).execute();
        System.out.println("End date and time changed to " + _newEndDate.toString() + " in Google Calendar.");
    }

    /**
     * Changes the description of the event in Google Calendar.
     *
     * @param _eventSummary
     * @param _newDescription
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public void editDescription(String _eventSummary, String _newDescription) throws GeneralSecurityException, IOException {

        // Retrieve the event from the API.
        Event event = calendarService().events().get(myCalendarId(), myEventId(_eventSummary)).execute();
        // Make a change.
        event.setDescription(_newDescription);
        //Update in Google.
        Event updatedEvent = calendarService().events().update(myCalendarId(), myEventId(_eventSummary), event).execute();
        System.out.println("Changing event description to " + _newDescription + " in Google Calendar.");
    }

    /**
     * Deletes a task using the task eventID.
     *
     * @param _eventId
     * @throws GeneralSecurityException
     * @throws IOException
     */
    @Override
    public void deleteTask(String _eventId) throws GeneralSecurityException, IOException {
        // Delete an event
        calendarService().events().delete(myCalendarId(), _eventId).execute();
    }
}
