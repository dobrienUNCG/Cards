package Cards.controllers.CardViewController;

import Cards.models.Card;
import Cards.models.HTMLMod;
import Cards.models.HTMLModel;
import Cards.translators.io.CardFile;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.ArrayList;

import static Cards.models.CardLogger.logg;
import static Cards.models.HTMLModel.save;

// TODO Break Into Two Classes (View and Controller)

public class CardViewController {
    public String body = null;

    @FXML
    VBox sidebar;

    @FXML
    HTMLEditor editor;

    Stage stage;


    HTMLMod doc;

    ArrayList<Card> cards;
    int current = 0;


    public CardViewController() {

    }

    public CardViewController(String x) {
        logg.entering(this.getClass().getName(), "CardViewController()");
        body = x;
        logg.exiting(this.getClass().getName(), "CardViewController()");
    }

    public void setBody(String x) {
        body = x;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void createNew() {
        logg.info("Creating Button --- DISABLED");

//        sidebar.getChildren().add(new Button("Testing"));
//
//        System.out.println(body);
    }

    public void openFile() {
        //FIXME Chain ends up throwing NullPointer
        logg.entering("CardViewController", "openFile");

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open Card File");
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("HTML Files", "*.html")
        );

        CardFile x = new CardFile(filechooser.showOpenDialog(stage));



        HTMLMod y = new HTMLMod(x.get_file()); // Still working on switching
        cards = y.get_cards();
        if(cards.isEmpty()){

        }else {
            setBody(cards.get(0).getBody());
        }
        change();

        int counter = 0;
        for(Card card : cards){
            Button button = new Button();
            button.setText(card.getName());
            button.setMaxWidth(100000000); // This is a temporary solution
            int finalCounter = counter;
            button.setOnAction(e->{
                 switch_card(finalCounter);
            });
            counter++;
            sidebar.getChildren().add(button);
        }

        logg.exiting("CardViewController", "openFile");

    }

    public void change() {
        logg.entering("CardViewController", "change");

        editor.setHtmlText(body);
        System.out.println(body);
        logg.exiting("CardViewController", "change");

    }


    public void saveit() {
        //TODO Add Save
        save(editor.getHtmlText());
    }


    public void switch_card(int x){

        setBody(cards.get(x).getBody());
        current = x;
        change();
    }

    public void go_to_main_menu() throws IOException {
        Stage appStage;
        Parent root;
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Menu.fxml"));
            appStage=(Stage)sidebar.getScene().getWindow();
            root= loader.load();
            Scene scene = new Scene(root);
            appStage.setScene(scene);
            appStage.show();

        }
    }
    public void com_save(){
        saveit();

    }
    public void com_load(){
        openFile();
    }


    public void close_menu(){
        Platform.exit();
    }



}
