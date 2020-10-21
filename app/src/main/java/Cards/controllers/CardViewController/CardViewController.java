package Cards.controllers.CardViewController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

public class CardViewController extends Application{
    @FXML
    VBox sidebar;
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/Template.fxml"));
        stage.setScene(new Scene((root)));
        stage.show();
    }
    public void createNew(){
        System.out.println("Creating Button");
        sidebar.getChildren().add(new Button("Testing"));
    }

    public void show(){
        launch();
    }



}
