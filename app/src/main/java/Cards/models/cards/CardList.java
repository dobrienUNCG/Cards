package Cards.models.cards;
/*
  Last Updated: 10/28/2020
  Card list model

  @AUTHOR Devin M. O'Brien
 */

import Cards.models.MetaData;

import java.util.ArrayList;

import static Cards.models.CardLogger.logg;

public class CardList implements NormalCard {

    public ArrayList<Card> cards = new ArrayList<>();
    private final MetaData metaData = new MetaData();

    /**
     * Creates a Card List
     *
     * @param _title
     * @param _description
     * @param _cards
     */
    public CardList(String _title, String _description, ArrayList<Card> _cards) {
        this.metaData.setTitle(_title);
        this.metaData.setDescription(_description);
        this.cards = _cards;
    }

    public Card most_recent() {
        Card mostRecent = null;
        for (Card card : this.cards) {
            if (null == mostRecent) {
                mostRecent = card;
            }
            // TODO Reverse IF Order
            if (0 <= mostRecent.getRecent().compareTo(card.getRecent().getDate())) {
                mostRecent = card;
            }
        }
        return mostRecent;
    }

    public String toString() {
        // TODO Fix Regex
        String title = "<title>" + this.metaData.getTitle().replaceAll("<(/|t)itle>", "") + "</title>";
        String description = "<meta name=\"description\" content=\"" + this.metaData.getDescription() + "\">";

        return "<head>" + title + " " + description + "</head>";
    }

    public Card getCard(int _index) {
        if (this.cards.size() <= _index) {
            logg.warning(
                    "Given index (" + _index + ") " + "is larger than the number of cards in card list (" + this.getName() + ")");
            return null;
        }
        return this.cards.get(_index);
    }

    public CardEvent theRecent() {
        CardEvent event = null;
        for (Card card :
                this.cards) {
            if (null == event) {
                event = card.getRecent();
            } else {
                if (0 > card.getRecent().compareTo(event.getDate())) {
                    event = card.getRecent();
                }
            }
        }
        return event;
    }

    //=====GETTER=====
    public String getBody() {
        return this.metaData.getDescription();
    }

    public String getName() {
        return this.metaData.getTitle();
    }

    public void setName(String _title) {
        this.metaData.setTitle(_title);
    }

    public void setBody(String _description) {
        this.metaData.setDescription(_description);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
