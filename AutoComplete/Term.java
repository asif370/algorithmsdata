/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * AutoComplete Assignment
 * http://www.cs.princeton.edu/courses/archive/fall14/cos226/assignments/autocomplete.html */

public class Term implements Comparable<Term> { 
    public Term(final String query, final double weight) { 
        if(query == null) { 
            throw new NullPointerException();
        }
        if(weight < 0) { 
            throw new IllegalArgumentException();
        }
    }
}
