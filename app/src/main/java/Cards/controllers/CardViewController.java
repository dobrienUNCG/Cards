/**MIT License

Copyright (c) 2021 Devin M. O'Brien

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
**/
package Cards.controllers;
/*
  Last Updated: 05/04/2021
  * Changed: Added MIT License
  Moving over from CardViewController
  Card View

  @author Devin M. O'Brien
 */

import Cards.models.AppModel;
import Cards.data.request.RequestManager;
import Cards.models.CardEventDifference;
import Cards.models.HTMLMod;
import Cards.models.cards.Card;
import Cards.models.cards.CardEvent;
import Cards.models.cards.CardList;
import Cards.translators.api.TaskEvent;
import Cards.translators.io.CardFile;
import Cards.translators.io.HTMLTranslator;
import Cards.translators.io.ViewIO;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import netscape.javascript.JSObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import static Cards.models.CardLogger.logg;

public class CardViewController {

    int current;
    CardFile openCard;
    CardList cardList;
    JavaScriptConnection javaScriptConnection = new JavaScriptConnection();
    boolean deleted;
    private static final int buttonWidth = 100000000;
    @FXML
    private VBox sidebar;
    @FXML
    private HTMLEditor editor;
    @FXML
    private Button titlebutton;

    public CardViewController() {
        super();

    }

    public CardViewController(CardFile _cardFile) {
        super();
    }

    @FXML
    public void initialize() {
        // Check if a card was passed by AppModel
        System.out.println(this.openCard);
        if (this.openCard != null) {
            this.openFile(this.openCard);

        } else {
            this.cardList = new CardList("New Card", "Description", new ArrayList<>());
        }
        String name = this.cardList.getName();
        this.titlebutton.setText(name);
        WebView web = (WebView) this.editor.lookup("WebView");
        WebEngine engine = web.getEngine();
        engine.getLoadWorker().stateProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (Worker.State.SUCCEEDED == newValue) {
                        JSObject window = (JSObject) engine.executeScript("window");
                        window.setMember("javaScriptConnection", this.javaScriptConnection);
                    }
                }
        );

    }

    public CardFile askSavePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save");
        // TODO Add HTML Filter
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        File file = fileChooser.showSaveDialog(this.sidebar.getScene().getWindow());
        return new CardFile(file);
    }

    public CardFile askFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load CardFile");
        // TODO Add HTML Filter
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        return new CardFile(fileChooser.showOpenDialog(this.sidebar.getScene().getWindow()));

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
        button.setMaxWidth(buttonWidth);
        Card card = new Card(title, "", null);
        this.cardList.getCards().add((card));
        int index = this.cardList.cards.size() - 1;
        button.setOnAction(e -> {
            this.switch_card(this.cardList.getCards().indexOf(card));
        });
        MenuItem menu = new MenuItem("Edit");
        MenuItem delete = new MenuItem("Delete");

        menu.setOnAction(e -> {
            button.fire();
            inputDialog.getEditor().setText(this.cardList.getCard(index).getName());
            inputDialog.showAndWait();
            String input = inputDialog.getEditor().getText();
            this.cardList.getCard((this.cardList.getCards().indexOf(card))).setName(input);
            button.setText(input);

        });
        delete.setOnAction(event -> {
            button.fire();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Delete Confirmation");
            alert.setContentText("Do you want to delete this card?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                this.cardList.getCards().remove(card);

            }
            this.sidebar.getChildren().remove(button);
            this.deleted = true;
        });
        ContextMenu contextMenu = new ContextMenu();
        contextMenu.getItems().add(menu);
        contextMenu.getItems().add(delete);
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
        AppModel.settingsController.addCardFile(this.openCard);
        HTMLTranslator htmlTranslator = new HTMLTranslator(this.openCard);
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

            MenuItem menu = new MenuItem("Edit");
            MenuItem delete = new MenuItem("Delete");
            menu.setOnAction(event -> {
                button.fire();
                TextInputDialog inputDialog = new TextInputDialog();
                inputDialog.setTitle("Title");
                inputDialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
                inputDialog.getEditor().setText(this.cardList.getCard(this.cardList.getCards().indexOf(card)).getName());
                inputDialog.showAndWait();
                String input = inputDialog.getEditor().getText();
                this.cardList.getCard(this.cardList.getCards().indexOf(card)).setName(input);
                button.setText(input);
                this.deleted = true;
            });
            delete.setOnAction(event -> {
                button.fire();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Dialog");
                alert.setHeaderText("Delete Confirmation");
                alert.setContentText("Do you want to delete this card?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    this.cardList.getCards().remove(card);
                    this.sidebar.getChildren().remove(button);

                }
                this.deleted = true;
            });
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.getItems().add(menu);
            contextMenu.getItems().add(delete);
            button.setContextMenu(contextMenu);

            button.setOnAction(e -> this.switch_card(this.cardList.getCards().indexOf(card)));
            counter++;
            this.sidebar.getChildren().add(button);
        }
        logg.exiting("CardViewController", "openFile");
    }

    public void openFile() {
        this.openFile(this.askFilePath());
    }

    /**
     * Changes content of HTML Editor
     *
     * @param _input
     */
    public void updateEditor(String _input) {
        this.editor.setHtmlText(_input);
        logg.info(_input);
    }

    /**
     * Switch to card
     *
     * @param _index card being switched to
     */
    public void switch_card(int _index) {
        logg.entering("CardViewController", "switch_card");
        if (!this.deleted)
            this.saveCard();
        this.deleted = false;
        this.current = _index;
        this.updateEditor(this.cardList.cards.get(_index).getBody());
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
        if (null != this.cardList.getName())
            title.setText(this.cardList.getName());
        TextArea desc = new TextArea();
        if (null != this.cardList.getBody())
            desc.setText(this.cardList.getBody());
        grid.add(title, 1, 1);
        grid.add(desc, 1, 2);

        Dialog dia = new Dialog();
        dia.getDialogPane().setContent(grid);
        dia.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dia.showAndWait();
        this.cardList.setName(title.getText());
        this.cardList.setBody(desc.getText());
        this.titlebutton.setText(this.cardList.getName());
    }

    public void com_save() {
        if (null == this.openCard) {
            this.openCard = this.askSavePath();
        }

        HTMLMod htmlMod = new HTMLMod(this.openCard.getPath().toString());
        this.saveCard();
        htmlMod.save(this.openCard, this.cardList);

    }

    public void com_load() {
        this.openFile();
    }

    public void initData(CardFile _openCard) {
        this.openCard = _openCard;
        openFile(this.openCard);
    }

    private void removeOldCards() {
        if (2 < this.sidebar.getChildren().size()) {
            this.sidebar.getChildren().remove(2, this.sidebar.getChildren().size());
        }
    }

    /**
     * This is can be directly called by the JavaScript. Needs to be a subclass for access to cardlist.
     */
    public class JavaScriptConnection {

        public void getCardEvent(String arg) {

            logg.warning(arg);
            CardEvent ce = CardViewController.this.cardList.getCard(CardViewController.this.current).getEvent(arg);
            Parent parent = AppModel.changeView(ViewIO.View.EVENT);

            FXMLLoader loader = (FXMLLoader) parent.getUserData();
            EventCreationView ecv = loader.getController();

            ecv.initData(ce);
            Scene now = new Scene(parent);
            AppModel.newWindowHold(now);
            CardViewController.this.saveCard();
            CardViewController.this.com_save();
            if (ecv.deleteEvent) {
                CardEvent temp = new CardEvent(ecv.createEvent(), ecv.completed.isSelected());
                CardViewController.this.saveCard();
                HTMLTranslator html = new HTMLTranslator(CardViewController.this.cardList.getCard(CardViewController.this.current));
                html.deleteEvent(CardViewController.this.cardList.getCard(CardViewController.this.current), temp);
                CardViewController.this.saveCard();
                CardViewController.this.com_save();
                return;
            } else {

                TaskEvent newTask = ecv.createEvent();
                CardEvent b = new CardEvent(newTask, ecv.completed.isSelected());
                HTMLTranslator html = new HTMLTranslator(CardViewController.this.cardList.getCards().get(CardViewController.this.current));

                html.editEvent(CardViewController.this.cardList.getCard(CardViewController.this.current), ce, b);
                CardViewController.this.updateEditor(CardViewController.this.cardList.getCard(CardViewController.this.current).getBody());
                CardEventDifference.checkDifference(ce, b);
                ce.setTaskEvent(b.getTaskEvent());

                ce.setComplete(b.isComplete());

            }
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
        this.saveCard();
        this.com_save();
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

        TaskEvent event = cr.createEvent();
        RequestManager.createEventRequest(event);
        this.saveCard();
        HTMLTranslator html = new HTMLTranslator(this.cardList.getCards().get(this.current));
        html.addEventsToCard(this.cardList.getCard(this.current), event, cr.completed.isSelected());
        CardEvent event1 = new CardEvent(event, cr.completed.isSelected());
        this.updateEditor(this.cardList.getCard(this.current).getBody());
        this.cardList.getCard(this.current).add_event(event1);
        return null;

    }

    //=================  SETTERS  ===============
    void saveCard() {
        String htmlText = this.editor.getHtmlText();
        if (this.cardList.getCards().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("There are no cards to save!");
        } else
            this.cardList.cards.get(this.current).setBody(htmlText);
    }

}
