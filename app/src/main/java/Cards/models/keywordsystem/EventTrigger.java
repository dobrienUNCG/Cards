package Cards.models.keywordsystem;

public class EventTrigger<K, V> {
    private K key;
    private V type;

    EventTrigger(K _key, V _type){
        this.key = _key;
        this.type = _type;
    }

    public V getType() {
        return type;
    }
    public K getKey() {
        return key;
    }

    public Boolean matches(K _in){
        if(key.toString().contentEquals(_in.toString())){
            return true;
        }else{
            return false;
        }
    }
}
