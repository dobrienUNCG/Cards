package Cards.data.request;

public enum RequestType{
        GET_EVENTID, GET_INFO_ALL, GET_IS_ALL_DAY, GET_DUE_DATE, GET_DESCRIPTION,
        GET_DATE_CREATED, GET_DUE_TODAY, GET_UPCOMING,
        POST_SUMMARY, POST_DATE_BEGIN, POST_DATE_END, POST_DESCRIPTION,
        DELETE_EVENT,
        PUT_CALENDAR, PUT_EVENT;
      public   boolean isGet(){
            return this.ordinal() <= 7;
        }
        public boolean isPost(){
            return this.ordinal() >= 8 && this.ordinal() <12;
        }
     public    boolean isDelete(){
            return this.ordinal() == 12;
        }
      public   boolean isPut(){
            return this.ordinal() == 13 || this.ordinal() == 14;
        }

}