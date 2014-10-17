import java.util.Arrays;
import java.util.Random;

/** Written by Ryan D'souza
  * Algorithms and Data Structures
  * Searches a 2X2 array (matrix)
  * to find the smallest value around it */

public class LocalMinimum { 
  
  private static final Random generator = new Random();
  
  public static int smallestValue(final int[][] array) {
    //Sort each of the arrays
    for(int i = 0; i < array.length; i++) { 
      Arrays.sort(array[i]);
      System.out.println(Arrays.toString(array[i]));
    }
    
    int smallestNumber = Integer.MAX_VALUE;
    
    //index[0] has array of all the smallest values from each array
    for(int i = 0; i < array[0].length; i++) { 
      if(array[0][i] < smallestNumber) { 
        smallestNumber = array[0][i];
      }
    }
    return smallestNumber;
  }
  
  /** Return array of size SIZE with randomly generated 
    * values between 0 and Integer.MAX_VALUE*/
  public static int[] getRandomArray(final int SIZE) { 
    final int[] newArray = new int[SIZE];
    
    for(int i = 0; i < newArray.length; i++) { 
      newArray[i] = generator.nextInt(Integer.MAX_VALUE);
    }
    return newArray;
  }
  
  public static void main(String[] ryan) { 
    final int SIZE = 20;
    final int[][] array = new int[SIZE][SIZE];
    
    for(int i = 0; i < array.length; i++) { 
      array[i] = getRandomArray(array.length);
    }
    
    System.out.println(smallestValue(array));
  }
}