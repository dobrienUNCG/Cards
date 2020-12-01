package Cards.data.request;
/**
 * Updated 11/27/20
 * Requests for the request manager.
 *
 * @Author Devin M. O'Brien
 */


import Cards.models.AppModel;
import Cards.translators.api.TaskEvent;

import java.io.Serializable;
import java.util.Objects;

public class Request implements Serializable {

    private final RequestType requestType;

    private TaskEvent taskEvent;
    private TaskEvent oldTaskEvent;

    public Request(RequestType _requestType) {
        this.requestType = _requestType;
    }

    public Request(RequestType _requestType, TaskEvent _old, TaskEvent _new) {
        this.requestType = _requestType;
        this.taskEvent = _new;
        this.oldTaskEvent = _old;
        AppModel.requestManager.addRequest(this);
    }

    public Request(RequestType _requestType, TaskEvent _old) {
        this.requestType = _requestType;
        this.taskEvent = _old;
        AppModel.requestManager.addRequest(this);
    }

    public RequestType getRequestType() {
        return this.requestType;
    }

    public TaskEvent getTaskEvent() {
        return this.taskEvent;
    }

    public TaskEvent getOldTaskEvent() {
        return this.oldTaskEvent;
    }

    public boolean equals(Request _o) {
        if (this.requestType == _o.requestType) {
            return this.taskEvent == _o.taskEvent;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.requestType, this.taskEvent, this.oldTaskEvent);
    }

    public void setTaskEvent(TaskEvent _taskEvent) {
        this.taskEvent = _taskEvent;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType=" + this.requestType +
                ", taskEvent=" + this.taskEvent +
                ", oldTaskEvent=" + this.oldTaskEvent +
                '}';
    }
}
