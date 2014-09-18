public final class RationalNumber { 
  /** Written by Ryan D'souza
    * Exercise 16: http://algs4.cs.princeton.edu/12oop/ */
  
  private long numerator;
  private long denominator;
 
  public RationalNumber(final long numerator, final long denominator) { 
    this.numerator = numerator;
    this.denominator = denominator;
  }
  
  @Override
  public boolean equals(final Object other) { 
    if(!(other instanceof RationalNumber)) { 
      return false;
    }
    
    final RationalNumber otherNumber = (RationalNumber) other;
    
    if(otherNumber == this) { 
      return true;
    }
    
    return false;
  }
  
  @Override
  public String toString() { 
    return numerator + "/" + denominator;
  }
}