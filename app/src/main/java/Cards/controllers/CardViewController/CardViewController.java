package Cards.controllers.CardViewController;

import Cards.models.HTMLMod;
import Cards.models.HTMLModel;
import Cards.translators.io.CardFile;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.fxml.FXMLLoader;

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
        logg.info("Creating Button");
        sidebar.getChildren().add(new Button("Testing"));
        editor.setHtmlText(body);
        System.out.println(body);
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

        logg.info(x.get_path());
        HTMLModel y = new HTMLModel(x.get_file());
        setBody(y.toString());
        change();

        logg.exiting("CardViewController", "openFile");

    }

    public void change() {
        logg.entering("CardViewController", "change");

        editor.setHtmlText(body);
        System.out.println(body);
        logg.exiting("CardViewController", "change");

    }

    public void saveit() {
        save(editor.getHtmlText());
    }


}
