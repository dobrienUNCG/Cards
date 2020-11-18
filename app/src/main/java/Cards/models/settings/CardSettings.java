package Cards.models.settings;

import Cards.translators.io.CardFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Cards.models.CardLogger.logg;

public class CardSettings {
    public static ArrayList<CardFile> recentCards = new ArrayList<CardFile>();
    static File settingsFile = new File("Settings.cfg");

    public CardSettings() {
        if ( settingsFile.exists() ) {
            getSettings();
        } else {
            create_settings();

        }

    }

    public static void add_file(CardFile _cardFile) {
        for ( CardFile cardFile : recentCards ) {
            if ( cardFile.getFile().getAbsolutePath().contentEquals(_cardFile.getFile().getAbsolutePath()) ) {
                return;
            }
        }
        recentCards.add(_cardFile);
    }

    static public void save_settings() {
        try {
            FileWriter writer = new FileWriter(settingsFile, false);

            for ( CardFile cardFile : recentCards ) {
                writer.write("#File:" + cardFile.get_card_file().getAbsolutePath() + "\n");
            }
            writer.close();
        } catch ( IOException _e ) {
            _e.printStackTrace();
        }

    }

    private void getSettings() {
        try {
            Scanner read = new Scanner(settingsFile);
            Boolean file_segment = false;
            while ( read.hasNext() ) {
                String line = read.nextLine();
                logg.info(line);
                if ( line.contains("#File:") ) {
                    recentCards.add(new CardFile(line.substring(6)));
                }
            }
        } catch ( FileNotFoundException _e ) {
            _e.printStackTrace();
        }

    }

    private void create_settings() {
        try {
            FileWriter fileWriter = new FileWriter(settingsFile, false);
            fileWriter.write("#File:Test.html");
            fileWriter.close();
        } catch ( IOException _e ) {
            _e.printStackTrace();
        }
    }
}
