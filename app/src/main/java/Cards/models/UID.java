package Cards.models;
/**
 * Date: 12/1/2020
 * Used to generate eventids for card events. Not used afterwards.
 * @author Devin M. O'Brien
 */

import java.util.UUID;

public class UID {

    protected final UUID uuid;

    public UID() {
        this.uuid = UUID.randomUUID();
    }


    @Override
    public String toString() {
        return this.uuid.toString();
    }
}
