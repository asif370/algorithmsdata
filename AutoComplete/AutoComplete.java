/** 
 * Written by Ryan D'souza
 * AutoComplete main class 
 * http://www.cs.princeton.edu/courses/archive/fall14/cos226/assignments/autocomplete.html */

public class AutoComplete { 

    private final Term[] terms;

    //Constructor
    public AutoComplete(final Term[] terms) { 
        this.terms = terms;
    }

    //Return all terms that start with the prefix in descending order of weights
    public Term[] allMatches(final String prefix) { 
        int start = BinarySearchDeluxe.firstIndexOf(terms, prefix, 
Term.byPrefixOrder);
        final int end = BinarySearchDeluxe.firstIndexOf(terms, prefix, Term.byPrefixOrder);

        final Term[] matches = new Term[end - start];

        for(int i = 0; start <= end; i++, start++) { 
            matches[i] = this.terms[start];
        }

        return matches;
    }
}

