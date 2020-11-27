package Cards.translators.jsoup;
/*
  Last Updated: 10/28/2020
  JSoup Translator Class

  @author Devin M. O'Brien
 */

import Cards.models.cards.Card;
import Cards.models.cards.CardEvent;
import Cards.translators.io.CardFile;
import org.jetbrains.annotations.Nullable;
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

    private final CardFile cardFile;
    private Document doc;



    /**
     * JSoup Translator Constructor
     * @param _cardFile
     */
     JSoupTranslator(CardFile _cardFile) {
        this.cardFile = _cardFile;
        this.parse_file();
    }
    /**
     * JSoup Translator Constructor
     * @param _s
     */
     JSoupTranslator(String _s) {
        this.cardFile = new CardFile(_s);
        this.parse_file();
    }
   public static  JSoupTranslator JSoupBuilder(CardFile _cardFile){
        return new JSoupTranslator(_cardFile);
    }
    public static  JSoupTranslator JSoupBuilder(String _s){
        return new JSoupTranslator(_s);
    }

    /**
     * IDK No longer used, need to deprecate
     * @param _card a card
     * @return parsed CardFile
     * @apiNote Was used for prototyping
     */
    public static String parseCard(Card _card) {
        Document tempDoc = Jsoup.parse(_card.getBody());
        Elements section = tempDoc.getElementsByTag("section");
        return section.text();
    }

    /**
     * IDK No longer used, need to deprecate
     * @param _card string of a card file
     * @return parsed string of a card file
     * @apiNote Was used for prototyping
     */
    public static String parseCard(String _card) throws Exception {
        throw new Exception("Fail");
        /*
        Document tempDoc = Jsoup.parse(_card);
        Elements section = tempDoc.getElementsByTag("section");
        if ( section.hasText() ) {
            return section.text();
        } else {
            return _card;
        }

         */
    }

    public static String replaceBodyTag(String _input) {
        Document temp = Jsoup.parse(_input);
        Element body = temp.body();
        Tag section = Tag.valueOf("section");
        body.replaceWith(new Element(Tag.valueOf("section"), ""));
        if (0 == body.childNodeSize()) {
            logg.info("No Children");
            return _input;
        }
        return body.toString();
    }

    public static String removeBodyTag(String _input) {
        Document temp = Jsoup.parse(_input);
        Element init = temp.body();
        Elements body = init.getElementsByTag("body");
        body.remove();

        return temp.toString();
    }

    public static String getTextInBody(String _input) {
        Document temp = Jsoup.parse(_input);
        Element init = temp.body();
        return init.html();

    }
    public static String normalize(String _input){
        Document temp = Jsoup.parse(_input);
        return temp.normalise().outerHtml();
    }


    private void parse_file() {
        try {
            this.doc = Jsoup.parse(this.cardFile.get_card_file(), "UTF-8");
            logg.info(this.doc.toString());
            this.doc.normalise();
        } catch ( IOException e ) {
            logg.warning("Failed to load file");
        }
    }



    @Nullable
    public String getTag(String x) {
       Elements ez = this.doc.getElementsByTag(x);
        if (null == ez) {
            logg.warning("Invalid Input");
            return null;
        }
        return ez.get(0).toString();
    }
    public String get_tag_inner(String x) {

        Elements ez = this.doc.getElementsByTag(x);
        if (null == ez) {
            logg.warning("Invalid Input");
            logg.exiting(this.getClass().getName(), "get_tag(String x)");
            return "null";
        }

       return ez.text();
    }

    public String get_meta(String type){
        String output = "";
        Elements metatags = this.doc.getElementsByTag("meta");
        for(Element tag : metatags){
           if( tag.attr("name").contentEquals(type)){
               output =  tag.attr("content");
                break;
           }
        }
        return output;
    }



        //=====GETTER=====
    public String getDoc() {
        return this.doc.toString();
    }

    public String getBody() {
        return this.doc.body().toString();
    }

    public String getHead() {
        return this.doc.head().toString();
    }

    public String getTitle() {
        Elements x = this.doc.getElementsByTag("title");
        return x.toString();
    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for ( Element x : this.doc.getElementsByTag("section") ) {
            String body = x.html(); // For HTML
            String title = x.attributes().get("title");

            ArrayList<CardEvent> events = new ArrayList<>();
            for ( Element y : x.getElementsByTag("span") ) {

                if ( y.attributes().hasDeclaredValueForKey("date") ) {
                    logg.fine("Date" + y.attr("date"));
                    LocalDateTime localDateTime = LocalDateTime.parse(y.attr("date"));
                    String desc = y.text();
                    // TODO Need to fix
                    //events.add(new CardEvent(localDateTime, desc));
                }

            }
            cards.add(new Card(title, body, events));
        }
        return cards;
    }

    //=================  SETTERS ===============

}
