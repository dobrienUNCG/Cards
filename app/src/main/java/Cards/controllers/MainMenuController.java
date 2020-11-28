package Cards.controllers;
/*
  Last Updated: 11/26/2020
  Controller for Main Menu View

  @author Devin M. O'Brien
 */

import Cards.models.AppModel;

import Cards.models.cards.CardList;
import Cards.models.settings.CardSettings;
import Cards.translators.io.CardFile;
import Cards.translators.io.HTMLTranslator;

import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
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
    private static final boolean contextMenuOpen = false;

    @SuppressWarnings("ConstantConditions")
    public void create_card() {
        AppModel.newWindow(new Scene(AppModel.changeView(CARD)));
    }

    @FXML
    public void importCard() {
        CardSettings.getRecentFiles().add(askFilePath());
        removeAll();
        loadPane();
    }

    /**
     * Sets up cards
     */
    @FXML
    public void initialize() {
        current = (AnchorPane) splitpane.getParent();
        loadPane();

    }

    public CardFile askFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load CardFile");
        // TODO Add HTML Filter
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        return new CardFile(fileChooser.showOpenDialog(this.stage));

    }

    void removeAll() {
        this.grid.getChildren().removeIf(Objects::nonNull);
    }

    void loadPane() {

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

            String name = cardList.getName();
            TitledPane titlePane = new TitledPane(name, webView);
            titlePane.getStyleClass().add("title");
            titlePane.setCollapsible(false);
            titlePane.setEffect(new DropShadow());
            final ContextMenu contextMenu = new ContextMenu();
            MenuItem remove = new MenuItem("Remove");
            int finalI = i;
            remove.setOnAction(_actionEvent -> {
                grid.getChildren().remove(titlePane);
                CardSettings.getRecentFiles().remove(finalI);
            });
            titlePane.setOnMouseClicked(e -> {
            if(e.getButton() == MouseButton.PRIMARY) {
                AppModel.activeFile = CardSettings.getRecentFiles().get(index);
                this.create_card();
            }
            });
            contextMenu.getItems().add(remove);
            webView.contextMenuEnabledProperty().set(false);
            titlePane.setContextMenu(contextMenu);
            titlePane.setOnContextMenuRequested(_contextMenuEvent -> {
                if (contextMenuOpen) {
                    contextMenu.hide();
                } else {
                    _contextMenuEvent.consume();
                }
            });
            grid.add(titlePane, i % 3, i / 3);
            if (i / 3 > 2 && i % 3 == 0) {
                grid.addRow(i / 3 + 1);
            }

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
            if (i / 3 > 2 && i % 3 == 0) {
                grid.addRow(i / 3 + 1);
            }
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
