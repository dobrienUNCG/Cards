package Cards.app;
/*
  Updated: 11/25/2020
  Helper class for debugging.

  @author Devin M. O'Brien
 *
 * IDK: Should be removed or changed for final version
 */

import static Cards.models.CardLogger.logg;



public interface DevMode {
    final boolean active = true;
    default void log(String _input){
        logg.info(_input);
    }
    default void warn(String _input){
        logg.warning(_input);
    }

}
