package Cards.models;
/**
 * Last Updated: 10/28/2020
 * Card Data Model
 *
 * @AUTHOR Devin M. O'Brien
 */

import java.util.ArrayList;

import static Cards.models.CardLogger.logg;
import static Cards.translators.jsoup.JSoupTranslator.getTextInBody;

public class Card {
    private String name;
    private String body;
    private ArrayList<CardEvent> events;

    public Card(String _name, String _body, ArrayList<CardEvent> _events) {
        this.name = _name;
        this.body = _body;
        this.events = _events;
    }

    @Override
    public String toString() {
        return "<section title=\"" + this.name + "\">" + this.body + "</section>";
    }

//=====GETTER=====
    public String getBody() {
        logg.info(this.body);
        return this.body;
    }

    //=================  GETTERS ===============

    public String getName() {
        return this.name;
    }

//=====SETTERS=====
    public void setBody(String _input) {
        String test = getTextInBody(_input);
        this.body = test;

    }
}
