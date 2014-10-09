public class Big_Integer { 
  /** Class to hold large integers in arrays 
    * Performs some computations */
  private static final int SIZE = 40;
  
  private final int[] digits;
  
  /** Default constructor */
  public Big_Integer() { 
    this.digits = new int[SIZE];
  }
  
  /** Constructor with number as a String */
  public Big_Integer(final String number) { 
    this.digits = parse(number);
  }
  
  /** Returns the digits */
  public int[] getDigits() { 
    return this.digits;
  }
  
  /** Returns an int[] of the number (passed as a string */
  public static int[] parse(final String number) { 
    final int[] theNumber = new int[SIZE];
    final char[] digits = number.toCharArray();
    
    for(int i = digits.length - 1, startAt = SIZE - 1; i >= 0 && startAt >= 0; startAt--, i--) { 
      theNumber[startAt] = ((int) digits[i]) - 48;
    }
    return theNumber;
  }
  
  /** Returns true if this is less than other number */
  public boolean isLessThan(final Big_Integer otherNumber) { 
    final int[] otherDigits = otherNumber.getDigits();
    
    if(this.equals(otherNumber)) { 
      return true;
    }
    
    for(int i = 0; i < otherDigits.length; i++) { 
      if(this.digits[i] > otherDigits[i]) { 
        return false;
      }
    }
    
    return true;
  }
  
  /** Returns true if this is greater than other number */
  public boolean isGreaterThan(final Big_Integer otherNumber) { 
    if(this.equals(otherNumber)) { 
      return true;
    }
    return !isLessThan(otherNumber);
  }
    
  /** Returns true if both integers are equal */
  @Override
  public boolean equals(final Object other) { 
    if(!(other instanceof Big_Integer)) { 
      return false;
    }
    
    final Big_Integer otherNum = (Big_Integer) other;
    final int[] otherDigits = otherNum.getDigits();
    
    for(int i = 0; i < otherDigits.length; i++) { 
      if(otherDigits[i] != this.digits[i]) { 
        return false;
      }
    }
    return true;
  }
}