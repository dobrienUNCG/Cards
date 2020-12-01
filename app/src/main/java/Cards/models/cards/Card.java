package Cards.models.cards;
/*
  Last Updated: 11/17/2020 4:49 PM
  Card Data Model

  @AUTHOR Devin M. O'Brien
 */

import Cards.models.MetaData;
import Cards.models.UID;

import java.util.ArrayList;

import static Cards.translators.jsoup.JSoupTranslator.getTextInBody;

public class Card implements NormalCard {

    private final MetaData metaData = new MetaData();
    private ArrayList<CardEvent> events = new ArrayList<>();
    private String body;

    /**
     * @param _name   Name of Card
     * @param _body   Body of Card HTML
     * @param _events CardEvents
     */
    public Card(String _name, String _body, ArrayList<CardEvent> _events) {
        this.metaData.setTitle(_name);
        if (!_body.contains("<script>"))
            this.body = _body + "<script>function openEvent(x){javaScriptConnection.getCardEvent(x);}</script>";
        else {
            this.body = _body;
        }
        this.events = _events;
        if (null == this.events) {
            this.events = new ArrayList<>();
        }
    }

    public void add_event(CardEvent _event) {
        this.events.add(_event);
    }

    public void remove_event(String _id) {
        this.events.removeIf(event -> event.getTaskEvent().getEventId() == _id);
    }

    @Override
    public String toString() {
        return "<section title=\"" + this.metaData.getTitle() + "\">" + this.body + "</section>";
    }

    public CardEvent getEvent(UID _uid) {
        for (CardEvent event :
                this.events) {
            if (event.getTaskEvent().getEventId().contentEquals(_uid.toString())) {
                return event;
            }

        }
        return null;
    }

    public CardEvent getEvent(String _uid) {
        for (CardEvent event :
                this.events) {
            if (event.getTaskEvent().getEventId().contentEquals(_uid)) {
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
        return this.events;
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

            if (0 < recent.compareTo(event.getDate())) {
                recent = event;
            }

        }
        return recent;
    }

    //=================  SETTERS  ===============
    //=====SETTERS=====
    public void setBody(String _input) {
        String test = getTextInBody(_input);
         test = test.replaceAll("<((/b)|b)ody>", " ");
        test = test.replaceAll("<script>[.\\n]*<\\/script>", "");
        this.body = test;

    }

    public void setName(String _name) {
        this.metaData.setTitle(_name);
    }
}
