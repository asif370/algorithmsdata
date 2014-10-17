import java.util.Arrays;
import java.util.Random;

/** Written by Ryan D'souza
  * Finds the two closest pairs to each other and prints the result */

public class ClosestPair { 
  public static final int SIZE = 500;
  public static final Random generator = new Random();
  
  /** Just returns a double[] of random numbers between 0 and SIZE * 3 */
  public static double[] getRandomNumbers() { 
    final double[] results = new double[SIZE];
    for(int i = 0; i < results.length; i++) { 
      results[i] = generator.nextInt(SIZE *SIZE * SIZE);
    }
    return results;
  }
  
  /** Returns two numbers with the smallest difference
    * 1. Sort array Nlog(N)
    * 2. Iterate through to compare/find two successive indices with the smallest value
    * 3. Return the results of those two values */
  public static double closestPair(final double[] values) { 
    Arrays.sort(values);
    double smallestDiff = Double.MAX_VALUE;
    int index1 = 0, index2 = 0;
    
    for(int i = 0; i < values.length - 1; i++) { 
      final double diff = Math.abs(values[i] - values[i + 1]);
      
      if(diff < smallestDiff) { 
        smallestDiff = diff;
        index1 = i;
        index2 = i + 1;
      }
    }
    
    return Math.abs(values[index2] - values[index1]);
  }
  
  public static void main(String[] ryan) { 
    final double[] numbers = getRandomNumbers();
    
    System.out.println(closestPair(numbers));
  }
}
