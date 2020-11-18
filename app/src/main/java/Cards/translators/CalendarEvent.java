package Cards.translators;




interface Date<T, V> extends Time<V>{


    T getDate();
    default boolean setDate(T date){
        return false;
    }

}
interface Time<V>{


    V getTime();
    boolean setTime(V time);

    int getHour();
    int getMinute();
    int getSecond();

    boolean setHour(V hour);
    boolean setMinute(V minute);
    boolean setSecond(V second);


    default boolean isPM(){
        if(getHour()>12)
            return true;
        else
            return false;
    }
    default boolean isAM(){
        if(getHour()<12){
            return true;
        }else{
            return false;
        }
    }
}