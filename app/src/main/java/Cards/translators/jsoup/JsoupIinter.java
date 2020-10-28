package Cards.translators.jsoup;

import Cards.models.Card;
import Cards.models.CardEvent;
import Cards.translators.io.CardFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;

/**
 * JSoup Translator Class
 * This class is the middleman between JSoup (HTML Parser Library)
 * and the program.
 */
public class JsoupIinter {
    private CardFile f;
    Document doc;
    JsoupIinter(){

    }

    public JsoupIinter(CardFile x){
        f = x;

        parse_file();
    }
    public JsoupIinter(String x){
       f = new CardFile(x);

        parse_file();
    }
    public String get_doc(){
        return doc.toString();
    }

    public String get_body(){
        return doc.body().toString();
    }
    public String get_head(){
        return doc.head().toString();
    }

    public String get_title(){
        Elements x = doc.getElementsByTag("title");
        return x.toString();
    }

    public String get_tag(String x){
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

    private void parse_file(){
        logg.entering(this.getClass().getName(), "parse_file()");
        try {
            doc = Jsoup.parse(f.get_card_file(), "UTF-8");
            logg.info(doc.toString());
        }catch(IOException e){
            logg.warning("Failed to load file");
        }
        logg.exiting(this.getClass().getName(), "parse_file()");
    }
    public ArrayList<Card> get_cards(){
        ArrayList<Card> cards = new ArrayList<Card>();
        for(Element x: doc.getElementsByTag("section")){
            String body = x.toString(); // For HTML
            String title = x.attributes().get("title");

            ArrayList<CardEvent> events = new ArrayList<CardEvent>();
            for(Element y: x.getElementsByTag("span")){

                if(y.attributes().hasDeclaredValueForKey("date")){
                   System.out.println("Here?");

                   System.out.println("Date"+ y.attr("date"));
                   LocalDateTime date = LocalDateTime.parse(y.attr("date").toString());
                   String desc = y.text();
                   events.add(new CardEvent(date, desc));
               }

            }
            cards.add(new Card(title, body, events));
        }
        return cards;
    }

}
