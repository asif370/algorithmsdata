public final class RationalNumber { 
  /** Written by Ryan D'souza
    * Exercise 16: http://algs4.cs.princeton.edu/12oop/ */
  
  private long numerator;
  private long denominator;
 
  public RationalNumber(final long numerator, final long denominator) { 
    this.numerator = numerator;
    this.denominator = denominator;
    simplify();
  }
  
  /** Returns this RationalNumber * other RationalNumber */
  public RationalNumber multiply(final RationalNumber otherNum) { 
    return new RationalNumber(this.numerator * otherNum.getNumerator(), 
                        this.denominator * otherNum.getDenominator());
  }
  
  /** Returns this RationalNumber * another number */
  public RationalNumber multiply(final long number) { 
    return new RationalNumber(this.numerator * number, this.denominator);
  }
  
  /** Returns this RationalNumber + another RationalNumber*/
  public RationalNumber add(final RationalNumber otherNum) { 
    if(this.denominator == otherNum.getDenominator()) { 
      return new RationalNumber(this.numerator + otherNum.getNumerator(), this.denominator);
    }
    
    //Find the LCD
    final long LCD = getLCM(this.denominator, otherNum.getDenominator());
    
    final long newNumerator = (this.numerator * LCD) + (otherNum.getNumerator() * LCD);
    final long newDenominator = this.numerator * LCD;
    
    return new RationalNumber(newNumerator, newDenominator);
  }
  
  /** Returns this RationalNumber - another RationalNumber */
  public RationalNumber subtract(final RationalNumber otherNum) { 
    if(this.denominator == otherNum.getDenominator()) { 
      return new RationalNumber(this.numerator - otherNum.getNumerator(), this.denominator);
    }
    
    final long LCD = getLCM(this.denominator, otherNum.getDenominator());
    
    final long newNumerator = (this.numerator * LCD) - (otherNum.getNumerator() * LCD);
    final long newDenominator = this.denominator * LCD;
    
    return new RationalNumber(newNumerator, newDenominator);
  }
  
  /** Returns this RationalNumber / another RationalNumber */
  public RationalNumber divide(final RationalNumber otherNum) { 
    final long newNumerator = this.numerator * otherNum.getDenominator();
    final long newDenominator = this.denominator * otherNum.getNumerator();
    return new RationalNumber(newNumerator, newDenominator);
  }
  
  /** Simplifies the fraction by dividing both numbers by the GCD */
  public void simplify() { 
    final long GCD = this.getGCD();
    if(GCD == 1 || GCD == 0) { 
      return;
    }
    
    this.numerator /= GCD;
    this.denominator /= GCD;
  }
  
  /** Returns Greatest Common Denominator, used for simplifying */
  public long getGCD() { 
    return getGCD(this.numerator, this.denominator);
  }
  
  /** Returns Greatest Common Denominator, used for simplifying */
  public static long getGCD(long numerator, long denominator) { 
    //In case of negatives
    numerator = Math.abs(numerator);
    denominator = Math.abs(denominator);
    
    if(denominator == 0) { 
      return numerator;
    }
    return getGCD(denominator, numerator % denominator);
  }
  
  /** Returns the Least Common Multiple, used for multiplying fractions */
  public long getLCM() { 
    return getLCM(this.numerator, this.denominator);
  }
  
  /** Returns the Least Common Multiple, used for multiplying fractions */
  public static long getLCM(long numerator, long denominator) { 
    numerator = Math.abs(numerator);
    denominator = Math.abs(denominator);
    return numerator * (denominator / (getLCM(numerator, denominator)));
  }
  
  /** Returns numerator */
  public long getNumerator() { 
    return this.numerator;
  }
  
  /** Returns denominator */
  public long getDenominator() { 
    return this.denominator;
  }
  
  /** Set numerator */
  public void setNumerator(final long num) { 
    this.numerator = num;
    simplify();
  }
  
  /** Sets denominator */
  public void setDenominator(final long num) { 
    this.denominator = num;
    simplify();
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
    
    return (this.numerator == otherNumber.getNumerator()) && 
      (this.denominator == otherNumber.getDenominator());
  }
  
  @Override
  public String toString() { 
    return numerator + "/" + denominator;
  }
  
  public static void main(String[] ryan) { 
    final RationalNumber myNum = new RationalNumber(2, 8);
    myNum.simplify();
    System.out.println(myNum);
  }
}