package Cards.views;
/****
 * NO CLUE
 * @author Devin M OBrien
 * @apiNote I do not remember what this was going to be for.
 */

import Cards.translators.io.CardFile;

public class CardPaneView {
    private String title;
    private String content;
    private CardFile cardFile;

    CardPaneView(String _title, String _content, CardFile _cardFile) {
        this.title = _title;
        this.content = _content;
        this.cardFile = _cardFile;
    }

}
