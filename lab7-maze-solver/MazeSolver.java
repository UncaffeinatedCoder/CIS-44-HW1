public class MazeSolver {

    private char[][] maze;

    public MazeSolver(char[][] maze) {
        this.maze = maze;
    }

    /**
     * Prints the current state of the maze.
     */
    public void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------");
    }

    /**
     * Public wrapper method to start the maze-solving process.
     * Finds the starting 'S' position and initiates the recursive search.
     * @return true if a path is found, false otherwise.
     */
    public boolean solve() {
        int startRow = -1;
        int startCol = -1;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == 'S') {
                    startRow = i;
                    startCol = j;
                    break;
                }
            }
        }

        if (startRow != -1) {
            return solve(startRow, startCol);
        }
        return false;
    }

    /**
     * The core recursive method to solve the maze.
     * @param row The current row position.
     * @param col The current column position.
     * @return true if this position leads to a solution, false otherwise.
     */
    private boolean solve(int row, int col) {

        // 1. Base Cases

        // Check if out of bounds
        if (row < 0 || row >= maze.length || col < 0 || col >= maze[0].length) {
            return false;
        }

        // Check if wall or already visited
        if (maze[row][col] == '#' || maze[row][col] == '.') {
            return false;
        }

        // Check if we reached the finish
        if (maze[row][col] == 'F') {
            return true;
        }

        // 2. Mark current cell as part of the path
        maze[row][col] = '.';

        // 3. Recursively try all four directions (North, East, South, West)
        if (solve(row - 1, col)) return true; // North
        if (solve(row, col + 1)) return true; // East
        if (solve(row + 1, col)) return true; // South
        if (solve(row, col - 1)) return true; // West

        // 4. Backtrack — no direction worked, unmark and return false
        maze[row][col] = ' ';
        return false;
    }

    public static void main(String[] args) {

        // Test Case 1: Maze WITH a solution
        char[][] mazeToSolve = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', 'S', ' ', '#', ' ', ' ', '#'},
            {'#', ' ', ' ', '#', ' ', '#', '#'},
            {'#', ' ', '#', ' ', ' ', ' ', '#'},
            {'#', ' ', ' ', ' ', '#', 'F', '#'},
            {'#', '#', '#', '#', '#', '#', '#'}
        };

        MazeSolver solver = new MazeSolver(mazeToSolve);
        System.out.println("Test 1 - Original Maze:");
        solver.printMaze();

        if (solver.solve()) {
            System.out.println("Test 1 - Solution Found:");
        } else {
            System.out.println("Test 1 - No Solution Found:");
        }
        solver.printMaze();


        // Test Case 2: Maze WITHOUT a solution (F is walled off)
        char[][] noSolutionMaze = {
            {'#', '#', '#', '#', '#', '#', '#'},
            {'#', 'S', ' ', ' ', ' ', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#'},
            {'#', ' ', '#', 'F', '#', ' ', '#'},
            {'#', ' ', '#', '#', '#', ' ', '#'},
            {'#', ' ', ' ', ' ', ' ', ' ', '#'},
            {'#', '#', '#', '#', '#', '#', '#'}
        };

        MazeSolver solver2 = new MazeSolver(noSolutionMaze);
        System.out.println("Test 2 - Original Maze:");
        solver2.printMaze();

        if (solver2.solve()) {
            System.out.println("Test 2 - Solution Found:");
        } else {
            System.out.println("Test 2 - No Solution Found:");
        }
        solver2.printMaze();
    }
}


