/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Represents a list in LISP */

public interface LispList { 
    public boolean isEmpty();

    public Object head();

    public LispList tail();
}
