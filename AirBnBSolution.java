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
        String thirdPerson = "Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.comdsouzarc@gmail.com,Miami,4";

        System.out.println(parseData(firstPerson));
        System.out.println(parseData(secondPerson));
        System.out.println(parseData(thirdPerson));
    }

    public static String parseData(final String input) {
        final StringBuilder result = new StringBuilder("");

        if(input.contains("\"")) {
            result.append("List<");
        }

        int last = -1;
        boolean isOpenParenthesis = false;
        boolean isOpenDouble = false;
        String temp = "";
        for(int i = 0; i < input.length(); i++) {

            boolean hasAdded = false;

            if(isOpenDouble && i + 3 < input.length() && input.substring(i, i + 3).equals("\"\"\"")) {
                isOpenDouble = false;
                result.append(temp);
                temp = "";
                last = i + 1;
                hasAdded = true;
            }
            else if(!isOpenDouble && i + 2 < input.length() && input.substring(i, i + 2).equals("\"\"")) {
                isOpenDouble = true;
                i++;
            }

            if(isOpenDouble) {
                temp += input.charAt(i);
                hasAdded = true;
            }

            else if(!isOpenDouble && !isOpenParenthesis && input.charAt(i) == '\"') {
                isOpenParenthesis = true;
            }
            else if(!isOpenDouble && isOpenParenthesis && input.charAt(i) == '\"') {
                isOpenParenthesis = false;
                result.append(temp);
                last = i;
                temp = "";
                hasAdded = true;
            }
            else if(isOpenParenthesis) {
                temp += input.charAt(i);
                hasAdded = true;
            }

            else if(!hasAdded && input.charAt(i) == ',' && !isOpenParenthesis && !isOpenDouble) {
                result.append(input.substring(last + 1, i) + "|");
                last = i;
                System.out.println("Here: " + i + "\t" + input.length());
                hasAdded = true;
            }
            else if(!hasAdded && !isOpenParenthesis && !isOpenDouble && input.contains("\"\"\"")) {
                result.append(input.charAt(i));
            }
        }

        result.append(input.substring(last + 1));

        if(input.contains("\"")) {
            result.append('>');
        }

        return result.toString();
    }
}
