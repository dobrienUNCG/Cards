package Cards.models;
/**
 * Last Updated: 10/26/2020
 * HTML Data Model
 *
 * @author Devin M. O'Brien
 * @deprecated No longer supported, but being used as a reference.
 */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Cards.models.CardLogger.logg;

/**
 * @deprecated No longer supported, but being used as reference.
 */
class HTMLModel {

    String document = null;

    public HTMLModel(File _x) {
        logg.entering(HTMLModel.class.getName(), "HTMLModel(File x)");
        logg.info("Creating HTMLModel from " + _x.toString());
        try {
            Document doc = Jsoup.parse(_x, "UTF-8");
            System.out.println(doc.toString());
            document = doc.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logg.exiting(HTMLModel.class.getName(), "HTMLModel(File x)");
    }

    public HTMLModel() {
        logg.entering(HTMLModel.class.getName(), "HTMLModel(File x)");
        logg.info("Creating HTMLModel from test");
        Path current = Paths.get("Test.html");
        System.out.println(current.toAbsolutePath());
        File y = new File(String.valueOf(current));
        try {
            Document doc = Jsoup.parse(y, "UTF-8");
            document = doc.toString();

            System.out.println(document);
            System.out.println(doc.head().getElementsByTag("title"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logg.exiting(HTMLModel.class.getName(), "HTMLModel(File x)");
    }

    static public void save(String _input) {
        try {
            FileWriter filew = new FileWriter("Test.html", false);
            filew.write(_input);
            filew.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public String toString() {
        return document;
    }
}
