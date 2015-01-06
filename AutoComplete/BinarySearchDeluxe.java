import java.util.Comparator;

/** 
 * Written by Ryan D'souza
 * BinarySearch for Autocomplete
 * http://www.cs.princeton.edu/courses/archive/fall14/cos226/assignments/autocomplete.html */

public class BinarySearchDeluxe { 
    public static <Key> int firstIndexOf(final Key[] array, final Key key, Comparator<Key> comparator) { 

        //Do binary search to find first occurrence of key
        int occurrence = binarySearch(array, key, comparator);

        //If it was not found, return -1
        if(occurrence == -1) { 
            return -1;
        }

        //Now that we have the location at which it occurs, go backwards in the array till we find its first occurrence
        while(key.equals(array[occurrence]) && occurrence >= 0) { 
            occurrence--;
        }

        //Return that first occurrence
        return occurrence;
    }

    public static <Key> int lastIndexOf(final Key[] array, Key key, Comparator<Key> comparator) { 

        //Do binary search to find first occurrence of key
        int occurrence = binarySearch(array, key, comparator);

        //If it was not found, return -1
        if(occurrence == -1) { 
            return -1;
        }

        //Now that we have the location at which it occurs, go forwards in the array till we find its last occurence
        while(key.equals(array[occurrence]) && occurrence < array.length) { 
            occurrence++;
        }

        return occurrence;
    }

    //Binary search to find where key occurs
    private static <Key> int binarySearch(final Key[] array, final Key key, final Comparator<Key> comparator) { 
        int low = 0, high = array.length - 1;
        int occurrence = 0;

        while(low <= high) { 

            final int middle = low + (high - low) / 2;

            //Value of comparing the two
            final int compare = comparator.compare(key, array[middle]);

            //It is before
            if(compare < 0) { 
                high = middle - 1;
            }
            //It is after
            else if(compare > 0) { 
                low = middle + 1;
            }
            else { 
                //This is where we found the value
                return middle;
            }
        }

        //It was not found
        return -1;
    }
}
