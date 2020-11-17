package Cards.models;
/**
 * Last Updated: 10/28/2020
 * HTML Data Model
 *
 * @AUTHOR Devin M. O'Brien
 */

import Cards.app.DevMode;
import Cards.translators.io.CardFile;
import Cards.translators.jsoup.JSoupTranslator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;
import static Cards.translators.jsoup.JSoupTranslator.normalize;
import static Cards.translators.jsoup.JSoupTranslator.replaceBodyTag;

public class HTMLMod {

    private  String head;
    private  String body;
    private String doc;
    private ArrayList<Card> cards;
    private CardFile cardFile;
    DevMode dev = (x) -> {logg.fine(x);};

    /**
     * HTMLMod Constructor
     *
     * @param _x
     */
    public HTMLMod(String _x) {
       this.cardFile = new CardFile(_x);
    }
    private void parse(String _x){
        JSoupTranslator jsoup = new JSoupTranslator(_x);
        this.head = jsoup.get_head();
        logg.info("head = " + this.head);
        this.body = jsoup.get_body();
        logg.info("body - " + this.body);
        this.doc = jsoup.get_doc();
        logg.info("doc = " + this.doc);
        this.cards = jsoup.get_cards();
    }
    private void parse(File _x){
        dev.log("Creating HTMLMod");
        JSoupTranslator jsoup = new JSoupTranslator(new CardFile(_x)); // JSoup interaction
        this.head = jsoup.get_head();
        logg.info("head = " + this.head);
        this.body = jsoup.get_body();
        logg.info("body - " + this.body);
        this.doc = jsoup.get_doc();
        logg.info("doc = " + this.doc);
        this.cards = jsoup.get_cards();
    }

    public HTMLMod(File _x) {
        this.cardFile = new CardFile(_x);
    }

    /**
     * @deprecated  Replaced
     * @param _input
     */
    public void save(String _input) {
        dev.log("Saving");
        try {
           String input = normalize(_input);
            FileWriter fileWriter = new FileWriter("Test.html", false);
            fileWriter.write("<html>" + this.head + "<body>" + input + "</body></html>");
            fileWriter.close();
        } catch ( IOException e ) {
            System.out.println(e);
        }
    }
    public void save(CardFile _cardFile, ArrayList<Card> _cards) {
        dev.log("Saving");
        String out = "";
        for(Card card: _cards){
            out += card.toString();
        }
        try {
           String input = normalize(out);
            FileWriter fileWriter = new FileWriter(_cardFile.getFile(), false);
            fileWriter.write("<html>" + this.head + "<body>" + input + "</body></html>");
            fileWriter.close();
        } catch ( IOException e ) {
            System.out.println(e);
        }
    }

    public ArrayList<Card> update_cards(ArrayList<Card> _cards) {
        this.cards = _cards;
        for ( Card card : cards ) {
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

//=====GETTER=====
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
