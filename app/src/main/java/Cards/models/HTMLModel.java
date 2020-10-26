package Cards.models;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import static Cards.models.CardLogger.logg;

public class HTMLModel {

    String tester = null;

    HTMLModel(File x){
        logg.entering(HTMLModel.class.getName(), "HTMLModel(File x)");
        logg.info("Creating HTMLModel from " + x.toString());
        try {
            Document doc = Jsoup.parse(x, "UTF-8");
            System.out.println(doc.toString());
           tester = doc.toString();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        logg.exiting(HTMLModel.class.getName(), "HTMLModel(File x)");
    }
    public HTMLModel(){

        logg.entering(HTMLModel.class.getName(), "HTMLModel(File x)");
        logg.info("Creating HTMLModel from test");
        Path current = Paths.get("Test.html");
        System.out.println(current.toAbsolutePath());
        File y = new File(String.valueOf(current));
        try{
            Document doc = Jsoup.parse(y, "UTF-8");
            tester = doc.toString();

            System.out.println(tester);
            System.out.println(doc.head().getElementsByTag("title"));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        logg.exiting(HTMLModel.class.getName(), "HTMLModel(File x)");

    }

 static public void save(String y){

        try {
            FileWriter x = new FileWriter("Test.html", false);
            x.write(y);
            x.close();
        }catch(IOException e){
            System.out.println(e);
        }

    }

    @Override
    public String toString() {
        return tester;
    }
}
