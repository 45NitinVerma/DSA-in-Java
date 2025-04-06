package Array.Pascal_Triangle.Variant2;
/* 
# Problem: Given the row number n. Print the n-th row of Pascal’s triangle.
## Intution: Our first observation regarding Pascal’s triangle should be that the n-th row of the triangle has exactly n elements. With this observation, we will proceed to solve this problem.

## Optimal Approach
Pascal's triangle is a triangular array where each number is the sum of the two numbers directly above it. For generating the nth row, we can use the mathematical relationship between elements rather than generating the entire triangle.

Here, we can observe that the numbers marked in red are added to the previous number to build the current number. In each step, the numerator is multiplied by the previous consecutive element, and the denominator is multiplied by the next consecutive element.

We will replicate this in our algorithm. First, we will mark the indices of the column starting from 0. Then we will simply multiply the numerator by (n-index) and the denominator by the index itself.

From column no. 1 the formula will be the following:
Current element = prevElement * (rowNumber - colIndex) / colIndex
Note: If we want to store the row elements, we just need to store each element instead of printing it.



## Basic Understanding
Each row n in Pascal's triangle contains n elements. Each element can be calculated using combinations (nCr) where n is the row number and r is the position in the row (0-based indexing).

## Key Observations with Examples

### Observation 1: Pattern in Row Elements
- First and last elements are always 1
- Each row is symmetric
- Row n has exactly n elements
```
Row 1:     1
Row 2:    1 1
Row 3:   1 2 1
Row 4:  1 3 3 1
Row 5: 1 4 6 4 1
```

### Observation 2: Mathematical Relationship
Each element can be calculated from the previous element using:
```
current = previous * (n - col) / col
```
Example: For n=5, col=1:
```
previous = 1
current = 1 * (5-1) / 1 = 4
```

## Step-by-Step Example
Let's generate the 4th row (n=5):

1. First Element:
   ```
   ans = 1
   Print: 1
   ```

2. Second Element:
   ```
   ans = 1 * (5-1) / 1 = 4
   Print: 1 4
   ```

3. Third Element:
   ```
   ans = 4 * (5-2) / 2 = 6
   Print: 1 4 6
   ```

4. Fourth Element:
   ```
   ans = 6 * (5-3) / 3 = 4
   Print: 1 4 6 4
   ```

5. Fifth Element:
   ```
   ans = 4 * (5-4) / 4 = 1
   Print: 1 4 6 4 1
   ```
*/
public class Approach2 {
    // Optimal Approach
    // Method to generate and print nth row of Pascal's Triangle
    public static void generatePascalRow(int n) {
        // First element is always 1
        long ans = 1;
        System.out.print(ans + " ");
        
        // Generate remaining elements using the formula
        for (int i = 1; i < n; i++) {
            // Calculate next element using the formula:
            // current = previous * (n-col) / col
            ans = ans * (n - i);  // Multiply with numerator
            ans = ans / i;        // Divide by denominator
            System.out.print(ans + " ");
        }
        System.out.println();
    }

    // Main method for testing
    public static void main(String[] args) {
        int n = 5;  // Generate 5th row
        System.out.println("Generating " + n + "th row of Pascal's Triangle:");
        generatePascalRow(n);
    }
}
/*
# Algorithm
1. Initialize and print first element as 1 manually
2. For each position i from 1 to n-1:
   - Multiply previous answer by (n-i)
   - Divide result by i
   - Print the result
3. Print newline

### Time Complexity Breakdown
1. First element printing: O(1)
2. Loop for remaining elements: O(n)
Total: O(n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - Only using a single variable 'ans'
2. Input Space: O(1)
   - Only storing row number n
Total: O(1)

# Advantages
1. Optimal time complexity O(n)
2. Constant space complexity
3. Handles large numbers efficiently using long datatype
4. Direct calculation without generating entire triangle

# Limitations
1. Might overflow for very large row numbers
2. Only generates one row at a time
3. Prints results directly (can't store for further use)

# Step-by-Step Process with Dry Run

## Example Input: n = 4

I'll provide a more detailed breakdown with tables and step-by-step explanations.

# Detailed Step-by-Step Process with Tables

## Input: n = 5 (Generate 5th row of Pascal's Triangle)

### Step-by-Step Execution Table:
```
Step | Previous | Formula                 | Calculation      | Result | Running Output
-----|----------|------------------------|------------------|---------|---------------
1    | -        | Initial value = 1      | 1               | 1       | 1
2    | 1        | 1 * (5-1)/1           | 1 * 4/1 = 4     | 4       | 1 4
3    | 4        | 4 * (5-2)/2           | 4 * 3/2 = 6     | 6       | 1 4 6
4    | 6        | 6 * (5-3)/3           | 6 * 2/3 = 4     | 4       | 1 4 6 4
5    | 4        | 4 * (5-4)/4           | 4 * 1/4 = 1     | 1       | 1 4 6 4 1
```

### Variable Tracking Table:
```
Step | i | n-i | Previous ans | Numerator (ans * (n-i)) | Denominator (i) | New ans
-----|---|----|--------------|-------------------------|-----------------|----------
1    | - | -  | 1           | -                       | -              | 1
2    | 1 | 4  | 1           | 1 * 4 = 4              | 1              | 4
3    | 2 | 3  | 4           | 4 * 3 = 12             | 2              | 6
4    | 3 | 2  | 6           | 6 * 2 = 12             | 3              | 4
5    | 4 | 1  | 4           | 4 * 1 = 4              | 4              | 1
```

### Detailed Step-by-Step Explanation:

1. **Initialization (Step 1)**:
   - Set initial value `ans = 1`
   - Print first value: 1
   - Current Output: `1`

2. **First Iteration (Step 2)**:
   - i = 1
   - Previous ans = 1
   - Calculate: ans = ans * (n-i)/i
   - ans = 1 * (5-1)/1 = 4
   - Current Output: `1 4`

3. **Second Iteration (Step 3)**:
   - i = 2
   - Previous ans = 4
   - Calculate: ans = ans * (5-2)/2
   - ans = 4 * 3/2 = 6
   - Current Output: `1 4 6`

4. **Third Iteration (Step 4)**:
   - i = 3
   - Previous ans = 6
   - Calculate: ans = ans * (5-3)/3
   - ans = 6 * 2/3 = 4
   - Current Output: `1 4 6 4`

5. **Fourth Iteration (Step 5)**:
   - i = 4
   - Previous ans = 4
   - Calculate: ans = ans * (5-4)/4
   - ans = 4 * 1/4 = 1
   - Final Output: `1 4 6 4 1`

### Memory State Table:
```
Variable | Initial | After Step 1 | After Step 2 | After Step 3 | After Step 4 | After Step 5
---------|---------|-------------|--------------|--------------|--------------|-------------
n        | 5       | 5           | 5            | 5            | 5            | 5
i        | -       | -           | 1            | 2            | 3            | 4
ans      | -       | 1           | 4            | 6            | 4            | 1
```

### Key Points from the Execution:

1. **Pattern Observations**:
   - Each element is calculated using only the previous element
   - The numerator decreases by 1 each step (n-i)
   - The denominator increases by 1 each step (i)

2. **Value Changes**:
   - Values increase until middle: 1 → 4 → 6
   - Then decrease symmetrically: 6 → 4 → 1

3. **Formula Progression**:
   - Step 1: Direct assignment (1)
   - Step 2: 1 * 4/1 = 4
   - Step 3: 4 * 3/2 = 6
   - Step 4: 6 * 2/3 = 4
   - Step 5: 4 * 1/4 = 1

4. **Memory Usage**:
   - Only three variables used throughout (n, i, ans)
   - Previous value constantly updated
   - No additional storage needed

This detailed breakdown shows how the algorithm efficiently generates the nth row of Pascal's Triangle using minimal space and optimal time complexity.

### Edge Cases Handling
1. **n = 1**
   ```
   Input: 1
   Output: 1
   [Single element row]
   ```

2. **n = 2**
   ```
   Input: 2
   Output: 1 1
   [Two element row]
   ```

TEST CASES:
1. Input: 1
   Expected: 1
   Output: 1
2. Input: 3
   Expected: 1 2 1
   Output: 1 2 1
3. Input: 5
   Expected: 1 4 6 4 1
   Output: 1 4 6 4 1
*/