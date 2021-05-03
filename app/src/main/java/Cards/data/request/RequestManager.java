package Cards.data.request;
/**
 * Date: 5/3/21
 * Request Manager
 *
 * @author Devin M. O'Brien
 */

import Cards.models.AppModel;
import Cards.translators.api.GoogleTranslator;
import Cards.translators.api.TaskEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import static Cards.models.CardLogger.logg;

public class RequestManager {

    final GoogleTranslator googleTranslator = new GoogleTranslator();
    private ArrayList<Request> request = new ArrayList<>();

    public RequestManager() {
        File test = new File("/requests.ser");
        if (test.exists()) {
            try {

                FileInputStream fileIn = new FileInputStream("request.ser");
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
        if (!AppModel.settingsController.isCalendarCreated()) {
            addRequest(new Request(RequestType.PUT_CALENDAR));
        }
    }

    public ArrayList<Object> submit() {

        if (!this.request.isEmpty()) {
            try {
                FileOutputStream fileout = new FileOutputStream("request.ser");

                ObjectOutputStream out = new ObjectOutputStream(fileout);
                ArrayList<Request> temp = null;
                out.writeObject(temp);
                if (temp != null && temp.size() > 5) {
                    request = (ArrayList<Request>) temp.subList(temp.size() - 5, temp.size() - 1);
                }

                out.close();
                fileout.close();
            } catch (IOException error) {
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
                    System.out.println(current);
                    System.out.println(current.getOldTaskEvent());
                    System.out.println(current.getTaskEvent());
                    logg.info(current.toString());
                    requestType = current.getRequestType();
                    responseSizeBefore = response.size();
                    switch (requestType) {
			    case GET_DATE_CREATED:
				    response.add(googleTranslator.taskDateCreated(current.getTaskEvent().getEventId()));
				    break;
                        case GET_EVENTID: response.add(googleTranslator.myEventId(current.getTaskEvent().getSummary()));
					  break;
                        case GET_INFO_ALL: 
					  response.add(googleTranslator.taskInfoAll(current.getTaskEvent().getEventId()));
					  break;
                        case GET_IS_ALL_DAY:
					  response.add((googleTranslator.taskAllDay(current.getTaskEvent().getEventId())));
					  break;
			case GET_DUE_DATE:
					  response.add(googleTranslator.taskDueDate(current.getTaskEvent().getEventId()));
					  break;
			case GET_DESCRIPTION:
					  response.add(googleTranslator.taskDescription(current.getTaskEvent().getEventId()));
					  break;
			case GET_DUE_TODAY:
					  response.add((googleTranslator.dueToday()));
					  break;
                        case GET_UPCOMING:
					  response.add(googleTranslator.upcomingTasks(requestType.getI()));
					  break;
			case POST_SUMMARY: current.getOldTaskEvent().setSummary(current.getTaskEvent().getSummary());
					   break;
			case POST_DATE_BEGIN:
					   googleTranslator.editBeginDate(current.getTaskEvent().getSummary(), current.getTaskEvent().getEndDate());
					   break;
			case POST_DATE_END: 
					   googleTranslator.editEndDate(current.getTaskEvent().getSummary(), current.getTaskEvent().getEndDateTime());
					   break;
			case POST_DESCRIPTION:
					   googleTranslator.editDescription(current.getTaskEvent().getSummary(), current.getTaskEvent().getDescription());
					   break;
			case DELETE_EVENT: 
					   googleTranslator.deleteTask(current.getTaskEvent().getEventId());
					   break;
                        case PUT_EVENT:
					   googleTranslator.newEvent(current.getTaskEvent());
					   break;
			case PUT_CALENDAR:
					   response.add(googleTranslator.newCalendar());
					   break;
                    }
                    responseSizeAfter = response.size();
                    if (responseSizeBefore == responseSizeAfter) {
                        response.add("");
                    }

                }
                AppModel.settingsController.calendarCreated();
                File serializedFile = new File("request.ser");
                if (serializedFile.exists()) {
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

    public static Request createEventRequest(TaskEvent _taskEvent) {
        return new Request(RequestType.PUT_EVENT, _taskEvent);
    }

    public void addRequest(Request _request) {
        if (request.contains(_request))
            return;
        request.add(_request);

    }


}
