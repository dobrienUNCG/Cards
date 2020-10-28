package Cards.translators.jsoup;
/**
 * Last Updated: 10/28/2020
 * JSoup Translator Class
 * @author Devin M. O'Brien
 */

import Cards.models.Card;
import Cards.models.CardEvent;
import Cards.translators.io.CardFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;


public class JSoupTranslator {

    private CardFile f;
    private Document doc;

    public JSoupTranslator(CardFile _x) {
        f = _x;
        parse_file();
    }


    public JSoupTranslator(String _x) {
        f = new CardFile(_x);

        parse_file();
    }

    public static String parseCard(Card _card){
        Document temp_doc = Jsoup.parse(_card.getBody());
        Elements section = temp_doc.getElementsByTag("section");
        return section.text();
    }
    public static String parseCard(String _card){
        Document temp_doc = Jsoup.parse(_card);
        Elements section = temp_doc.getElementsByTag("section");
        if(section.hasText())
        return section.text();
        else
            return _card;
    }

    private void parse_file() {
        logg.entering(this.getClass().getName(), "parse_file()");
        try {
            doc = Jsoup.parse(f.get_card_file(), "UTF-8");
            logg.info(doc.toString());
        } catch (IOException e) {
            logg.warning("Failed to load file");
        }
        logg.exiting(this.getClass().getName(), "parse_file()");
    }

    //=================  GETTERS ===============

    public String get_doc() {
        return doc.toString();
    }

    public String get_body() {
        return doc.body().toString();
    }

    public String get_head() {
        return doc.head().toString();
    }

    public String get_title() {
        Elements x = doc.getElementsByTag("title");
        return x.toString();
    }

    public ArrayList<Card> get_cards() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Element x : doc.getElementsByTag("section")) {
            String body = x.html(); // For HTML
            String title = x.attributes().get("title");

            ArrayList<CardEvent> events = new ArrayList<CardEvent>();
            for (Element y : x.getElementsByTag("span")) {

                if (y.attributes().hasDeclaredValueForKey("date")) {
                    System.out.println("Here?");

                    System.out.println("Date" + y.attr("date"));
                    LocalDateTime date = LocalDateTime.parse(y.attr("date").toString());
                    String desc = y.text();
                    events.add(new CardEvent(date, desc));
                }

            }
            cards.add(new Card(title, body, events));
        }
        return cards;
    }

    public String get_tag(String x) {
        logg.entering(this.getClass().getName(), "get_tag(String x)");
        Elements ez = doc.getElementsByTag(x);
        if (ez == null) {
            logg.warning("Invalid Input");
            logg.exiting(this.getClass().getName(), "get_tag(String x)");
            return null;
        }

        logg.exiting(this.getClass().getName(), "get_tag(String x)");
        return ez.get(0).toString();
    }

    public static String replaceBodyTag(String _input){
        Document temp = Jsoup.parse(_input);
        Element body = temp.body();
        body.replaceWith(new Element(Tag.valueOf("section"), ""));
        if(body.childNodeSize() == 0){
            logg.info("No Children");
            return _input;
        }
        return body.toString();
    }

    //=================  SETTERS ===============


}
