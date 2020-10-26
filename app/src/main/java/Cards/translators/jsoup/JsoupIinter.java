package Cards.translators.jsoup;

import Cards.translators.io.CardFile;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import static Cards.models.CardLogger.logg;

public class JsoupIinter {
    private CardFile f;
    Document doc;
    JsoupIinter(){

    }

    public JsoupIinter(CardFile x){
        f = x;
        logg.info("f = " + f.get_path());
        parse_file();
    }
    public JsoupIinter(String x){
       f = new CardFile(x);
        logg.info("f = " + f.get_path());
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
        doc = Jsoup.parse(f.get_path());
        logg.exiting(this.getClass().getName(), "parse_file()");
    }

}
