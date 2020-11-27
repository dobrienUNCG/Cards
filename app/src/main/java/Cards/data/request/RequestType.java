package Cards.data.request;

enum RequestType {
    GET_EVENTID, GET_INFO_ALL, GET_IS_ALL_DAY, GET_DUE_DATE, GET_DESCRIPTION,
    GET_DATE_CREATED, GET_DUE_TODAY, GET_UPCOMING, GET_LIMIT,
    POST_SUMMARY, POST_DATE_BEGIN, POST_DATE_END, POST_DESCRIPTION, POST_LIMIT,
    DELETE_EVENT,
    PUT_CALENDAR, PUT_EVENT, PUT;
    int i;

    RequestType() {

    }

    RequestType(int _value) {
        this.i = _value;
    }

    //=================  GETTERS ===============
    public int getI() {
        return i;
    }

    public boolean isDelete() {
        return this.ordinal() == DELETE_EVENT.ordinal();
    }

    public boolean isGet() {
        return this.ordinal() < GET_LIMIT.ordinal();
    }

    public boolean isPost() {
        return this.ordinal() > GET_LIMIT.ordinal() && this.ordinal() < POST_LIMIT.ordinal();
    }

    public boolean isPut() {
        return this.ordinal() > DELETE_EVENT.ordinal();
    }


}