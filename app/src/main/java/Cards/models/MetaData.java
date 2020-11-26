package Cards.models;
/**
 * Update: 11/18/2020
 * MetaData Model
 *
 * @apiNote To be adaptable, it is using a hashmap to store data. Rather than
 *  fields.
 */

import java.util.HashMap;

/**
 * TODO Add UUID
 */
public class MetaData{
    final HashMap<String, Object> metaData = new HashMap<>();
    MetaData(){
        metaData.put("title", null);
        metaData.put("description", null);
        metaData.put("edit", null);
    }
    Object get(String key){
        return metaData.get(key);
    }
    String getTitle(){
        return (String) metaData.get("title");
    }
    void setTitle(String _title){
        metaData.replace("title", _title);
    }
    String getDescription(){
        return (String) metaData.get("description");
    }
    void setDescription(String _description){
        metaData.replace("description", _description);
    }
}
