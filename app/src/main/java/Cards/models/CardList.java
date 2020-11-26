package Cards.models;

import java.util.ArrayList;

public class CardList {
    @SuppressWarnings("UnusedAssignment")
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
        metaData.setTitle(_title);
        metaData.setDescription(_description);
        this.cards = _cards;
    }

    public Card most_recent() {
        Card most_recent = null;
        for ( Card card : this.cards ) {
            if ( most_recent == null ) {
                most_recent = card;
            }
            if ( most_recent.getRecent().getDate().compareTo(card.getRecent().getDate()) < 0 ) {

            } else {
                most_recent = card;
            }
        }
        return most_recent;
    }

    public String toString() {
        String title = "<title>" + metaData.getTitle().replaceAll("<(/|t)itle>", "") + "</title>";
        String description = "<meta name=\"description\" content=\""+metaData.getDescription() + "\">";

        return "<head>" + title + " " + description + "</head>";
    }

    public Card getCard(int _index) {
        if ( cards.size() <= _index ) {
            System.err.println(
                    "Given index (" + _index + ") " + "is larger than the number of cards in card list (" + this.getTitle() + ")");
            return null;
        }
        return this.cards.get(_index);
    }

    //=====GETTER=====
    public String getDescription() {
        return this.metaData.getDescription();
    }

    public String getTitle() {
        return this.metaData.getTitle();
    }

    public void setTitle(String _title){
        this.metaData.setTitle(_title);
    }
    public void setDescription(String _description){
        this.metaData.setDescription(_description);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
