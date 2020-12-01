package Cards.controllers;
/*
  Last Updated: 12/1/2020
  Controller for Main Menu View

  @author Devin M. O'Brien
 */


import Cards.models.AppModel;
import Cards.models.cards.CardList;
import Cards.translators.io.CardFile;
import Cards.translators.io.HTMLTranslator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

import static Cards.models.CardLogger.logg;
import static Cards.translators.io.ViewIO.View.*;

public class MainMenuController {

    boolean running;
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

    public void create_card() {
        AppModel.newWindow(new Scene(AppModel.changeView(CARD)));

    }

    public static void create_card(CardFile _cardFile) {
            Parent parent = AppModel.changeView(CARD);
            FXMLLoader loader = (FXMLLoader) parent.getUserData();
            CardViewController cardViewController = loader.getController();
            Scene scene = new Scene(parent);
            AppModel.newWindow(scene);
            cardViewController.initData(_cardFile);


    }

    @FXML
    public void importCard() {
        AppModel.settingsController.addCardFile(this.askFilePath());
        this.removeAll();
        this.loadPane();
    }

    /**
     * Sets up cards
     */
    @FXML
    public void initialize() {
        this.current = (AnchorPane) this.splitpane.getParent();
        this.loadPane();
    }

    public CardFile askFilePath() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load CardFile");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*"));
        return new CardFile(fileChooser.showOpenDialog(this.stage));
    }

    void removeAll() {
        this.grid.getChildren().removeIf(Objects::nonNull);
    }

    void loadPane() {
        if (!this.running) {
            ArrayList<CardFile> cardFileArrayList = AppModel.settingsController.getCardFiles();
            this.running = true;
            for (int i = 0; i < cardFileArrayList.size() && 6 > i; i++) {
                // Gets Card List
                HTMLTranslator htmlTranslator = new HTMLTranslator(cardFileArrayList.get(i));
                CardList cardList = htmlTranslator.get_card_list();
                WebView webView = new WebView();
                cardFileArrayList.get(i).set_cardlist(cardList);
                String body = cardList.getBody();
                webView.getEngine().loadContent("<p>" + body + "</p>");
                webView.setEffect(new DropShadow());
                int index = i;
                String name = cardList.getName();
                TitledPane titlePane = new TitledPane(name, webView);
                titlePane.getStyleClass().add("title");
                titlePane.setCollapsible(false);
                titlePane.setEffect(new DropShadow());
                ContextMenu contextMenu = new ContextMenu();
                MenuItem remove = new MenuItem("Remove");
                int finalI = i;
                remove.setOnAction(_actionEvent -> {
                    this.grid.getChildren().remove(titlePane);
                    cardFileArrayList.remove(finalI);
                    this.removeAll();
                    this.loadPane();
                });
                titlePane.setOnMouseClicked(e -> {
                    if (MouseButton.PRIMARY == e.getButton()) {
                        CardFile cardFile = cardFileArrayList.get(finalI);
                        AppModel.settingsController.movetoFront(cardFile);
                        this.removeAll();
                        this.loadPane();
                        MainMenuController.create_card(cardFile);
                    }
                });
                contextMenu.getItems().add(remove);
                webView.contextMenuEnabledProperty().set(false);
                titlePane.setContextMenu(contextMenu);
                titlePane.setOnContextMenuRequested(_contextMenuEvent -> {
                    if (contextMenuOpen) {
                        contextMenu.hide();
                    } else {
                    }
                });
                this.grid.add(titlePane, i % 3, i / 3);
                if (2 < i / 3 && 0 == i % 3) {
                    this.grid.addRow(i / 3 + 1);
                }

            }

            logg.info(String.valueOf(cardFileArrayList.size()));

            if (6 > cardFileArrayList.size()) {
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
                if (2 < i / 3 && 0 == i % 3) {
                    this.grid.addRow(i / 3 + 1);
                }
            }
            this.running = false;
        }
    }

    @FXML
    void openHome() {

        this.current.getChildren().setAll(this.splitpane);
        this.removeAll();
        this.loadPane();
    }

    @FXML
    void openCalendar() {
        this.current.getChildren().setAll(AppModel.changeView(CALENDAR));
    }

    @FXML
    void openSettings() {
        this.current.getChildren().setAll(AppModel.changeView(SETTINGS));
    }

    @FXML
    void openPersonal() {
        this.current.getChildren().setAll(AppModel.changeView(PERSONAL));
    }

    @FXML
    void openHelp() {
        this.current.getChildren().setAll(AppModel.changeView(HELP));
    }
}
