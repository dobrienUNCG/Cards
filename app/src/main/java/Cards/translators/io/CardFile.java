package Cards.translators.io;
/**
 * Last Updated: 10/28/2020
 * Card File
 * @author Devin M. O'Brien
 */

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


    public CardFile(String _x) {
        logg.entering(this.getClass().getName(), "CardFile(String _x)");
        Path fil;
        try {
            path = Paths.get(_x);
            file = new File(String.valueOf(path));
            logg.info(file.getAbsolutePath().toString());


        } catch (Exception e) {
            logg.warning(e.toString());

        }
        logg.exiting(this.getClass().getName(), "CardFile(String _x)");
    }

    public CardFile(File _x) {
        file = _x;
        path = file.toPath();
    }


    //=================  GETTERS ===============

    public File get_file() {
        return file;
    }

    public File get_card_file() {
        return file;
    }

    //=================  SETTERS ===============

    public void set_path(String _x) {
        logg.entering(this.getClass().getName(), "CardFile(String _x)");
        Path fil;
        try {
            path = Paths.get(_x);
            logg.info("Path: " + path.toString());

        } catch (Exception e) {
            logg.warning("Failed To Get Path " + e.toString());
        }
        logg.exiting(this.getClass().getName(), "CardFile(String _x)");

    }

    public void set_path(Path _x) {
        path = _x;
    }

}
