package Array.Pascal_Triangle.Variant3;

import java.util.ArrayList;
import java.util.List;

/* 
# Problem: Given the number of rows n. Print the first n rows of Pascal’s triangle.

## Optimal Approach:
### Intuition
Now, in the optimal approach of variation 2, we have learned how to generate a row in O(n) time complexity. So, in order to optimize the overall time complexity, we will be using that approach for every row. Thus the total time complexity will reduce. 

- Each number in a row is the sum of the two numbers directly above it
- First and last elements of each row are always 1
- Formula for calculating elements: C(n,r) = n!/(r! * (n-r)!)
- Can be optimized using previous row calculations

## Key Observations with Examples

### Observation 1: Row Pattern
- Each row starts and ends with 1
- Row n has n elements
- Elements are symmetric around the middle
Example: Row 4: [1, 4, 6, 4, 1]

### Observation 2: Element Calculation
Each element can be calculated using:
- Previous element × (row-col) / col
Example: In row 3, second element: 1 × (3-1) / 1 = 2

### Observation 3: Visual Structure
```
Row 1:     1
Row 2:    1 1
Row 3:   1 2 1
Row 4:  1 3 3 1
Row 5: 1 4 6 4 1
```

## Step-by-Step Example
Let's work through n = 5:

1. Generate Row 1:
   ```
   [1]
   Only one element, always 1
   ```

2. Generate Row 2:
   ```
   [1, 1]
   First and last elements are 1
   ```

3. Generate Row 3:
   ```
   [1, 2, 1]
   Calculate middle element: 1 × (3-1) / 1 = 2
   ```

## Special Cases

### Case 1: Single Row
Input: n = 1
- Behavior: Returns single row
- Result: [[1]]

### Case 2: Empty Input
Input: n = 0
- Behavior: Returns empty list
- Result: []
#
 */
public class Approach2 {
   // Optimal approach
    // Method to generate a single row of Pascal's triangle
    public static List<Integer> generateRow(int row) {
        long ans = 1;
        List<Integer> ansRow = new ArrayList<>();
        ansRow.add(1); // First element is always 1
        
        // Calculate remaining elements using formula:
        // element = previous_element * (row-col) / col
        for (int col = 1; col < row; col++) {
            ans = ans * (row - col);
            ans = ans / col;
            ansRow.add((int)ans);
        }
        return ansRow;
    }
    
    // Method to generate entire Pascal's triangle
    public static List<List<Integer>> pascalTriangle(int n) {
        List<List<Integer>> ans = new ArrayList<>();
        
        // Generate each row and add to result
        for (int row = 1; row <= n; row++) {
            ans.add(generateRow(row));
        }
        return ans;
    }
    
    // Main method for testing
    public static void main(String[] args) {
        int n = 5;
        List<List<Integer>> result = pascalTriangle(n);
        
        // Print triangle in formatted way
        for (int i = 0; i < result.size(); i++) {
            // Add spaces for triangle shape
            for (int j = 0; j < n - i - 1; j++) {
                System.out.print(" ");
            }
            // Print row elements
            for (int ele : result.get(i)) {
                System.out.print(ele + " ");
            }
            System.out.println();
        }
    }
}
/* 
# Algorithm: 
1. First, we will run a loop(say row) from 1 to n.
2. Inside the loop, we will call a generateRow() function and add the returned list to our final answer. 3. Inside the function we will do the following:
4. First, we will store the 1st element i.e. 1 manually.
5. After that, we will use a loop(say col) that runs from 1 to n-1. It will store the rest of the elements.
6. Inside the loop, we will use the specified formula to print the element. We will multiply the previous answer by (row-col) and then divide it by col itself.
7. Thus, the entire row will be stored and returned.
8. Finally, we will return the answer list.


### Time Complexity Breakdown per Step
1. Row generation: O(n)
2. Total rows: O(n)
Total: O(n²)

### Space Complexity Breakdown
1. Auxiliary Space: O(1) (excluding output space)
2. Output Space: O(n²) (for storing triangle)
Total: O(n²)

# Advantages
1. Optimal time complexity O(n²)
2. No need for factorial calculations
3. Handles large inputs efficiently
4. Memory efficient (generates one row at a time)

# Limitations
1. Integer overflow for large n
2. Limited by available memory for large n
3. Not suitable for calculating single elements in large rows

# Potential Improvements
1. Use BigInteger for handling larger numbers
2. Implement parallel processing for large triangles
3. Add memoization for frequently accessed rows

I'll create a detailed step-by-step explanation of Pascal's Triangle generation with a comprehensive table and visual representation.

# Detailed Execution Table with Dry Run for n = 5

```
Step | Row | Previous Row | Current Calculation                | Result Row    | Triangle State
-----|-----|--------------|-----------------------------------|---------------|------------------
1    | 1   | None        | First row is always [1]           | [1]          | [[1]]
2    | 2   | [1]         | ans = 1                          | [1,1]        | [[1], [1,1]]
3    | 3   | [1,1]       | ans = 1 * (3-1)/1 = 2           | [1,2,1]      | [[1], [1,1], [1,2,1]]
4    | 4   | [1,2,1]     | ans1 = 1 * (4-1)/1 = 3          | [1,3,3,1]    | [[1], [1,1], [1,2,1], [1,3,3,1]]
     |     |             | ans2 = 3 * (4-2)/2 = 3           |              |
5    | 5   | [1,3,3,1]   | ans1 = 1 * (5-1)/1 = 4          | [1,4,6,4,1]  | [[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]
     |     |             | ans2 = 4 * (5-2)/2 = 6           |              |
     |     |             | ans3 = 6 * (5-3)/3 = 4           |              |
```

# Step-by-Step Process Explanation

## Row 1 Generation:
1. Initialize first row with [1]
2. No calculations needed
3. Result: [1]

## Row 2 Generation:
1. Start with [1]
2. For col=1:
   - ans = 1 (no calculation needed)
3. Add final 1
4. Result: [1,1]

## Row 3 Generation:
1. Start with [1]
2. For col=1:
   - ans = 1 * (3-1)/1 = 2
3. Add final 1
4. Result: [1,2,1]

## Row 4 Generation:
1. Start with [1]
2. For col=1:
   - ans = 1 * (4-1)/1 = 3
3. For col=2:
   - ans = 3 * (4-2)/2 = 3
4. Add final 1
5. Result: [1,3,3,1]

## Row 5 Generation:
1. Start with [1]
2. For col=1:
   - ans = 1 * (5-1)/1 = 4
3. For col=2:
   - ans = 4 * (5-2)/2 = 6
4. For col=3:
   - ans = 6 * (5-3)/3 = 4
5. Add final 1
6. Result: [1,4,6,4,1]

# Formula Breakdown for Each Element:
For any position (row,col):
```
Current_Element = Previous_Element * (row-col) / col

Where:
- row = current row number (1-based)
- col = current column position (1-based)
- Previous_Element = last calculated element in current row
```

# Visual Triangle Growth:
```
Step 1:     1

Step 2:    1 1

Step 3:   1 2 1

Step 4:  1 3 3 1

Step 5: 1 4 6 4 1
```

# Key Points for Understanding:
1. Each row starts and ends with 1
2. Each new element is calculated using the formula
3. Number of elements in each row equals row number
4. Each row is symmetric
5. Elements grow larger towards the middle of each row
6. The element at position (row,col) represents C(row-1,col-1)

### Edge Cases Handling
1. **n = 0**
   ```
   Input: 0
   Output: []
   Explanation: Empty triangle
   ```

2. **n = 1**
   ```
   Input: 1
   Output: [[1]]
   Explanation: Single row
   ```

TEST CASES:
1. Input: n = 5
   Expected: [[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]
   Output: [[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]

2. Input: n = 2
   Expected: [[1], [1,1]]
   Output: [[1], [1,1]]

3. Input: n = 0
   Expected: []
   Output: []
 */