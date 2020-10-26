package Cards.models;


import Cards.translators.jsoup.JsoupIinter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Cards.models.CardLogger.logg;

public class HTMLMod {

    String head;
    String body;
    String doc;

    public HTMLMod(String x){
       logg.entering("HTMLMod", "HTMLMod");
        JsoupIinter y = new JsoupIinter(x);
        head = y.get_head();
        logg.info("head = " + head);
        body = y.get_body();
        logg.info("body - " + body);
        doc = y.get_doc();
        logg.info("doc = " + doc);
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
}
