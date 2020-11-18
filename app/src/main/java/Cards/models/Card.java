package Cards.models;
/**
 * Last Updated: 11/17/2020 4:49 PM
 * Card Data Model
 *
 * @AUTHOR Devin M. O'Brien
 */

import java.util.ArrayList;
import java.util.UUID;

import static Cards.models.CardLogger.logg;
import static Cards.translators.jsoup.JSoupTranslator.getTextInBody;

public class Card {
    private MetaData metaData = new MetaData();
    private ArrayList<CardEvent> events;
    private String body;
    /**
     * @param _name   Name of Card
     * @param _body   Body of Card HTML
     * @param _events CardEvents
     */
    public Card(String _name, String _body, ArrayList<CardEvent> _events) {
        metaData.setTitle(_name);
        this.body = _body;
        this.events = _events;
    }

    public void add_event(CardEvent _event) {
        this.events.add(_event);
    }

    /**
     * @deprecated Not Implemented
     * @param _index
     */
    public void remove_event(int _index) {
        throw new RuntimeException("Not Implemented");
    }

    /**
     * @deprecated Not Implemented
     * @param uuid
     */
    public void remove_event(UUID uuid) {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public String toString() {
        return "<section title=\"" + metaData.getTitle() + "\">" + this.body + "</section>";
    }

    //=====GETTER=====
    public String getBody() {
        return this.body;
    }

    public String getName() {
        return metaData.getTitle();
    }

    public CardEvent getRecent() {
        CardEvent recent = null;
        for ( CardEvent event : events ) {
            if ( recent == null ) {
                recent = event;
            }
            if ( recent.getDate().compareTo(event.getDate()) < 0 ) {

            } else {
                recent = event;
            }

        }
        return recent;
    }

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
