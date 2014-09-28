import java.util.LinkedList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RD_Stack<Ryan> implements Iterable<Ryan> { 
  /** Written by Ryan D'souza
    * Algorithms and Data Structures
    * My own implementation of the Stack data structure
    * Using a LinkedList (fastest for adding and popping */
  
  private final LinkedList<Ryan> underlying;
  
  /** Constructor */
  public RD_Stack() { 
    this.underlying = new LinkedList<Ryan>();
  }
  
  /** Returns if empty */
  public boolean isEmpty() { 
    return this.underlying.size() == 0;
  }
  
  /** Returns size */
  public int size() { 
    return this.underlying.size();
  }
  
  /** For adding an item */
  public void push(Ryan theRyan) { 
    underlying.addFirst(theRyan);
  }
  
  /** For removing an item */
  public Ryan pop() { 
    if(underlying.size() == 0) { 
      throw new NoSuchElementException("No elements to pop from");
    }
    return underlying.removeFirst();
  }
  
  /** For viewing without removing the first item */
  public Ryan peek() { 
    if(underlying.size() == 0) { 
      throw new NoSuchElementException("No elements to peek from");
    }
    return underlying.get(0);
  }
  
  @Override
  public String toString() { 
    final StringBuilder allContents = new StringBuilder("");
    for(Ryan item : underlying) { 
      allContents.append(item.toString());
    }
    
    return allContents.toString();
  }
  
  @Override
  public Iterator<Ryan> iterator() { 
    return new MyIterator();
  }
  
  /** My own iterator */
  private class MyIterator implements Iterator<Ryan> { 
    public MyIterator() { 
    }
    
    @Override
    public boolean hasNext() { 
      return underlying.size() > 0;
    }
    
    /** Intentionally do nothing */
    public void remove() { 
      //Do nothing
    }
    
    @Override
    public Ryan next() {
      if(hasNext()) { 
        return underlying.remove(0);
      }
      else { 
        throw new NoSuchElementException();
      }
    }
  }
}