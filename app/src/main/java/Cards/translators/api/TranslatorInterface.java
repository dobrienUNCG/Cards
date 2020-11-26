package Cards.translators.api;

/**
 * Updated 11/24/20 - Added deleteTask()
 *
 * Implemented by the Google Calendar Translator (GoogleTranslator.java)
 *
 * @author Sage Bonfield
 */
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.List;

public interface TranslatorInterface {

    public String myEventId(String _eventSummary) throws GeneralSecurityException, IOException;

    public String taskInfoAll(String _eventId) throws IOException, GeneralSecurityException;

    public String taskDueDate(String _eventId) throws GeneralSecurityException, IOException;

    public String taskDescription(String _eventId) throws GeneralSecurityException, IOException;

    public String taskDateCreated(String _eventId) throws GeneralSecurityException, IOException;

    public boolean taskAllDay(String _eventId) throws GeneralSecurityException, IOException;

    public void editTaskSummary(String _oldSummary, String _newSummary) throws GeneralSecurityException, IOException;

    public void editBeginDate(String _eventSummary, DateTime _newBeginDate) throws GeneralSecurityException, IOException;

    public void editEndDate(String _eventSummary, DateTime _newEndDate) throws GeneralSecurityException, IOException;

    public void editDescription(String _eventSummary, String _newDescription) throws GeneralSecurityException, IOException;

    public void deleteTask(String _eventId) throws GeneralSecurityException, IOException;

    public List<TaskEvent> dueToday() throws GeneralSecurityException, IOException, ParseException;

    public List<TaskEvent> upcomingTasks(int _quantity) throws GeneralSecurityException, IOException;

    public String newCalendar() throws GeneralSecurityException, IOException;

    public void newEvent(TaskEvent _event) throws IOException, GeneralSecurityException;

    public String myCalendarId() throws IOException, GeneralSecurityException;

    public Calendar calendarService() throws GeneralSecurityException, IOException;

    public Credential getCredentials(final NetHttpTransport _httpTransport) throws IOException;
}