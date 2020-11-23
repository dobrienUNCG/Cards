package Cards.models;
/**
 * Last Updated: 10/28/2020
 * Card Event Data Model
 *
 * @AUTHOR Devin M. O'Brien
 */

import java.time.LocalDateTime;

public class CardEvent {

    private LocalDateTime date;
    private String description;
    private String uuid;

    public CardEvent(LocalDateTime date, String description) {
        this.date = date;
        this.description = description;
    }

    public CardEvent(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CardEvent{" + "date=" + this.date + ", desc='" + this.description + '\'' + '}';
    }

    //=====GETTER=====
    public LocalDateTime getDate() {
        return this.date;
    }

    public String getDescription() {
        if ( this.description == null ) {
            return null;
        }
        return this.description;
    }

    //=====SETTERS=====
    public void setDate(LocalDateTime _date) {
        this.date = _date;
    }

    public void setDescription(String _description) {
        this.description = _description;
    }

}
