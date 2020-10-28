package Cards.views;

import Cards.models.HTMLModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import static Cards.models.CardLogger.logg;

public class MainMenuView extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        logg.entering(this.getClass().getName(), "start");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Menu.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        logg.exiting(this.getClass().getName(), "start");
    }

    public void show()
    {
        logg.entering(this.getClass().getName(), "show");
        launch();
        logg.exiting(this.getClass().getName(), "show");

    }


}
