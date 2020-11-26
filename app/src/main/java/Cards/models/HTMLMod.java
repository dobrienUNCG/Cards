package Cards.models;
/**
 * Last Updated: 11/18/2020
 * HTML Data Model
 *
 * @apiNote Moving away from using this to HTMLTranslator.
 * @AUTHOR Devin M. O'Brien
 */


import Cards.models.settings.CardSettings;
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

    private String head;
    private String body;
    private String doc;
    private ArrayList<Card> cards;
    private final CardFile cardFile;

    /**
     * HTMLMod Constructor
     *
     * @param _x
     */
    public HTMLMod(String _x) {
        this.cardFile = new CardFile(_x);
    }

    public HTMLMod(File _x) {
        this.cardFile = new CardFile(_x);
    }

    public void parse() {
        CardFile _x = cardFile;
        JSoupTranslator jsoup = new JSoupTranslator(_x);
        this.head = jsoup.get_head();
        logg.info("head = " + this.head);
        this.body = jsoup.get_body();
        logg.info("body - " + this.body);
        this.doc = jsoup.get_doc();
        logg.info("doc = " + this.doc);
        this.cards = jsoup.get_cards();
    }

    /**
     * @param _input
     * @deprecated Replaced
     */
    public void save(String _input) {

        try {
            String input = normalize(_input);
            FileWriter fileWriter = new FileWriter("Test.html", false);
            String out = "<html>" + (this.head != null ? this.head : "") + "<body>" + input + "</body></html>";
            logg.info(out);
            fileWriter.write(out);
            fileWriter.close();
        } catch (IOException e) {
            // TODO Change To Logger
            System.out.println(e);
        }
    }

    public void save(CardFile _cardFile, ArrayList<Card> _cards) {
        StringBuilder out = new StringBuilder();
        CardSettings.add_file(_cardFile);
        for (Card card : _cards) {
            out.append(card.toString());
        }
        try {
            String out2 = "<html>" + (this.head != null ? this.head : "") + "<body>" + out + "</body></html>";
            out2 = normalize(out2);
            logg.info(out.toString());
            FileWriter fileWriter = new FileWriter(_cardFile.getFile(), false);
            fileWriter.write(out2);
            fileWriter.close();
        } catch (IOException e) {
            // TODO Change to Logger
            System.out.println(e);
        }
    }

    public void save(CardFile _cardFile, CardList _cardList) {
        StringBuilder out = new StringBuilder();
        CardSettings.add_file(_cardFile);
        for (Card card : _cardList.getCards()) {
            out.append(card.toString());
        }
        out = new StringBuilder(out.toString().replaceAll("<((/b)|b)ody>", ""));
        try {
            String out2 = "<html>" + _cardList.toString() + "<body>" + out + "</body></html>";
            out2 = normalize(out2);
            logg.info(out.toString());
            FileWriter fileWriter = new FileWriter(_cardFile.getFile(), false);
            fileWriter.write(out2);
            fileWriter.close();
        } catch (IOException e) {
            // TODO Change to logger
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

    /**
     * @param _cards
     * @return
     * @deprecated Replaced
     */
    public Card update_cards(Card _cards) throws Exception {
        throw new Exception("Deprecated Method");
       /* String temp = replaceBodyTag(_cards.getBody());
        _cards.setBody(temp);
        return _cards;*/
    }

    @Override
    public String toString() {
        return "HTMLMod{" + "head='" + head + '\'' + ", body='" + body + '\'' + ", doc='" + doc + '\'' + ", cards=" + cards + '}';
    }

    public String cardsToString() {
        return "" + cards;
    }

    //=================  GETTERS ===============
    public String getBody() {
        return body;
    }

    public String getDoc() {
        return doc;
    }

    public String getHead() {
        return head;
    }

    public ArrayList<Card> get_cards() {
        return cards;
    }

}
