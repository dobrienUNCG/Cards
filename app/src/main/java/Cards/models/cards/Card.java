package Cards.models.cards;
/*
  Last Updated: 11/17/2020 4:49 PM
  Card Data Model

  @AUTHOR Devin M. O'Brien
 */

import Cards.models.MetaData;
import Cards.models.UID;

import java.util.ArrayList;
import java.util.UUID;

import static Cards.translators.jsoup.JSoupTranslator.getTextInBody;

public class Card implements NormalCard {

    private final MetaData metaData = new MetaData();
    private final ArrayList<CardEvent> events;
    private String body;

    /**
     * @param _name   Name of Card
     * @param _body   Body of Card HTML
     * @param _events CardEvents
     */
    public Card(String _name, String _body, ArrayList<CardEvent> _events) {
        metaData.setTitle(_name);
        if(!_body.contains("<script>"))
            this.body = _body + "<script>function openEvent(x){test.getCardEvent(x);}</script>";
        else {
            this.body = _body;
        }
        this.events = _events;
    }

    public void add_event(CardEvent _event) {
        this.events.add(_event);
    }

    /**
     * @param _index position
     * @deprecated Not Implemented
     */
    public void remove_event(int _index) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * @param uuid
     * @deprecated Not Implemented
     */
    public void remove_event(UUID uuid) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public String toString() {
        return "<section title=\"" + metaData.getTitle() + "\">" + this.body + "</section>";
    }

    public CardEvent getEvent(UID _uid) {
        for (CardEvent event :
                events) {
            if (event.getTaskEvent().getEventId().contentEquals(_uid.toString())) {
                return event;
            }

        }
        return null;
    }
    public CardEvent getEvent(String _uid) {
        for (CardEvent event :
                events) {
            if (event.getTaskEvent().getEventId().contentEquals(_uid.toString())) {
                return event;
            }

        }
        return null;
    }

//=================  GETTERS ===============
    //=====GETTER=====
    public String getBody() {
        return this.body;
    }

    public ArrayList<CardEvent> getEvents() {
        return events;
    }

    public String getName() {
        return this.metaData.getTitle();
    }

    public CardEvent getRecent() {
        CardEvent recent = null;
        for (CardEvent event : this.events) {
            if (null == recent) {
                recent = event;
            }

            if (recent.compareTo(event.getDate()) > 0) {
                recent = event;
            }

        }
        return recent;
    }

//=================  SETTERS  ===============
    //=====SETTERS=====
    public void setBody(String _input) {
        String test = getTextInBody(_input);
        String test2 = test.replaceAll("<((/b)|b)ody>", " ");
        this.body = test;

    }

    public void setName(String _name) {
        metaData.setTitle(_name);
    }
}
