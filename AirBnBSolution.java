import java.io.*;
import java.util.*;

/*
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
    public static void main(String[] args) {
        String firstPerson = "John,Smith,john.smith@gmail.com,Los Angeles,1";
        String secondPerson = "Jane,Roberts,janer@msn.com,\"San Francisco, CA\",\"2, 3\"";
        String thirdPerson = "Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,4";

        System.out.println(parseData(firstPerson));
        System.out.println(parseData(secondPerson));
        System.out.println(parseData(thirdPerson));
    }

    public static String parseData(final String input) {
        final StringBuilder result = new StringBuilder("");

        if(input.contains("\"")) {
            result.append("List<");
        }

        boolean openQuotes = false;

        for(int i = 0; i < input.length(); i++) {
            if(!openQuotes && i + 2 < input.length() && input.substring(i, i + 2).equals("\"\"")) {
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
            if(!openQuotes && input.charAt(i) == '\"') {
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

            else if(!openQuotes && input.charAt(i) == ',') {
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
