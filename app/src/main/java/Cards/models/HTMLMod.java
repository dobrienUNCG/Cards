package Cards.models;


import Cards.translators.io.CardFile;
import Cards.translators.jsoup.JsoupIinter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;

public class HTMLMod {

    String head;
    String body;
    String doc;
    ArrayList<Card> cards;


    /**
     * Currently sets up placeholder data, once I look at the translator, I will try
     * fine tuning.
     * @param x
     */
    public HTMLMod(String x){
       logg.entering("HTMLMod", "HTMLMod");
        JsoupIinter y = new JsoupIinter(x); // JSoup interaction
        head = y.get_head();
        logg.info("head = " + head);
        body = y.get_body();
        logg.info("body - " + body);
        doc = y.get_doc();
        logg.info("doc = " + doc);
        cards = y.get_cards();
       logg.exiting("HTMLMod", "HTMLMod");
       ;
    }
    public HTMLMod(File x){
       logg.entering("HTMLMod", "HTMLMod");
        JsoupIinter y = new JsoupIinter(new CardFile(x)); // JSoup interaction
        head = y.get_head();
        logg.info("head = " + head);
        body = y.get_body();
        logg.info("body - " + body);
        doc = y.get_doc();
        logg.info("doc = " + doc);
        cards = y.get_cards();
       logg.exiting("HTMLMod", "HTMLMod");
       ;
    }


    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getDoc() {
        return doc;
    }
    public ArrayList<Card> get_cards(){
        return cards;
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
    public String cardsToString(){
        return "" + cards;
    }
}
