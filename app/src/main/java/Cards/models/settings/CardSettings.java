package Cards.models.settings;
/*
  Last Updated: 10/10/2020
  Card Settings model

  @AUTHOR Devin M. O'Brien
 */

import Cards.translators.io.CardFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static Cards.models.CardLogger.logg;

public class CardSettings {

    private final ArrayList<CardFile> recentCards = new ArrayList<>();
    private final File settingsFile = new File("Settings.cfg");
    private boolean calendarCreated;

    public CardSettings() {
        if (this.settingsFile.exists()) {
            this.getSettings();
        } else {
            this.create_settings();

        }

    }

    public void removeCardFile(CardFile _cardFile) {
        this.recentCards.remove(_cardFile);
    }

    public void add_file(CardFile _cardFile) {
        for (CardFile cardFile : this.recentCards) {
            if (cardFile.getFile().getAbsolutePath().contentEquals(_cardFile.getFile().getAbsolutePath())) {
                return;
            }
        }
        this.recentCards.add(_cardFile);
    }

    public void moveToFront(CardFile _cardFile) {
        this.recentCards.remove(_cardFile);
        this.recentCards.add(0, _cardFile);
    }

    public void save_settings() {
        try {
            FileWriter writer = new FileWriter(this.settingsFile, false);

            for (CardFile cardFile : this.recentCards) {
                writer.write(Preface.FILE + cardFile.get_card_file().getAbsolutePath() + "\n");
            }
            writer.write(Preface.CALENDAR + String.valueOf(this.calendarCreated));
            writer.close();
        } catch (IOException error) {
            logg.warning(error.toString());
        }

    }

    private void getSettings() {
        try {
            Scanner read = new Scanner(this.settingsFile);
            Boolean fileSegment = false;
            while (read.hasNext()) {
                String line = read.nextLine();
                if (line.contains(Preface.FILE.toString())) {
                    // TODO Add Checker to prevent Exception from crashing
                    try {
                        CardFile test = new CardFile(line.substring(6));
                        if (test.getFile().exists())
                            this.recentCards.add(new CardFile(line.substring(6)));
                    } catch (Exception error) {
                        logg.severe(error.toString());
                    }
                }
                if (line.contains(Preface.CALENDAR.toString())) {
                    if (line.toLowerCase().contains("true")) {
                        this.calendarCreated = true;
                    }
                }
            }
        } catch (FileNotFoundException error) {
            logg.warning(error.toString());
        }

    }

    private void create_settings() {
        try {
            FileWriter fileWriter = new FileWriter(this.settingsFile, false);
            fileWriter.write(Preface.CALENDAR + ((String.valueOf(this.calendarCreated))));
            fileWriter.close();
        } catch (IOException error) {
            logg.warning(error.toString());
        }
    }

    private enum Preface {
        FILE {
            @Override
            public String toString() {
                return "#File:";
            }
        }, CALENDAR {
            @Override
            public String toString() {
                return "#Calendar:";
            }
        }

    }

    //=================  GETTERS ===============
    public ArrayList<CardFile> getRecentFiles() {
        return this.recentCards;
    }

    public boolean isCalendarCreated() {
        return this.calendarCreated;
    }

    //=================  SETTERS  ===============
    public void setCalendarCreated(boolean _calendarCreated) {
        this.calendarCreated = _calendarCreated;
    }
}
