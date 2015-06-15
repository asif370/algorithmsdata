/** 
 * Written by Ryan D'souza
 * Generates then draws then solves a maze */

public class Maze {
    
    //Maze dimension
    private final int N; 
    
    //Storing maze coordinates ie. is there a wall north of north[i][j]
    private final boolean[][] north;
    private final boolean[][] east;
    private final boolean[][] south;
    private final boolean[][] west;
    private final boolean[][] visited;
    
    private boolean done = false;
    
    /** Default constructor */
    public Maze(int N) {
        this.N = N;
        StdDraw.setXscale(0, N+2);
        StdDraw.setYscale(0, N+2);
        
        //Initialize border cells as already visited
        visited = new boolean[N+2][N+2];
        
        for (int x = 0; x < N+2; x++) {
            visited[x][0] = visited[x][N+1] = true;
        }
        for (int y = 0; y < N+2; y++) {
            visited[0][y] = visited[N+1][y] = true;
        }
        
        
        //All the walls are true
        north = new boolean[N+2][N+2];
        east  = new boolean[N+2][N+2];
        south = new boolean[N+2][N+2];
        west  = new boolean[N+2][N+2];
        for (int x = 0; x < N+2; x++) {
            for (int y = 0; y < N+2; y++) {
                north[x][y] = east[x][y] = south[x][y] = west[x][y] = true;
            }
        }
        
        generate();
    }
    
    /** Generates the maze */
    private void generate(int x, int y) {
        visited[x][y] = true;
        
        //While there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {
            
            //Pick random neighbor
            while (true) {
                
                //Random double between 0 and 1
                double r = Math.random();
                
                //Try this direction (down)
                if (r < 0.25 && !visited[x][y+1]) {
                    north[x][y] = south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                
                //This direction (right)
                else if (r >= 0.25 && r < 0.50 && !visited[x+1][y]) {
                    east[x][y] = west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                
                //Try up
                else if (r >= 0.5 && r < 0.75 && !visited[x][y-1]) {
                    south[x][y] = north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                
                //Left
                else if (r >= 0.75 && r < 1.00 && !visited[x-1][y]) {
                    west[x][y] = east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }
    
    /* Call the recursive generation function */ 
    private void generate() {
        generate(1, 1);
    }
    
    /* Solve the maze using breadth-first search */
    private void solve(int x, int y) {
        
        //Out of bounds
        if (x == 0 || y == 0 || x == N+1 || y == N+1) {
            return;
        }
        
        //If we've already visited 
        if (done || visited[x][y]) {
            return;
        }
        
        //Now we've visited the spot
        visited[x][y] = true;
        
        //Draw it on the screen
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
        
        //Reached the middle --> Maze is solved
        if (x == N/2 && y == N/2) {
            done = true;
        }
        
        //Checks for walls in all directions, if no walls, solves in that direction (recursive)
        if (!north[x][y]) {
            solve(x, y + 1);
        }
        if (!east[x][y])  {
            solve(x + 1, y);
        }
        if (!south[x][y]) {
            solve(x, y - 1);
        }
        if (!west[x][y])  {
            solve(x - 1, y);
        }
        
        //Finished solving
        if (done) {
            return;
        }
        
        //If we hit a dead end
        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show(30);
    }
    
    /** Solves the maze */
    public void solve() {
        
        //We haven't visited anything yet
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                visited[x][y] = false;
            }
        }
        
        //We are just starting so we're not done
        done = false;
        
        //Start from the beginning
        solve(1, 1);
    }
    
    /** Draws the maze */
    public void draw() {
        
        //Start and end
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.filledCircle(N/2.0 + 0.5, N/2.0 + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);
        
        //Maze walls
        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= N; x++) {
            for (int y = 1; y <= N; y++) {
                
                //If there is a wall, draw it
                if (south[x][y]) {
                    StdDraw.line(x, y, x + 1, y);
                }
                if (north[x][y]) {
                    StdDraw.line(x, y + 1, x + 1, y + 1);
                }
                if (west[x][y])  {
                    StdDraw.line(x, y, x, y + 1);
                }
                if (east[x][y])  {
                    StdDraw.line(x + 1, y, x + 1, y + 1);
                }
            }
        }
        StdDraw.show(1000);
    }
    
    //For testing
    public static void main(String[] args) {
        int N = 10; //Integer.parseInt(args[0]);
        Maze maze = new Maze(N);
        StdDraw.show(0);
        maze.draw();
        maze.solve();
    }   
}