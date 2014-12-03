import java.util.LinkedList;

/** Written by Ryan D'souza
 * Algorithms and Data Structures
 * Reads a data set of 1,000 names and numbers from a file
 * Handles lookups by name and reverses lookups by phone number 
 * Uses Comparator and binary search for both lookups */

public class Telephone { 
    /** Represents a Person in the phone book */
    private static class Person { 
        public final String name;
        public final String phoneNumber;

        public Person(final String name, final String phoneNumber) { 
            this.name = name;
            this.phoneNumber = phoneNumber;
        }
    }
}
