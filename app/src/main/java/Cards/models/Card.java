package Cards.models;

import java.util.ArrayList;
import java.util.Date;
//TODO Write Class
public class Card{
    private String name;
    private Date created_date;
    private Date modified_date;
    private String body;
    ArrayList<CardEvent> events;

    public Card(){

    }
    Card(String name, String body){

    }
    public Card(String name, String body, ArrayList<CardEvent> events){
        this.name = name;
        this.body = body;
        this.events = events;
    }

    public String getBody() {
        return body;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "\nCard{" +
                "name='" + name + '\'' +
                ", created_date=" + created_date +
                ", modified_date=" + modified_date +
                ", \nbody='" + body + '\'' +
                ", events=" + events +
                '}';
    }
}
