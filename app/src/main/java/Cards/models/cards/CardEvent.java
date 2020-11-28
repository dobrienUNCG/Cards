package Cards.models.cards;
/*
  Last Updated: 10/28/2020
  Card Event Data Model

  @AUTHOR Devin M. O'Brien
 */

import Cards.translators.api.TaskEvent;
import com.google.api.client.util.DateTime;

public class CardEvent {

    private TaskEvent taskEvent;
    private Boolean complete;

    public CardEvent(TaskEvent _taskEvent, Boolean _complete) {
        this.taskEvent = _taskEvent;
        this.complete = _complete;
    }

    public long compareTo(DateTime _dateTime) {
        return this.taskEvent.getBeginDateTime().getValue() - _dateTime.getValue();
    }

//=================  GETTERS ===============
    public DateTime getDate() {
        return this.taskEvent.getBeginDateTime();
    }

    public TaskEvent getTaskEvent() {
        return this.taskEvent;
    }

    public boolean isComplete() {
        return this.complete;
    }

//=================  SETTERS  ===============
    public void setTaskEvent(TaskEvent _taskEvent) {
        this.taskEvent = _taskEvent;
    }
    public void setComplete(boolean _complete){
        this.complete = _complete;
    }

}
