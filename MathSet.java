import java.util.HashSet;
import java.util.Iterator;

/** 
 * Written by Ryan D'souza
 * Implements the API for MathSet
 * Algorithms and Data Structures */

public class MathSet<Key> {
  
  //Represents the universe --> all possible values
  private final Key[] universe;
  
  //Chosen values
  private final HashSet<Key> values;
  
  /** Constructor */
  public MathSet(final Key[] universe) {
    this.values = new HashSet<Key>();
    this.universe = universe;
  }
  
  /** Set of all keys in the universe that aren't in the global set */
  public MathSet<Key> complement() {
    if(this.values == null) {
      return new MathSet<Key>(this.universe);
    }
    
    final MathSet<Key> complement = new MathSet<Key>(null);
    for(Key key : this.universe) {
      if(!values.contains(key)) {
        complement.add(key);
      }
    }
    
    return complement;
  }
  
  /** Puts any keys from 'a' into the set if they aren't already there */
  public void union(MathSet<Key> a) {
    Iterator<Key> aKeys = a.iterator();
    
    while(aKeys.hasNext()) {
      Key temp = aKeys.next();
      if(!this.values.contains(temp)) {
        this.values.add(temp);
      }
    }
  }
  
  /** Removes any keys from the global set that are not in 'a' */
  public void intersection(MathSet<Key> a) {
    Iterator<Key> existingKeys = this.values.iterator();
    
    while(existingKeys.hasNext()) {
      if(!a.contains(existingKeys.next())) {
        existingKeys.remove();
      }
    }
  }
  
  /** Basic/straightforward Set interface methods */
  public void add(Key key) {
    this.values.add(key);
  }
  
  public Iterator<Key> iterator() {
    return this.values.iterator();
  }
  
  public void delete(Key key) {
    if(this.values.contains(key)) {
      this.values.remove(key);
    }
  }
  
  public boolean contains(Key key) {
    return this.values.contains(key);
  }
  
  public boolean isEmpty() {
    return this.values.size() == 0;
  }
  
  public int size() {
    return this.values.size();
  }
}