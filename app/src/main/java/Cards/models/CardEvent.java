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
    private String desc;

    public CardEvent(LocalDateTime date, String desc) {
        this.date = date;
        this.desc = desc;
    }

    public CardEvent(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "CardEvent{" +
                "date=" + this.date +
                ", desc='" + this.desc + '\'' +
                '}';
    }

    //=================  GETTERS ===============

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime _date) {
        this.date = _date;
    }

    //=================  SETTERS ===============

    public String getDesc() {
        if (this.desc == null)
            return null;
        return this.desc;
    }

    public void setDesc(String _desc) {
        this.desc = _desc;
    }


}
