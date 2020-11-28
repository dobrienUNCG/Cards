package Cards.models;

import java.util.UUID;

@SuppressWarnings("unused")
public class UID {

    protected final UUID uuid;

    public UID(){
        uuid = UUID.randomUUID();
    }
    @SuppressWarnings("unused")
    UID(String _uuid){
        uuid = UUID.fromString(_uuid);
    }

    @Override
    public String toString() {
       return uuid.toString();
    }
}
