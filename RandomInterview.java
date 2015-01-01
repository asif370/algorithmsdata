import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/** 
 * Written by Ryan D'souza
 * A random bunch of interview question solutions */

public class RandomInterview { 

    //Prints the local min/max from array where n +- 1
    public static void localMinMax() { 

        //Specified array. 
        //Local Maxes: 4, 5, 4, 5
        //Local Mins: 2, 3, 3, 4
        final int[] array = {4, 3, 2, 3, 4, 5, 4, 3, 4, 3, 4, 5, 4};
        System.out.println(Arrays.toString(array));

        int min = Integer.MAX_VALUE;

        for(int i = 0; i < array.length - 1; i++) { 

            if(array[i] < min) { 
                min = array[i];

                if(array[i + 1] > min) { 
                    System.out.println(min);
                    min = Integer.MAX_VALUE;
                }
            }

        }
    }

    //Find if 2 pairs of points, each pair representing a line on a line segment, infinitely intersect
    public static void infinitelyIntersect() { 

        //Random points
        final double line1x1 = 5, line1x2 = 10;
        final double line1y1 = 3, line1y2 = 6;

        final double line2x1 = 100, line2x2 = 350;
        final double line2y1 = 60, line2y2 = 170;

        //Slope
        final double line1Slope = (line1y2 - line1y1) / (line1x2 - line1x1);
        final double line2Slope = (line2y2 - line2y1) / (line2x2 - line2x1);

        //If they don't have the same slope, they interesect only one
        if(line1Slope != line2Slope) { 
            System.out.println("Interesect once");
            return;
        }

        // y = mx + b --> y - mx = b;
        // Calculate b
        final double line1B = line1y1 - (line1Slope * line1x1);
        final double line2B = line2y1 - (line2Slope * line2x1);

        //If they don't have the y-intercept, they're parallel
        if(line1B != line2B) { 
            System.out.println("Parallel - never intersect");
        }
        else { 
            System.out.println("Infinitely intersect");
        }
    }

    //Given a string of a number (example '10101' or '9123') and a map of integers to chars (1 => a,… 26 => z)
    //print out how many combination there are. (ie: 111 => aaa OR ak OR ka = 3
    public static void numCombinations() { 

        final String string = "11110101";
        final HashMap<Integer, Character> map = new HashMap<Integer, Character>(26);
        final HashSet<Character> removedChars = new HashSet<Character>();

        //Update Map with all chars in alphabet
        for(int i = 0; i < 26; i++) { 

            //i + 1 = key, i + 97 = ASCII character
            map.put(i + 1, (char) (i + 97));
        }

        //Represents the number of letters that can be pulled
        //ie. in "10101", letters related to only '1' and '10' can be pulled
        int numLetters = 0;

        //Go through the array pulling possible combinations
        for(int i = 0; i < string.length(); i++) { 

            //Iterate through for longer keys (ie. 101, 1010, 101010)
            for(int y = i; y < string.length(); y++) { 

                //the key
                final int key = i == y ? Character.getNumericValue(i) : Integer.parseInt(string.substring(i, y));

                //If it has a value, add it to the set (which discounts duplicates) and 
                //to the overall count
                final Character val = map.get(key);

                if(val != null) { 
                    numLetters++;
                    System.out.println("key: " + key + "\t" + val);
                    removedChars.add(val);
                }
            }
        }

        //Factorial
        int fact = 1;
        for(int i = 1; i <= numLetters; i++) { 
            fact *= i;
        }
        System.out.println("# combos: " + fact);
    }

    //Square root
    public static void sqrt() { 
        final double number = 12;

        double result = number / 2;
        double temp = 0;

        do { 
            temp = result;
            result = (temp + (number / temp)) / 2;
        } while ((temp - result) != 0);

        System.out.println("\tSQRT: " + number + " is: " + result);
    }

    public static void main(String[] ryan) { 
        localMinMax();
        //infinitelyIntersect();
        //numCombinations();

        //sqrt();
    }
}
