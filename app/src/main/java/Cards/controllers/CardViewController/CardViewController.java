package Cards.controllers.CardViewController;
/**
 * Last Updated: 10/28/2020
 * Controller for the Card View.
 *
 * @AUTHOR Devin M. O'Brien
 */

import Cards.models.Card;
import Cards.models.HTMLMod;
import Cards.translators.io.CardFile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static Cards.models.CardLogger.logg;

public class CardViewController {
    public String body;

    @FXML
    VBox sidebar;

    @FXML
    HTMLEditor editor;

    Stage stage;

    ArrayList<Card> cards;
    HashMap<String, Button> openCards;
    int current = 0;
    CardFile openCard;
    HTMLMod htmlMod;

    public CardViewController() {

    }

    public CardViewController(String _x) {
        logg.entering(this.getClass().getName(), "CardViewController()");
        this.body = _x;
        logg.exiting(this.getClass().getName(), "CardViewController()");
    }

    public void createNew() {
        logg.info("Creating Button --- DISABLED");

    }

    public void openFile() {
        logg.entering("CardViewController", "openFile");

        // Setup for file chooser dialog
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Open Card File");
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("HTML Files", "*.html")
        );

        this.cards = new ArrayList<>();
        this.current = 0;
        this.openCard = new CardFile(filechooser.showOpenDialog(stage));
        removeOldCards();
        this.htmlMod = new HTMLMod(openCard.get_file());
        this.cards = this.htmlMod.get_cards();
        if ( !this.cards.isEmpty() ) {
            updateEditor(this.cards.get(0).getBody());
        }

        // Loading cards to editor
        int counter = 0;

        for ( Card card : cards ) {
            Button button = new Button();
            button.setText(card.getName());
            button.setMaxWidth(100000000);
            int finalCounter = counter;

            button.setOnAction(e -> {
                switch_card(finalCounter);
            });

            counter++;
            this.sidebar.getChildren().add(button);
        }
        logg.exiting("CardViewController", "openFile");
    }

    public void updateEditor(String _input) {
        logg.entering("CardViewController", "change");
        this.editor.setHtmlText(_input);
        System.out.println(_input);
        logg.exiting("CardViewController", "change");
    }

    public void switch_card(int _x) {
        logg.entering("CardViewController", "switch_card");
        this.cards.get(this.current).setBody(this.editor.getHtmlText());
        this.current = _x;
        updateEditor(this.cards.get(_x).getBody());
        logg.exiting("CardViewController", "switch_card");

    }

    public void go_to_main_menu() throws IOException {
        Stage appStage;
        Parent root;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Menu.fxml"));
        appStage = (Stage) sidebar.getScene().getWindow();
        root = loader.load();
        Scene scene = new Scene(root);
        appStage.setScene(scene);
        appStage.show();
    }

    public void com_save() {

        String out = "";
        this.cards.get(this.current).setBody(this.editor.getHtmlText());
        for ( Card x : cards ) {
            out = out + x.toString();
        }
        this.htmlMod.save(out);
    }

    public void com_load() {
        openFile();
    }

    public void close_menu() {
        Platform.exit();
    }

    private void removeOldCards() {
        if ( this.sidebar.getChildren().size() > 2 ) {
            this.sidebar.getChildren().remove(2, this.sidebar.getChildren().size());
        }
    }

}
