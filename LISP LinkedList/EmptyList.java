/** 
 * Written by Ryan D'souza
 * Algorithms and Data Structures
 * Represents an empty list 
 *
 * See LispList.java for dependency/compilation/run info 
 * */

public class EmptyList extends LispList { 
   
   /** Empty constructor */
   public EmptyList() { 
   }

   @Override
   /** Returns true because it's an empty list */
   public boolean isEmpty() { 
       return true;
   }

   @Override
   /** Throws exception (project specifications) 
    * because there is no head */
   public Object head() { 
       throw new UnsupportedOperationException();
   }

   @Override
   /** Throws exception (project specification) 
    * because there is no tail */
   public LispList tail() { 
       throw new UnsupportedOperationException();
   }

   @Override
   /** Returns the length of the list */
   public int length() { 
       return 0;
   }

   @Override
   /** Returns "" according to specifications */
   public String toString() { 
       return "";
   }
}
