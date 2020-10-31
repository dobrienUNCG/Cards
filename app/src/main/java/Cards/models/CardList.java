package Cards.models;

import java.util.ArrayList;

public class CardList {
    private String title;
    private String desc;
    public ArrayList<Card>  cards = new ArrayList<>();
    public CardList(String _title, String _desc, ArrayList<Card> _cards){
        this.title = _title;
        this.desc = _desc;
        this.cards = _cards;
    }
    public Card most_recent(){
        Card most_recent = null;
        for(Card card : cards){
            if(most_recent == null){
                most_recent = card;
            }
            if(most_recent.getRecent().getDate().compareTo(card.getRecent().getDate()) < 0){

            }else{
                most_recent = card;
            }
        }
        return most_recent;
    }
    public String toString(){
        return title + " " + desc + " " + cards.toString();
    }
}
