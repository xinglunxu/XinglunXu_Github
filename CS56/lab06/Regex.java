import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.JOptionPane;

public class Regex {

    public static void main(String[] args){
        while (true) {
	    
            Pattern pattern = Pattern.compile("\\w+@\\w+\\.\\w{2,4}");
	    
	    
            String enteredString = JOptionPane.showInputDialog("Enter input string to search: ");
            Matcher matcher = pattern.matcher(enteredString);

            boolean found = false;
            while (matcher.find()) {
                System.out.printf("I found the text" +
                    " \"%s\" starting at " +
				  "index %d and ending at index %d.%n",
				  matcher.group(),
				  matcher.start(),
				  matcher.end());
                found = true;
            }
            if(!found){
                System.out.printf("No match found.%n");
            }

	    
        }
    }
}