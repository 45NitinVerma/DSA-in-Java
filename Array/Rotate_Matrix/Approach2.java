package Array.Rotate_Matrix;
/* 
# Problem: Rotate Matrix 90 Degrees Clockwise
# Intution: By observation, we see that the first column of the original matrix is the reverse of the first row of the rotated matrix, so that’s why we transpose the matrix and then reverse each row, and since we are making changes in the matrix itself space complexity gets reduced to O(1).

Therefore, to achieve a 90-degree clockwise rotation, we:
1. Transpose the matrix (convert rows to columns and vice versa).
2. Reverse each row of the transposed matrix.

This approach modifies the matrix in place, reducing space complexity to **O(1)**.

## Basic Understanding
We want to rotate a 2D matrix by 90 degrees clockwise without using extra space. The main steps involve swapping elements for transposing and then rearranging rows.

## Key Observations with Examples

### Observation 1: Transpose Operation
- Swaps elements across the main diagonal
- Transforms rows into columns and vice versa
- Uses nested loops with a smart traversal to avoid duplicate swaps

### Observation 2: Row Reversal
- After transposing, reverse each row
- Achieves 90-degree clockwise rotation
- Uses in-place swapping to minimize space usage

## Step-by-Step Example
Let's rotate the matrix [[1,2,3],[4,5,6],[7,8,9]]

1. Initial Matrix:
   ```
   1 2 3
   4 5 6
   7 8 9
   ```

2. After Transpose:
   ```
   1 4 7
   2 5 8
   3 6 9
   ```

3. After Row Reversal (Final Rotated Matrix):
   ```
   7 4 1
   8 5 2
   9 6 3
   ```

This is the final rotated matrix.
 */
public class Approach2 {
    // Optimal Approach: In-place 90-degree Clockwise Rotation
    static void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // Step 1: Transpose the Matrix
        for (int i = 0; i < n; i++) {
            // Only traverse upper triangular matrix to avoid double swapping
            for (int j = i; j < n; j++) {
                // Swap matrix[i][j] with matrix[j][i]
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        
        // Step 2: Reverse Each Row
        for (int i = 0; i < n; i++) {
            // Reverse row using two-pointer technique
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    // Main method for demonstrating matrix rotation
    public static void main(String[] args) {
        // Sample input matrix
        int[][] arr = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        
        System.out.println("Original Matrix:");
        printMatrix(arr);
        
        // Rotate the matrix
        rotate(arr);
        
        System.out.println("\nRotated Matrix:");
        printMatrix(arr);
    }
    
    // Helper method to print matrix
    static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
/* 
## Algorithm
1. **Transpose the matrix**:
   - Iterate through the upper triangle of the matrix (only for `j >= i`) and swap `matrix[i][j]` with `matrix[j][i]`.
2. **Reverse each row**:
   - For each row in the matrix, reverse its elements.

### Time Complexity Breakdown
1. Transpose Operation: O(n²)
2. Row Reversal: O(n²)
Total: O(n²)

### Space Complexity
1. Auxiliary Space: O(1)
    - In-place modification without extra space
2. Input Space: O(n²)

# Advantages
1. O(1) extra space complexity
2. Simple and intuitive approach
3. Works for square matrices of any size

# Limitations
1. Only works for square matrices
2. Modifies the original matrix

# Potential Improvements
1. Add input validation for matrix
2. Create a method to return new rotated matrix if in-place modification is not desired

EXECUTION FLOW
 * ---------------------------------------------------------------
 * Step | Current State     | Variables | Action              | Explanation
 * ---------------------------------------------------------------
 * 1    | {{1,2,3},{4,5,6},{7,8,9}} | i=0, j=0 | temp=arr[0][0]=1 | Start of transposition
 * ---------------------------------------------------------------
 * ...  | ...             | i=1, j=0 | temp=arr[1][0]=4 | Swap arr[1][0] and arr[0][1]
 * ---------------------------------------------------------------
 * ...  | {{1,4,3},{4,5,6},{7,8,9}} | i=1, j=1 | Continue           |  Transposition continues
 * ---------------------------------------------------------------
 * ...  | {{1,4,7},{2,5,8},{3,6,9}} | i=0, j=0 | temp=arr[0][0]=1 | Start of row reversal
 * ---------------------------------------------------------------
 * ...  | ...             | i=0, j=1 | Swap arr[0][0] and arr[0][2]| Row reversal continues 
 * ---------------------------------------------------------------
 * ...  | {{7,4,1},{8,5,2},{9,6,3}} |           |                   | Final rotated matrix
 * ---------------------------------------------------------------


# Test Cases
1. 3x3 Matrix: [[1,2,3],[4,5,6],[7,8,9]]
2. 2x2 Matrix: [[1,2],[3,4]]
3. Single Element Matrix: [[1]]

# Edge Cases
1. Empty Matrix
2. Null Matrix
3. Large Matrices
*/