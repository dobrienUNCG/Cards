package Cards.models.controllersystem;

import Cards.translators.io.CardFile;
import Cards.translators.io.ViewIO;

public class AModel implements Model<String, Boolean>{
    Property name = new Property("Card");

    @Override
    public Boolean changeValue(String _name) {
        return null;
    }

    @Override
    public Boolean checkValue(String _name) {
        return null;
    }

    @Override
    public Boolean ask(String _operation) {
        return null;
    }

    enum Name{
        VIEW(), SETTING;
    }




}
