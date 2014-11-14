import java.util.Arrays;
/** Percolation
  * isOpen/isConnected id[num] = itself */

public class Percolation {
  private final int N;
  private final boolean[][] isConnectedBoard;
  private final QuickUnionUF underlyingBoard;
  
  /** Constructor
    * Reads in int N for board size
    * Initializes array to all blocked */
  public Percolation() { 
    this.N = StdIn.readInt();
    this.isConnectedBoard = new boolean[N][N];
    this.underlyingBoard = new QuickUnionUF(this.N * this.N);
  }
  
  /** Opens location in row i, column j
    * Has to calculate that based on a single array */
  public void open(final int i, final int j) { 
    if (i > N || j > N) { 
      throw new IllegalArgumentException();
    }
    
    final int N = this.N - 1;
    
    //If it's the top row, is automatically full
    if(j == 0) { 
      this.isConnectedBoard[j][i] = true;
    }
    
    if(i > 0) { 
      if(this.isConnectedBoard[j][i - 1]) { 
        this.underlyingBoard.union(i * N + j, i * N + (j - 1));
      }
    }
    
    if(i < N) { 
      if(this.isConnectedBoard[j][i + 1]) { 
        this.underlyingBoard.union(i * N + j, i * N + (j + 1));
      }
    }
    
    if(j > 0) { 
      if(this.isConnectedBoard[j - 1][i]) { 
        this.underlyingBoard.union(i * N + j, (i - 1) * N + j);
      }
    }
    
    if(j < N) { 
      if(this.isConnectedBoard[j + 1][i]) { 
        this.underlyingBoard.union(i * N + j, (i + 1) * N + j);
      }
    }
  }
  
  /** Is site open */
  public boolean isOpen(final int i, final int j) { 
    if(i > N || j > N) { 
      throw new IllegalArgumentException();
    }
    return true; //this.underlyingBoard.union(i, j);
  }
  
  /** Checks neighboring areas and returns true if
    * a site is full */
  private boolean isConnected(final int j, final int i) { 

    return false;
  }
  
  public static void main(String[] ryan) { 
    final Percolation perc = new Percolation();
    
    int counter = 0;
    
    while(true) { 
      StdOut.println("Open: ");
      final int x = StdIn.readInt();
      final int y = StdIn.readInt();
      StdOut.println("Open (" + x + ", " + y + ")");
      perc.open(x, y);
      perc.printBoard();
    }
  }
  
  public void printBoard() { 
    for(int i = 0; i < N; i++) { 
      for(int y = 0; y < N; y++) { 
        System.out.print(this.isConnectedBoard[i][y] + "\t");
      }
      System.out.println("");
    }
  }
  
  public boolean[][] getBoard() { 
    return this.isConnectedBoard;
  }
}
