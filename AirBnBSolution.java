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
        result.append(input.substring(last + 1));

        if(input.contains("\"")) {
            result.append('>');
        }

        return result.toString();
    }
}
