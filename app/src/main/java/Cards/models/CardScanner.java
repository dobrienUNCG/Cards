package Cards.models;

import Cards.app.DevMode;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CardScanner implements DevMode{
    final String input;
    final ArrayList<Pattern> keys = new ArrayList<Pattern>();


    public CardScanner(String input) {
        log("Created Scanner");
        this.input = input;
         keys.add(Pattern.compile("/[@]/"));
         keys.add(Pattern.compile("(.*)(at)\\s(\\S*)"));


    }

    public void start() {
        Scanner scan = new Scanner(input);
        Pattern keyword = keys.get(1);
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



