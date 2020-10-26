package Cards.controllers.CardViewController;
import Cards.models.HTMLModel;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

import static Cards.models.CardLogger.logg;
import static Cards.models.HTMLModel.save;

// TODO Break Into Two Classes (View and Controller)

public class CardViewController extends Application{
    @FXML
    VBox sidebar;
    @FXML
    HTMLEditor editor;

    public String body = null;

    public void setBody(String x){
        body = x;
    }
    public CardViewController(){

    }
    public CardViewController(String x) {
        logg.entering(this.getClass().getName(), "CardViewController()");
        body = x;
        logg.exiting(this.getClass().getName(), "CardViewController()");
    }


    @Override
    public void init() throws Exception{

    }
    @Override
    public void start(Stage stage) throws Exception{
        logg.entering(this.getClass().getName(), "start");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Template.fxml"));
        Parent root = loader.load();
        CardViewController controller = loader.<CardViewController>getController();
        controller.setBody((new HTMLModel().toString()));


        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        logg.exiting(this.getClass().getName(), "start");
    }
    public void createNew(){
        logg.info("Creating Button");
        sidebar.getChildren().add(new Button("Testing"));
        editor.setHtmlText(body);
        System.out.println(body);
    }


    public void show()
    {
        logg.entering(this.getClass().getName(), "show");
        launch(body);
        logg.exiting(this.getClass().getName(), "show");

    }
    public void change(){

        editor.setHtmlText(body);
        System.out.println(body);
    }

    public void saveit(){
        save(editor.getHtmlText());
    }


}
