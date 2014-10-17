import java.util.Arrays;

/**
 *  The <tt>ThreeSumFast</tt> class provides static methods for counting
 *  and printing the number of triples in an array of distinct integers that
 *  sum to 0 (ignoring integer overflow).
 *  <p>
 *  This implementation uses sorting and binary search and takes time 
 *  proportional to N^2 log N, where N is the number of integers.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/14analysis">Section 1.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class ThreeSumFast {
  
  // returns true if the sorted array a[] contains any duplicated integers
  private static boolean containsDuplicates(int[] a) {
    for (int i = 1; i < a.length; i++)
      if (a[i] == a[i-1]) return true;
    return false;
  }
  
  /**
   * Prints to standard output the (i, j, k) with i < j < k such that a[i] + a[j] + a[k] == 0.
   * @param a the array of integers
   * @throws IllegalArgumentException if the array contains duplicate integers
   */
  public static void printAll(int[] a) {
    int N = a.length;
    Arrays.sort(a);
    if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
    for (int i = 0; i < N; i++) {
      for (int j = i+1; j < N; j++) {
        int k = Arrays.binarySearch(a, -(a[i] + a[j]));
        if (k > j) StdOut.println(a[i] + " " + a[j] + " " + a[k]);
      }
    }
  } 
  
  /**
   * Returns the number of triples (i, j, k) with i < j < k such that a[i] + a[j] + a[k] == 0.
   * @param a the array of integers
   * @return the number of triples (i, j, k) with i < j < k such that a[i] + a[j] + a[k] == 0
   */
  public static int count(int[] a) {
    int N = a.length;
    Arrays.sort(a);
    if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
    int cnt = 0;
    for (int i = 0; i < N; i++) {
      for (int j = i+1; j < N; j++) {
        int k = Arrays.binarySearch(a, -(a[i] + a[j]));
        if (k > j) cnt++;
      }
    }
    return cnt;
  } 
  
  /**
   * Reads in a sequence of distinct integers from a file, specified as a command-line argument;
   * counts the number of triples sum to exactly zero; prints out the time to perform
   * the computation.
   */
  public static void main(String[] args)  { 
    In in = new In(args[0]);
    int[] a = in.readAllInts();
    int cnt = count(a);
    StdOut.println(cnt);
  } 
} 
