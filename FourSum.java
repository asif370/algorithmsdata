import java.util.Arrays;

/** Written by Ryan D'souza
  * Algorithms and Data Structures
  * Finds the number of four number tuples in
  * an array whose sum is 0 */

public class FourSum { 
  public static int count(final int[] array) { 
    Arrays.sort(array);
    final int size = array.length;
    
    int count = 0;
    
    for(int i = 0; i < size; i++) { 
      for(int y = i + 1; y < size; y++) { 
        for(int x = y + 1; x < size; x++) { 
          int k = Arrays.binarySearch(array, -(array[i] + array[y] + array[x]));
          if (k > x) {
            count++;
          }
        }
      }
    }
    return count;
  }
}