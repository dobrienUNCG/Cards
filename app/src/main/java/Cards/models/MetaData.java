package Cards.models;
/*
  Update: 11/18/2020
  MetaData Model

  @apiNote To be adaptable, it is using a hashmap to store data. Rather than
 *  fields.
*/

import java.util.HashMap;

public class MetaData {

    private final HashMap<String, Object> metaData = new HashMap<>();

    public MetaData() {
        metaData.put("title", null);
        metaData.put("description", null);
        metaData.put("edit", null);
    }

    public Object get(String key) {
        return metaData.get(key);
    }

    //=================  GETTERS ===============
    public String getDescription() {
        return (String) metaData.get("description");
    }

    public String getTitle() {
        return (String) metaData.get("title");
    }

    //=================  SETTERS  ===============
    public void setDescription(String _description) {
        metaData.replace("description", _description);
    }

    public void setTitle(String _title) {
        metaData.replace("title", _title);
    }
}
