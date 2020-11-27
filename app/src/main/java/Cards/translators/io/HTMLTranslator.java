package Cards.translators.io;
/*
  Translator Between Translator
  Date: 11/24/2020
 */

import Cards.models.cards.CardList;
import Cards.translators.jsoup.JSoupTranslator;


public class HTMLTranslator {
    private final JSoupTranslator js;

   public HTMLTranslator(CardFile _file) {
        js = JSoupTranslator.JSoupBuilder(_file);

    }
    public CardList get_card_list(){
     String  title = js.get_tag_inner("title");
     String desc = js.get_meta("description");
        return new CardList(title, desc, js.getCards());
    }
}
