/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Represents a list in LISP */

public abstract class LispList { 

    //Static variable for constructor (see main method for example
    public static LispList NIL = new EmptyList();

    /** For constructor, see main method for example */
    public static NonEmptyList cons(final Object head) { 
        if(NIL instanceof EmptyList) { 
            NIL = new NonEmptyList(head, new EmptyList());
        }
        else { 
            NIL = new NonEmptyList(head, NIL);
        }
        System.out.println(NIL.toString());
        return (NonEmptyList) NIL;
    }

    public abstract boolean isEmpty();

    public abstract Object head();

    public abstract LispList tail();

    public abstract int length();

    public abstract String toString();

    public static void main(String[] ryan) { 
        NonEmptyList listItem = new NonEmptyList("Ryan", new NonEmptyList("D'souza", new EmptyList()));
        System.out.println(listItem.length());

        System.out.println(listItem.toString());

        listItem = LispList.NIL.cons("Elia").cons("JERE").cons("Algors");

        System.out.println(listItem.length());

        System.out.println(listItem.toString());
    }
}
