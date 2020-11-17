package Cards.views;
/**
 * Last Updated: 11/17/2020 4:48 PM
 * Moving over from CardViewController
 * Card View
 *
 * @author Devin M. O'Brien
 */

import Cards.app.AppModel;

import Cards.models.Card;
import Cards.models.CardList;
import Cards.models.HTMLMod;
import Cards.translators.io.CardFile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static Cards.models.CardLogger.logg;
import static Cards.models.settings.CardSettings.add_file;

public class CardView {

    public String body;
    @FXML
    VBox sidebar;
    @FXML
    HTMLEditor editor;
    @FXML
    Button titlebutton;
    Stage stage;
    ArrayList<Card> cards;
    HashMap<String, Button> openCards;
    int current = 0;
    CardFile openCard;
    CardList cardList;
    HTMLMod htmlMod;
    String select = "(function getText(){\n" + "if(window.getSelection){\n" + "return window.getSelection();\n" + "}" + "})()";
    public CardView() {

    }
    public CardView(CardFile x) {
        logg.entering(this.getClass().getName(), "CardViewController()");

        logg.exiting(this.getClass().getName(), "CardViewController()");
    }

    @FXML
    void close_menu() {

    }

    @FXML
    public void initialize() {
        if ( AppModel.activeFile != null ) {
            openFile(AppModel.activeFile);
        }
    }


    public CardFile askFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        CardFile cardFile1 = new CardFile(fileChooser.showSaveDialog(stage));
        return cardFile1;

    }

    /**
     * Create New Card
     */
    public void createNew() {
        logg.info("Creating Button --- DISABLED");
        TextInputDialog prompt = new TextInputDialog();
        prompt.setTitle("Title");

        prompt.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        prompt.showAndWait();

        String text = prompt.getEditor().getText();
        Button button = new Button();
        button.setText(text);
        button.setMaxWidth(100000000);

        if ( cards == null ) {
            cards = new ArrayList<>();
        }
        cards.add(new Card(text, "", null));
        final int index = cards.size() - 1;
        button.setOnAction(e -> {
            switch_card(index);
        });
        MenuItem menu = new MenuItem("Edit");
        menu.setOnAction(event -> {
            System.out.println("Hello?");
            button.fire();
            prompt.getEditor().setText(cards.get(index).getName());
            prompt.showAndWait();
            String input = prompt.getEditor().getText();
            cards.get(index).setName(input);
            button.setText(input);
        });
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(menu);
        button.setContextMenu(contextMenu);
        this.sidebar.getChildren().add(button);
    }

    /**
     * Opens a Card
     *
     * @param _cardFile TODO Add Seperate
     */
    public void openFile(CardFile _cardFile) {
        this.cards = new ArrayList<>();
        this.current = 0;
        this.openCard = AppModel.activeFile;
        AppModel.activeFile = null;
        removeOldCards();
        add_file(this.openCard);
        this.htmlMod = new HTMLMod(openCard.getFile());
        this.cards = this.htmlMod.get_cards();
        if ( ! this.cards.isEmpty() ) {
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

    public void openFile() {

    }

    /**
     * Changes content of HTML Editor
     * @param _input
     */
    public void updateEditor(String _input) {
        this.editor.setHtmlText(_input);
        System.out.println(_input);
    }

    /**
     * Switch to card
     * @param _index card being switched to
     */
    public void switch_card(int _index) {
        logg.entering("CardViewController", "switch_card");
        this.cards.get(this.current).setBody(this.editor.getHtmlText());
        this.current = _index;
        updateEditor(this.cards.get(_index).getBody());
        logg.exiting("CardViewController", "switch_card");
    }

    /**
     * calls Dialog to change CardList Information
     *
     * @deprecated Incomplete
     *         TODO Finish Dialog
     */
    public void changeDetails() {

    }

    public void go_to_main_menu() throws IOException {

    }
    public void com_save() {

    }

    public void com_load() {
        openFile();
    }

    private void removeOldCards() {
        if ( this.sidebar.getChildren().size() > 2 ) {
            this.sidebar.getChildren().remove(2, this.sidebar.getChildren().size());
        }
    }

    private void add_event() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/EventCreationDialog.fxml"));
        Dialog<Scene> d = null;
        try {
            d = loader.load();
        } catch ( IOException z ) {

        }
        if ( d != null ) {

        }

    }

    /**
     * @deprecated Not implemented
     *         TODO Finsih Checking for Events
     */
    void check_for_events() {

    }

    //=====GETTER=====
    //=====GETTER=====
    public Scene get_a_card() throws Exception {
        logg.entering(this.getClass().getName(), "start");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Template.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("./Card.css").toExternalForm());

        logg.exiting(this.getClass().getName(), "start");
        return scene;
    }

    public String get_selection() {
        WebView webView = (WebView) editor.lookup("WebView");
        if ( webView != null ) {
            Object y = webView.getEngine().executeScript(select);
            if ( y instanceof String ) {
                System.out.println((String) y);
                return (String) y;
            }

        }
        return null;
    }

}



