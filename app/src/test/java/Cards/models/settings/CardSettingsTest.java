package Cards.models.settings;

import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

import static Cards.models.settings.CardSettings.settingsFile;
import static org.junit.jupiter.api.Assertions.*;

class CardSettingsTest {

    @Test
   public  void duplicateCheck() {
        ArrayList<String> files = new ArrayList<>();
        String temp;
        try {
            System.out.println("Settings File Path"+ settingsFile.getAbsolutePath());
            Scanner scan = new Scanner(settingsFile.getAbsolutePath());
            temp = scan.nextLine();
            if(temp.contains("#")){
                //noinspection RedundantOperationOnEmptyContainer
                for(String x: files){
                    if(x.contentEquals(temp)){
                        fail("Duplicates Found");
                    }
                }
                files.add(temp);
            }
        }catch ( Exception e ){
            fail("Couldn't open file");
        }
        System.out.println("Passed Test");

    }
}