package Array.Set_Matrix_Zeroes;

import java.util.ArrayList;
import java.util.Arrays;

/*
# Problem Title: Set Matrix Zeroes - Optimal In-Place Modification

# Intuition
- Main approach: Use the first row and column as markers instead of extra arrays
- We use the first row and column of the matrix itself to track which rows and columns should be set to zero
- Special handling for cell [0][0] using an extra variable `col0`

In the previous approach, the time complexity is minimal as the traversal of a matrix takes at least O(N*M)(where N = row and M = column). In this approach, we can just improve the space complexity. So, instead of using two extra matrices row and col, we will use the 1st row and 1st column of the given matrix to keep a track of the cells that need to be marked with 0. But here comes a problem. If we try to use the 1st row and 1st column to serve the purpose, the cell matrix[0][0] is taken twice. To solve this problem we will take an extra variable col0 initialized with 1. Now the entire 1st row of the matrix will serve the purpose of the row array. And the 1st column from (0,1) to (0,m-1) with the col0 variable will serve the purpose of the col array.
If any cell in the 0th row contains 0, we will mark matrix[0][0] as 0 and if any cell in the 0th column contains 0, we will mark the col0 variable as 0.
Thus we can optimize the space complexity.

## Basic Understanding
The problem requires us to modify a matrix such that if any cell contains 0, the entire row and column containing that cell should be set to 0, while doing this in-place with minimal extra space.

## Key Observations with Examples

### Observation 1: First Row and Column as Markers
- Instead of using separate arrays, we can use the first row and column of the matrix
- Challenge: cell [0][0] belongs to both first row and first column
* Solution: Use extra variable `col0` for first column

### Observation 2: Processing Order
- Cannot process first row/column immediately
- Must process the rest of the matrix first
Example:
```
Original Matrix:    After Step 1:       Final Result:
1 1 1              1 0 1               1 0 1
1 0 1      →       0 0 1       →       0 0 0
1 1 1              1 1 1               1 0 1
```

### Observation 3: Cell [0][0] Special Case
- [0][0] serves as marker for first row
- `col0` variable serves as marker for first column
Example:
```
If matrix[1][1] = 0:
- Set matrix[1][0] = 0 (row marker)
- Set matrix[0][1] = 0 (column marker)
```

## Step-by-Step Example
Let's work through a 3x3 matrix:

1. Initial State:
   ```
   1 1 1
   1 0 1
   1 1 1
   ```

2. Mark First Row/Column:
   ```
   1 0 1  // First row markers
   0 0 1  // matrix[1][0] marked
   1 1 1
   ```

3. Process Inner Matrix:
   ```
   1 0 1
   0 0 0  // Row/col containing 0 processed
   1 0 1
   ```

## Special Cases

### Case 1: Zero in First Row
Input: [[0, 1, 1], [1, 1, 1], [1, 1, 1]]
- Behavior: Entire first row becomes 0
- Result: [[0, 0, 0], [0, 1, 1], [1, 1, 1]]

### Case 2: Zero in First Column
Input: [[1, 1, 1], [0, 1, 1], [1, 1, 1]]
- Behavior: Entire first column becomes 0
- Result: [[0, 1, 1], [0, 1, 1], [0, 1, 1]]

*/
public class Approach3 {
    /*
    Approach 3: Optimal approach using in-place row and column markers: This method modifies the input matrix such that if any cell contains 0, all cells in the same row and column are set to 0.
     */
    static ArrayList<ArrayList<Integer>> zeroMatrix(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        // Variable to track if the first column should be set to zero
        int col0 = 1;

        // Step 1: Traverse the matrix to mark the first row and column indicating which rows and columns should be zeroed
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == 0) { // If the current cell is zero
                    matrix.get(i).set(0, 0); // Mark the first cell of the row
                    if (j != 0) { // For columns other than the first column
                        matrix.get(0).set(j, 0); // Mark the first cell of the column
                    } else {
                        col0 = 0; // Mark the first column indicator
                    }
                }
            }
        }

        // Step 2: Use the marks in the first row and column to set the rest of the matrix
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // If the row or column is marked, set the cell to zero
                if (matrix.get(i).get(0) == 0 || matrix.get(0).get(j) == 0) {
                    matrix.get(i).set(j, 0);
                }
            }
        }

        // Step 3: Handle the first row separately
        if (matrix.get(0).get(0) == 0) {
            for (int j = 0; j < m; j++) {
                matrix.get(0).set(j, 0); // Set all cells in the first row to zero
            }
        }

        // Step 4: Handle the first column separately
        if (col0 == 0) {
            for (int i = 0; i < n; i++) {
                matrix.get(i).set(0, 0); // Set all cells in the first column to zero
            }
        }

        return matrix; // Return the modified matrix
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        matrix.add(new ArrayList<>(Arrays.asList(1, 1, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(1, 0, 1)));
        matrix.add(new ArrayList<>(Arrays.asList(1, 1, 1)));

        System.out.println("Original Matrix:");
        printMatrix(matrix);

        int n = matrix.size();
        int m = matrix.get(0).size();
        ArrayList<ArrayList<Integer>> result = zeroMatrix(matrix, n, m);

        System.out.println("\nMatrix after processing:");
        printMatrix(result);
    }

    private static void printMatrix(ArrayList<ArrayList<Integer>> matrix) {
        for (ArrayList<Integer> row : matrix) {
            for (Integer ele : row) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
/*
# Algorithm
1. Initialize col0 = 1 to handle first column separately
2. Mark first row and column as markers for zeros
3. Process inner matrix (1,1 to n-1,m-1)
4. Process first row based on [0][0]
5. Process first column based on col0

## Detailed Alogrithm: 
1. First, we will traverse the matrix and mark the proper cells of 1st row and 1st column with 0 accordingly. The marking will be like this: 
- if cell(i, j) contains 0, we will mark the i-th row i.e. matrix[i][0] with 0 and we will mark j-th column i.e. matrix[0][j] with 0.
- if i is 0, we will mark matrix[0][0] with 0 but if j is 0, we will mark the col0 variable with 0 instead of marking matrix[0][0] again.
2. After step 1 is completed, we will modify the cells from (1,1) to (n-1, m-1) using the values from the 1st row, 1st column, and col0 variable.
3. We will not modify the 1st row and 1st column of the matrix here as the modification of the rest of the matrix(i.e. From (1,1) to (n-1, m-1)) is dependent on that row and column.
4. Finally, we will change the 1st row and column using the values from matrix[0][0] and col0 variable. Here also we will change the row first and then the column.
- If matrix[0][0] = 0, we will change all the elements from the cell (0,1) to (0, m-1), to 0.
- If col0 = 0, we will change all the elements from the cell (0,0) to (n-1, 0), to 0.

Observations: Why in the second step, we are first marking the matrix from the cell (1,1) to (n-1, m-1) and not from (0,0):
Let’s understand this using the following example:
Given matrix:
1 1 1 1
1 0 1 1
1 1 0 1
0 1 1 1
Now, we will try to apply step 1 in the above matrix, col0 will be 0 as (3,0) contains 0 and it will look like the following:
1 0 0 1
0 0 1 1
0 1 0 1
0 1 1 1
Now, in the second step we will try to start modifying the cells with value 0 from (0,0). First, we will change the value of (0,0) to 0 as col0 is marked with 0. After that, while checking for cell (0, 3) we will find that the value of (0,0) is 0. And we will again modify the cell (0,3) with 0. But this should not happen as (0,0) was initially 1 and that is why (0,3) should remain with the value 1.
This is why we cannot change the 1st row and 1st column on the first go as the rest of the matrix is dependent on them. If we do it, the modification of the matrix will be incorrect.

In the 3rd step, why we are marking the 1st row first and then the 1st column:
We can notice that the modification of the 1st row is dependent on matrix[0][0] and the modification of the 1st column is dependent on col0 which is an independent variable. Now, if we modify the 1st column first, matrix[0][0] might be changed and this will hinder the modification of the 1st row as well. But if we simply do the opposite, the 1st row will be changed first, based on the value matrix[0][0] and then the 1st column will be changed based on the variable col0. This is why the order of change matters.

### Time Complexity Breakdown per Step
1. First row/column marking: O(n*m)
2. Inner matrix processing: O(n*m)
3. First row/column processing: O(n+m)
Total: O(n*m)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
- Only using one extra variable (col0)
2. Input Space: O(1)
- Modifying matrix in-place
Total: O(1)

# Advantages
1. Optimal space complexity (O(1))
2. No extra arrays needed
3. In-place modification

# Limitations
1. Modifies input matrix
2. Cannot preserve original values
3. Complex implementation

# Potential Improvements
1. Add input validation
2. Add error handling
3. Add support for different data types

# Step-by-Step Process with Dry Run

# Detailed Step-by-Step Process with Input Matrix [[1,1,1], [1,0,1], [1,1,1]]

## Complete Execution Table
```
Step | Matrix State          | col0 | Action                      | Explanation
-----|----------------------|------|-----------------------------|-----------------
0    | [[1,1,1],           | 1    | Initial State              | Original matrix
     |  [1,0,1],           |      |                            |
     |  [1,1,1]]           |      |                            |
-----|----------------------|------|-----------------------------|-----------------
1    | [[1,1,1],           | 1    | Found zero at (1,1)        | Marking row 1 and
     |  [0,0,1],           |      | Mark: matrix[1][0] = 0     | column 1 as markers
     |  [1,1,1]]           |      | Mark: matrix[0][1] = 0     |
-----|----------------------|------|-----------------------------|-----------------
2    | [[1,0,1],           | 1    | Process inner matrix       | Using markers to set
     |  [0,0,1],           |      | Check markers for (1,1)    | zeros in inner matrix
     |  [1,1,1]]           |      |                            |
-----|----------------------|------|-----------------------------|-----------------
3    | [[1,0,1],           | 1    | Process inner matrix       | Setting zeros based
     |  [0,0,0],           |      | Set zeros based on         | on row & col markers
     |  [1,0,1]]           |      | markers                    |
-----|----------------------|------|-----------------------------|-----------------
4    | [[1,0,1],           | 1    | Final state after          | Matrix is now
     |  [0,0,0],           |      | processing first           | completely processed
     |  [1,0,1]]           |      | row and column            |
```

## Step-by-Step Explanation in Points

1. **Initial State (Step 0)**
   - Input matrix is [[1,1,1], [1,0,1], [1,1,1]]
   - col0 is initialized to 1
   - No modifications yet

2. **Marking Phase (Step 1)**
   - Find zero at position (1,1)
   - Mark first row: matrix[0][1] = 0
   - Mark first column: matrix[1][0] = 0
   - Matrix becomes [[1,0,1], [0,0,1], [1,1,1]]

3. **Processing Inner Matrix (Step 2)**
   - Start from position (1,1)
   - Check markers in first row and column
   - If either marker is 0, set current cell to 0
   - Process each inner cell (1,1), (1,2), (2,1), (2,2)

4. **Processing First Row/Column (Step 3)**
   - Check matrix[0][0] for first row
   - Check col0 for first column
   - Set appropriate cells to 0
   - Final matrix: [[1,0,1], [0,0,0], [1,0,1]]

## Visual Step-by-Step Matrix Transformation

1. **Initial Matrix**
```
1 1 1
1 0 1
1 1 1
```

2. **After Marking First Row/Column**
```
1 0 1  ← Marked column with 0
0 0 1  ← Marked row with 0
1 1 1
```

3. **After Processing Inner Matrix**
```
1 0 1
0 0 0  ← Inner cells processed
1 0 1
```

4. **Final Result**
```
1 0 1
0 0 0
1 0 1
```

## Key Points to Remember
1. **Marker System**
   - First row serves as column markers
   - First column serves as row markers
   - col0 variable handles first column specially

2. **Processing Order**
   - Mark first row/column first
   - Process inner matrix using markers
   - Process first row/column last

3. **Special Cases**
   - col0 handles first column
   - matrix[0][0] handles first row
   - Process order is crucial for correctness

4. **Validation Points**
   - Cell (1,1) changes due to both row and column markers
   - First row/column processed last to maintain marker integrity
   - Inner matrix depends on markers for correct transformation

This detailed breakdown shows how each step transforms the matrix and why the specific order of operations is crucial for the algorithm's correctness.

### Edge Cases Handling
1. **Empty Matrix**
   ```
   Input: []
   Output: []
   Handle empty matrix case
   ```

2. **Single Element Matrix**
   ```
   Input: [[0]]
   Output: [[0]]
   No processing needed
   ```

TEST CASES:
1. Input: [[1,1,1],[1,0,1],[1,1,1]]
   Expected: [[1,0,1],[0,0,0],[1,0,1]]
   Output: [[1,0,1],[0,0,0],[1,0,1]]
2. Input: [[0,1,1],[1,1,1],[1,1,1]]
   Expected: [[0,0,0],[0,1,1],[0,1,1]]
   Output: [[0,0,0],[0,1,1],[0,1,1]] */
/* 
 */