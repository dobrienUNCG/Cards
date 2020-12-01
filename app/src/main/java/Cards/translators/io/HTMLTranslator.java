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
    private CardFile cardFile;

    /**
     * Creates a new HTML Translator
     *
     * @param _file the file
     */
    public HTMLTranslator(CardFile _file) {
        this.js = JSoupTranslator.JSoupBuilder(_file);
    }

    public HTMLTranslator(Card _card) {
        this.js = JSoupTranslator.JSoupBuilder(_card);

    }

    public HTMLTranslator(String _input) {
        this.cardFile = new CardFile(_input);
        this.js = JSoupTranslator.JSoupBuilder(this.cardFile);
    }

    /**
     * Add events to a card.
     *
     * @param _card      the card
     * @param _taskEvent the task event
     * @param completed  is completed
     */
    public void addEventsToCard(Card _card, TaskEvent _taskEvent, boolean completed) {
        this.js.addTask(_taskEvent, completed);
        _card.setBody(this.js.getCard().getBody());
    }

    public void editEvent(Card _Card, CardEvent _old, CardEvent _new) {
        _Card.setBody(this.js.editTask(_old, _new));

    }

//=================  GETTERS ===============

    /**
     * Gets card list.
     *
     * @return the card list
     */
    public CardList get_card_list() {
        String title = this.js.get_tag_inner("title");
        String desc = this.js.get_meta("description");
        return new CardList(title, desc, this.js.getCards());
    }

    public void deleteEvent(Card _card, CardEvent _event) {
        _card.setBody(this.js.deleteTask(_event));
        _card.remove_event(_event.getTaskEvent().getEventId());

    }


}
