package Cards.models.cards;
/*
  Last Updated: 10/28/2020
  Card Event Data Model

  @AUTHOR Devin M. O'Brien
 */

import Cards.translators.api.TaskEvent;
import com.google.api.client.util.DateTime;

public class CardEvent{
   private TaskEvent taskEvent;

   public CardEvent(TaskEvent _taskEvent){
        this.taskEvent = _taskEvent;
   }

    public DateTime getDate() {
       return taskEvent.getBeginDateTime();
    }
    public long compareTo(DateTime _dateTime){
       return this.taskEvent.getBeginDateTime().getValue() - _dateTime.getValue();
    }

    public TaskEvent getTaskEvent() {
        return taskEvent;
    }

    public void setTaskEvent(TaskEvent _taskEvent) {
        this.taskEvent = _taskEvent;
    }

}
