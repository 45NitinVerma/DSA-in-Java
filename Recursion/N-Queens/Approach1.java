// Approach1: Standard Approach
import java.util.*;
public class Approach1 {
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];

        // Initialize the board with empty spaces
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        placeQueens(board, 0, result);
        return result;
    }

    private static void placeQueens(char[][] board, int col, List<List<String>> result) {
        // Base case: If all columns are filled, we have a valid solution
        if (col == board.length) {
            result.add(constructSolution(board));
            return;
        }

        // Try placing a queen in each row of the current column
        for (int row = 0; row < board.length; row++) {
            if (isValid(board, row, col)) {
                // Place the queen
                board[row][col] = 'Q';
                // Recurse to the next column
                placeQueens(board, col + 1, result);
                // Backtrack: remove the queen to try other positions
                board[row][col] = '.';
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col) {
        // Check row on left side
        for (int j = 0; j < col; j++) {
            if (board[row][j] == 'Q') {
                return false;
            }
        }

        // Check upper diagonal on left side
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        // Check lower diagonal on left side
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }

    private static List<String> constructSolution(char[][] board) {
        List<String> solution = new ArrayList<>();
        for (char[] row : board) {
            solution.add(new String(row));
        }
        return solution;
    }

    // Main method for testing
    public static void main(String[] args) {
        List<List<String>> result = solveNQueens(4);
        System.out.println(result);
    }
}
/**
 * Time and Space Complexity Analysis:
 * 
 * Time Complexity: O(N!)
 * - The backtracking algorithm explores a tree of possibilities.
 * - In the worst case, for the first column, we have N choices,for the second column, we have N-1 choices, and so on.
 * - This leads to an upper bound of N! configurations.
 * - However, the actual number of explored configurations is less due to the
 * early pruning of invalid setups.
 * 
 * Detailed breakdown:
 * - placeQueens method: called recursively for each valid placement
 * - isValid method: O(N) time for each call
 * - constructSolution: O(N^2) time to build the solution string
 * 
 * Space Complexity: O(N) for recursion + O(S * N^2) for solutions
 * Where S is the number of valid solutions (which grows exponentially with N)
 * 
 * Recursive call stack:
 * - Maximum depth of recursion is N (one call for each column)
 * - Each recursive call uses O(1) extra space
 * - Total space for call stack: O(N)
 * 
 * Storage of solutions:
 * - Each solution requires O(N^2) space (N strings of length N)
 * - Total space for solutions: O(S * N^2)
 * 
 * The board itself: O(N^2) space
 * 
 * Overall space complexity: O(N) + O(S * N^2) + O(N^2) = O(S * N^2)
 * 
 * Note: The number of solutions S grows exponentially with N,but is much
 * smaller than N! for practical values of N.
 */
