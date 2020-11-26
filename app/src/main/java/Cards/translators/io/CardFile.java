package Cards.translators.io;
/**
 * Last Updated: 10/28/2020
 * Card File
 *
 * @author Devin M. O'Brien
 * TODO Fix Settings Crash when files are moved
 */

import Cards.models.CardList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Cards.models.CardLogger.logg;

public class CardFile {
    private File file;
    private Path path;
    private CardList cardList;

    public CardFile(String _file) {
        logg.entering(this.getClass().getName(), "CardFile(String _file)");
        try {
            this.path = Paths.get(_file);
            this.file = new File(String.valueOf(path));
            logg.info(file.getAbsolutePath());
            logg.info(path.toString());

        } catch ( Exception e ) {
            logg.warning(e.toString());

        }
        logg.exiting(this.getClass().getName(), "CardFile(String _file)");
    }

    public CardFile(File _file) {
        this.file = _file;
        this.path = this.file.toPath();
    }



    //=====GETTER=====
    public File getFile() {
        return this.file;
    }

    public File get_card_file() {
        return this.file;
    }
    public CardList getCardList(){
        return this.cardList;
    }


    //=====SETTERS=====
    public void set_path(String _path) {
        logg.entering(this.getClass().getName(), "CardFile(String _path)");

        try {
            this.path = Paths.get(_path);
            logg.info("Path: " + this.path.toString());

        } catch ( Exception e ) {
            logg.warning("Failed To Get Path " + e.toString());
        }
        logg.exiting(this.getClass().getName(), "CardFile(String _path)");

    }

    public Path getPath(){
        return path;
    }

    public void set_path(Path _path) {
        this.path = _path;
    }
    public void set_cardlist(CardList _cardlist){
        this.cardList = _cardlist;
    }

}
