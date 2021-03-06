import java.util.Arrays;

public class Big_Integer { 
  /** Class to hold large integers in arrays 
    * Performs some computations 
    * Note: Project specifications require data to be held in int arrays
    * This was built as a sample for my friend in AP CS 
    */
  private static final int SIZE = 40;
  
  private final int[] digits;
  private final boolean isNegative;
  
  /** Default constructor */
  public Big_Integer() { 
    this.digits = new int[SIZE];
    this.isNegative = false;
  }
  
  /** Constructor with number as a String */
  public Big_Integer(final String number) { 
    this.digits = parse(number);
    this.isNegative = false;
  }
  
    
  /** Constructor with number as a String */
  public Big_Integer(final String number, final boolean isNegative) { 
    this.digits = parse(number);
    this.isNegative = isNegative;
  }
  
  /** Private constructor, used in add/subtract methods */
  private Big_Integer(final int[] digits) { 
    this.digits = digits;
    this.isNegative = false;
  }
  
  /** Private constructor, used in add/subtract methods */
  private Big_Integer(final int[] digits, final boolean isNegative) { 
    this.digits = digits;
    this.isNegative = isNegative;
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
  
  /** Returns a new integer that represents sum of this int + other int */
  public Big_Integer add(final Big_Integer otherNumber) {
    final int[] otherDigits = otherNumber.getDigits();
    final int[] sumValues = new int[SIZE];
    
    for(int i = otherDigits.length - 1; i >= 0; i--) { 
      final int sum = otherDigits[i] + this.digits[i];
      if(sum > 10) { 
        sumValues[i] = sum % 10;
        if((i - 1) >= 0) { 
          otherDigits[i] += i;
        }
      }
      else { 
        sumValues[i] = sum;
      }
    }
    return new Big_Integer(sumValues);
  }
  
  /** Returns true if number is greater than or equal to other number */
  public boolean isGreaterThanOrEqualTo(final Big_Integer otherNum) { 
    if(this.equals(otherNum)) { 
      return true;
    }
    return this.isGreaterThan(otherNum);
  }
  
  /** Returns true if number is less than or equal to other number */
  public boolean isLessThanOrEqualTo(final Big_Integer otherNum) { 
    if(this.equals(otherNum)) { 
      return true;
    }
    return this.isLessThan(otherNum);
  }
  
  /** Return true if number is negative */
  public boolean isNegative() { 
    return this.isNegative;
  }
  
  //this - otherNum
  public Big_Integer subtract(final Big_Integer otherNum) { 
    if(otherNum.isNegative()) { 
      return this.add(otherNum);
    }
    
    final int[] otherDigits = otherNum.getDigits();
    final int[] resultDigits = new int[SIZE];
    int borrow = 0; 
    
    for(int i = 0; i < otherDigits.length - 1; i++) { 
      resultDigits[i] = this.digits[i] - otherDigits[i] - borrow;
      
      if(resultDigits[i] < 10) { 
        borrow = 1;
        resultDigits[i] += 10;
        otherDigits[i+1] -= 1;
      }
      else { 
        borrow = 0;
      } 
    }
    
    
    return new Big_Integer(resultDigits);
  }
  
  public static void main(String[] ryan) { 
    final Big_Integer one = new Big_Integer("123");
    final Big_Integer two = new Big_Integer("124");
    
    System.out.println(one.toString());
    System.out.println(two.toString());
    System.out.println(one.subtract(two).toString());
    /*System.out.println(one.equals(two));
    System.out.println(one.isGreaterThan(two));
    System.out.println(one.isLessThan(two));
    System.out.println(one.add(two).toString());
    System.out.println(one.isGreaterThanOrEqualTo(two));
    System.out.println(one.isLessThanOrEqualTo(two)); */
  }
  
  /** Returns String representation of array */
  @Override
  public String toString() { 
    return Arrays.toString(digits);
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