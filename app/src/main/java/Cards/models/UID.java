package Cards.models;

import java.util.UUID;


public class UID {

    protected final UUID uuid;

    public UID(){
        uuid = UUID.randomUUID();
    }

    /**
     * @deprecated Was going to be used for the card event.
     * @param _uuid
     */
    UID(String _uuid){
        uuid = UUID.fromString(_uuid);
    }

    @Override
    public String toString() {
       return uuid.toString();
    }
}
