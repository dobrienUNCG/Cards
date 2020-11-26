package Cards.controllers;

import Cards.models.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

 public abstract class Controller implements Runnable {
    private Thread thread;
    private boolean parent = false;
    private Model model;
    public Controller(boolean _parent){
        this.parent = _parent;
    }
    public Controller(){

    }

     public Model getModel() {
         return model;
     }

     public synchronized void wakeupParent(){
        if(!parent)
        notifyAll();
    }
    public synchronized void beWoken(){
        if(parent)
            checkModel();
    }
    void checkModel(){

    }
    void gotoSleep(){
        if(parent)
            try {
                wait();
            }catch(Exception x){
                System.err.println(x);
            }
    }

     @Override
     public void run() {
        if(parent) {
            checkModel();
            gotoSleep();
        }
     }

     public void setParent(){
        parent = true;
     }
     public void setModel(Model _model) {


    }


}
