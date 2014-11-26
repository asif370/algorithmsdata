/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Represents a list in LISP
 *
 * Dependencies: EmptyList.java NonEmptyList.java
 *
 * Run Instructions: javac *.java
 * java LispList
 *
 *
 * OUTPUT: 
 * 2 
 * Constructor 1 Constructor 2
 * 
 * 3
 * Diff. Constructor 3 Diff. Constructor 2 Diff. Constructor 1 */

public abstract class LispList { 

    //Static variable for constructor (see main method for example)
    public static LispList NIL = new EmptyList();

    /** For constructor, see main method for example */
    public static NonEmptyList cons(final Object head) { 
        if(NIL instanceof EmptyList) { 
            NIL = new NonEmptyList(head, new EmptyList());
        }
        else { 
            NIL = new NonEmptyList(head, NIL);
        }
        return (NonEmptyList) NIL;
    }

    public abstract boolean isEmpty();

    public abstract Object head();

    public abstract LispList tail();

    public abstract int length();

    public abstract String toString();

    public static void main(String[] ryan) { 
        NonEmptyList listItem = new NonEmptyList("Constructor 1", new NonEmptyList("Constructor 2", new EmptyList()));
        System.out.println(listItem.length());

        System.out.println(listItem.toString());

        listItem = LispList.NIL.cons("Diff. Constructor 1").cons("Diff. Constructor 2").cons("Diff. Constructor 3");

        System.out.println(listItem.length());

        System.out.println(listItem.toString());
    }

    public abstract boolean contains(Object other);
}
