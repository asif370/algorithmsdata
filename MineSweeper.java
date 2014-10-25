import java.util.Arrays;
import java.util.Stack;

public class MineSweeper { 
  
  //Size of board: M X N
  private final int M, N;
  
  //Restrict pieces to only those in enum
  private final PIECES[][] field;
  
  public MineSweeper() { 
    this.M = 4; //StdIn.readInt();
    this.N = 4; //StdIn.readInt();
    this.field = new PIECES[this.M][this.N];
    
    for(int i = 0; i <= this.N - 1; i++) { 
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
    
    printArray();
    
  }
  
  private int numSpace(final int myX, final int myY) { 
    if(field[myX][myY] == PIECES.MINE) { 
      return 0;
    }
    
    int mines = 0;
    
    if(myX > 0) { 
      if(field[myX - 1][myY] == PIECES.MINE) { 
        mines++;
      }
      if(myY > 0) { 
        if(field[myX - 1][myY - 1] == PIECES.MINE) { 
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
    return mines;
  }
  
  
  private void printArray() { 
    for(int i = 0; i < field.length; i++) { 
      System.out.println(Arrays.toString(field[i]));
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