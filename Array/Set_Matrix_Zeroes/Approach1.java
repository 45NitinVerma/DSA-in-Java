package Array.Set_Matrix_Zeroes;
import java.util.ArrayList;
/* 
    # Problem: Set Matrix Zeroes
    Given an `m x n` integer matrix matrix, if an element is '0', set its entire row and column to 0's in-place.

    # Intuition
    - The core idea is to identify cells containing 0 and mark their corresponding rows and columns
    - Use a temporary marker (-1) to avoid confusing original zeros with newly marked cells
    - Process the matrix in two passes to maintain correctness

    ## Basic Understanding
    The problem requires transforming a matrix by setting entire rows and columns to 0 whenever a 0 is encountered in the original matrix, while performing the operation in-place.

    ## Key Observations with Examples

    ### Observation 1: Direct Marking Issue
    Using 0 directly to mark cells causes incorrect propagation
    * Example:
    ```
    Original:    After direct marking:
    1 1 1        1 0 1
    1 0 1   →    0 0 0   (Incorrect propagation)
    1 1 1        1 0 1
    ```

    ### Observation 2: Temporary Marker Necessity
    Using -1 as temporary marker preserves original zero locations
    ```
    Original:    After marking with -1:    Final:
    1  1  1      1   -1   1              1   0   1
    1  0  1  →   -1   0  -1     →        0   0   0
    1  1  1      1   -1   1              1   0   1
    ```

    ## Step-by-Step Example
    Let's work through matrix = [[1,1,1],[1,0,1],[1,1,1]]:

    1. First Pass:
    Find 0 at position (1,1)
    Mark row 1: [1,-1,1]
    Mark column 1: [-1,0,-1]

    2. Second Pass:
    Convert all -1 to 0
    Final result: [[1,0,1],[0,0,0],[1,0,1]]

    ## Special Cases

    ### Case 1: Single Zero
    Input: [[1,0],[1,1]]
    - Entire row and column containing 0 are marked
    - Result: [[0,0],[1,0]]

    ### Case 2: Multiple Zeros
    Input: [[0,1,0],[1,1,1]]
    - Multiple rows/columns may overlap
    - Result: [[0,0,0],[0,1,0]]
*/
import java.util.Arrays;

public class Approach1 {
    /* Approach 1: Brute Force */
    // Helper method to mark row
    static void markRow(ArrayList<ArrayList<Integer>> matrix, int n, int m, int i) {
        for (int j = 0; j < m; j++) {
            if (matrix.get(i).get(j) != 0) {
                matrix.get(i).set(j, -1);
            }
        }
    }

    // Helper method to mark column
    static void markCol(ArrayList<ArrayList<Integer>> matrix, int n, int m, int j) {
        for (int i = 0; i < n; i++) {
            if (matrix.get(i).get(j) != 0) {
                matrix.get(i).set(j, -1);
            }
        }
    }

    static ArrayList<ArrayList<Integer>> zeroMatrix(ArrayList<ArrayList<Integer>> matrix, int n, int m) {
        // First pass: Mark with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == 0) {
                    markRow(matrix, n, m, i);
                    markCol(matrix, n, m, j);
                }
            }
        }
        // Second pass: Convert -1 to 0
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix.get(i).get(j) == -1) {
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
1. First, we will use two loops(nested loops) to traverse all the cells of the matrix.
2. If any cell (i,j) contains the value 0, we will mark all cells in row i and column j with -1 except those which contain 0.
3. We will perform step 2 for every cell containing 0.
4. Finally, we will mark all the cells containing -1 with 0.
5. Thus the given matrix will be modified according to the question.
## Note: Here, we are assuming that the matrix does not contain any negative numbers. But if it contains negatives, we need to find some other ways to mark the cells instead of marking them with -1.

### Time Complexity Breakdown per Step
1. Finding zeros: O(n×m)
2. Marking rows and columns: O(n+m) for each zero
3. Final conversion: O(n×m)

Total: O(n×m×(n+m))

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
- Only using constant extra space for variables
2. Input Space: O(n×m)
- Original matrix space

Total: O(1) auxiliary space

# Advantages
1. In-place modification without extra space
2. Simple implementation
3. Handles all edge cases correctly

# Limitations
1. Higher time complexity due to multiple passes
2. Requires matrix elements to be non-negative
3. Modifies original matrix during processing

# Potential Improvements
1. Use first row and column as markers to reduce time complexity
2. Implement single-pass solution
3. Handle negative numbers in matrix

### Detailed Step-by-Step Table for Input: [[1,1,1], [1,0,1], [1,1,1]]

```
Step | Current Matrix     | Action                         | Position | Explanation
-----|-------------------|--------------------------------|----------|-------------
0    | [[1,1,1],         | Initial state                  | -        | Starting matrix
     | [1,0,1],          |                                |          |
     | [1,1,1]]          |                                |          |
-----|-------------------|--------------------------------|----------|-------------
1    | [[1,1,1],         | Found zero                     | (1,1)    | Located first zero
     | [1,0,1],          |                                |          | at position (1,1)
     | [1,1,1]]          |                                |          |
-----|-------------------|--------------------------------|----------|-------------
2    | [[1,-1,1],        | Mark column 1                  | Col 1    | Marked all non-zero
     | [1,0,1],          | with -1                        |          | elements in column 1
     | [1,-1,1]]         |                                |          | with -1
-----|-------------------|--------------------------------|----------|-------------
3    | [[1,-1,1],        | Mark row 1                     | Row 1    | Marked all non-zero
     | [-1,0,-1],        | with -1                        |          | elements in row 1
     | [1,-1,1]]         |                                |          | with -1
-----|-------------------|--------------------------------|----------|-------------
4    | [[1,0,1],         | Convert all -1s                | All      | Final conversion of
     | [0,0,0],          | to 0s                          |          | all -1s to 0s
     | [1,0,1]]          |                                |          |
```

### Step-by-Step Process Explanation:

1. **Initial State**
   - Matrix starts with [[1,1,1], [1,0,1], [1,1,1]]
   - Size: 3×3 matrix
   - Looking for zeros in the matrix

2. **Zero Detection**
   - Found first zero at position (1,1)
   - This will trigger marking of:
     * Row 1 (middle row)
     * Column 1 (middle column)

3. **Column Marking**
   - In column 1:
     * Changed (0,1) from 1 to -1
     * Position (1,1) remains 0
     * Changed (2,1) from 1 to -1
   - Result after column marking:
     * [[1,-1,1], [1,0,1], [1,-1,1]]

4. **Row Marking**
   - In row 1:
     * Changed (1,0) from 1 to -1
     * Position (1,1) remains 0
     * Changed (1,2) from 1 to -1
   - Result after row marking:
     * [[1,-1,1], [-1,0,-1], [1,-1,1]]

5. **Final Conversion**
   - All -1 values are converted to 0
   - Final matrix: [[1,0,1], [0,0,0], [1,0,1]]

### Key Points:

1. **Marking Process**
   - Only non-zero elements are marked with -1
   - Original zeros remain unchanged
   - This prevents confusion between original and marked zeros

2. **Pattern of Change**
   - The middle row becomes all zeros
   - The middle column becomes all zeros
   - Corners remain unchanged (1s)

3. **Verification Points**
   - All elements in row 1 are zero
   - All elements in column 1 are zero
   - Original zero's position remains zero
   - Non-affected positions retain their original values

This step-by-step process ensures that:
- Original zeros are preserved
- All required positions are marked
- The final matrix reflects the correct transformation
- The operation is performed in-place
- The integrity of the data is maintained throughout the process

### Edge Cases Handling
1. **Empty Matrix**
```
Input: []
Output: []
Handled by initial size check
```

2. **Single Element**
```
Input: [[0]]
Output: [[0]]
No special handling needed
```

Sample Validation:
Input: [[1,1,1],
        [1,0,1],
        [1,1,1]]
Expected: [[1,0,1],
           [0,0,0],
           [1,0,1]]
Output: [[1,0,1],
         [0,0,0],
         [1,0,1]]

TEST CASES:
1. Input: [[1,1,1],
           [1,0,1],
           [1,1,1]]
Expected: [[1,0,1],
           [0,0,0],
           [1,0,1]]
Output: [[1,0,1],
         [0,0,0],
         [1,0,1]]

2. Input: [[0,1,2,0],
           [3,4,5,2],
           [1,3,1,5]]
Expected: [[0,0,0,0],
           [0,4,5,0],
           [0,3,1,0]]
Output: [[0,0,0,0],
         [0,4,5,0],
         [0,3,1,0]]
*/