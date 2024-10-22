package Sudoku_Solver;
public class Approach1 {
    // Solves the Sudoku puzzle by filling the empty cells
    public static void solveSudoku(char[][] board) {
        solve(board); // Start solving the board
    }

    // Helper function that uses backtracking to solve the board
    private static boolean solve(char[][] board) {
        // Iterate over each cell in the 9x9 grid
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // If the cell is empty ('.'), try filling it
                if (board[row][col] == '.') {
                    // Try placing numbers 1 to 9
                    for (char num = '1'; num <= '9'; num++) {
                        // If placing num is valid, place it
                        if (isValid(board, row, col, num)) {
                            board[row][col] = num; // Place the number

                            // Recursively solve for the next empty cell
                            if (solve(board)) {
                                return true; // Solution found, exit recursion
                            } else {
                                // Backtrack: Reset the cell and try another number
                                board[row][col] = '.';
                            }
                        }
                    }
                    // No valid number could be placed, trigger backtracking
                    return false;
                }
            }
        }
        // If all cells are filled correctly, return true
        return true;
    }

    // Checks whether placing 'num' in board[row][col] is valid
    private static boolean isValid(char[][] board, int row, int col, char num) {
        // Check the row, column, and 3x3 sub-grid
        for (int i = 0; i < 9; i++) {
            // Check if 'num' is already in the same row
            if (board[row][i] == num)
                return false;
            // Check if 'num' is already in the same column
            if (board[i][col] == num)
                return false;
            // Check if 'num' is in the 3x3 sub-box
            // 3 * (row / 3) gives the starting row of the sub-box
            // 3 * (col / 3) gives the starting column of the sub-box
            if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == num)
                return false;
        }
        return true; // The number can be placed safely
    }

    public static void main(String[] args) {
        // Example Sudoku board with empty cells represented by '.'
        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        solveSudoku(board); // Solve the Sudoku puzzle

        // Print the solved Sudoku board
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}

/*
 * Time Complexity:
 * - Worst-case: O(9^(n)), where n is the number of empty cells.
 * - For each empty cell, we have 9 possible digits to try, leading to an
 * exponential time complexity.
 * - This is because the algorithm may try all possible numbers in each empty
 * cell in the worst case.
 * 
 * Space Complexity:
 * - O(n*n) in the worst case due to the recursion stack used for backtracking.
 * - The board itself takes O(1) space since it's always a fixed 9x9 grid.
 * - The recursion depth can go up to the number of empty cells, so the space
 * complexity is proportional to the number of empty cells in the grid.
 */