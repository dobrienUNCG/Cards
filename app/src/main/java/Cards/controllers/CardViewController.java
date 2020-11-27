package Cards.controllers;
/*
  Last Updated: 11/17/2020 4:48 PM
  Moving over from CardViewController
  Card View

  @author Devin M. O'Brien
 */

import Cards.app.AppModel;

import Cards.data.request.RequestManager;
import Cards.models.cards.Card;
import Cards.models.cards.CardList;
import Cards.models.HTMLMod;

import Cards.translators.io.CardFile;

import Cards.translators.io.HTMLTranslator;
import Cards.translators.io.ViewIO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import static Cards.models.CardLogger.logg;
import static Cards.models.settings.CardSettings.add_file;

public class CardViewController {

    public String body;
    @FXML
    VBox sidebar;
    @FXML
    HTMLEditor editor;
    @FXML
    Button titlebutton;
    Stage stage;
    int current = 0;
    CardFile openCard;
    CardList cardList;
    HTMLMod htmlMod;

    private static final int buttonWidth = 100000000;

    public CardViewController() {

    }

    public CardViewController(CardFile x) {
        logg.entering(this.getClass().getName(), "CardViewController()");

        logg.exiting(this.getClass().getName(), "CardViewController()");
    }

    @FXML
    public void initialize() {
        if (AppModel.activeFile != null) {
            openFile(AppModel.activeFile);
            AppModel.activeFile = null;
        } else {
            this.cardList = new CardList("New Card", "Description", new ArrayList<>());
        }
        String name = this.cardList.getName();
        this.titlebutton.setText(name);

    }

    public void setTags() {

    }

    public CardFile askSavePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        // TODO Add HTML Filter
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        return new CardFile(fileChooser.showSaveDialog(stage));

    }

    public CardFile askFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load CardFile");
        // TODO Add HTML Filter
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        return new CardFile(fileChooser.showOpenDialog(stage));

    }

    /**
     * Create New Card
     */
    public void createNew() {
        TextInputDialog inputDialog = new TextInputDialog();
        inputDialog.setTitle("Title");

        inputDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        inputDialog.showAndWait();

        String title = inputDialog.getEditor().getText();
        Button button = new Button();
        button.setText(title);
        button.setMaxWidth(this.buttonWidth);

        this.cardList.getCards().add((new Card(title, "", null)));
        int index = this.cardList.cards.size() - 1;
        button.setOnAction(e -> switch_card(index));
        MenuItem menu = new MenuItem("Edit");
        menu.setOnAction(event -> {
            button.fire();
            inputDialog.getEditor().setText(cardList.getCard(index).getName());
            inputDialog.showAndWait();
            String input = inputDialog.getEditor().getText();
            cardList.getCard((index)).setName(input);
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
     * @param _cardFile
     */
    public void openFile(CardFile _cardFile) {
        this.current = 0;
        this.openCard = _cardFile;
        this.removeOldCards();
        add_file(this.openCard);
        HTMLTranslator htmlTranslator = new HTMLTranslator(openCard);
        this.cardList = htmlTranslator.get_card_list();

        if (!this.cardList.cards.isEmpty()) {
            this.updateEditor(this.cardList.cards.get(0).getBody());
        }
        // Loading cards to editor
        int counter = 0;

        for (Card card : this.cardList.cards) {

            Button button = new Button();
            String name = card.getName();
            button.setText(name);
            button.setMaxWidth(buttonWidth);
            int finalCounter = counter;
            button.setOnAction(e -> this.switch_card(finalCounter));
            counter++;
            this.sidebar.getChildren().add(button);
        }
        logg.exiting("CardViewController", "openFile");
    }

    public void openFile() {
        openFile(askFilePath());
    }

    /**
     * Changes content of HTML Editor
     *
     * @param _input
     */
    public void updateEditor(String _input) {
        this.editor.setHtmlText(_input);
        System.out.println(_input);
    }

    /**
     * Switch to card
     *
     * @param _index card being switched to
     */
    public void switch_card(int _index) {
        logg.entering("CardViewController", "switch_card");
        saveCard();
        this.current = _index;
        updateEditor(this.cardList.cards.get(_index).getBody());
        logg.exiting("CardViewController", "switch_card");
    }

    /**
     * calls Dialog to change CardList Information
     *
     * @deprecated Incomplete
     * TODO Finish Dialog
     */
    public void changeDetails() {
        GridPane grid = new GridPane();
        TextField title = new TextField();
        if (this.cardList.getName() != null)
            title.setText(cardList.getName());
        TextArea desc = new TextArea();
        if (this.cardList.getBody() != null)
            desc.setText(this.cardList.getBody());
        grid.add(title, 2, 1);
        grid.add(desc, 1, 2);

        Dialog dia = new Dialog();
        dia.getDialogPane().setContent(grid);
        dia.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dia.showAndWait();
        cardList.setName(title.getText());
        cardList.setBody(desc.getText());
        titlebutton.setText(cardList.getName());
    }

    public void go_to_main_menu() throws IOException {

    }

    public void showHTML() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HTML");
        alert.setContentText(editor.getHtmlText());
        alert.show();
    }

    public void com_save() {
        if (this.openCard == null) {
            this.openCard = this.askSavePath();
        }
        if (this.htmlMod == null)
            this.htmlMod = new HTMLMod(this.openCard.getPath().toString());
        saveCard();
        this.htmlMod.save(this.openCard, this.cardList);

    }

    public void com_load() {
        openFile();
    }

    private void removeOldCards() {
        if (this.sidebar.getChildren().size() > 2) {
            this.sidebar.getChildren().remove(2, this.sidebar.getChildren().size());
        }
    }

//=================  GETTERS ===============

    /**
     * Creates an event.
     *
     * @return
     */
    public String get_selection() {
            /*
              JavaScript loaded as a string.
                TODO Make Static (or Volatile)
             */
        String script = "(function insertAround(){\n" +
                // Finds all span elements in the document.
                "    var spec = document.getElementsByTagName(\"span\");\n" +
                "    var size = spec.length;\n" +
                "    var save;\n" +
                //Loops through the array of span tags, removing old tags
                "    for(var i = 0; i < size; i++){\n" +
                "        if(spec.item(i).getAttribute(\"type\") == \"edit\"){\n" +
                //          Copies content of edit tag.
                "           save = spec.item(i).innerHTML;\n" +
                "            var save2 = spec.item(i);\n" +
                "            save2.insertAdjacentHTML(\"beforebegin\", save);\n" +
                "            save2.parentElement.removeChild(save2);\n" +
                "        }\n" +
                "    }\n" +
                "    var range = window.getSelection().getRangeAt(0);\n" +
                "    var x = document.createElement(\"span\");\n" +
                "    x.setAttribute(\"type\", \"edit\");\n" +
                "    range.surroundContents(x);\n" +
                "})()";
        WebView webView = (WebView) this.editor.lookup("WebView");

        if (null != webView) {
            webView.getEngine().executeScript(script);
            // For Debugging TODO Remove Out
            logg.info(webView.getEngine().getDocument().getChildNodes().toString());
        }

        // TODO Add Caller to Event View and edit event code to the card.

        Parent parent = AppModel.changeView(ViewIO.View.EVENT);
        FXMLLoader fxmlLoader = (FXMLLoader) parent.getUserData();
        EventCreationView cr = fxmlLoader.getController();
        Scene eventEditor = new Scene(Objects.requireNonNull(parent));

        AppModel.newWindowHold(eventEditor);

        System.out.println(cr.toString());
        RequestManager.createEventRequest(cr.createEvent());
        // TODO Add

        return null;
    }

    // TODO Implement or Remove
    @FXML
    void close_menu() {

    }

    void saveCard() {
        String htmlText = this.editor.getHtmlText();
        this.cardList.cards.get(this.current).setBody(htmlText);
    }

    /**
     * @deprecated Not implemented
     * TODO Finsih Checking for Events
     */
    void check_for_events() {

    }

}