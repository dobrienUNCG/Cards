package Cards.models;


import java.util.HashMap;

/**
 * TODO Add UUID
 */
public class MetaData{
    HashMap<String, Object> metaData = new HashMap<>();
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
