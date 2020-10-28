package Cards.models;

import java.time.LocalDateTime;
import java.util.Date;

public class CardEvent {
    LocalDateTime date;
    String desc;
    public CardEvent(LocalDateTime date, String desc){
        this.date = date;
        this.desc = desc;
    }
    CardEvent(LocalDateTime date){
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDesc() {
        if(desc == null)
            return null;
        return desc;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "CardEvent{" +
                "date=" + date +
                ", desc='" + desc + '\'' +
                '}';
    }
}
