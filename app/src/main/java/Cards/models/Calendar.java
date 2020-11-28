package Cards.models;

import Cards.data.request.Request;
import Cards.data.request.RequestType;
import Cards.translators.api.TaskEvent;

import java.util.List;

import static Cards.data.request.RequestType.GET_UPCOMING;

public class Calendar{
    private final int x = 10;
    public String calendarSetup(){
        String retVal = "";
        RequestType request = GET_UPCOMING;
        request.setI(x);
        AppModel.requestManager.submit();
        AppModel.requestManager.addRequest(new Request(request));
        try {
            List<TaskEvent> list =(List<TaskEvent>)AppModel.requestManager.submit().get(0);
            if(!list.isEmpty()){
              for(int y = 0;y<x;y++){
                retVal+=list.get(y).getSummary()+"\n";
                }
            }else{
                retVal = "No upcoming tasks!";
            }
            }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return retVal;
    }
}
