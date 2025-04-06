package Array.Rotate_Matrix;

/* 
# Matrix Rotation - 90 Degrees Clockwise
# Problem: You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

# Intuition
- Rotate a square matrix 90 degrees clockwise using extra space
- Systematically map original matrix indices to new positions

## Basic Understanding
Transform a square matrix by rotating its elements 90 degrees clockwise, changing row and column relationships

## Key Observations

### Observation 1: Index Transformation
* New position determined by mapping original indices
* Formula: `rotated[j][n-i-1] = matrix[i][j]`
For a given element at position `(i, j)` in the original matrix:
- In the rotated matrix, its new position will be `(j, n - i - 1)`.

### Example Matrix
Given the matrix:
```
1 2 3
4 5 6
7 8 9
```
The transformed matrix after a 90-degree clockwise rotation will be:
```
7 4 1
8 5 2
9 6 3
```

### Observation 2: Matrix Structure
* Works only for square matrices
* Preserves total number of elements
* Predictable transformation pattern

## Step-by-Step Example
Rotating matrix `{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}`

1. Create new matrix of same size
2. Map each element to new position
3. Return transformed matrix

*/
public class Approach1 {
    /* Approach 1: Brute Force using another matrix */
    static int[][] rotateMatrix(int[][] matrix) {
        int n = matrix.length;
        int[][] rotated = new int[n][n];
        
        // Rotate matrix 90 degrees clockwise
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rotated[j][n - i - 1] = matrix[i][j];
            }
        }
        return rotated;
    }

    public static void main(String[] args) {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        
        // Rotate and display matrix
        int[][] rotated = rotateMatrix(arr);
        
        System.out.println("Original Matrix:");
        printMatrix(arr);
        
        System.out.println("\nRotated Matrix:");
        printMatrix(rotated);
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
# Algorithm
1. Create new matrix of same dimensions
2. Iterate through original matrix
3. Map elements using transformation formula
4. Return new matrix

### Time Complexity Breakdown
1. Matrix Iteration: O(n²)
2. Element Mapping: O(1)
Total: O(n²)

### Space Complexity
1. Auxiliary Space: O(n²)
2. Input Space: O(n²)
Total: O(n²)

# Advantages
1. Simple and intuitive approach
2. Preserves matrix structure
3. Works for square matrices of any size

# Limitations
1. Uses additional space
2. Not in-place rotation
3. Less efficient for large matrices

# Potential Improvements
1. Implement in-place rotation
2. Handle non-square matrices
3. Add input validation

# Test Cases
1. Input: `{{1, 2}, {3, 4}}`
   Expected: `{{3, 1}, {4, 2}}`
2. Input: `{{1}}`
   Expected: `{{1}}`
3. Input: `{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}`
   Expected: `{{7, 4, 1}, {8, 5, 2}, {9, 6, 3}}`

### Edge Cases
1. Single element matrix
2. 2x2 matrix
3. Larger square matrices

Key Observations:
1. Consistent index transformation
2. Predictable rotation pattern
3. Maintains matrix dimensions
*/