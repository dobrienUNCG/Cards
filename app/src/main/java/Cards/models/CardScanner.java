package Cards.models;

import Cards.app.DevMode;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static Cards.models.CardLogger.logg;

public class CardScanner implements DevMode{
   
    static ArrayList<Pattern> pattern = new ArrayList<>();
   static {
    	pattern.add(Pattern.compile(""));
    }


    public CardScanner(String _input) {
        log("Created Scanner");
        start(_input);

    }

    public void start(String _input) {
    	// FIXME This is needs to be completed
        Scanner scan = new Scanner(_input);
        Pattern keyword = pattern.get(1);
        Matcher matcher = null;
        String line = null;
        while ( scan.hasNext() ) {
            line = scan.nextLine();
            System.out.println(line);
            matcher = keyword.matcher(line);
            System.out.println(keyword.matcher(line).find());
            if(matcher.find()){
                System.out.println(matcher.group(1));
                System.out.println(matcher.group(3));
            }

        }

    }

}



