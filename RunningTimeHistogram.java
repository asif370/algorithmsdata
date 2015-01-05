import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;

/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Takes command-line arguments N and T, does 
 * T trials of quick-sort on an array of N random Double values
 * Plots results on a histogram */

public class RunningTimeHistogram { 

    public static final Random generator = new Random();

    private final long N, T;
    private final HashMap<Long, Integer> runningTimeCounter = new HashMap<Long, Integer>();

    public RunningTimeHistogram(final long N, final long numTrials) { 
        this.N = N;
        this.T = numTrials;

        beginTesting();
    }

    private void beginTesting() { 

        //For each trial
        for(long i = 0; i < T; i++) { 

            //Generate a random array
            final double[] randomArray = randomArray(N);

            final long startTime = System.currentTimeMillis();
            quicksort(randomArray);

            //Quick-sort time
            final long runningTime = System.currentTimeMillis() - startTime;

            //Get the number of instances that time occured
            final Integer occur = runningTimeCounter.get(runningTime);

            //If it never happened before, add it
            if(occur == null || occur == 0) { 
                runningTimeCounter.put(runningTime, 1);
            }

            //Otherwise, just increment it
            else { 
                runningTimeCounter.put(runningTime, occur + 1);
            }
        }

        //Print values
        for(Long l : runningTimeCounter.keySet()) { 
            System.out.println(l + "\t" + runningTimeCounter.get(l));
        }
    }

    public static void quicksort(double[] a) {
        quicksort(a, 0, a.length - 1);
    }

    // quicksort a[left] to a[right]
    public static void quicksort(double[] a, int left, int right) {
        if (right <= left) return;
        int i = partition(a, left, right);
        quicksort(a, left, i-1);
        quicksort(a, i+1, right);
    }

    // partition a[left] to a[right], assumes left < right
    private static int partition(double[] a, int left, int right) {
        int i = left - 1;
        int j = right;
        while (true) {
            while (less(a[++i], a[right]))      // find item on left to swap
                ;                               // a[right] acts as sentinel
            while (less(a[right], a[--j]))      // find item on right to swap
                if (j == left) break;           // don't go out-of-bounds
            if (i >= j) break;                  // check if pointers cross
            exch(a, i, j);                      // swap two elements into place
        }
        exch(a, i, right);                      // swap with partition element
        return i;
    }

    // is x < y ?
    private static boolean less(double x, double y) {
        return (x < y);
    }

    // exchange a[i] and a[j]
    private static void exch(double[] a, int i, int j) {
        double swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void main(String[] ryan) { 
        final long N = 1000000;
        final long T = 100000;

        new RunningTimeHistogram(N, T);
    }

    /** Returns an array of size filled with random double values */
    public double[] randomArray(final long size) { 
        final double[] array = new double[(int)size];

        for(int i = 0; i < array.length; i++) { 
            array[i] = generator.nextDouble() * 100;
        }

        return array;
    }
}
