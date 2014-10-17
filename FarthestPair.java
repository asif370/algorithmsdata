import java.util.Random;
import java.util.Arrays;
/** Written by Ryan D'souza
  * Algorithms and Data Structures
  * Farthest pair (in one dimension). 
  * Write a program that, given an array a[] of N double values, 
  * finds a farthest pair : two values whose difference is no smaller 
  * than the the difference of any other pair (in absolute value). 
  * The running time of your program should be linear in the worst case
  * 
  * Two methods of implementation: 
  * 1. Sort array (Nlog(N)), difference of first and last value
  * 2. Iterate through entire array (O(N)), record smallest and largest value, return
  * . */

public class FarthestPair { 
  
  private static final Random generator = new Random();
  
  /** Returns difference of smallest/largest value
    * Sort array (Nlog(N)), difference of first and last value */
  public static double getFarthestPair(final double[] values) {
    Arrays.sort(values);
    return values[values.length - 1] - values[0];
  }
  
  /** Returns difference of smallest/largest value
    * Iterate through entire array (O(N)), record smallest and largest value, return */
  public static double getFarthestPair1(final double[] values) { 
    double smallest = Double.MAX_VALUE;
    double largest = Double.MIN_VALUE;
    for(Double d : values) { 
      if(d < smallest) { 
        smallest = d;
      }
      else if(d > largest) { 
        largest = d;
      }
    }
    return largest - smallest;
  }
  
  public static void main(String[] ryan) { 
    final int SIZE = 500;
    final double[] randomValues = new double[SIZE];
    for(int i = 0; i < randomValues.length; i++) { 
      randomValues[i] = generator.nextInt(SIZE);
    }
    
    System.out.println(Arrays.toString(randomValues));
    
    final long startTime1 = System.nanoTime();
    final double value = getFarthestPair1(randomValues);
    System.out.println("VALUE: " + value + "\tTIME (NS): " + (System.nanoTime() - startTime1));
    
    final long startTime2 = System.nanoTime();
    final double value1 = getFarthestPair1(randomValues);
    System.out.println("VALUE: " + value1 + "\tTIME (NS): " + (System.nanoTime() - startTime2));
  }
}
    
    
    
    
    
    