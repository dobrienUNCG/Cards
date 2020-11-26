package Cards.translators.api;

/**
 * Updated 11/13/20 - Added allDay parameter to constructor.
 *
 * Constructor for TaskEvent object with getters and setters.
 *
 * @author Jake Keels
 * @author Sage Bonfield
 */
import com.google.api.client.util.DateTime;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

public class TaskEvent extends Main {

    private final TranslatorInterface thisTranslator = new GoogleTranslator();
    private String summary;
    //All-Day Event use
    private DateTime beginDate;
    private DateTime endDate;
    //Not All-Day Event use
    private DateTime beginDateTime;
    private DateTime endDateTime;

    private String description;
    private DateTime dateCreated;
    private File fileLocation;
    private String eventId;
    private boolean allDay;

    public TaskEvent(String _summary, DateTime _beginDate, DateTime _endDate, String _description, DateTime _dateCreated, File _fileLocation, String _eventId, boolean _allDay) throws IOException, GeneralSecurityException {
        if (_summary != (null)) {
            summary = _summary;
        } else {
            throw new IllegalArgumentException("Event name cannot be null");
        }
        eventId = _eventId;
        allDay = _allDay;
        beginDate = _beginDate;
        endDate = _endDate;
        beginDateTime = _beginDate;
        endDateTime = _endDate;
        description = _description;
        dateCreated = _dateCreated;

    }

    //================================= GETTERS ================================
    /**
     * Gets the eventID associated with an event.
     *
     * @return eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * '
     * Gets the summary of an event.
     *
     * @return summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * *Only For Getting The Start Date to All-Day Events* Gets the start date
     * to an event.
     *
     * @return beginDate
     */
    public DateTime getBeginDate() {
        return beginDate;
    }

    /**
     * *Only For Getting The End Date For All-Day Events* Gets the end date for
     * an event.
     *
     * @return endDate
     */
    public DateTime getEndDate() {
        return endDate;
    }

    /**
     * *Only For Getting The Start Time Of Not-All-Day Events* Gets the start
     * time of an event.
     *
     * @return beginDateTime
     */
    public DateTime getBeginDateTime() {
        return beginDateTime;
    }

    /**
     * *Only For Getting End Time Of Not-All-Day Event* Gets the end time of an
     * event.
     *
     * @return endDateTime
     */
    public DateTime getEndDateTime() {
        return endDateTime;
    }

    /**
     * Gets the description of an event
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the date that the event was created.
     *
     * @return dateCreated
     */
    public DateTime getDateCreated() {
        return dateCreated;
    }

    /**
     * Returns true if All-Day event, false if otherwise.
     *
     * @return allDay
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public boolean getAllDay() throws GeneralSecurityException, IOException {
        return allDay;
    }

    /**
     * Gets the file location
     *
     * @return fileLocation
     */
    public File getFileLocation() {
        return fileLocation;
    }

    //================================= SETTERS ================================
    /**
     * Sets the Google-Created eventID to the object.
     *
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setEventId() throws GeneralSecurityException, IOException {
        String myEventId = this.thisTranslator.myEventId(summary);
        eventId = myEventId;
    }

    /**
     * Changes the summary of the event in Google Calendar and in the object.
     *
     * @param _newSummary name to which the event will change
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setSummary(String _newSummary) throws GeneralSecurityException, IOException {
        this.thisTranslator.editTaskSummary(this.summary, _newSummary);
        this.summary = _newSummary;
        System.out.println("Summary changed.");
    }

    /**
     * *Only For Setting Start Date To All-Day Event* Sets the start date to an
     * event.
     *
     * @param _beginLong
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setBeginDate(long _beginLong) throws GeneralSecurityException, IOException {

        DateTime newBeginDate = new DateTime(_beginLong);
        this.thisTranslator.editBeginDate(this.summary, newBeginDate);
        this.beginDate = new DateTime(_beginLong);
        System.out.println("Start date changed.");
    }

    /**
     * *Only For Setting The End Date To All-Day Events* Sets the end date to
     * an event.
     *
     * @param _endLong
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setEndDate(long _endLong) throws GeneralSecurityException, IOException {

        DateTime newEndDate = new DateTime(_endLong);
        this.thisTranslator.editEndDate(this.summary, newEndDate);
        this.endDate = new DateTime(_endLong);
        System.out.println("End date changed.");
    }

    /**
     * *Only For Setting The Start Time Of Not-All-Day Events* Sets the start
     * time of an event.
     *
     * @param _beginDateTime
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setBeginDateTime(DateTime _beginDateTime) throws GeneralSecurityException, IOException {

        this.thisTranslator.editBeginDate(this.summary, _beginDateTime);
        this.beginDateTime = _beginDateTime;
        System.out.println("Start date & time changed.");
    }

    /**
     * *Only For Setting End Time To Not-All-Day Event* Sets the end time of an
     * event.
     *
     * @param _endDateTime
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setEndDateTime(DateTime _endDateTime) throws GeneralSecurityException, IOException {

        this.thisTranslator.editEndDate(this.summary, _endDateTime);
        this.endDateTime = _endDateTime;
        System.out.println("End date & time changed.");
    }

    /**
     * Sets the description of an event.
     *
     * @param _description
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setDescription(String _description) throws GeneralSecurityException, IOException {
        this.thisTranslator.editDescription(this.summary, _description);
        this.description = _description;
        System.out.println("Description changed.");
    }

    /**
     * Sets the date that the event was created. Must use setter before getter
     * --> ....,null,null,null).
     *
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setDateCreated() throws GeneralSecurityException, IOException {
        dateCreated = new DateTime(this.thisTranslator.taskDateCreated(eventId));
    }

    /**
     * Sets an event to all-day and sets the start and end date.Event must not
     * be All-day to start this method.
     *
     * @param _startDate
     * @param _endDate
     * @throws java.text.ParseException
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setAllDay(String _startDate, String _endDate) throws ParseException, GeneralSecurityException, IOException {

        if (this.allDay == false) {

            long startLong = dateInLong(_startDate);
            long endLong = dateInLong(_endDate);
            setBeginDate(startLong);
            setEndDate(endLong);
            this.allDay = true;
        } else {
            System.out.println("Must not be all day task to run this method.");
        }
    }

    /**
     * Sets an event to not all day and sets the start and end time. Event must
     * be All-day to start this method.
     *
     * @param _startDateTime in RFC3339 format, include offset *for now
     *
     * @param _endDateTime in RFC3339 format, include offset *for now* (-5:00
     * after autumnal equinox to spring equinox, -04:00 otherwise)
     * @throws GeneralSecurityException
     * @throws IOException
     */
    public void setNotAllDay(DateTime _startDateTime, DateTime _endDateTime) throws GeneralSecurityException, IOException {

        if (this.allDay == true) {

            setBeginDateTime(_startDateTime);
            setEndDateTime(_endDateTime);
            this.allDay = false;
        } else {
            System.out.println("Must be all day task to run this method.");
        }
    }

    /**
     * Sets the file location.
     *
     * @param _fileLocation
     */
    public void setFileLocation(File _fileLocation) {
        this.fileLocation = _fileLocation;
    }

}
