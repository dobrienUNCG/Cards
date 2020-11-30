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

    private static final ArrayList<CardFile> recentCards = new ArrayList<>();
    static final File settingsFile = new File("Settings.cfg");
    public static boolean calendarCreated = false;
    private static enum Preface{
        FILE{
            @Override
           public  String toString(){
                return "#File:";
            }
        }, CALENDAR{
            @Override
            public String toString(){
                return "#Calendar:";
            }
        };

    }
    public CardSettings() {
        if (settingsFile.exists()) {
            getSettings();
        } else {
            create_settings();

        }

    }

    public static ArrayList<CardFile> getRecentFiles(){
        return recentCards;
    }

    public static void add_file(CardFile _cardFile) {
        for (CardFile cardFile : recentCards) {
            if (cardFile.getFile().getAbsolutePath().contentEquals(_cardFile.getFile().getAbsolutePath())) {
                return;
            }
        }
        recentCards.add(_cardFile);
    }

  public static void moveToFront(CardFile _cardFile){
        recentCards.remove(_cardFile);
        recentCards.add(0, _cardFile);
  }


    static public void save_settings() {
        try {
            FileWriter writer = new FileWriter(settingsFile, false);

            for (CardFile cardFile : recentCards) {
                writer.write(Preface.FILE + cardFile.get_card_file().getAbsolutePath() + "\n");
            }
            writer.write(Preface.CALENDAR + String.valueOf(calendarCreated));
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }

    }

    private void getSettings() {
        try {
            Scanner read = new Scanner(settingsFile);
            Boolean fileSegment = false;
            while (read.hasNext()) {
                String line = read.nextLine();
                if (line.contains(Preface.FILE.toString())) {
                    // TODO Add Checker to prevent Exception from crashing
                    try {
                        CardFile test = new CardFile(line.substring(6));
                        if(test.getFile().exists())
                        recentCards.add(new CardFile(line.substring(6)));
                    }catch(Exception error){
                        logg.severe(error.toString());
                    }
                }
                if (line.contains(Preface.CALENDAR.toString())){
                    if(line.toLowerCase().contains("true")){
                        calendarCreated = true;
                    }
                }
            }
        } catch (FileNotFoundException error) {
            error.printStackTrace();
        }

    }

    private void create_settings() {
        try {
            FileWriter fileWriter = new FileWriter(settingsFile, false);
            fileWriter.write(Preface.FILE+ "Test.html");
            fileWriter.write(Preface.CALENDAR + ((String.valueOf(calendarCreated))));
            fileWriter.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
