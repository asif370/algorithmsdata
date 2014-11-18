/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Represents a list in LISP */

public abstract class LispList { 

    public static LispList emptyList = new EmptyList();
    public static LispList listHead = new EmptyList();
    
    public static LispList cons(final Object head) { 
        //If the head of the list is empty
        if(listHead instanceof EmptyList) {
            //Make a nonemptylist with empty as tail
            listHead = new NonEmptyList(head, emptyList);
       } 
        
        //Else, add a new element to the tail
        else {
            //First, go to last element
            LispList tempHead = listHead;
            while(tempHead.tail() != null) {
                //If last element is empty
                if((tempHead.tail() instanceof EmptyList)) { 
                    //Add the new item to tail
                    tempHead = new NonEmptyList(head,tempHead); 
                    //Then add empty list to tail
                    tempHead = new NonEmptyList(null, new EmptyList());
                }

                //Otherwise, keep on going
                else { 
                    tempHead = tempHead.tail();
                }
            }
        }

        return listHead;
    }



    public abstract boolean isEmpty();

    public abstract Object head();

    public abstract LispList tail();

    public abstract int length();

    public abstract String toString();

    public static void main(String[] ryan) { 
        NonEmptyList listItem = new NonEmptyList("Ryan", new NonEmptyList("D'souza", new EmptyList()));

        System.out.println(listItem.length());
    }
}
