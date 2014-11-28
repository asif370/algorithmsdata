/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Represents a nonempty list in LISP 
 *
 * See LispList.java for dependency/compilation/run information
 * */

public class NonEmptyList extends LispList { 
    private Object head;
    private LispList tail;

    public NonEmptyList(final Object head, final LispList tail) { 
        this.head = head;
        this.tail = tail;
    }

    /** Empty constructor */
    public NonEmptyList() { 
        this.head = null;
        this.tail = null;
    }

    /** Merges two lists */
    public NonEmptyList merge(final LispList other) { 
        //If it's an empty list, don't do anything
        if(other instanceof EmptyList) { 
            return this;
        }

        //Find the last node of the other list
        LispList otherLast = other.tail();

        //While there is a tail and it's not empty
        while(otherLast.tail() != null && !(otherLast.tail() instanceof EmptyList)) { 
            otherLast = otherLast.tail();
        }

        //Set the last node to this first
        otherLast.setTail(this);

        return this;
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

    /** Set the head */
    public void setHead(final Object head) { 
        this.head = head;
    }

    @Override
    /** Set the tail */
    public void setTail(final LispList tail) { 
        this.tail = tail;
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

    @Override
    public boolean contains(Object other) { 
        if(!(other instanceof NonEmptyList)) { 
            return false;
        }

        final NonEmptyList list = (NonEmptyList) other;

        LispList start = list.tail();

        while(start != null && start.head() != null) { 
            if(start.head().equals(other)) { 
                return true;
            }
        }

        return false;
    }
}
