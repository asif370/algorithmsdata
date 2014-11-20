import java.util.Random;
import java.util.Arrays;

/**
 * Written by Ryan D'souza
 * A collection of handwritten sorts */

public class Sort { 

    private static final Random generator = new Random();

    /** Perform selection sort to sort the array
     * O(N^2) performance */
    public static void selectionSort(final int[] array) { 
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
    }

    public static void main(String[] ryan) { 
        final int NUMELEMENTS = 10;

        final int[] array = randomArray(NUMELEMENTS); 

        System.out.println("Original\t" + Arrays.toString(array));

        selectionSort(array);

        System.out.println("\nNEW\t" + Arrays.toString(array));
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
}
