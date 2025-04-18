package Array;
import java.util.ArrayList;
import java.util.List;

/* 
# Problem Title: Spiral Matrix Traversal
Problem: Given an m x n matrix, return all elements of the matrix in spiral order.

# Intuition
The core concept is to traverse the matrix in a spiral pattern, similar to peeling an onion layer by layer. 
- We move in a clockwise direction: right → down → left → up
- Use four pointers (top, right, bottom, left) to track boundaries
- Process one layer at a time, adjusting boundaries inward

## Steps:
- Create and initialize variables top as starting row index, bottom as ending row index left as starting column index, and right as ending column index.
- In each outer loop traversal print the elements of a square in a clockwise manner.
- Print the top row, i.e. Print the elements of the top row from column index left to right and increase the count of the top so that it will move to the next row.
- Print the right column, i.e. Print the rightmost column from row index top to bottom and decrease the count of right.
- Print the bottom row, i.e. if top <= bottom, then print the elements of a bottom row from column right to left and decrease the count of bottom
- Print the left column, i.e. if left <= right, then print the elements of the left column from the bottom row to the top row and increase the count of left.
- Run a loop until all the squares of loops are printed.

## Basic Understanding
Given a matrix, we need to print elements in a spiral order starting from the outermost elements and moving inward. Think of it as drawing a spiral from outside to inside.

## Key Observations with Examples

### Observation 1: Layer-based Pattern
Each spiral traversal forms a rectangle/square layer
* Outer layer is processed first
* Move inward for subsequent layers
Example: For a 4x4 matrix:
```
First Layer:    Then Inner Layer:
1  2   3  4      ▢  ▢  ▢  ▢
5  ▢  ▢  8  →   ▢  6  7   ▢
9  ▢  ▢  12     ▢  10 11  ▢
13 14  15 16     ▢  ▢  ▢  ▢
```

### Observation 2: Four Directional Movement
Each layer requires four distinct movements:
```
→ Right:  [1, 2, 3, 4]
↓ Down:   [8, 12, 16]
← Left:   [15, 14, 13]
↑ Up:     [9, 5]
```

### Observation 3: Boundary Management
Four pointers manage the traversal boundaries:
```
top = 0 ─────────────┐ right = m-1
         │  Matrix   │
left = 0 └───────────┘ bottom = n-1
```

## Step-by-Step Example
Let's work through a 4×4 matrix:

1. Initial State:
```
1  2  3  4
   5  6  7  8
   9  10 11 12
   13 14 15 16
   
   top=0, bottom=3, left=0, right=3
   ```
   
2. First Layer:
   ```
   [Movement: →] 1,2,3,4   (top++)
   [Movement: ↓] 8,12,16   (right--)
   [Movement: ←] 15,14,13  (bottom--)
   [Movement: ↑] 9,5       (left++)
   ```
   
   ## Special Cases
   
   ### Case 1: Single Row Matrix
   Input: [[1, 2, 3]]
   - Only right movement needed
   - Result: [1, 2, 3]
   
   ### Case 2: Single Column Matrix
   Input: [[1], [2], [3]]
   - Only downward movement needed
   - Result: [1, 2, 3]
   */
  public class SpiralMatrix {
    
    // Method to traverse matrix in spiral order
    public static List<Integer> printSpiral(int[][] mat) {
        // List to store the spiral order traversal
        List<Integer> ans = new ArrayList<>();

        // Get matrix dimensions
        int n = mat.length; // number of rows
        int m = mat[0].length; // number of columns

        // Initialize boundary pointers
        int top = 0, left = 0; // top and left boundaries
        int bottom = n - 1, right = m - 1; // bottom and right boundaries

        // Continue until all elements are traversed
        while (top <= bottom && left <= right) {
            // 1. Traverse from left to right (top row)
            for (int i = left; i <= right; i++)
                ans.add(mat[top][i]);
            top++; // Move top boundary down

            // 2. Traverse from top to bottom (rightmost column)
            for (int i = top; i <= bottom; i++)
                ans.add(mat[i][right]);
            right--; // Move right boundary left

            // 3. Traverse from right to left (bottom row)
            if (top <= bottom) { // Check if row exists
                for (int i = right; i >= left; i--)
                    ans.add(mat[bottom][i]);
                bottom--; // Move bottom boundary up
            }

            // 4. Traverse from bottom to top (leftmost column)
            if (left <= right) { // Check if column exists
                for (int i = bottom; i >= top; i--)
                    ans.add(mat[i][left]);
                left++; // Move left boundary right
            }
        }
        return ans;
    }
        
        // Main method for testing
        public static void main(String[] args) {
        // Test matrix
        int[][] mat = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        
        // Get spiral order
        List<Integer> ans = printSpiral(mat);

        // Print result
        System.out.print("Spiral Order: ");
        for(int i = 0; i < ans.size(); i++){
            System.out.print(ans.get(i) + " ");
        }
        System.out.println();
    }
}
/*
# Algorithm
1. Initialize pointers (top, right, bottom, left) for matrix boundaries
2. While elements remain untraversed (top ≤ bottom && left ≤ right):
   - Move Left to Right: Traverse the top row and increment top.
   - Move Top to Bottom: Traverse the right column and decrement right.
   - Move Right to Left (if rows remain): Traverse the bottom row and decrement bottom.
   - Move Bottom to Top (if columns remain): Traverse the left column and increment left.
3. Stop when all elements are visited.
4. Return result list

## Note: As we can see in the code snippet below, two edge conditions are being added in the last two ‘for’ loops: when we are moving from right to left and from bottom to top. 

These conditions are added to check if the matrix is a single column or a single row. So, whenever the elements in a single row are traversed they cannot be traversed again backward so the condition is checked in the right-to-left loop. When a single column is present, the condition is checked in the bottom-to-top loop as elements from bottom to top cannot be traversed again.

Let me explain the edge conditions in detail with examples.

# Edge Conditions Explanation

## 1. Single Row Matrix Case
Consider a matrix with only one row:
```
[1, 2, 3, 4]
```

### Without Edge Condition:
```
Step 1 (→): [1,2,3,4] (correct)
Step 2 (↓): [] (nothing to traverse)
Step 3 (←): [4,3,2,1] (WRONG! We'd traverse same elements backwards)
Step 4 (↑): [] (nothing to traverse)
```

### With Edge Condition (if top <= bottom):
```
Step 1 (→): [1,2,3,4] (correct)
Step 2 (↓): [] (nothing to traverse)
Step 3 (←): [] (condition fails: top > bottom)
Step 4 (↑): [] (condition fails: left > right)

Final Result: [1,2,3,4] (correct)
```

## 2. Single Column Matrix Case
Consider a matrix with only one column:
```
[1]
[2]
[3]
[4]
```

### Without Edge Condition:
```
Step 1 (→): [1] (correct)
Step 2 (↓): [2,3,4] (correct)
Step 3 (←): [] (nothing to traverse)
Step 4 (↑): [4,3,2] (WRONG! We'd traverse same elements backwards)
```

### With Edge Condition (if left <= right):
```
Step 1 (→): [1] (correct)
Step 2 (↓): [2,3,4] (correct)
Step 3 (←): [] (condition fails: left > right)
Step 4 (↑): [] (condition fails: left > right)

Final Result: [1,2,3,4] (correct)
```

## Code Implementation with Explanations:
```java
while (top <= bottom && left <= right) {
    // Right movement - always safe as matrix has at least one element
    for (int i = left; i <= right; i++)
        ans.add(mat[top][i]);
    top++;

    // Down movement - always safe if there's more than one row
    for (int i = top; i <= bottom; i++)
        ans.add(mat[i][right]);
    right--;

    // Left movement - needs check to avoid duplicate traversal
    if (top <= bottom) {  // Check if there's still an untraversed row
        for (int i = right; i >= left; i--)
            ans.add(mat[bottom][i]);
        bottom--;
    }

    // Up movement - needs check to avoid duplicate traversal
    if (left <= right) {  // Check if there's still an untraversed column
        for (int i = bottom; i >= top; i--)
            ans.add(mat[i][left]);
        left++;
    }
}
```

## Visual Examples for Edge Cases:

### Example 1: 1×3 Matrix
```
Initial Matrix: [1, 2, 3]

Step 1 (→): [1,2,3]
           [●,●,●]  // after right movement
           
top++: now top > bottom, so left and up movements are skipped
Result: [1,2,3]
```

### Example 2: 3×1 Matrix
```
Initial Matrix:  [1]
                [2]
                [3]

Step 1 (→): [1]
           [●]
           [2]
           [3]

Step 2 (↓): [1]
           [●]
           [●]
           [●]
           
right--: now left > right, so left and up movements are skipped
Result: [1,2,3]
```

### Example 3: 2×2 Matrix
```
[1, 2]
[3, 4]

Steps with conditions:
1. Right: [1,2]   // No condition needed
   [●,●]
   [3,4]

2. Down: [1,2]    // No condition needed
   [●,●]
   [3,●]

3. Left: [1,2]    // Condition passes: top <= bottom
   [●,●]
   [●,●]

4. Up: Complete   // Condition fails: left > right
Result: [1,2,4,3]
```

These edge conditions are crucial for:
1. Preventing duplicate traversal of elements
2. Handling single row/column matrices correctly
3. Ensuring the spiral order is maintained
4. Avoiding index out of bounds errors

Without these conditions, the algorithm would try to traverse already visited elements in reverse direction for single row or column matrices, leading to incorrect results.


### Time Complexity Breakdown per Step
1. Initialization: O(1)
2. Matrix Traversal: O(m×n) - visit each element once
Total: O(m×n) where m is rows and n is columns

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - only pointers
2. Output Space: O(m×n) - to store result
Total: O(m×n)

# Advantages
1. Single pass solution - visits each element exactly once
2. In-place traversal - no extra space except output
3. Handles all matrix shapes (square, rectangular)

# Limitations
1. Cannot modify original matrix
2. Requires additional space for output
3. Not suitable for sparse matrices

# Step-by-Step Process with Dry Run

## Example Input: 4×4 matrix
I'll create a detailed step-by-step walkthrough with tables and explanations for the Spiral Matrix problem.

### Detailed Table for 4×4 Matrix Example
Input Matrix:
```
 1  2  3  4
 5  6  7  8
 9  10 11 12
13 14 15 16
```

```
Step | Current Element | Direction | Updated Pointers| Current Result List  | Matrix State (● = visited)
-----|----------------|-----------|------------------|----------------------|------------------
0    | Start          | -         | t=0,r=3,b=3,l=0  | []                   | 1  2  3  4
     |                |           |                  |                      | 5  6  7  8
     |                |           |                  |                      | 9  10 11 12
     |                |           |                  |                      | 13 14 15 16

1    | 1,2,3,4        | Right     | t=1,r=3,b=3,l=0  | [1,2,3,4]            | ●  ●  ●  ●
     |                |           |                  |                      | 5  6  7  8
     |                |           |                  |                      | 9  10 11 12
     |                |           |                  |                      | 13 14 15 16

2    | 8,12,16        | Down      | t=1,r=2,b=3,l=0  | [1,2,3,4,8,12,16]    | ●  ●  ●  ●
     |                |           |                  |                      | 5  6  7  ●
     |                |           |                  |                      | 9  10 11 ●
     |                |           |                  |                      | 13 14 15 ●

3    | 15,14,13       | Left      | t=1,r=2,b=2,l=0  | [1,2,3,4,8,12,16,    | ●  ●  ●  ●
     |                |           |                  | 15,14,13]            | 5  6  7  ●
     |                |           |                  |                      | 9  10 11 ●
     |                |           |                  |                      | ●  ●  ●  ●

4    | 9,5            | Up        | t=1,r=2,b=2,l=1  | [1,2,3,4,8,12,16,    | ●  ●  ●  ●
     |                |           |                  | 15,14,13,9,5]        | ●  6  7  ●
     |                |           |                  |                      | ●  10 11 ●
     |                |           |                  |                      | ●  ●  ●  ●

5    | 6,7            | Right     | t=2,r=2,b=2,l=1  | [1,2,3,4,8,12,16,    | ●  ●  ●  ●
     |                |           |                  | 15,14,13,9,5,6,7]    | ●  ●  ●  ●
     |                |           |                  |                      | ●  10 11 ●
     |                |           |                  |                      | ●  ●  ●  ●

6    | 11,10          | Left      | t=2,r=1,b=1,l=1  | [1,2,3,4,8,12,16,    | ●  ●  ●  ●
     |                |           |                  | 15,14,13,9,5,6,7,    | ●  ●  ●  ●
     |                |           |                  | 11,10]               | ●  ●  ●  ●
     |                |           |                  |                      | ●  ●  ●  ●
```

### Step-by-Step Process Explanation:

1. **Initial Setup (Step 0)**:
   - Initialize pointers: top=0, right=3, bottom=3, left=0
   - Empty result list
   - Entire matrix unvisited

2. **First Right Movement (Step 1)**:
   - Traverse top row from left=0 to right=3
   - Add elements [1,2,3,4] to result
   - Update top pointer: top++ (now top=1)
   - Mark top row as visited

3. **First Down Movement (Step 2)**:
   - Traverse right column from top=1 to bottom=3
   - Add elements [8,12,16] to result
   - Update right pointer: right-- (now right=2)
   - Mark rightmost column as visited

4. **First Left Movement (Step 3)**:
   - Traverse bottom row from right=2 to left=0
   - Add elements [15,14,13] to result
   - Update bottom pointer: bottom-- (now bottom=2)
   - Mark bottom row as visited

5. **First Up Movement (Step 4)**:
   - Traverse left column from bottom=2 to top=1
   - Add elements [9,5] to result
   - Update left pointer: left++ (now left=1)
   - Mark leftmost column as visited

6. **Inner Spiral Right (Step 5)**:
   - Traverse remaining top row from left=1 to right=2
   - Add elements [6,7] to result
   - Update top pointer: top++ (now top=2)

7. **Final Movement (Step 6)**:
   - Traverse remaining bottom row from right=1 to left=1
   - Add elements [11,10] to result
   - Complete traversal as all elements are visited

### Key Points from the Table:
1. Each step represents one complete directional movement
2. Pointers update after each directional movement
3. Visited elements are marked with ● in the matrix state
4. Result list grows incrementally with each movement
5. The spiral pattern is clearly visible in the visiting order
6. The process continues until all elements are visited

The final output array: [1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10]

This detailed breakdown shows how the algorithm systematically visits each element exactly once while maintaining the spiral order, demonstrating both the time efficiency (O(m×n)) and the space efficiency (O(1) auxiliary space) of the solution.

### Edge Cases Handling
1. **Single Row**
   ```
   Input: [[1,2,3]]
   Output: [1,2,3]
   Only right traversal needed
   ```

2. **Single Column**
   ```
   Input: [[1],[2],[3]]
   Output: [1,2,3]
   Only downward traversal needed
   ```

TEST CASES:
1. Input: [[1,2,3],[4,5,6],[7,8,9]]
   Expected: [1,2,3,6,9,8,7,4,5]
   Output: [1,2,3,6,9,8,7,4,5]
2. Input: [[1]]
   Expected: [1]
   Output: [1]
3. Input: [[1,2],[3,4]]
   Expected: [1,2,4,3]
   Output: [1,2,4,3]
 */