package Cards.models;


public abstract class Model <T, K>   {
    protected T t;
    protected K k;

    public void change(T property){
        t = property;
    }
    abstract public K interpret();

}
