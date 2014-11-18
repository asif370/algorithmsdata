/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Represents a nonempty list in LISP */

public class NonEmptyList extends LispList { 
    private Object head;
    private LispList tail;

    public NonEmptyList(final Object head, final LispList tail) { 
        this.head = head;
        this.tail = tail;
    }

    @Override
    /** Returns true if this list is empty */
    public boolean isEmpty() { 
        return head == null && tail == null;
    }

    @Override
    /** Returns the head of the object */
    public Object head() { 
        return this.head;
    }

    @Override
    /** Returns the next item */
    public LispList tail() { 
        return this.tail;
    }

    @Override
    /** Returns the length of the list */
    public int length() { 
        LispList next = tail;
        int counter = 1;

        while(!(next instanceof EmptyList) && next.tail() != null) { 
            counter++;
            next = next.tail();

            if(next instanceof EmptyList) { 
                return counter;
            }
        }
        return counter;
    }

    @Override
    public String toString() { 
        return this.head.toString() + " " + this.tail.toString();
    }
}
