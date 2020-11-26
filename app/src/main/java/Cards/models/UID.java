package Cards.models;

import java.util.UUID;

public class UID {

    protected UUID uuid;

    public UID(){
        uuid = UUID.randomUUID();
    }
    UID(String _uuid){
        uuid = UUID.fromString(_uuid);
    }

}
