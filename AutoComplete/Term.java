import java.util.Comparator;

/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * AutoComplete Assignment
 * http://www.cs.princeton.edu/courses/archive/fall14/cos226/assignments/autocomplete.html */

public class Term implements Comparable<Term> { 
    public final String query;
    public final double weight;

    public Term(final String query, final double weight) { 
        if(query == null) { 
            throw new NullPointerException();
        }
        if(weight < 0) { 
            throw new IllegalArgumentException();
        }

        this.query = query;
        this.weight = weight;
    }

    public int compareTo(final Term other) { 
        return this.compareTo(other);
    }

    //For sorting by lexicographic order
    public static final Comparator<Term> byLexicographic = new Comparator<Term>() { 
        @Override
        public int compare(final Term t1, final Term t2) { 
            return t1.query.compareTo(t2.query);
        }
    };

    //Compare terms in lexicographic order using only first R characters
    public static class byPrefixOrder implements Comparator<Term> {
        private final int r;

        public byPrefixOrder(final int r) { 
            this.r = r;
        }

        @Override
        public int compare(final Term t1, final Term t2) { 
            return t1.query.substring(r).compareTo(t2.query.substring(r));
        }
    }

    public String toString() { 
        return this.query + this.weight;
    }
}
