import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/** 
 * Written by Ryan D'souza
 * A random bunch of interview question solutions */

public class RandomInterview { 

    public static final Random gen = new Random();

    //Prints the local min/max from array where n +- 1
    public static void localMinMax() { 

        //Specified array. 
        //Local Maxes: 4, 5, 4, 5
        //Local Mins: 2, 3, 3, 4
        final int[] array = {4, 3, 2, 3, 4, 5, 4, 3, 4, 3, 4, 5, 4};
        System.out.println(Arrays.toString(array));

        //For holding the previous value. Initialize to smallest possible
        int previous = Integer.MIN_VALUE;

        //Iterate through all elements
        for(int i = 0; i < array.length; i++) { 

            //A temporary place holder
            final int current = array[i];

            //If we're at the last element
            if(i == array.length - 1) { 

                //And we're smaller than it
                if(previous > current) { 
                    System.out.println("Minimum: " + array[i]);
                }

                //Or greater than it
                else if(previous < current) {
                    System.out.println("Maximum: " + array[i]);
                }
            }

            //Everything up to the last number
            else { 

                //Another temporary variable
                final int next = array[i + 1];

                //If we're still smaller
                if(current < next && current < previous) {
                    System.out.println("Minimum: " + current);
                }

                //If we're greater
                else if(current > next && current > previous) { 
                    System.out.println("Maximum: " + current);
                }
            }

            previous = current;
        }
    }

    public static boolean containsDuplicates() { 

        //Input
        final String word = "abcdefghijklmnopqrstuvqxyzabc";

        //Represent each ASCII Char
        final boolean[] containsLetters = new boolean[128];

        //We only care about first ASCII chars, so if the string is longer, cut it off
        final String matters = word.length() > containsLetters.length ? word.substring(containsLetters.length) : word;

        //Go through all letters
        for(Character c : matters.toCharArray()) { 
            
            //If it's already there, return false;
            if(containsLetters[(int)c]) { 
                return false;
            }
            
            //Else, add it
            else { 
                containsLetters[(int)c] = true;
            }
        }

        return true;
    }

    //Prints the missing numbers from 1 - N in an array
    public static void printMissing() {

        //Input
        final int[] input = new int[10];

        //Fill with random numbers
        for(int i = 0; i < input.length; i++) { 
            input[i] = gen.nextInt(input.length);
        }

        System.out.println(Arrays.toString(input));

        //Like a boolean array but smaller
        final BitSet values = new BitSet(input.length);

        //Initialize those elements to true
        for(int i : input) { 
            values.set(i, true);
        }

        //Essentially, iterate through all the 'false' bits
        for(int i = input.length; i >= 0; i--) {
            i = values.previousClearBit(i);

            //The last value is -1, and we never want to print that
            if(i >= 0) {
                System.out.println(i);
            }
        }
    }

    //Print single missing number from 1-N in an array
    public static void printSingleMissing() { 

        //Input
        final int[] input = new int[10];

        //Fill from 1 - length
        for(int i = 0; i < input.length; i++) { 
            input[i] = i + 1;
        }

        //Make a single number go missing
        input[2] = 0;

        System.out.println("\nNew Array: " + Arrays.toString(input));

        //Way #1: Calculate what the sum should be: sum = n * (n + 1) / 2
        final int totalSum = (input.length * (input.length + 1)) / 2;

        //Calculate the array's sum
        int arraySum = 0;
        for(int i : input) { 
            arraySum += i;
        }

        //Missing number is the difference
        System.out.println("Missing: " + (totalSum - arraySum));

        //Way #2: XOR all the values from 1 - N
        int xorCorrect = 0;
        for(int i = 1; i <= input.length; i++) { 
            xorCorrect ^= i;
        }

        //XOR all values of the array
        int xorArray = 0;
        for(int i = 0; i < input.length; i++) { 
            xorArray = xorArray ^ input[i];
        }

        //Missing is result of XORing the two
        System.out.println("Missing: " + (xorCorrect ^ xorArray));
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
        //localMinMax();
        //infinitelyIntersect();
        //numCombinations();

        //sqrt();

        //printMissing();
        printSingleMissing();
    }
}
