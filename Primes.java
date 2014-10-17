/** Written by Ryan D'souza
  * Algorithms and Data Structures 
  * Prints true/false if number is a prime */
public class Primes { 
  
  /** Uses Euclid's algorithm to determine if number is prime */
  public static boolean isPrime(final int num) { 
    if(num == 1) { 
      return false;
    }
    for(int i = 2 ;2 * i < num; i++) {
      if(num % i == 0)
        return false;
    }
    return true;
  }
  
  public static void main(String[] args) { 
    while(true) { 
      System.out.println(isPrime(StdIn.readInt()));
    }
  }
}