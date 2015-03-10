import java.util.LinkedList;

/* Written by Ryan D'souza
AirBnB Coding Interview

Run Instructions:
    1. javac AirBnBSolution.java
    2. java AirBnBSolution

# Test Input:
# John,Smith,john.smith@gmail.com,Los Angeles,1
# Jane,Roberts,janer@msn.com,"San Francisco, CA","2, 3"
# "Alexandra ""Alex""",Menendez,alex.menendez@gmail.com,Miami,4

# Parsed Output
# John|Smith|john.smith@gmail.com|Los Angeles|1
# List<Jane|Roberts|janer@msn.com|San Francisco, CA|2, 3>
# List<Alexandra "Alex"|Menendez|alex.menendez@gmail.com|Miami|4>
 */

public class AirBnBSolution {

    /** For testing */
    public static void main(String[] args) {

        //Given input
        final String firstPerson = "John,Smith,john.smith@gmail.com,Los Angeles,1";
        final String secondPerson = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",\"2, 3\"";
        final String thirdPerson = "Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,4";

        final LinkedList<String> data = new LinkedList<String>();
        data.add(firstPerson);
        data.add(secondPerson);
        data.add(thirdPerson);

        for(String person : data) {
            System.out.println(parseData(person));
            }
    }
    
    /** Returns data in a parsed format */
    public static String parseData(final String input) {

        //For holding the results (most efficient data structure for string addition)
        final StringBuilder result = new StringBuilder("");

        //Default format
        if(input.contains("\"")) {
            result.append("List<");
        }

        //Go through all the letters
        for(int i = 0; i < input.length(); i++) {

            //Double quotes (thirdPerson example)
            if(i + 2 < input.length() && input.substring(i, i + 2).equals("\"\"")) {

                //Go until we reach the end of the double quotes (indicated by triple quote)
                for(int y = i + 1; y < input.length() - 3; y++) {
                    if(input.substring(y, y + 3).equals("\"\"\"")) {
                        result.append(input.charAt(y));
                        openQuotes = false;
                        i = y + 3;
                        y = input.length();
                    }

                    else {
                        if(input.charAt(y) == ',') {
                            result.append("|");
                        }
                        else {
                            result.append(input.charAt(y));
                        }
                    }
                }
            }
            if(input.charAt(i) == '\"') {
                for(int y = i + 1; y < input.length(); y++) {
                    if(input.charAt(y) == '\"') {
                        openQuotes = true;
                        i = y + 1;
                        y = input.length();
                    }
                    else {
                        result.append(input.charAt(y));
                    }
                }
                result.append("|");
            }

            else if(input.charAt(i) == ',') {
                result.append("|");
            }
            else {
                if(input.charAt(i) != '\"') {
                    result.append(input.charAt(i));
                }
            }

        }

        if(input.contains("\"")) {
            result.append('>');
        }

        return result.toString();
    }
}
