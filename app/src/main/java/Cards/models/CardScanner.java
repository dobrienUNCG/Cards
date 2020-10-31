package Cards.models;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CardScanner {

    String input;
    ArrayList<Pattern> keys = new ArrayList<Pattern>();


    public CardScanner(String input) {
        this.input = input;
         keys.add(Pattern.compile("/[@]/"));
         keys.add(Pattern.compile(".*at.*"));

    }

    public void start() {
        Scanner scan = new Scanner(input);
        while(scan.hasNext()) {

            if ( scan.findInLine(keys.get(1)) != null ) {
                System.out.println("Testing");
            }
            System.out.println(scan.next());
        }
    }



}



