import java.util.Arrays;

/** Percolation
 * isOpen/isConnected id[num] = itself */

public class Percolation {
    private final int N;
    private final SITE[][] board;

    /** Constructor
     * Reads in int N for board size
     * Initializes array to all blocked */
    public Percolation() { 
        this.N = StdIn.readInt();
        this.board = new SITE[N][N];

        for(int i = 0; i < this. board.length; i++) { 
            for(int y = 0; y < this.board[i].length; y++) { 
                this.board[i][y] = SITE.BLOCKED;
            }
        }
    }

    /** Opens location in row i, column j
     * Has to calculate that based on a single array */
    public void open(final int i, final int j) { 
        if (i >= N || j >= N) { 
            throw new IllegalArgumentException();
        }
        board[i][j] = SITE.EMPTY;
    }

    /** Is site open */
    public boolean isOpen(final int i, final int j) { 
        if(i >= N || j >= N) { 
            throw new IllegalArgumentException();
        }
        return board[i][j] == SITE.EMPTY;
    }

    /** Checks neighboring areas and returns true if
     * a site is full */
    private boolean isConnected(final int i, final int j) { 
        if(this.board[i][j] == SITE.FULL) { 
            return true;
        }

        //Check top left diagonal
        if(i > 0 && j > 0) {
            if(this.board[i-1][j-1] == SITE.FULL) { 
                return true;
            }
        }

        //Check directly above
        if(j > 0) { 
            if(this.board[i][j-1] == SITE.FULL) { 
                return true;
            }
        }

        //Check top right diagonal
        if(j > 0 && i < N) { 
            if(this.board[i + 1][j-1] == SITE.FULL) { 
                return true;
            }
        }

        //Check same level left
        if(i > 0) { 
            if(this.board[i - 1][j] == SITE.FULL) { 
                return true;
            }
        }

        //Check same level right
        if(i < N) { 
            if(this.board[i + 1][j] == SITE.FULL) { 
                return true;
            }
        }

        //Check bottom left diagonal
        if(i > 0 && j < N) { 
            if(this.board[i - 1][j + 1] == SITE.FULL) { 
                return true;
            }
        }

        //Check directly below
        if(j < N) { 
            if(this.board[i][j + 1] == SITE.FULL) { 
                return true;
            }
        }

        //Check bottom right diagonal
        if(i < N && j < N) { 
            if(this.board[i + 1][j + 1] == SITE.FULL) { 
                return true;
            }
        }
        return false;
    }

    public static void main(String[] ryan) { 
        final Percolation perc = new Percolation();
        perc.open(0, 2);
        perc.printBoard();
    }

    /** Enum representing site. Can be 1 of three stages
     * 1. Empty: Open, but not connected
     * 2. Full: Open and connected
     * 3. Blocked: Neither open nor connected */
    public enum SITE { 
        EMPTY, FULL, BLOCKED;
    }

    public void printBoard() { 
        for(int i = 0; i < N; i++) { 
            for(int y = 0; y < N; y++) { 
                System.out.print(board[i][y] + "\t");
            }
            System.out.println("");
        }
    }

    public SITE[][] getBoard() { 
        return this.board;
    }
}
