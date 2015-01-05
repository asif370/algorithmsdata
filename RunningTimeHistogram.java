import java.util.Scanner;
import java.util.Random;

/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Takes command-line arguments N and T, does 
 * T trials of quick-sort on an array of N random Double values
 * Plots results on a histogram */

public class RunningTimeHistogram { 

    public static final Random generator = new Random();

    private final long N, T;
    private final long[] times;

    public RunningTimeHistogram(final long N, final long numTrials) { 
        this.N = N;
        this.T = numTrials;
        this.times = new long[numTrials];

        beginTesting();
    }

    private void beginTesting() { 

        //For each trial
        for(int i = 0; i < times.length; i++) { 

            //Generate a random array
            final double[] randomArray = randomArray(N);

            final long startTime = System.currentTimeMillis();
            sort(randomArray);

            //Save the time 
            times[i] = (int) (System.currentTimeMillis() - startTime);
        }
    }


    private double[] numbers;

    public void sort(double[] values) {
        // check for empty or null array
        if (values ==null || values.length==0){
            return;
        }
        this.numbers = values;
        number = values.length;
        quicksort(0, number - 1);
    }

    private void quicksort(int low, int high) {
        int i = low, j = high;
        // Get the pivot element from the middle of the list
        int pivot = numbers[low + (high-low)/2];

        // Divide into two lists
        while (i <= j) {
            // If the current value from the left list is smaller then the pivot
            // element then get the next element from the left list
            while (numbers[i] < pivot) {
                i++;
            }
            // If the current value from the right list is larger then the pivot
            // element then get the next element from the right list
            while (numbers[j] > pivot) {
                j--;
            }

            // If we have found a values in the left list which is larger then
            // the pivot element and if we have found a value in the right list
            // which is smaller then the pivot element then we exchange the
            // values.
            // As we are done we can increase i and j
            if (i <= j) {
                exchange(i, j);
                i++;
                j--;
            }
        }
        // Recursion
        if (low < j)
            quicksort(low, j);
        if (i < high)
            quicksort(i, high);
    }

    private void exchange(int i, int j) {
        double temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }

    public static void main(String[] ryan) { 
        final long N = 1000000;
        final long T = 100000;

        new RunningTimeHistogram(N, T);
    }

    /** Returns an array of size filled with random double values */
    public double[] randomArray(final int size) { 
        final double[] array = new double[size];

        for(int i = 0; i < array.length; i++) { 
            array[i] = generator.nextDouble() * 100;
        }

        return array;
    }
}
