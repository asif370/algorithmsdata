import java.util.Arrays;

/** 
 * Written by Ryan D'souza
 * A random bunch of interview question solutions */

public class RandomInterview { 

    //Prints the local min/max from array where n +- 1
    public static void localMinMax() { 

        //Specified array
        final int[] array = {4, 3, 2, 3, 4, 5, 6, 7, 8, 7, 6, 5, 4, 3, 2};
        System.out.println(Arrays.toString(array));

        //Start iterating through the array
        for(int i = 0; i < array.length - 1; i++) { 

            int localMin = array[i];
            int localMax = array[i];

            if(localMin > array[i + 1]) { 
                for(int y = i + 1; y < array.length; y++) { 
                    if(localMin >= array[y]) { 
                        localMin = array[y];
                    }
                    else { 
                        System.out.println("Local min: " + localMin);
                        y = array.length;
                        break;
                    }
                }
            }

            if(localMax <= array[i + 1]) { 
                for(int y = i + 1; y < array.length; y++) { 
                    if(localMax <= array[y]) { 
                        localMax = array[y];
                    }
                    else { 
                        System.out.println("Local max: " + localMax);
                        y = array.length;
                        break;
                    }
                }
            }
            else { 
                System.out.println("Local Max: " + localMax);
            }
        }
    }

    public static void main(String[] ryan) { 
        localMinMax();
    }
}
