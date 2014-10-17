import java.util.Arrays;

/** Written by Ryan D'souza
  * Algorithms and Data Structures
  * Better version of 3-Sum Program that 
  * counts the number of three elements in an array
  * whose sum = 0 in N^2 log (N) time */

import java.util.Arrays;

public class Better3Sum { 
  
  /** Returns number of triples with sum of 0*/
  public static int count(final int[] array) {
    Arrays.sort(array);
    int cnt = 0;
    for (int i = 0; i < array.length; i++) {
      for (int j = i+1; j < array.length; j++) {
        int k = Arrays.binarySearch(array, -(array[i] + array[j]));
        if (k > j) cnt++;
      }
    }
    return cnt;
  } 
} 
