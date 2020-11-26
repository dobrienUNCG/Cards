package Cards.data.request;
/**
 * Request Manager
 * @author Devin M. O'Brien
 *
 */

import Cards.translators.api.GoogleTranslator;

import java.util.ArrayList;
import java.util.Iterator;

public class RequestManager {

    ArrayList<Request> request = new ArrayList<>();
    GoogleTranslator googleTranslator = new GoogleTranslator();


    public RequestManager() {

    }

    ArrayList<String> submit() {
        Iterator<Request> requestIterator = request.iterator();
        Request current;
        RequestType requestType;
        ArrayList<String> response = new ArrayList<>();
        try {
            while (requestIterator.hasNext()) {
                current = requestIterator.next();
                requestType = current.getRequestType();
                switch (requestType) {
                    case GET_DATE_CREATED:
                        response.add(googleTranslator.taskDateCreated(current.getParams().get(0)));
                        break;
                    case GET_EVENTID:
                        response.add(googleTranslator.myEventId(current.getParams().get(0)));
                        break;
                    case GET_INFO_ALL:
                        response.add(googleTranslator.taskInfoAll(current.getParams().get(0)));
                        break;
                    case GET_IS_ALL_DAY:
                        response.add(String.valueOf(googleTranslator.taskAllDay(current.getParams().get(0))));
                        break;
                    case GET_DUE_DATE:
                        response.add(googleTranslator.taskDueDate(current.getParams().get(0)));
                        break;
                    case GET_DESCRIPTION:
                        response.add(googleTranslator.taskDescription(current.getParams().get(0)));
                        break;
                    case GET_DUE_TODAY:
                        response.add(String.valueOf(googleTranslator.dueToday()));
                        break;
                    case GET_UPCOMING:
                        response.add(String.valueOf(googleTranslator.upcomingTasks(Integer.parseInt(current.getParams().get(0)))));
                        break;
                    case POST_SUMMARY:
                        googleTranslator.editTaskSummary(current.getParams().get(0), current.getParams().get(1));
                        break;
                    case POST_DATE_BEGIN:
                        googleTranslator.editBeginDate(current.getParams().get(0), current.getTaskEvent().getEndDate());
                        break;
                    case POST_DATE_END:
                        googleTranslator.editEndDate(current.getParams().get(0), current.getTaskEvent().getEndDateTime());
                        break;
                    case POST_DESCRIPTION:
                        googleTranslator.editDescription(current.getParams().get(0), current.getParams().get(1));
                        break;
                    case DELETE_EVENT:
                        googleTranslator.deleteTask(current.getParams().get(0));
                        break;
                    case PUT_EVENT:
                        googleTranslator.newEvent(current.getTaskEvent());
                        break;
                }
            }
        }catch(Exception e){
            System.err.println(e);
        }
        request.clear();
        return response;

    }



   public void addRequest(Request _request) {

    }


}
