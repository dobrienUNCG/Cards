package Cards.app;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

import java.net.URL;

public class JavaFXController extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        // TODO Repurpose Class

        Parent root = FXMLLoader.load(getClass().getResource("/Menu.fxml"));
        stage.setScene(new Scene((root)));
        /*
        Long Notice:
            I found out that you cannot actually have a scene (or is it a stage?)
            within the FXML file or it will fail to load with a weird error stating
            that stage cannot be cast to parent.
         */
        stage.show();
    }
    public static void show(){
        launch();
    }


}
