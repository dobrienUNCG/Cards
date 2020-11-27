package Cards.controllers;
/*
  Last Updated: 11/26/2020
  Controller for Main Menu View

  @author Devin M. O'Brien
 */

import Cards.app.AppModel;

import Cards.models.cards.CardList;
import Cards.models.settings.CardSettings;
import Cards.translators.io.HTMLTranslator;

import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.util.Objects;

import static Cards.models.CardLogger.logg;


import static Cards.translators.io.ViewIO.View.*;

public class MainMenuController {
boolean running = false;
    @FXML
    Button home_button;
    @FXML
    Stage stage;
    @FXML
    GridPane grid;
    @FXML
    SplitPane splitpane;
    AnchorPane current;
    private static final int buttonMax = 255;

    @SuppressWarnings("ConstantConditions")
    public void create_card() {
        AppModel.newWindow(new Scene(AppModel.changeView(CARD)));
    }

    void removeAll(){
        this.grid.getChildren().removeIf(Objects::nonNull);
    }

    /**
     * Sets up cards
     */
    @FXML
    public void initialize() {
        current = (AnchorPane) splitpane.getParent();
        loadPane();

    }

    void loadPane(){

        for (int i = 0; i < CardSettings.getRecentFiles().size(); i++) {
            // Gets Card List

            HTMLTranslator htmlTranslator = new HTMLTranslator(CardSettings.getRecentFiles().get(i));
            CardList cardList = htmlTranslator.get_card_list();

            WebView webView = new WebView();
            CardSettings.getRecentFiles().get(i).set_cardlist(cardList);

            String body = cardList.getBody();

            webView.getEngine().loadContent("<p>" + body + "</p>");
            webView.setEffect(new DropShadow());
            final int index = i;
            webView.setOnMouseClicked(e -> {
                AppModel.activeFile = CardSettings.getRecentFiles().get(index);
                create_card();
            });
            String name = cardList.getName();
            TitledPane titlePane = new TitledPane(name, webView);

            titlePane.getStyleClass().add("title");
            titlePane.setCollapsible(false);
            titlePane.setEffect(new DropShadow());

            grid.add(titlePane, i % 3, i / 3);


        }

        logg.info(String.valueOf(CardSettings.getRecentFiles().size()));

        if (CardSettings.getRecentFiles().size() < 6) {
            logg.info("Not Enough Cards");
            Button addCard = new Button();
            addCard.setText("+");
            addCard.setOnMouseClicked(event -> {
                this.create_card();

            });
            addCard.setMaxWidth(buttonMax);
            addCard.setMaxHeight(buttonMax);
            int i = this.grid.getChildren().size();
            this.grid.add(addCard, i % 3, i / 3);
        }
    }
    @FXML
    void openHome() {

        current.getChildren().setAll(splitpane);
        removeAll();
        loadPane();
    }

    @FXML
    void openCalendar() {
        current.getChildren().setAll(AppModel.changeView(CALENDAR));
    }

    @FXML
    void openSettings() {
        current.getChildren().setAll(AppModel.changeView(SETTINGS));
    }

    @FXML
    void openPersonal() {
        current.getChildren().setAll(AppModel.changeView(PERSONAL));
    }

    @FXML
    void openHelp() {
        current.getChildren().setAll(AppModel.changeView(HELP));
    }
}
