package Cards.translators.jsoup;
/*
  Last Updated: 12/1/2020
  JSoup Translator Class

  @author Devin M. O'Brien
 */

import Cards.models.AppModel;
import Cards.models.cards.Card;
import Cards.models.cards.CardEvent;
import Cards.models.cards.CardList;
import Cards.translators.api.TaskEvent;
import Cards.translators.io.CardFile;
import com.google.api.client.util.DateTime;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;
import static Cards.translators.jsoup.JSoupTranslator.Attribute.*;

public class JSoupTranslator {

    private CardFile cardFile;
    private Document doc;
    private Card card;

    /**
     * JSoup Translator Constructor
     *
     * @param _cardFile
     */
    JSoupTranslator(CardFile _cardFile) {
        this.cardFile = _cardFile;
        this.parse_file();
    }

    /**
     * JSoup Translator Constructor
     *
     * @param
     */
    JSoupTranslator(Card card) {
        this.card = card;
        this.parseCard(this.card);
    }

    public static JSoupTranslator JSoupBuilder(CardFile _cardFile) {
        return new JSoupTranslator(_cardFile);
    }

    public static JSoupTranslator JSoupBuilder(Card card) {
        return new JSoupTranslator(card);
    }

    public static String replaceBodyTag(String _input) {
        Document temp = Jsoup.parse(_input);
        Element body = temp.body();
        Tag section = Tag.valueOf("section");
        body.replaceWith(new Element(Tag.valueOf("section"), ""));
        if (0 == body.childNodeSize()) {
            logg.info("No Children");
            return _input;
        }
        return body.toString();
    }

    public static String getTextInBody(String _input) {
        Document temp = Jsoup.parse(_input);
        Element init = temp.body();
        return init.html();

    }

    public static String normalize(String _input) {
        Document temp = Jsoup.parse(_input);
        return temp.normalise().outerHtml();
    }

    public String addTask(TaskEvent taskInfo, boolean completed) {
        try {

            Elements spans = this.doc.getElementsByTag("span");
            for (Element span : spans) {
                if (span.attr("type").equals("edit")) {
                    try {
                        span = span.attr("type", String.valueOf(EVENT));
                        span.attr("onclick", "openEvent('" + taskInfo.getEventId() + "');");
                        span.addClass("event");
                        span.attr("contenteditable", "false");
                        span.attr("style", "background-color: yellow;");
                        span.attr(String.valueOf(EVENT_ID), taskInfo.getEventId());
                        span.attr(String.valueOf(EVENT_NAME), taskInfo.getSummary());
                        span.attr(String.valueOf(EVENT_DESCRIPTION), taskInfo.getDescription());
                        span.attr(String.valueOf(EVENT_ALL_DAY), taskInfo.getAllDay());
                        span.attr(String.valueOf(EVENT_START_DATE), taskInfo.getBeginDateTime().toString());
                        span.attr(String.valueOf(EVENT_END_DATE), taskInfo.getEndDateTime().toString());
                        span.attr(String.valueOf(EVENT_CREATED), taskInfo.getDateCreated().toString());
                        span.attr(String.valueOf(EVENT_COMPLETED), completed);
                        return this.doc.toString();
                    } catch (Exception ex) {

                    }

                }
            }
        } catch (Exception _e) {
            _e.printStackTrace();
        }
        return null;

    }

    public String editTask(CardEvent _old, CardEvent _new) {
        //TODO
        try {
            Elements spans = this.doc.getElementsByAttributeValue(String.valueOf(EVENT_ID), _old.getTaskEvent().getEventId());
            Element span = spans.get(0);
            TaskEvent taskInfo = _new.getTaskEvent();
            span.attr("contenteditable", "false");
            span.attr("onclick", "openEvent('" + taskInfo.getEventId() + "');");
            span.attr("style", "background-color: yellow;");
            span.addClass("event");
            span.attr(String.valueOf(EVENT_ID), taskInfo.getEventId());
            span.attr(String.valueOf(EVENT_NAME), taskInfo.getSummary());
            span.attr(String.valueOf(EVENT_DESCRIPTION), taskInfo.getDescription());
            span.attr(String.valueOf(EVENT_ALL_DAY), taskInfo.getAllDay());
            span.attr(String.valueOf(EVENT_START_DATE), taskInfo.getBeginDateTime().toString());
            span.attr(String.valueOf(EVENT_END_DATE), taskInfo.getEndDateTime().toString());
            span.attr(String.valueOf(EVENT_CREATED), taskInfo.getDateCreated().toString());
            span.attr(String.valueOf(EVENT_COMPLETED), _new.isComplete());
            return this.doc.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String deleteTask(CardEvent _old) {
        Elements spans = this.doc.getElementsByAttributeValue(String.valueOf(EVENT_ID), _old.getTaskEvent().getEventId());
        String inner = spans.get(0).html();
        spans.get(0).before(inner);
        spans.get(0).remove();
        return this.doc.toString();
    }

    @Nullable
    public String getTag(String x) {
        Elements ez = this.doc.getElementsByTag(x);
        if (null == ez) {
            logg.warning("Invalid Input");
            return null;
        }
        return ez.get(0).toString();
    }

    public String get_tag_inner(String x) {

        Elements ez = this.doc.getElementsByTag(x);
        if (null == ez) {
            logg.warning("Invalid Input");
            logg.exiting(this.getClass().getName(), "get_tag(String x)");
            return "null";
        }

        return ez.text();
    }

    public String get_meta(String type) {
        String output = "";
        Elements metatags = this.doc.getElementsByTag("meta");
        for (Element tag : metatags) {
            if (tag.attr("name").contentEquals(type)) {
                output = tag.attr("content");
                break;
            }
        }
        return output;
    }

    private void parseCard(Card _card) {
        this.doc = Jsoup.parse(_card.getBody());
    }

    private void parse_file() {
        try {
            this.doc = Jsoup.parse(this.cardFile.get_card_file(), "UTF-8");
            logg.info(this.doc.toString());
            this.doc.normalise();
        } catch (IOException e) {
            logg.warning("Failed to load file");
        }
    }

    public static void save(CardFile _cardFile, CardList _cardList) {
        StringBuilder out = new StringBuilder();
        AppModel.settingsController.addCardFile(_cardFile);
        for (Card card : _cardList.getCards()) {
            out.append(card);
        }
        out = new StringBuilder(out.toString().replaceAll("<((/b)|b)ody>", ""));
        try {
            String out2 = "<html>" + _cardList + "<body>" + out + "</body></html>";
            out2 = normalize(out2);
            FileWriter fileWriter = new FileWriter(_cardFile.getFile(), false);
            fileWriter.write(out2);
            fileWriter.close();
        } catch (IOException _e) {

            logg.severe(_e.toString());
        }
    }

    enum Attribute {
        EVENT_ID {
            @Override
            public String toString() {
                return "eventID";
            }
        }, EVENT_NAME {
            @Override
            public String toString() {
                return "eventName";
            }
        }, EVENT_DESCRIPTION {
            @Override
            public String toString() {
                return "eventDescription";
            }
        }, EVENT_START_DATE {
            @Override
            public String toString() {
                return "eventStartDate";
            }
        }, EVENT_START_TIME {
            @Override
            public String toString() {
                return "eventStartTime";
            }
        }, EVENT_END_DATE {
            @Override
            public String toString() {
                return "eventEndDate";
            }
        }, EVENT_END_TIME {
            @Override
            public String toString() {
                return "eventEndTime";
            }
        }, EVENT_ALL_DAY {
            @Override
            public String toString() {
                return "allDay";
            }
        }, EVENT_CREATED {
            @Override
            public String toString() {
                return "eventCreated";
            }
        }, EVENT {
            @Override
            public String toString() {
                return "event";
            }
        },
        EVENT_COMPLETED {
            @Override
            public String toString() {
                return "complete";
            }
        }
    }

    //=================  GETTERS ===============
    public String getBody() {
        return this.doc.body().toString();
    }

    public Card getCard() {
        ArrayList<Card> cards = new ArrayList<>();

        Element html = this.doc;
        String title = this.doc.attributes().get("title");

        ArrayList<CardEvent> events = new ArrayList<>();
        for (Element span : this.doc.getElementsByTag("span")) {

            if (span.attributes().hasDeclaredValueForKey(String.valueOf(EVENT))) {
                String id = span.attr(String.valueOf(EVENT_ID));
                String summary = span.attr(String.valueOf(EVENT_NAME));
                String description = span.attr(String.valueOf(EVENT_DESCRIPTION));
                String allday = span.attr(String.valueOf(EVENT_ALL_DAY));
                String eventStart = span.attr(String.valueOf(EVENT_START_DATE));
                String eventEnd = span.attr(String.valueOf(EVENT_END_DATE));
                String eventCreated = span.attr(String.valueOf(EVENT_CREATED));
                String completed = span.attr(String.valueOf(EVENT_COMPLETED));
                try {
                    events.add(new CardEvent(new TaskEvent(summary, DateTime.parseRfc3339(eventStart), DateTime.parseRfc3339(eventEnd), description, DateTime.parseRfc3339(eventCreated), null, id, Boolean.parseBoolean(allday)), Boolean.parseBoolean(completed)));
                } catch (IOException | GeneralSecurityException ex) {
                    ex.printStackTrace();
                }
            }

        }
        String body = this.doc.getElementsByTag("body").get(0).html();
        return new Card(title, body, events);

    }

    public ArrayList<Card> getCards() {
        ArrayList<Card> cards = new ArrayList<>();
        for (Element x : this.doc.getElementsByTag("section")) {
            String body = x.html(); // For HTML
            String title = x.attributes().get("title");

            ArrayList<CardEvent> events = new ArrayList<>();
            for (Element span : x.getElementsByTag("span")) {

                if (span.attributes().hasDeclaredValueForKey("type")) {

                    if (span.attr("type").equals("event")) {

                        String id = span.attr(String.valueOf(EVENT_ID));
                        String summary = span.attr(String.valueOf(EVENT_NAME));
                        String description = span.attr(String.valueOf(EVENT_DESCRIPTION));
                        String allday = span.attr(String.valueOf(EVENT_ALL_DAY));
                        String eventStart = span.attr(String.valueOf(EVENT_START_DATE));
                        String eventEnd = span.attr(String.valueOf(EVENT_END_DATE));
                        String eventCreated = span.attr(String.valueOf(EVENT_CREATED));
                        String completed = span.attr(String.valueOf(EVENT_COMPLETED));
                        try {
                            logg.info(eventStart);
                            events.add(new CardEvent(new TaskEvent(summary, DateTime.parseRfc3339(eventStart), DateTime.parseRfc3339(eventEnd), description, DateTime.parseRfc3339(eventCreated), null, id, Boolean.parseBoolean(allday)), Boolean.parseBoolean(completed)));
                        } catch (IOException | GeneralSecurityException ex) {
                            logg.severe(ex.toString());
                        }
                    }
                }

            }
            cards.add(new Card(title, body, events));
        }
        return cards;
    }

    public String getDoc() {
        return this.doc.toString();
    }

    public String getHead() {
        return this.doc.head().toString();
    }

    public String getTitle() {
        Elements x = this.doc.getElementsByTag("title");
        return x.toString();
    }

    //=================  SETTERS ===============

}
