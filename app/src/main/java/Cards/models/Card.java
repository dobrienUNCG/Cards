package Cards.models;
/**
 * Last Updated: 10/28/2020
 * Card Data Model
 *
 * @AUTHOR Devin M. O'Brien
 */

import java.util.ArrayList;

import static Cards.models.CardLogger.logg;
import static Cards.translators.jsoup.JSoupTranslator.parseCard;


public class Card {
    private  String name;
    private  String body;
    private  ArrayList<CardEvent> events;

    public Card(String _name, String _body, ArrayList<CardEvent> _events) {
        this.name = _name;
        this.body = _body;
        this.events = _events;
    }

    @Override
    public String toString() {
        return "\nCard{" +
                "name='" + this.name + '\'' +
                ", created_date=" +
                ", modified_date=" +
                ", \nbody='" + this.body + '\'' +
                ", events=" + this.events +
                '}';
    }

    public void setBody (String _input){
logg.entering("Card", "setBody(" + _input + ")");
        if(_input.equals(" ") || _input.isEmpty() || _input.length() < 1)
            System.out.println("SAVE ME!");

        logg.info(_input + _input.length());
        logg.info(_input.length() + "");

        if(_input.charAt(0) == '<') {
            String parsed = parseCard(_input);
            this.body = parsed;
            logg.info(this.body);
        }
logg.exiting("Card", "setBody");

    }

    //=================  GETTERS ===============

    public String getBody() {
        logg.info(this.body);
        return this.body;
    }

    public String getName() {
        return this.name;
    }
}
