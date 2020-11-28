package Cards.data.request;
/**
 * Request Manager
 * @author Devin M. O'Brien
 *
 */

import Cards.models.settings.CardSettings;
import Cards.translators.api.GoogleTranslator;
import Cards.translators.api.TaskEvent;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

import static Cards.models.CardLogger.logg;

public class RequestManager {

     ArrayList<Request> request = new ArrayList<>();
    final GoogleTranslator googleTranslator = new GoogleTranslator();


    public RequestManager() {
        File test = new File("/requests.ser");
        if(test.exists()) {
            try {

                FileInputStream fileIn = new FileInputStream("requests.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                this.request = (ArrayList<Request>) in.readObject();
                in.close();
                fileIn.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {

            } catch (ClassNotFoundException _e) {
                _e.printStackTrace();
            }
        }
        if(!CardSettings.calendarCreated) {
            logg.info(String.valueOf(CardSettings.calendarCreated));
            addRequest(new Request(RequestType.PUT_CALENDAR));
        }
    }

    public ArrayList<Object> submit() {

        if (!this.request.isEmpty()) {
            try{
                FileOutputStream fileout = new FileOutputStream("request.ser");

                ObjectOutputStream out = new ObjectOutputStream(fileout);
                out.writeObject(this.request);

                out.close();
                fileout.close();
            }catch (IOException error){
                System.out.println(error);
            }
            Iterator<Request> requestIterator = this.request.iterator();
            Request current;
            RequestType requestType;
            ArrayList<Object> response = new ArrayList<>();
            int responseSizeBefore;
            int responseSizeAfter;
            try {
                while (requestIterator.hasNext()) {
                    current = requestIterator.next();
                    logg.info(current.toString());
                    requestType = current.getRequestType();
                    responseSizeBefore = response.size();
                    switch (requestType) {
                        case GET_DATE_CREATED -> response.add(googleTranslator.taskDateCreated(current.getTaskEvent().getEventId()));
                        case GET_EVENTID -> response.add(googleTranslator.myEventId(current.getTaskEvent().getSummary()));
                        case GET_INFO_ALL -> response.add(googleTranslator.taskInfoAll(current.getTaskEvent().getEventId()));
                        case GET_IS_ALL_DAY -> response.add((googleTranslator.taskAllDay(current.getTaskEvent().getEventId())));
                        case GET_DUE_DATE -> response.add(googleTranslator.taskDueDate(current.getTaskEvent().getEventId()));
                        case GET_DESCRIPTION -> response.add(googleTranslator.taskDescription(current.getTaskEvent().getEventId()));
                        case GET_DUE_TODAY -> response.add((googleTranslator.dueToday()));
                        case GET_UPCOMING -> response.add(googleTranslator.upcomingTasks(requestType.getI()));
                        case POST_SUMMARY -> googleTranslator.editTaskSummary(current.getOldTaskEvent().getSummary(), current.getTaskEvent().getSummary());
                        case POST_DATE_BEGIN -> googleTranslator.editBeginDate(current.getTaskEvent().getSummary(), current.getTaskEvent().getEndDate());
                        case POST_DATE_END -> googleTranslator.editEndDate(current.getTaskEvent().getSummary(), current.getTaskEvent().getEndDateTime());
                        case POST_DESCRIPTION -> googleTranslator.editDescription(current.getTaskEvent().getSummary(), current.getTaskEvent().getDescription());
                        case DELETE_EVENT -> googleTranslator.deleteTask(current.getTaskEvent().getEventId());
                        case PUT_EVENT -> googleTranslator.newEvent(current.getTaskEvent());
                        case PUT_CALENDAR -> response.add(googleTranslator.newCalendar());
                    }
                    responseSizeAfter = response.size();
                    if (responseSizeBefore == responseSizeAfter) {
                        response.add("");
                    }

                }
                CardSettings.calendarCreated = true;
                File serializedFile = new File("/requests.ser");
                if(serializedFile.exists()){
                        serializedFile.delete();
                }
            } catch (Exception e) {
                // TODO Change to logger
                System.err.println(e);
            }
            request.clear();

            return response;

        }
        return null;
    }

    public static  Request createEventRequest(TaskEvent _taskEvent){
        return new Request(RequestType.PUT_EVENT, _taskEvent);
    }



   public void addRequest(Request _request) {
        if(request.contains(_request))
            return;
        request.add(_request);

    }


}
