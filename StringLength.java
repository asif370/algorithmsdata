import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * StringLength: Sort an ArrayList of Strings by increasing length
 * Supply a comparator */

public class StringLength implements Comparator<StringLength>, Comparable<StringLength> { 
    public final String value;

    public StringLength(final String value) { 
        this.value = value;
    }

    @Override
    public int compareTo(final StringLength other) { 
        return this.value.length() -  other.value.length();
    }

    @Override
    public int compare(final StringLength first, final StringLength second) { 
        return first.value.length() - second.value.length();
    }

    /** Sorts the list from smallest length to longest length */
    public static void sort(final ArrayList<StringLength> toSort) { 
        Collections.sort(toSort);
    } 

    //For testing
    public static void main(String[] ryan) { 
        final ArrayList<StringLength> values = new ArrayList<StringLength>(ryan.length);
        System.out.println("YO");
        for(String r : ryan) { 
            values.add(new StringLength(r));
        }

        System.out.println("Original");
        for(StringLength v : values) { 
            System.out.print(v.value + "\t");
        }

        sort(values);
        System.out.println("\nNew");
        for(StringLength v : values) { 
            System.out.print(v.value + "\t");
        }
    }
}
