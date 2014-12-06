import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;

/** Written by Ryan D'souza
 * Algorithms and Data Structures
 * Reads a data set of 1,000 names and numbers from a file
 * Handles lookups by name and reverses lookups by phone number 
 * Uses Comparator and binary search for both lookups */

public class Telephone { 

    //Two lists, each holds people in different orders
    //names = by name, phoneNumbers = by phone number
    private final ArrayList<Person> names, phoneNumbers;

    /** Represents a Person in the phone book */
    private static class Person {
        public final String name;
        public final String phoneNumber;

        /** Constructor */
        public Person(final String name, final String phoneNumber) { 
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        /** Comparator for comparing by first name */
        public static final Comparator<Person> sortByPhone = new Comparator<Person>() { 
            @Override
            public int compare(final Person p1, final Person p2) { 
                return p1.phoneNumber.compareTo(p2.phoneNumber);
            }
        };

        /** Comparator for comparing by last name */
        public static final Comparator<Person> sortByName = new Comparator<Person>() {
            @Override
            public int compare(final Person p1, final Person p2) { 
                return p1.name.compareTo(p2.name);
            }
        };
    }

    /** Constructor with fileName of phone numbers and names */
    public Telephone(final String fileName) { 
        this.names = new ArrayList<Person>();
        this.phoneNumbers = new ArrayList<Person>();
        try { 
            final BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String line = "";

            while((line = reader.readLine()) != null) { 
                final String phoneNumber = line.substring(0, line.indexOf(" ")).replace(" ", "");
                final String name = line.substring(line.indexOf(" ") + 1);
                
                //Save memory by only using pointers
                final Person person = new Person(name, phoneNumber);
                this.names.add(person);
                this.phoneNumbers.add(person);
            }

            //Sort respective arrays
            Collections.sort(this.names, Person.sortByName);
            Collections.sort(this.phoneNumbers, Person.sortByPhone);
        }

        catch(Exception e) { 
            System.out.println("Error\t" + e.toString());
        }
    }

    /** Constructor with array of People */
    public Telephone(final Person[] people) { 
        this.names = new ArrayList<Person>(Arrays.asList(people));
        this.phoneNumbers = new ArrayList<Person>(Arrays.asList(people));
        
        //Sort respective arrays
        Collections.sort(this.names, Person.sortByName);
        Collections.sort(this.phoneNumbers, Person.sortByPhone);
    }

}
