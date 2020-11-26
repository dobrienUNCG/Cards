package Cards.data.request;

import Cards.translators.api.TaskEvent;

import java.util.ArrayList;

public class Request {
private final RequestType requestType;
private final ArrayList<String> params;
private TaskEvent taskEvent;


public Request(RequestType _requestType, ArrayList<String> _params){
    this.requestType = _requestType;
            this.params = _params;
}

    public ArrayList<String> getParams() {
        return params;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public TaskEvent getTaskEvent(){
    return taskEvent;
    }
    public void setTaskEvent(TaskEvent _taskEvent){
        this.taskEvent = _taskEvent;
    }


}