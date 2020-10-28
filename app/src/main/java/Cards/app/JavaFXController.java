package Cards.app;


import javafx.scene.Parent;
import javafx.scene.Scene;


import java.util.HashMap;

// https://stackoverflow.com/questions/37200845/how-to-switch-scenes-in-javafx
public class JavaFXController {
   private HashMap<String, Parent> views = new HashMap<>();
   private Scene active;

   JavaFXController(Scene active){
       this.active = active;
   }

   public void add_scene(String name, Parent scene){
       views.put(name, scene);
   }
   public void remove_scene(String name){
       views.remove(name);
   }
   public void activate(String name){
       active.setRoot(views.get(name));
   }

}
