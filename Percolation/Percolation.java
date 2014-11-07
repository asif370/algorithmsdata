import java.util.Arrays;

/** Percolation
  * isOpen/isConnected id[num] = itself */

public class Percolation {
  private final int N;
  private final SITE[] board;
  
  /** Constructor
    * Reads in int N for board size
    * Initializes array to all blocked */
  public Percolation() { 
    this.N = StdIn.readInt();
    this.board = new SITE[N * N];
    
    for(int i = 0; i < this.board.length; i++) { 
      this.board[i] = SITE.BLOCKED;
    }
  }
  
  /** Opens location in row i, column j
    * Has to calculate that based on a single array */
  public void open(final int i, final int j) { 
      if (i >= N || j >= N) { 
          throw new IllegalArgumentException();
      }

      board[j * N + i] = SITE.EMPTY;
  }
  
  public static void main(String[] ryan) { 
      final Percolation perc = new Percolation();
      System.out.println(Arrays.toString(perc.getBoard()));
  }
  
  /** Enum representing site. Can be 1 of three stages
    * 1. Empty: Open, but not connected
    * 2. Full: Open and connected
    * 3. Blocked: Neither open nor connected */
  public enum SITE { 
    EMPTY, FULL, BLOCKED;
  }

  public SITE[] getBoard() { 
      return this.board;
  }
}
