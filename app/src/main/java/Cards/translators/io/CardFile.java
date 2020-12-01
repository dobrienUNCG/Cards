package Cards.translators.io;
/*
 * Last Updated: 10/28/2020
 * An wrapper for file paths.
 *
 * @author Devin M. O'Brien
 *
 */

import Cards.models.cards.CardList;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Cards.models.CardLogger.logg;

public class CardFile {

    private File file;
    private Path path;
    private CardList cardList;

    public CardFile(String _file) {
        try {
            this.path = Paths.get(_file);
            this.file = new File(String.valueOf(this.path));
        } catch (Exception e) {
            logg.warning(e.toString());
        }
    }

    public CardFile(File _file) {
        this.file = _file;
        this.path = this.file.toPath();
    }

    //=================  GETTERS ===============
    public CardList getCardList() {
        return this.cardList;
    }

    //=====GETTER=====
    public File getFile() {
        return this.file;
    }

    public Path getPath() {
        return this.path;
    }

    public File get_card_file() {
        return this.file;
    }

    //=================  SETTERS  ===============
    public void set_cardlist(CardList _cardlist) {
        this.cardList = _cardlist;
    }

    public void set_path(Path _path) {
        this.path = _path;
    }

    //=====SETTERS=====
    public void set_path(String _path) {
        logg.entering(this.getClass().getName(), "CardFile(String _path)");

        try {
            this.path = Paths.get(_path);
            logg.info("Path: " + this.path);

        } catch (Exception e) {
            logg.warning("Failed To Get Path " + e);
        }
        logg.exiting(this.getClass().getName(), "CardFile(String _path)");

    }

}
