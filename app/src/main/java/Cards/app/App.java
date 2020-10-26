/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Cards.app;


import Cards.controllers.CardViewController.CardViewController;
import Cards.models.Card;
import Cards.models.HTMLModel;
import org.w3c.dom.html.HTMLDocument;
import Cards.models.CardLogger;

import static Cards.models.CardLogger.logg;
import static Cards.models.CardLogger.logger_setup;

public class App{


    public static void main(String[] args) {



       logger_setup(); // This sets up  the logger; only needed here
       logg.fine("Logger has been setup");

       HTMLModel x = new HTMLModel();

        // JavaFXController red = new JavaFXController();
        //  red.show();
       //CardViewController card = new CardViewController();
      //  card.show();

        CardViewController card = new CardViewController(x.toString());
        System.out.println(x.toString());

        card.show();
        logg.fine("Exiting Program");
    }
}
