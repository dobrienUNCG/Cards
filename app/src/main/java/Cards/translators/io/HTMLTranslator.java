package Cards.translators.io;
/*
  Translator Between Translator
  Date: 11/28/2020
  @author: Devin M. O'Brien

 */

import Cards.models.cards.Card;
import Cards.models.cards.CardEvent;
import Cards.models.cards.CardList;
import Cards.translators.api.TaskEvent;
import Cards.translators.jsoup.JSoupTranslator;

/**
 * Performs a small range of operations using the JSoupTranslator.
 *
 * @apiNote This is a smaller version of the HTMLMod class.
 */
public class HTMLTranslator {

    private final JSoupTranslator js;

    /**
     * Creates a new HTML Translator
     *
     * @param _file the file
     */
    public HTMLTranslator(CardFile _file) {
        js = JSoupTranslator.JSoupBuilder(_file);
    }

    /**
     * Creates a new HTML Translator
     *
     * @param card the card
     */
    public HTMLTranslator(Card card) {
        js = JSoupTranslator.JSoupBuilder(card);
    }

    /**
     * Add events to a card.
     *
     * @param _card      the card
     * @param _taskEvent the task event
     * @param completed  is completed
     */
    public void addEventsToCard(Card _card, TaskEvent _taskEvent, boolean completed) {
        js.addTask(_taskEvent, completed);
        _card.setBody(js.getCard().getBody());
    }
    public void editEvent(Card _Card, CardEvent _old, CardEvent _new){
        _Card.setBody(js.editTask(_old, _new));

    }

//=================  GETTERS ===============
    /**
     * Gets card list.
     *
     * @return the card list
     */
    public CardList get_card_list() {
        String title = js.get_tag_inner("title");
        String desc = js.get_meta("description");
        return new CardList(title, desc, js.getCards());
    }

}
