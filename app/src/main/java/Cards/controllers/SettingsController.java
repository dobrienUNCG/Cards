/**
MIT License

Copyright © 2021 Devin M. O'Brien

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
**/
package Cards.controllers;
/**
 * Date: 05/04/2021
 * Changed: Added MIT License
 * Card settings controller
 * @author Devin M. O'Brien
 */

import Cards.models.settings.CardSettings;
import Cards.translators.io.CardFile;

import java.util.ArrayList;

public class SettingsController {

    private final CardSettings cardSettings = new CardSettings();

    public void removeCardFile(CardFile _cardFile) {
        this.cardSettings.removeCardFile(_cardFile);
    }

    public void addCardFile(CardFile _cardFile) {
        this.cardSettings.add_file(_cardFile);
    }

    public void save() {
        this.cardSettings.save_settings();
    }

    public void movetoFront(CardFile _cardFile) {
        this.cardSettings.moveToFront(_cardFile);
    }

    public void calendarCreated() {
        this.cardSettings.setCalendarCreated(true);
    }

    //=================  GETTERS ===============
    public ArrayList<CardFile> getCardFiles() {
        return this.cardSettings.getRecentFiles();
    }

    public boolean isCalendarCreated() {
        return this.cardSettings.isCalendarCreated();
    }


}
