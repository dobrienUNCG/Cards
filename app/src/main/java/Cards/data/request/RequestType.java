package Cards.data.request;

public abstract class RequestType{
    enum requestType{
        POST, GET, PUT;
    }
    abstract requestType getRequestType();
}
