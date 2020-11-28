package Cards.models.controllersystem;

public class Property<TName>
{
    TName property;
    Property(TName _property){
        this.property = _property;

    }
    public TName getValue(){
        return property;
    }
    public void setValue(TName _property){
        this.property = _property;
    }

}
