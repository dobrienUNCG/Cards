package Cards.translators.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Cards.models.CardLogger.logg;

public class CardFile {
    private File file;
    private Path path;
    CardFile(Path x){

    }
    public CardFile(String x){
        logg.entering(this.getClass().getName(), "CardFile(String x)");
        Path fil;
        try{
            path = Paths.get(x);

        }catch(Exception e){
            logg.warning(e.toString());

        }
        logg.exiting(this.getClass().getName(), "CardFile(String x)");
    }
    CardFile(){

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

        }catch(Exception e){
            logg.warning("Failed To Get Path "+ e.toString());


        }
        logg.exiting(this.getClass().getName(), "CardFile(String x)");

    }


    public String get_path() {
        return path.toAbsolutePath().toString();
    }
}
