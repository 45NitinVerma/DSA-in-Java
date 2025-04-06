package Array.Diagonal_Sum;
/* 
Problem: Matrix Diagonal Sum
Given a square matrix mat, return the sum of the matrix diagonals.
Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.

## Intuition
The problem requires traversing a square matrix and summing elements along two key diagonals:
- Primary Diagonal: Elements where row index equals column index (i == j)
- Secondary Diagonal: Elements where row and column indices sum to (matrix length - 1)

## Basic Understanding
- A diagonal is a line of elements from one corner of the matrix to the opposite corner
- We need to add elements from both diagonals without double-counting the center element in odd-sized matrices

## Key Observations

### Observation 1: Diagonal Traversal Patterns
- Primary Diagonal: Follows the condition `i == j`
- Secondary Diagonal: Follows the condition `i + j == matrix.length - 1`
* Important to handle overlapping center element in odd-sized matrices

### Observation 2: Matrix Symmetry
- For a square matrix, diagonals always cross at the center
- Center element needs special handling to avoid double-counting
*/

public class Approach2 {
    /* Approach 2: Optimal Approach using 1 loop */
    public static int diagonalSum(int[][] matrix) {
        int sum = 0;
        int n = matrix.length;

        // Traverse the matrix
        for (int i = 0; i < n; i++) {
            // Primary diagonal: where row index equals column index
            sum += matrix[i][i];

            // Secondary diagonal: where row + column equals matrix length - 1
            sum += matrix[i][n - 1 - i];
        }

        // Remove center element if matrix has odd length to avoid double-counting
        if (n % 2 != 0) {
            int center = n / 2;
            sum -= matrix[center][center];
        }

        return sum;
    }

    public static void main(String[] args) {
        // Example matrix
        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };

        // Calculate and print diagonal sum
        int result = diagonalSum(matrix);
        System.out.println("Diagonal Sum of the matrix is: " + result);
    }
}
/* 
## Algorithm Steps
1. Initialize sum to 0
2. Traverse matrix using single loop
3. Add primary diagonal elements (where i == j)
4. Add secondary diagonal elements (where i + j == matrix length - 1)
5. If matrix length is odd, subtract center element to avoid double-counting
6. Return total sum

## Time Complexity Breakdown
- Main Traversal: O(n)
- Space Complexity: O(1) - only using a sum variable

## Advantages
1. Single pass through the matrix
2. Constant extra space
3. Works for matrices of any size
4. Simple and intuitive implementation

## Limitations
1. Only works for square matrices
2. Assumes valid input matrix

## Visualization of Diagonal Traversal
```
For matrix: 
[1  2  3]
[4  5  6]
[7  8  9]

Primary Diagonal (i == j):   1, 5, 9
Secondary Diagonal (i + j == length - 1):  3, 5, 7
Final Sum: 1 + 5 + 9 + 3 + 5 + 7 = 30
```

# Detailed Matrix Diagonal Sum Analysis

## Input Matrix
```
[1  2  3]
[4  5  6]
[7  8  9]
```

## Comprehensive Dry Run Table

| Step         | i | j | Current |          Diagonal Check         |     Action      | Running    | Explanation 
                       | Element |                                 |      Sum        |
|--------------|---|---|---------|---------------------------------|-----------------|------------|---------------------------------------
| 0            | - | - |    -    |                -                |   Initialize    |     0      | Start with sum = 0 
| 1            | 0 | 0 |    1    | Primary Diagonal (i == j)       |     Add 1       |     1      | First element of primary diagonal 
| 2            | 0 | 2 |    3    | Secondary Diagonal (i + j == 2) |     Add 3       |     4      | First element of secondary diagonal 
| 3            | 1 | 1 |    5    | Primary Diagonal (i == j)       |     Add 5       |     9      | Center element of matrix 
| 4            | 1 | 1 |    5    | Secondary Diagonal (i + j == 2) | Already counted |     9      | Center is already in sum, skip 
| 5            | 2 | 0 |    7    | Secondary Diagonal (i + j == 2) |     Add 7       |     16     | Last element of secondary diagonal 
| 6            | 2 | 2 |    9    | Primary Diagonal (i == j)       |     Add 9       |     25     | Last element of primary diagonal 
| Final Result | - | - |    -    |                -                |        -        |     25     | Total diagonal sum 

## Step-by-Step Process Explanation

### Initialization Phase
1. Create a sum variable initialized to 0
2. Determine the matrix size (3x3 in this example)
3. Prepare to traverse the matrix

### Diagonal Traversal
1. **Primary Diagonal Traversal**
   - Identifies elements where row index equals column index
   - Captures: (0,0), (1,1), (2,2)
   - Corresponding elements: 1, 5, 9
   - Mathematical pattern: `matrix[i][i]`

2. **Secondary Diagonal Traversal**
   - Identifies elements where row and column indices sum to (matrix length - 1)
   - Captures: (0,2), (1,1), (2,0)
   - Corresponding elements: 3, 5, 7
   - Mathematical pattern: `matrix[i][matrix.length - 1 - i]`

### Center Element Handling
- In odd-sized matrices, the center element is counted twice
- Requires subtraction of the center element to avoid double-counting
- In our example, (1,1) element (value 5) is the center

### Result Compilation
- Add all primary diagonal elements
- Add all secondary diagonal elements
- Subtract center element if matrix size is odd
- Return final sum

## Detailed Walkthrough of Diagonal Paths

### Primary Diagonal Path
```
(0,0) -> (1,1) -> (2,2)
 1   ->   5   ->   9
```

### Secondary Diagonal Path
```
(0,2) -> (1,1) -> (2,0)
 3   ->   5   ->   7
```

## Algorithm Complexity Analysis

### Time Complexity
- Single pass through matrix
- Constant time operations
- O(n) where n is matrix dimension

### Space Complexity
- Uses only a single variable for sum
- O(1) extra space

## Special Case Scenarios

### 1. Odd-Sized Matrix
- Center element handled by subtraction
- Ensures no double-counting

### 2. Even-Sized Matrix
- No center element subtraction needed
- Simple diagonal addition

## Visualization

```
Diagonal Traversal Visualization:

[1] 2  [3]    Primary Diagonal:   1, 5, 9
 4 [5]  6     Secondary Diagonal: 3, 5, 7
[7]  8 [9]    
```

## Key Takeaways
1. Efficient single-pass algorithm
2. Works for both odd and even-sized matrices
3. Handles center element carefully
4. Minimal extra space required

## Test Cases
1. Odd-sized Matrix: 
   - Input: [[1,2,3],[4,5,6],[7,8,9]]
   - Expected Output: 25

2. Even-sized Matrix:
   - Input: [[1,2],[3,4]]
   - Expected Output: 10

3. Single Element Matrix:
   - Input: [[5]]
   - Expected Output: 5

## Edge Cases Handling
- Empty matrix (return 0)
- Single element matrix
- Odd and even-sized matrices

### Key Points to Remember
1. Use single loop for efficiency
2. Handle center element carefully
3. Validate matrix input before processing
*/