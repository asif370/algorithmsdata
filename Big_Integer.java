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
  
  public int[] parse(final String number) { 
    return null;
  }
  
  
}