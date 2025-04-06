package Array.Diagonal_Sum;
/* 
Problem: Matrix Diagonal Sum
Given a square matrix mat, return the sum of the matrix diagonals.
Only include the sum of all the elements on the primary diagonal and all the elements on the secondary diagonal that are not part of the primary diagonal.

## Intuition
This approach uses a brute force method with nested loops to traverse every element of the matrix and identify diagonal elements based on specific conditions.

## Basic Understanding
- Primary Diagonal: Elements where row index equals column index (i == j)
- Secondary Diagonal: Elements where row and column indices sum to (matrix length - 1)
- Goal is to sum these diagonal elements without double-counting the center element

## Key Observations
### Observation 1: Nested Loop Traversal
- Outer loop iterates through rows
- Inner loop iterates through columns
- Allows checking every single matrix element

### Observation 2: Diagonal Element Conditions
- Primary Diagonal: `i == j`
- Secondary Diagonal: `i + j == matrix.length - 1`
- Each element is checked against these conditions
*/

public class Approach1 {
    /* Approach 1: Brute Force Approach using two loops */
    public static int diagonalSum(int matrix[][]) {
        int sum = 0;
        // Nested loops to traverse entire matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // Check for primary diagonal elements
                if (i == j) {
                    sum += matrix[i][j];
                }
                // Check for secondary diagonal elements
                else if(i+j == matrix.length-1){
                    sum += matrix[i][j];
                }
            }
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
2. Use nested loops to traverse entire matrix
3. Check each element against diagonal conditions
   - If primary diagonal (i == j), add to sum
   - If secondary diagonal (i + j == matrix.length - 1), add to sum
4. Return total sum

## Time Complexity Analysis
- Outer Loop: O(n)
- Inner Loop: O(n)
- Total Time Complexity: O(n²)
## Space Complexity: O(1) - only using a sum variable

## Advantages
1. Simple and straightforward implementation
2. Works with any square matrix
3. Checks every element explicitly
4. Easy to understand logic

## Limitations
1. Inefficient for large matrices
2. O(n²) time complexity
3. Potentially redundant element checks
4. Manually checking every matrix element

## Visualization of Diagonal Traversal
```
Matrix: 
[1  2  3]
[4  5  6]
[7  8  9]

Primary Diagonal (i == j):   1, 5, 9
Secondary Diagonal (i + j == length - 1):  3, 5, 7
Final Sum: 1 + 5 + 9 + 3 + 5 + 7 = 30
```

# Detailed Dry Run: Matrix Diagonal Sum 
## Input Matrix
```
[1  2  3]
[4  5  6]
[7  8  9]
```

## Comprehensive Execution Table

| Step | i | j | Current Element |         Condition Check         | Action | Running Sum | Explanation 
|------|---|---|-----------------|---------------------------------|--------|-------------|--------------------------------------------------
| 1    | 0 | 0 |        1        | i == j (Primary Diagonal)       | Add 1  |      1      | First iteration, first primary diagonal element 
| 2    | 0 | 1 |        2        | No diagonal condition           | Skip   |      1      | Element not on any diagonal 
| 3    | 0 | 2 |        3        | i + j == 2 (Secondary Diagonal) | Add 3  |      4      | Secondary diagonal element at top-right 
| 4    | 1 | 0 |        4        | i + j == 2 (Secondary Diagonal) | Add 4  |      8      | Secondary diagonal element at left side 
| 5    | 1 | 1 |        5        | i == j (Primary Diagonal)       | Add 5  |      13     | Center element on both diagonals 
| 6    | 1 | 2 |        6        | No diagonal condition           | Skip   |      13     | Element not on any diagonal 
| 7    | 2 | 0 |        7        | No diagonal condition           | Skip   |      13     | Element not on any diagonal 
| 8    | 2 | 1 |        8        | No diagonal condition           | Skip   |      13     | Element not on any diagonal 
| 9    | 2 | 2 |        9        | i == j (Primary Diagonal)       | Add 9  |      22     | Last primary diagonal element 

## Detailed Step-by-Step Process
### Initialization Phase
1. **Create Empty Sum Variable**
   - Initialize `sum = 0`
   - Prepares to accumulate diagonal elements
   - Ensures clean slate for calculation

### Nested Loop Traversal
2. **Outer Loop (Rows) Processing**
   - Iterates through each row of the matrix
   - `i` represents current row index (0 to 2)
   - Provides vertical traversal mechanism

3. **Inner Loop (Columns) Processing**
   - Iterates through each column of the matrix
   - `j` represents current column index (0 to 2)
   - Enables comprehensive matrix element examination

### Diagonal Identification Mechanism
4. **Primary Diagonal Condition (`i == j`)**
   - Identifies elements where row and column indices match
   - Captures diagonal from top-left to bottom-right
   - Elements added: 1, 5, 9

5. **Secondary Diagonal Condition (`i + j == matrix.length - 1`)**
   - Identifies elements where row and column indices sum to matrix length minus 1
   - Captures diagonal from top-right to bottom-left
   - Elements added: 3, 4, 5

### Special Characteristics
6. **Center Element Handling**
   - Element at position `[1][1]` (value 5) is unique
   - Appears on both primary and secondary diagonals
   - Carefully added only once to prevent double-counting

## Algorithmic Insights

### Condition Matching Logic
- Primary Diagonal: Strict equality match (`i == j`)
- Secondary Diagonal: Sum-based match (`i + j == matrix.length - 1`)
- Ensures comprehensive diagonal element capture

### Complexity Analysis
- Total Iterations: 9 (3x3 matrix)
- Actual Diagonal Elements Added: 5 elements (1, 3, 4, 5, 9)
- Final Sum: 22

## Visualization of Diagonal Elements
```
Diagonal Elements Highlighted:
[1  -  3]
[-  5  -]
[7  -  9]
```

## Final Output Calculation
- Input Matrix: [[1,2,3],[4,5,6],[7,8,9]]
- Diagonal Sum: 22
- Verification: 1 + 3 + 4 + 5 + 9 = 22

## Key Takeaways
1. Nested loops allow exhaustive matrix exploration
2. Conditional checks identify diagonal elements
3. Careful handling prevents element double-counting
4. Time complexity increases with matrix size

## Detailed Execution Walkthrough

### Nested Loop Execution
1. First Iteration (i = 0)
   - Checks (0,0): Primary Diagonal ✓ (Add 1)
   - Checks (0,1): Not Diagonal
   - Checks (0,2): Secondary Diagonal ✓ (Add 3)

2. Second Iteration (i = 1)
   - Checks (1,0): Secondary Diagonal ✓ (Add 4)
   - Checks (1,1): Primary Diagonal ✓ (Add 5)
   - Checks (1,2): Not Diagonal

3. Third Iteration (i = 2)
   - Checks (2,0): Not Diagonal
   - Checks (2,1): Not Diagonal
   - Checks (2,2): Primary Diagonal ✓ (Add 9)

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
- Supports different matrix sizes
- Handles overlapping center element
- Works with 1x1, 2x2, and larger matrices

## Potential Improvements
1. Optimize to single loop traversal
2. Reduce redundant checks
3. Improve time complexity from O(n²)

## Additional Insights
This brute force method demonstrates a direct, explicit approach to solving the diagonal sum problem by systematically checking each matrix element against specific conditions.

I'll explain step by step how the code handles the center element (overlapping case) using the input matrix:
```
[1  2  3]
[4  5  6]
[7  8  9]
```

Let's track every step for the center element (5):

## Step-by-Step Process

### Step 1: When Code Reaches Center Element (i=1, j=1)
```java
// Current position: matrix[1][1] = 5
for (int i = 0; i < matrix.length; i++) {       // i = 1
    for (int j = 0; j < matrix[0].length; j++) { // j = 1
```

### Step 2: First Condition Check
```java
if (i == j) {  // Check if element is on primary diagonal
    // Is 1 == 1? YES
    // This condition is TRUE for center element
    sum += matrix[i][j];  // Adds 5 to sum
}
```

### Step 3: Second Condition Check
```java
else if(i + j == matrix.length - 1) {  
    // Since first condition was TRUE
    // Code NEVER reaches this line for center element
    // Even though 1 + 1 = 2, and matrix.length - 1 = 2
    sum += matrix[i][j];  // This line is NOT executed for center element
}
```

### Step 4: Result
- Center element (5) is only added ONCE because:
  1. First condition (`i == j`) captures it
  2. `else if` prevents checking second condition

## Visual Step Tracking

```
Position: [1][1] (Center element = 5)

1. Check i == j
   i = 1, j = 1
   1 == 1 ✓ TRUE
   Action: Add 5 to sum

2. Check i + j == matrix.length - 1
   1 + 1 == 3 - 1
   2 == 2 ✓ TRUE
   BUT never reaches this check due to else if
```

### Complete Path for Center Element:
1. Enter outer loop (i = 1)
2. Enter inner loop (j = 1)
3. Check first condition (TRUE)
4. Add 5 to sum
5. Skip second condition
6. Move to next iteration

### Verification Table for Center Element:

| Step | Position | Value | Condition | Result | Action | Final Sum |
|------|----------|-------|-----------|---------|---------|------------|
| 1 | [1][1] | 5 | i == j | TRUE | Add 5 | +5 |
| 2 | [1][1] | 5 | i+j == length-1 | Skipped | None | No change |

This approach naturally prevents double-counting through the `else if` structure, rather than explicitly handling it through subtraction like in the optimized approach.

*/