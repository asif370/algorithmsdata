import java.util.Random;
import java.util.Arrays;

/**
 * Written by Ryan D'souza
 * A collection of sorts written by memory */

public class Sort { 

    private static final Random generator = new Random();

    /** Perform selection sort to sort the array
     * O(N^2) performance
     * ~N^2 / 2 compares + N exchanges
     * */
    public static void selectionSort(final int[] array) { 
        final long startTime = System.currentTimeMillis();
        for(int i = 0; i < array.length; i++) { 
            int min = Integer.MAX_VALUE;
            int minLoc = 0;

            for(int y = i; y < array.length; y++) { 
                if(array[y] < min) { 
                    min = array[y];
                    minLoc = y;
                }
            }
            array[minLoc] = array[i];
            array[i] = min;
        }
        System.out.println("SelectionSort MS: " + (System.currentTimeMillis() - startTime));
    }

    /** Perform Insertion sort to sort the array
     * Performance depends on how sorted array is
     * Best case: N^2/4 
     * Worse: N^2/2 */
    public static void insertionSort(final int[] array) { 
        final long startTime = System.currentTimeMillis();
        for(int i = 1; i < array.length; i++) { 
            for(int j = i; j > 0 && array[j] < array[j - 1];  j--) { 
                //Swap the variables
                array[j] ^= array[j - 1];
                array[j-1] ^= array[j];
                array[j] ^= array[j - 1];
            }
        }
        System.out.println("InsertionSort MS: " + (System.currentTimeMillis() - startTime));
    }

    public static void main(String[] ryan) { 
        final int NUMELEMENTS = ryan.length > 0 ? Integer.parseInt(ryan[0]) : 100;

        int[] array = randomArray(NUMELEMENTS); 
        System.out.println("Original\t" + Arrays.toString(array));
        selectionSort(array);
        System.out.println("NEW After Selection Sort\t" + Arrays.toString(array));

        array = randomArray(NUMELEMENTS);
        System.out.println("\nOriginal\t" + Arrays.toString(array));
        insertionSort(array);
        System.out.println("NEW After Insertion Sort\t" + Arrays.toString(array));
    }

    /** Returns an array of size num filled with random elements
     * between 0 and num ^ 3 */
    public static int[] randomArray(final int num) { 
        final int[] array = new int[num];

        for(int i = 0; i < array.length; i++) { 
            array[i] = generator.nextInt((int)Math.pow(num, 3));
        }

        return array;
    }

    /** Fills array with random elements betweeon 0 and 
     * size ^3 */
    public static void randomizeDigits(final int[] array) { 
        for(int i = 0; i < array.length; i++) { 
            array[i] = generator.nextInt((int)Math.pow(array.length, 3));
        }
    }
}
