package Cards.models;

import Cards.data.request.Request;
import Cards.data.request.RequestType;
import Cards.models.cards.CardEvent;
import Cards.translators.api.TaskEvent;

import java.util.ArrayList;

public class CardEventDifference {

    enum Property{
        NAME, START_DATE, END_DATE, DESCRIPTION, COMPLETION, ALL_DAY;
    }
    public static void checkDifference(CardEvent _cardEvent1, CardEvent _cardEvent2){
        TaskEvent a = _cardEvent1.getTaskEvent();
        TaskEvent b = _cardEvent2.getTaskEvent();
        if(!a.getBeginDateTime().equals(b.getBeginDateTime())){

            AppModel.requestManager.addRequest((new Request(RequestType.POST_DATE_BEGIN, a, b)));

        }
        if(!a.getEndDateTime().equals(b.getEndDateTime())){

            AppModel.requestManager.addRequest((new Request(RequestType.POST_DATE_END, _cardEvent2.getTaskEvent())));
        }
        if(!a.getDescription().contentEquals(b.getDescription())){

            AppModel.requestManager.addRequest(new Request(RequestType.POST_DESCRIPTION, _cardEvent2.getTaskEvent()));
        }
        if(!a.getSummary().equals(b.getSummary())){

            AppModel.requestManager.addRequest(new Request(RequestType.POST_SUMMARY, a, _cardEvent2.getTaskEvent()));
        }


    }
}
