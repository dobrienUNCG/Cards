package Cards.models.controllersystem;

abstract class Controller extends Thread{
    private final boolean appController;
    private Model model;
    Controller(Model _model){
        appController = false;
        this.model = _model;
    }
    Controller(Model _model, boolean _appController){
        this.appController = _appController;
        this.model = _model;
    }

    public void start(){

    }

    @Override
    public void run() {
        if(appController){


        }
    }
}