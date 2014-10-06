import java.util.Stack;

public class Parenthesis { 
  /**
   * Written by Ryan D'souza
   * Algorithms and Data Structures 
   * Uses a stack to determine whether
   * an input's parentheses are properly balanced */
  
  public static void main(String[] ryan) { 
    
    //Get the input
    final String input = StdIn.readString();
    
    //Add the input to the stack
    final Stack<Character> par = new Stack<Character>();
    for(char c : input.toCharArray()) { 
      par.push(c);
    }
    
    //If the string has paranthesis
    boolean properlyBalanced = input.contains("(") && input.contains(")");
    
    //Go through the stack
    while(!par.empty() && properlyBalanced) { 
      
      // ')'
      final char shouldBeClosedPar = par.pop();
      
      if(shouldBeClosedPar == ')') { 
        
        //Get the next char, should be '('
        final char shouldBeOpen = par.pop();
        if(shouldBeOpen != '(') { 
          properlyBalanced = false;
        }
      }
    }
    
    StdOut.println(properlyBalanced);
  }
}