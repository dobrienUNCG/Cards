package Cards.controllers;
/**
 * Last Updated: 10/28/2020
 * Controller for Main Menu View
 *
 * @author Devin M. O'Brien
 */


import Cards.app.AppModel;

import Cards.models.CardList;
import Cards.translators.io.CardFile;
import Cards.translators.io.HTMLTranslator;

import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static Cards.models.CardLogger.logg;

import static Cards.models.settings.CardSettings.recentCards;
import static Cards.translators.io.ViewIO.View.*;

public class MainMenuController {


    @FXML
    Button home_button;
    @FXML
    Stage stage;

    @FXML
    GridPane grid;
    @FXML
    SplitPane splitpane;


    AnchorPane current;

    @FXML
    void openHome(){
        current.getChildren().setAll(splitpane);
    }
    @FXML
    void openCalendar(){
        current.getChildren().setAll(AppModel.changeView(CALENDAR));
    }
    @FXML
    void openSettings(){
        current.getChildren().setAll(AppModel.changeView(SETTINGS));
    }
    @FXML
    void openPersonal(){
        current.getChildren().setAll(AppModel.changeView(PERSONAL));
    }

    @FXML
    void openHelp(){
       current.getChildren().setAll(AppModel.changeView(HELP));
    }

    @SuppressWarnings("ConstantConditions")
    public void create_card() {
        AppModel.newWindow(new Scene(AppModel.changeView(CARD)));
    }

    /**
     * Sets up cards
     */
    @FXML
    public void initialize() {
        current = (AnchorPane) splitpane.getParent();

        for ( int i = 0; i < recentCards.size() && i <= 6; i++ ) {
            // Gets Card List
            HTMLTranslator htmlTranslator = new HTMLTranslator(recentCards.get(i));
            CardList cardList = htmlTranslator.get_card_list();

            WebView webView = new WebView();
            recentCards.get(i).set_cardlist(cardList);

            webView.getEngine().loadContent("<p>" + cardList.getDescription() + "</p>");
            webView.setEffect(new DropShadow());
            final int index = i;
            webView.setOnMouseClicked(e->{
                AppModel.activeFile = recentCards.get(index);
                create_card();
            });
            TitledPane titlePane = new TitledPane(cardList.getTitle(), webView);
            titlePane.getStyleClass().add("title");
            titlePane.setCollapsible(false);
            titlePane.setEffect(new DropShadow());
            grid.add(titlePane, i % 3, i / 3);
        }
        logg.info("" + recentCards.size());

        if ( recentCards.size() < 6 ) {
            logg.info("Not Enough Cards");
            Button addCard = new Button();
            addCard.setText("+");
            addCard.setOnMouseClicked(event -> {
                addCard.setDisable(true);
                create_card();

            });
            addCard.setMaxWidth(255);
            addCard.setMaxHeight(255);
            int i = grid.getChildren().size();
            grid.add(addCard, i % 3, i / 3);
        }

    }
}
