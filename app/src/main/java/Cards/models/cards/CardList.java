package Cards.models.cards;

import Cards.models.MetaData;

import java.util.ArrayList;

public class CardList implements NormalCard{
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
    public CardList(String _title, String _description, ArrayList<Card> _cards){
        this.metaData.setTitle(_title);
        this.metaData.setDescription(_description);
        this.cards = _cards;
    }

    public Card most_recent() {
        Card mostRecent = null;
        for ( Card card : this.cards ) {
            if ( mostRecent == null ) {
                mostRecent = card;
            }
            // TODO Reverse IF Order
            if ( mostRecent.getRecent().compareTo(card.getRecent().getDate()) >= 0 ) {
                mostRecent = card;
            }
        }
        return mostRecent;
    }

    public String toString() {
        // TODO Fix Regex
        String title = "<title>" + this.metaData.getTitle().replaceAll("<(/|t)itle>", "") + "</title>";
        String description = "<meta name=\"description\" content=\""+ this.metaData.getDescription() + "\">";

        return "<head>" + title + " " + description + "</head>";
    }

    public Card getCard(int _index) {
        if ( cards.size() <= _index ) {
            System.err.println(
                    "Given index (" + _index + ") " + "is larger than the number of cards in card list (" + this.getName() + ")");
            return null;
        }
        return this.cards.get(_index);
    }

    //=====GETTER=====
    public String getBody() {
        return this.metaData.getDescription();
    }

    public String getName() {
        return this.metaData.getTitle();
    }

    public void setName(String _title){
        this.metaData.setTitle(_title);
    }
    public void setBody(String _description){
        this.metaData.setDescription(_description);
    }

    public ArrayList<Card> getCards() {
        return this.cards;
    }
}
