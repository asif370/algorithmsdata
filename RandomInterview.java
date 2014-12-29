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

    //Find if 2 pairs of points, each pair representing a line on a line segment, infinitely intersect
    public static void infinitelyIntersect() { 

        //Random points
        final double line1x1 = 5, line1x2 = 10;
        final double line1y1 = 3, line1y2 = 6;

        final double line2x1 = 100, line2x2 = 350;
        final double line2y1 = 60, line2y2 = 170;

        //Slope
        final double line1Slope = (line1y2 - line1y1) / (line1x2 - line1x1);
        final double line2Slope = (line2y2 - line2y1) / (line2x2 - line2x1);

        //If they don't have the same slope, they interesect only one
        if(line1Slope != line2Slope) { 
            System.out.println("Interesect once");
        }

        // y = mx + b --> y - mx = b;
        // Calculate b
        final double line1B = line1y1 - (line1Slope * line1x1);
        final double line2B = line2y1 - (line2Slope * line2x1);

        //If they don't have the y-intercept, they're parallel
        if(line1B != line2B) { 
            System.out.println("Parallel - never intersect");
        }
        else { 
            System.out.println("Infinitely intersect");
        }
    }

    public static void main(String[] ryan) { 
        localMinMax();
    }
}
