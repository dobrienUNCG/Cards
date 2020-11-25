package Cards.translators.io;
/**
 * Translator Between Translator
 * Date: 11/24/2020
 */

import Cards.models.CardList;
import Cards.translators.jsoup.JSoupTranslator;


public class HTMLTranslator {
    private JSoupTranslator js;

   public HTMLTranslator(CardFile _file) {
        js = new JSoupTranslator(_file);

    }
    public CardList get_card_list(){
     String  title = js.get_tag_inner("title");
     String desc = js.get_meta("description");
     CardList cardList = new CardList(title, desc, js.get_cards());
     return cardList;
    }
}
