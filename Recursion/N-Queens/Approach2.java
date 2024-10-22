// Approach2: Array based approach using hashing
import java.util.*;
public class Approach2 {
    // Method to solve N-Queens and return all possible solutions
    public static List<List<String>> solveNQueens(int n) {
        // Initialize the board with '.' to represent empty spaces
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = '.';

        // Result list to store all the valid board configurations
        List<List<String>> res = new ArrayList<>();

        // Arrays to track conflicts for rows, lower diagonals, and upper diagonals
        int[] leftRow = new int[n]; // Tracks whether a row is under attack
        int[] lowerDiagonal = new int[2 * n - 1]; // Tracks attacks along row + col diagonals
        int[] upperDiagonal = new int[2 * n - 1]; // Tracks attacks along row - col diagonals

        // Start solving the problem from the first column
        solve(0, board, res, leftRow, lowerDiagonal, upperDiagonal);
        return res;
    }

    // Recursive method to attempt placing queens column by column
    static void solve(int col, char[][] board, List<List<String>> res, int[] leftRow, int[] lowerDiagonal,
            int[] upperDiagonal) {
        // Base case: If all queens are placed (i.e., col == board size), add the board
        // configuration to the result
        if (col == board.length) {
            res.add(construct(board)); // Add current board configuration to the result
            return;
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < board.length; row++) {
            // Check if placing a queen in the current row and column causes conflicts
            if (leftRow[row] == 0 && lowerDiagonal[row + col] == 0
                    && upperDiagonal[board.length - 1 + col - row] == 0) {
                // Place the queen and mark the row, lower diagonal, and upper diagonal as
                // occupied
                board[row][col] = 'Q';
                leftRow[row] = 1; // Mark the current row
                lowerDiagonal[row + col] = 1; // Mark the lower diagonal (row + col)
                upperDiagonal[board.length - 1 + col - row] = 1; // Mark the upper diagonal (row - col)

                // Recurse to try placing queens in the next column
                solve(col + 1, board, res, leftRow, lowerDiagonal, upperDiagonal);

                // Backtrack: remove the queen and unmark the row and diagonals
                board[row][col] = '.';
                leftRow[row] = 0;
                lowerDiagonal[row + col] = 0;
                upperDiagonal[board.length - 1 + col - row] = 0;
            }
        }
    }

    // Helper method to convert the board configuration into a list of strings
    static List<String> construct(char[][] board) {
        List<String> res = new LinkedList<>();
        for (char[] row : board) {
            res.add(new String(row)); // Convert each row of the board into a string
        }
        return res;
    }

    // Main method to test the solution
    public static void main(String args[]) {
        int N = 4;
        List<List<String>> queens = solveNQueens(N); // Solve for a 4x4 board
        int i = 1;

        // Print all solutions
        for (List<String> solution : queens) {
            System.out.println("Arrangement " + i + ":");
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
            i++;
        }
    }
}

/**
 * Time Complexity: O(N!)
 * ---------------
 * - The time complexity of this approach is O(N!).
 * - For the first column, there are N possible rows where we can place a queen.
 * - For the second column, there are N-1 possible placements (since no two
 * queens can be in the same row or diagonal).
 * - This continues until we have only one valid placement left for the last
 * column.
 * - Hence, the total number of possibilities is N * (N-1) * (N-2) * ... * 1 =
 * O(N!).
 * 
 * Space Complexity: O(S * N^2)
 * -----------------
 * - The space complexity is O(N) for the recursion stack (because at most N
 * recursive calls will be on the stack at once).
 * - We use arrays for conflict checking:
 * - `leftRow[]` uses O(N) space.
 * - `lowerDiagonal[]` and `upperDiagonal[]` each use O(2N - 1) ≈ O(N) space.
 * - The board itself uses O(N^2) space, as it's an NxN grid.
 * - Storing solutions uses O(S * N^2) space, where S is the number of valid
 * solutions.
 * 
 * Overall Space Complexity: 
 * - O(N) for recursion + O(N) for conflict arrays + O(N^2) for the board + O(S
 * * N^2) for solutions.
 * - Thus, the total space complexity is O(S * N^2) where S is the number of
 * solutions.
 */
