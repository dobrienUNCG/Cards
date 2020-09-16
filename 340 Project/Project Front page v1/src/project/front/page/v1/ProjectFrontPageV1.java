package project.front.page.v1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Admin
 */
public class ProjectFrontPageV1 extends Application {

    private static int buttonHeight = 75;
    private static int buttonWidth = 180;

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        //Main Tab Buttons
        Button fileExplorer = new Button("File explorer");
        fileExplorer.setOnAction(actionEvent -> {
            //Sets screen to file Explorer
            System.out.println("this takes you to the file explorer");
        });
        fileExplorer.setPrefSize(buttonWidth, buttonHeight);
        /* code for adding image to button-not repeating for obv reasons
        FileInputStream input = new FileInputStream("resources/images/iconmonstr-home-6-48.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);

        Button button = new Button("Home", imageView);
         */
//        ------------------------------------------------------
        Button calendar = new Button("Calendar");
        calendar.setOnAction(actionEvent -> {
            //Sets screen to file Explorer
            System.out.println("this takes you to the calendar");
        });
        calendar.setMinSize(buttonWidth, buttonHeight);
        calendar.setPrefSize(buttonWidth, buttonHeight);
        //--------------------------------------------------------
        Button userSettings = new Button("User Settings");
        userSettings.setOnAction(actionEvent -> {
            //Sets screen to file Explorer
            System.out.println("this takes you to the user settings");
        });
        userSettings.setPrefSize(buttonWidth, buttonHeight);
        //------------------------------------------------------
        Button settings = new Button("Settings");
        settings.setOnAction(actionEvent -> {
            //Sets screen to file Explorer
            System.out.println("this takes you to the settings");
        });
        settings.setPrefSize(buttonWidth, buttonHeight);
        //------------------------------------------------------
        Button help = new Button("Help");
        help.setOnAction(actionEvent -> {
            //Sets screen to Help
            System.out.println("this takes you to the help screen");
        });
        help.setPrefSize(buttonWidth, buttonHeight);
        //**********************************************

        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setTranslateX(-buttonWidth * 3);
        VBox vBox = new VBox(fileExplorer, calendar, userSettings, settings, help, separator);
        vBox.setSpacing(0);
        vBox.setPadding(new Insets(0, 0, 0, 0));
        primaryStage.setTitle("Hello World!");
        Scene scene = new Scene(vBox, 750, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
