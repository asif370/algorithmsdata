import java.util.Arrays;
import java.util.Stack;

public class MineSweeper { 
  
  //Size of board: M X N
  private final int M, N;
  
  //Restrict pieces to only those in enum
  private final PIECES[][] field;
  private final Character[][] fieldNumbers;
  
  public MineSweeper() { 
    final String[] nums = StdIn.readLine().split(" ");
    
    this.M = Integer.parseInt(nums[0]);
    this.N = Integer.parseInt(nums[1]);
    this.field = new PIECES[this.M][this.N];
    this.fieldNumbers = new Character[this.M][this.N];
    
    for(int i = 0; i < field.length; i++) { 
      final String input = StdIn.readLine();
      
      for(int y = 0; y < input.length(); y++ ) {
        if(input.charAt(y) == '*') { 
          this.field[i][y] = PIECES.MINE;
        }
        else { 
          this.field[i][y] = PIECES.SAFE;
        }
      }
    }
    
    for(int i = 0; i < field.length; i++) { 
      for(int y = 0; y < field[i].length; y++) { 
        this.fieldNumbers[i][y] = numSpace(i, y);
      }
    }
    
    printArray(fieldNumbers);
  }
  
  private Character numSpace(final int myX, final int myY) { 
    int mines = 0;
    
    if(field[myX][myY] == PIECES.MINE) { 
      System.out.print("HERE");
      return '*';
    }
    
    if(myX > 0) { 
      if(field[myX - 1][myY] == PIECES.MINE) { 
        mines++;
      }
      if(myY > 0) { 
        if(field[myX - 1][myY - 1] == PIECES.MINE) { 
          mines++;
        }
      }
      if(myY < field.length - 1) { 
        if(field[myX - 1][myY + 1] == PIECES.MINE) { 
          mines++;
        }
      }
    }
    if(myX < field.length - 1) { 
      if(field[myX + 1][myY] == PIECES.MINE) { 
        mines++;
      }
      if(myY < field[0].length - 1) { 
        if(field[myX + 1][myY + 1] == PIECES.MINE) { 
          mines++;
        }
      }
      if(myY > 0) { 
        if(field[myX + 1][myY - 1] == PIECES.MINE) { 
          mines++;
        }
      }
    }
    if(myY > 0) { 
      if(field[myX][myY - 1] == PIECES.MINE) { 
        mines++;
      }
    }
    if(myY < field[0].length - 1) { 
      if(field[myX][myY + 1] == PIECES.MINE) { 
        mines++;
      }
    }
    return (char) ('0' + mines);
  }
  
  
  private static void printArray(final Object[][] array) { 
    for(int i = 0; i < array.length; i++) { 
      System.out.println(Arrays.toString(array[i]));
    }
  }
  
  public static void main(String[] ryan) { 
    new MineSweeper();
  }
  
  /** Only two kinds of pieces in MineSweeper */
  private enum PIECES { 
    MINE, SAFE;
  }
}