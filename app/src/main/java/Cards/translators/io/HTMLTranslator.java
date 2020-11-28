package Cards.translators.io;
/*
  Translator Between Translator
  Date: 11/24/2020
 */

import Cards.models.cards.Card;
import Cards.models.cards.CardList;
import Cards.translators.api.TaskEvent;
import Cards.translators.jsoup.JSoupTranslator;


public class HTMLTranslator {
    private final JSoupTranslator js;

   public HTMLTranslator(CardFile _file) {
        js = JSoupTranslator.JSoupBuilder(_file);
    }
    public HTMLTranslator(Card card){
       js = JSoupTranslator.JSoupBuilder(card);
    }
    public CardList get_card_list(){
     String  title = js.get_tag_inner("title");
     String desc = js.get_meta("description");
        return new CardList(title, desc, js.getCards());
    }
    public void addEventsToCard(Card _card, TaskEvent _taskEvent, boolean completed){
        js.addTask(_taskEvent, completed);
        _card.setBody(js.getCard().getBody());
    }

}
