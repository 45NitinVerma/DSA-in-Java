package Array.Set_Matrix_Zeroes;

import java.util.ArrayList;
import java.util.Arrays;

/* 
# Problem Title: Set Matrix Zeroes - Converting Matrix Elements to Zero Based on Zero Positions

# Intuition
- When we find a zero in the matrix, entire row and column containing that zero should be set to zero
- Instead of marking cells during traversal, we can use separate arrays to mark which rows and columns need to be zeroed
- This marking approach reduces time complexity compared to marking during traversal

## Basic Understanding
The problem requires transforming a matrix by setting entire rows and columns to zero if they contain at least one zero element. Rather than modifying the matrix during initial traversal, we use auxiliary arrays to track which rows and columns need modification.

## Key Observations with Examples

### Observation 1: Row and Column Marking
- Use separate arrays to mark rows and columns containing zeros
- Avoids modifying matrix during initial traversal
Example: For a 3x3 matrix with zero at position (1,1):
```
1 1 1    row[] = [0,1,0]
1 0 1 -> col[] = [0,1,0]
1 1 1    
```

### Observation 2: Two-Pass Approach
- First pass: Mark rows and columns that contain zeros
- Second pass: Use marked arrays to modify original matrix
Example:
```
First Pass:  Mark row[1] and col[1]
Second Pass: Set entire row 1 and column 1 to zero
```

## Step-by-Step Example
Let's work through a 3x3 matrix:

1. Initial Matrix:
   1 1 1
   1 0 1
   1 1 1
   Create row[3] and col[3] arrays initialized with 0

2. First Pass - Mark arrays:
   row = [0,1,0]  // row 1 marked
   col = [0,1,0]  // column 1 marked

3. Second Pass - Update matrix:
   1 0 1
   0 0 0
   1 0 1

## Special Cases

### Case 1: All Zeros
Input: [[0,0],[0,0]]
- Behavior: All rows and columns marked
- Result: Matrix remains all zeros

### Case 2: No Zeros
Input: [[1,1],[1,1]]
- Behavior: No rows or columns marked
- Result: Matrix remains unchanged

### Case 3: Single Zero
Input: [[1,0],[1,1]]
- Behavior: One row and one column marked
- Result: One row and one column become zero
*/

public class Approach2 {
    // Better approach using row and col array
    static ArrayList<ArrayList<Integer>> zeroMatrix(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        int[] row = new int[n]; // row array
        int[] col = new int[m]; // col array

        // First pass: Mark rows and columns
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        // Second pass: Update matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix.get(i).set(j, 0);
                }
            }
        }

        return matrix;
    }
    public static void main(String[] args) {
      ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
      matrix.add(new ArrayList<>(Arrays.asList(1, 1, 1)));
      matrix.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
      matrix.add(new ArrayList<>(Arrays.asList(1, 1, 1)));

      int n = matrix.size();
      int m = matrix.get(0).size();

      ArrayList<ArrayList<Integer>> ans = zeroMatrix(matrix, n, m);

      System.out.println("The Final matrix is: ");
      for (ArrayList<Integer> row : ans) {
          for (Integer ele : row) {
              System.out.print(ele + " ");
          }
          System.out.println();
      }
  }
}

/*
# Algorithm
1. First, we will declare two arrays: a row array of size N and a col array of size M and both are initialized with 0.
2. Then, we will use two loops(nested loops) to traverse all the cells of the matrix.
3. If any cell (i,j) contains the value 0, we will mark ith index of row array i.e. row[i] and jth index of col array col[j] as 1. It signifies that all the elements in the ith row and jth column will be 0 in the final matrix.
4. We will perform step 3 for every cell containing 0.
5. Finally, we will again traverse the entire matrix and we will put 0 into all the cells (i, j) for which either row[i] or col[j] is marked as 1.
6. Thus we will get our final matrix.

### Time Complexity Breakdown per Step
1. Array initialization: O(n + m)
2. First pass matrix traversal: O(n × m)
3. Second pass matrix traversal: O(n × m)

Total: O(n × m)

### Space Complexity Breakdown
1. Auxiliary Space: O(n + m)
   - row array: O(n)
   - col array: O(m)
2. Input Space: O(n × m)
   - Original matrix

Total: O(n + m)

# Advantages
1. Better time complexity compared to in-place marking
2. Simple implementation with two passes
3. Preserves original values until final modification

# Limitations
1. Requires additional space for marking arrays
2. Not suitable for extremely large matrices due to space requirement
3. Requires two complete matrix traversals

# Potential Improvements
1. Use first row and column as marking arrays to achieve O(1) space
2. Implement parallel processing for larger matrices
3. Add input validation and error handling

# Step-by-Step Process with Dry Run

## Example Input: 3x3 matrix

Let me create a detailed table with step-by-step explanation for the Set Matrix Zeroes problem using the input matrix:
```
1 1 1
1 0 1
1 1 1
```

### Detailed Execution Table
```
Step | Matrix State     | Row Array  | Col Array  | Action                   | Result
-----|-----------------|------------|------------|--------------------------|------------------
0    | 1 1 1           | [0,0,0]    | [0,0,0]    | Initialize arrays        | Arrays created
     | 1 0 1           |            |            |                          |
     | 1 1 1           |            |            |                          |
-----|-----------------|------------|------------|--------------------------|------------------
1    | 1 1 1           | [0,1,0]    | [0,1,0]    | Found 0 at (1,1)        | Mark row[1]=1
     | 1 0 1           |            |            | Mark row & col          | Mark col[1]=1 
     | 1 1 1           |            |            |                          |
-----|-----------------|------------|------------|--------------------------|------------------
2    | 1 0 1           | [0,1,0]    | [0,1,0]    | Process row 0           | Update based on
     | 0 0 0           |            |            | Update if row[i]=1 or    | marked arrays
     | 1 0 1           |            |            | col[j]=1                 |
```

### Step-by-Step Process Explanation

1. **Initialization Phase (Step 0)**
   - Create row array of size 3 (n=3) initialized with zeros: [0,0,0]
   - Create col array of size 3 (m=3) initialized with zeros: [0,0,0]
   - Original matrix remains unchanged

2. **First Pass - Marking Phase (Step 1)**
   - Scan matrix cell by cell:
     * Position (0,0)=1: No action
     * Position (0,1)=1: No action
     * Position (0,2)=1: No action
     * Position (1,0)=1: No action
     * Position (1,1)=0: Mark row[1]=1 and col[1]=1
     * Position (1,2)=1: No action
     * Position (2,0)=1: No action
     * Position (2,1)=1: No action
     * Position (2,2)=1: No action
   - After first pass:
     * row array = [0,1,0]
     * col array = [0,1,0]

3. **Second Pass - Update Phase (Step 2)**
   - Process matrix cell by cell:
     * Position (0,0): No change (row[0]=0 and col[0]=0)
     * Position (0,1): Set to 0 (col[1]=1)
     * Position (0,2): No change (row[0]=0 and col[2]=0)
     * Position (1,0): Set to 0 (row[1]=1)
     * Position (1,1): Set to 0 (row[1]=1 and col[1]=1)
     * Position (1,2): Set to 0 (row[1]=1)
     * Position (2,0): No change (row[2]=0 and col[0]=0)
     * Position (2,1): Set to 0 (col[1]=1)
     * Position (2,2): No change (row[2]=0 and col[2]=0)

### State Changes Summary
1. Initial State:
```
Matrix:     Row Array:   Col Array:
1 1 1       [0,0,0]     [0,0,0]
1 0 1
1 1 1
```

2. After Marking:
```
Matrix:     Row Array:   Col Array:
1 1 1       [0,1,0]     [0,1,0]
1 0 1
1 1 1
```

3. Final State:
```
Matrix:     Row Array:   Col Array:
1 0 1       [0,1,0]     [0,1,0]
0 0 0
1 0 1
```

This detailed breakdown shows how each step transforms the matrix and how the marking arrays are used to track which rows and columns need to be zeroed. The algorithm efficiently completes the transformation in two passes without needing additional complex data structures.

### Edge Cases Handling
1. **Empty Matrix**
   ```
   Input: []
   Output: []
   Handles empty matrix case safely
   ```

2. **Single Element**
   ```
   Input: [[0]]
   Output: [[0]]
   Single element remains unchanged if zero
   ```

TEST CASES:
1. Input: [[1,1,1],[1,0,1],[1,1,1]]
   Expected: [[1,0,1],[0,0,0],[1,0,1]]
   Output: [[1,0,1],[0,0,0],[1,0,1]]
2. Input: [[0,1,1],[1,1,1],[1,1,1]]
   Expected: [[0,0,0],[0,1,1],[0,1,1]]
   Output: [[0,0,0],[0,1,1],[0,1,1]]
 */