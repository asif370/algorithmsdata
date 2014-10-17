import java.util.Random;

/** Written by Ryan D'souza
  * Algorithms and Data Structures
  * Tests the speed of ThreeSum and ThreeSumFast */


public class SumTester { 
  
  private static final Random generator = new Random();
  
  /** Return array of size SIZE with randomly generated 
    * values between 0 and SIZE*/
  public static int[] getRandomArray(final int SIZE) { 
    final int[] newArray = new int[SIZE];
    
    for(int i = 0; i < newArray.length; i++) { 
      newArray[i] = generator.nextInt(SIZE);
    }
    return newArray;
  }
  
  
  public static void main(String[] ryan) { 
    final int SIZE = 5000;
    
    final int[] array1 = getRandomArray(SIZE);
    final int[] array2 = getRandomArray(SIZE);
    
    final long start1 = System.nanoTime();
    ThreeSum.count(array1);
    System.out.println("Slow time NS: " + (System.nanoTime() - start1));
    
    final long start2 = System.nanoTime();
    ThreeSum.count(array2);
    System.out.println("Fast time NS: " + (System.nanoTime() - start2));
    
  }
}