package Cards.data.request;

import Cards.models.AppModel;
import Cards.translators.api.TaskEvent;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {
private final RequestType requestType;

private TaskEvent taskEvent;
private TaskEvent oldTaskEvent;


public Request(RequestType _requestType){
    this.requestType = _requestType;
}
public Request(RequestType _requestType, TaskEvent _old, TaskEvent _new){
    this.requestType = _requestType;
            this.taskEvent = _new;
            this.oldTaskEvent = _old;
    AppModel.requestManager.addRequest(this);
}
public Request(RequestType _requestType, TaskEvent _old){
    this.requestType = _requestType;
            this.taskEvent = _old;
    AppModel.requestManager.addRequest(this);
}



    public RequestType getRequestType() {
        return requestType;
    }

    public TaskEvent getTaskEvent(){
    return taskEvent;
    }
    public TaskEvent getOldTaskEvent(){
    return oldTaskEvent;
    }


    public boolean equals(Request _o) {
        if(this.requestType == _o.requestType){
            if(this.taskEvent == _o.taskEvent){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestType, taskEvent, oldTaskEvent);
    }

    public void setTaskEvent(TaskEvent _taskEvent){
        this.taskEvent = _taskEvent;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType=" + requestType +
                ", taskEvent=" + taskEvent +
                ", oldTaskEvent=" + oldTaskEvent +
                '}';
    }
}
