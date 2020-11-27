package Cards.models.controllersystem;

 interface Model<T,K> {
    public K changeValue(T _name);
    public K checkValue(T _name);
    K ask(T _operation);


}
