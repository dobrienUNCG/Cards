package Cards.models;
/**
 * Last Updated: 10/28/2020
 * HTML Data Model
 *
 * @AUTHOR Devin M. O'Brien
 */

import Cards.translators.io.CardFile;
import Cards.translators.jsoup.JSoupTranslator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;
import static Cards.translators.jsoup.JSoupTranslator.replaceBodyTag;

public class HTMLMod {

    private final String head;
    private final String body;
    private String doc;
    private ArrayList<Card> cards;

    public HTMLMod(String _x) {
        logg.entering("HTMLMod", "HTMLMod");
        JSoupTranslator y = new JSoupTranslator(_x); // JSoup interaction
        this.head = y.get_head();
        logg.info("head = " + this.head);
        this.body = y.get_body();
        logg.info("body - " + this.body);
        this.doc = y.get_doc();
        logg.info("doc = " + this.doc);
        this.cards = y.get_cards();
        logg.exiting("HTMLMod", "HTMLMod");
    }

    public HTMLMod(File _x) {
        logg.entering("HTMLMod", "HTMLMod");
        JSoupTranslator jst = new JSoupTranslator(new CardFile(_x)); // JSoup interaction
        this.head = jst.get_head();
        logg.info("head = " + this.head);
        this.body = jst.get_body();
        logg.info("body - " + this.body);
        this.doc = jst.get_doc();
        logg.info("doc = " + this.doc);
        this.cards = jst.get_cards();
        logg.exiting("HTMLMod", "HTMLMod");
    }

    public void save(String _input) {
        try {
            FileWriter filew = new FileWriter("Test.html", false);
            filew.write(this.head + "<body>" + _input + "</body></html>");
            filew.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public ArrayList<Card> update_cards(ArrayList<Card> _cards) {
        this.cards = _cards;
        for (Card card : cards) {
            String temp = replaceBodyTag(card.getBody());
            card.setBody(temp);
        }
        return _cards;
    }

    public Card update_cards(Card _cards) {

        String temp = replaceBodyTag(_cards.getBody());
        _cards.setBody(temp);

        return _cards;
    }


    @Override
    public String toString() {
        return "HTMLMod{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", doc='" + doc + '\'' +
                ", cards=" + cards +
                '}';
    }

    public String cardsToString() {
        return "" + cards;
    }

    //=================  GETTERS ===============

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getDoc() {
        return doc;
    }

    public ArrayList<Card> get_cards() {
        return cards;
    }

}
