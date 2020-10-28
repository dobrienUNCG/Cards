package Cards.translators.io;

import Cards.models.Card;

import java.io.File;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;

public class CardFile {
    private File file;
    private Path path;
    private ArrayList<Card> cards;


    public CardFile(String x){
        logg.entering(this.getClass().getName(), "CardFile(String x)");
        Path fil;
        try{
            path = Paths.get(x);
            file = new File(String.valueOf(path));
            logg.info(file.getAbsolutePath().toString());


        }catch(Exception e){
            logg.warning(e.toString());

        }
        logg.exiting(this.getClass().getName(), "CardFile(String x)");
    }

    public CardFile(File x){
        file = x;
        path = file.toPath();
    }

    public void set_path(Path x){
        path = x;
    }
    public File get_file(){
        return file;
    }

    public void set_path(String x){
        logg.entering(this.getClass().getName(), "CardFile(String x)");
        Path fil;
        try{
            path = Paths.get(x);
            logg.info("Path: " + path.toString());

        }catch(Exception e){
            logg.warning("Failed To Get Path "+ e.toString());


        }
        logg.exiting(this.getClass().getName(), "CardFile(String x)");

    }




    public File get_card_file(){
        return file;
    }
}
