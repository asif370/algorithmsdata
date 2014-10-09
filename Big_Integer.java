public class Big_Integer { 
  /** Class to hold large integers in arrays */
  private static final int SIZE = 40;
  
  private final int[] digits;
  
  public Big_Integer() { 
    this.digits = new int[SIZE];
  }
  
  public Big_Integer(final String number) { 
    this.digits = parse(number);
  }
  
  public static int[] parse(final String number) { 
    final int[] theNumber = new int[SIZE];
    final char[] digits = number.toCharArray();
    
    for(int i = digits.length - 1, startAt = SIZE - 1; i >= 0 && startAt >= 0; startAt--, i--) { 
      theNumber[startAt] = ((int) digits[i]) - 48;
    }
    return theNumber;
  }
}