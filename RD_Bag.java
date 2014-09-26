import javax.management.relation.Role;
import javax.management.relation.RoleList;
import java.util.Iterator;

public class RD_Bag<RYAN_DSOUZA>  { 
  /** Written by Ryan D'souza
    * Algorithms and Data Structures
    * Custom implementation of Princeton University's Bag class
    * http://algs4.cs.princeton.edu/13stacks/Bag.java.html
    * Uses RoleList as underlying data structure for fun */
  
  private final RoleList dataStructure;
  
  /** Constructor */
  public RD_Bag() { 
    this.dataStructure = new RoleList();
  }
  
  /** Return true if bag is empty */
  public boolean isEmpty() { 
    return this.dataStructure.size() == 0;
  }
  
  /**Return size of bag */
  public int size() { 
    return this.dataStructure.size();
  }
  
  /** Add item to bag */
  public void add(Object object) { 
    this.dataStructure.add(object);
  }
  
  /** Returns the iterator */
  public Iterator iterator() { 
    return new ListIterator();
  }
  
  /** My own iterator */
  private class ListIterator implements Iterator { 
    private int itemCount;
    public ListIterator() { 
      itemCount = 0;
    }
    
    @Override
    public boolean hasNext() { 
      return this.itemCount <= dataStructure.size();
    }
    
    @Override
    public void remove() { 
      //Intentionally do nothing
    }
    
    @Override
    public Object next() { 
      if(hasNext()) { 
        itemCount++;
        return dataStructure.get(itemCount);
      }
      return null;
    }
  }
}