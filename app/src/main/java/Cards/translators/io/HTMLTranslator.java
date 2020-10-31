package Cards.translators.io;

import Cards.models.CardList;
import Cards.translators.jsoup.JSoupTranslator;

public class HTMLTranslator {
    JSoupTranslator js = null;
    public HTMLTranslator(String _file) {
        js = new JSoupTranslator(new CardFile(_file));

    }
    public CardList get_card_list(){
     String  title = js.get_tag_inner("title");
     String desc = js.get_tag_inner("meta");
     CardList x = new CardList(title, desc, js.get_cards());
     return x;
    }
}
