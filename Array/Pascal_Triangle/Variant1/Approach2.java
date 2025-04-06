package Array.Pascal_Triangle.Variant1;
/* 
# Problem Title: Finding Element in Pascal's Triangle at Position (r,c)
# Problem: Given row number r and column number c. Print the element at position (r, c) in Pascal’s triangle.

## Optimal Approach:
### Intution:
We can optimize this calculation by the following observation. 
Assume, given r = 7, c = 4. 
Now, n = r-1 = 7-1 = 6 and r = c-1 = 4-1 = 3
Let’s calculate 6C3 = 6! / (3! *(6-3)!) = (6*5*4*3*2*1) / ((3*2*1)*(3*2*1))
This will boil down to (6*5*4) / (3*2*1)

So, nCr = (n*(n-1)*(n-2)*.....*(n-r+1)) / (r*(r-1)*(r-2)*....1)
                                
Now, we will use this optimized formula to calculate the value of nCr. But while implementing this into code we will take the denominator in the forward direction like: 
(n / 1)*((n-1) / 2)*.....*((n-r+1) / r).

The core insight is that any element at position (r,c) in Pascal's Triangle can be calculated using the combination formula: ⁿCᵣ where n = r-1 and r = c-1.
- Instead of generating the entire triangle, we can directly calculate the specific element
- Uses an optimized version of the combination formula

## Basic Understanding
Pascal's Triangle is a triangular array where each number is the sum of the two numbers directly above it. We need to find the value at a specific position (r,c) without generating the entire triangle.

## Key Observations with Examples

### Observation 1: Combination Pattern
Each element in Pascal's Triangle at position (r,c) can be calculated as: (r-1)C(c-1)
* Example: For position (5,3), we calculate ⁴C²
* This gives us a direct formula instead of building the whole triangle

### Observation 2: Optimized Calculation
Instead of calculating full factorials, we can optimize the combination calculation:
```
For 6C3:
Traditional: (6!) / (3! * 3!)
Optimized: (6 * 5 * 4) / (3 * 2 * 1)
```

## Step-by-Step Example
Let's work through finding element at position (5,3):

1. Convert to Combination Problem:
   ```
   r = 5, c = 3
   n = r-1 = 4
   r = c-1 = 2
   Need to calculate: ⁴C²
   ```

2. Calculate Using Optimized Formula:
   ```
   Result = (4 * 3) / (2 * 1)
   Result = 12 / 2
   Result = 6
   ```

## Special Cases

### Case 1: First Column
Input: r = any, c = 1
- Always returns 1
- Result: 1

### Case 2: Last Column
Input: r = n, c = n
- Same as first column
- Result: 1

### Case 3: Second Column
Input: r = n, c = 2
- Equals to (r-1)
- Result: r-1

*/
public class Approach2 {
    // Approach 2: Without using factorial
    // Method to calculate combination (nCr)
    public static long nCr(int n, int r) {
        long res = 1;
        
        // Optimized combination calculation
        for (int i = 0; i < r; i++) {
            // Multiply numerator terms (n-i)
            res = res * (n - i);
            // Divide by denominator terms (i+1)
            res = res / (i + 1);
        }
        return res;
    }

    // Method to get element at position (r,c)
    public static int pascalTriangle(int r, int c) {
        // Convert position to combination problem
        int element = (int) nCr(r - 1, c - 1);
        return element;
    }

    // Main method with test cases
    public static void main(String[] args) {
        // Test different positions
        int[][] testCases = {{5,3}, {4,2}, {6,4}, {3,1}};
        
        for (int[] test : testCases) {
            int r = test[0];
            int c = test[1];
            int element = pascalTriangle(r, c);
            System.out.printf("Element at position (%d,%d): %d%n", 
                            r, c, element);
        }
    }
}
/*
# Algorithm
1. Convert position (r,c) to combination problem (r-1)C(c-1)
2. Initialize result as 1
3. For i from 0 to (c-1):
   - Multiply result by (n-i)
   - Divide result by (i+1)
4. Return final result

### Time Complexity Breakdown per Step
1. Position conversion: O(1)
2. Combination calculation: O(r)
Total: O(r) where r is the column number

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
   - Only using a single variable for calculation
2. Input Space: O(1)
   - Only storing row and column numbers
Total: O(1)

# Advantages
1. Direct calculation without generating entire triangle
2. Optimized combination calculation
3. Constant space complexity
4. Handles large position values efficiently

# Limitations
1. May have integer overflow for large values
2. Limited by numerical precision
3. Not suitable when multiple elements are needed

# Step-by-Step Process with Dry Run

## Example Input: r=5, c=3
I'll provide a detailed step-by-step breakdown of the Pascal's Triangle element calculation with visualization and explanation.

# Detailed Step-by-Step Process

Let's analyze multiple test cases with a comprehensive table representation:

## Test Case 1: r=5, c=3
```
Initial Values: r=5, c=3
Converting to nCr: n=4 (r-1), r=2 (c-1)
Need to calculate: ⁴C₂

Step | Current Operation    | Calculation | Result | Running Total | Explanation
-----|---------------------|-------------|---------|---------------|-------------
1    | Initialize          | res = 1     | 1       | 1            | Start with 1
2    | First multiply      | 1 * 4       | 4       | 4            | Multiply by (n-0)
3    | First divide        | 4 / 1       | 4       | 4            | Divide by (0+1)
4    | Second multiply     | 4 * 3       | 12      | 12           | Multiply by (n-1)
5    | Second divide       | 12 / 2      | 6       | 6            | Divide by (1+1)
Final Result: 6
```

## Test Case 2: r=6, c=4
```
Initial Values: r=6, c=4
Converting to nCr: n=5 (r-1), r=3 (c-1)
Need to calculate: ⁵C₃

Step | Current Operation    | Calculation | Result | Running Total | Explanation
-----|---------------------|-------------|---------|---------------|-------------
1    | Initialize          | res = 1     | 1       | 1            | Start with 1
2    | First multiply      | 1 * 5       | 5       | 5            | Multiply by (n-0)
3    | First divide        | 5 / 1       | 5       | 5            | Divide by (0+1)
4    | Second multiply     | 5 * 4       | 20      | 20           | Multiply by (n-1)
5    | Second divide       | 20 / 2      | 10      | 10           | Divide by (1+1)
6    | Third multiply      | 10 * 3      | 30      | 30           | Multiply by (n-2)
7    | Third divide        | 30 / 3      | 10      | 10           | Divide by (2+1)
Final Result: 10
```

# Detailed Explanation of Steps

1. **Initialization Phase**
   - Convert position (r,c) to combination problem
   - Set n = r-1 and r = c-1
   - Initialize result variable to 1

2. **Calculation Loop**
   - For each iteration i from 0 to r-1:
     * Multiply by (n-i)
     * Divide by (i+1)

3. **Step-by-Step Process Explained**

For r=5, c=3 example:
```
a) Converting to Combination Problem:
   - n = 5-1 = 4
   - r = 3-1 = 2
   - Need to calculate ⁴C₂

b) First Iteration (i=0):
   - Multiply: 1 * (4-0) = 4
   - Divide: 4 / (0+1) = 4

c) Second Iteration (i=1):
   - Multiply: 4 * (4-1) = 12
   - Divide: 12 / (1+1) = 6

d) Final Result: 6
```

# Visual Representation

For r=5, c=3 in Pascal's Triangle:
```
Row 1:                1
Row 2:             1     1
Row 3:          1     2     1
Row 4:       1     3     3     1
Row 5:    1     4     6     4     1
                      ↑
                Target Element (6)
```

# Key Points to Remember:
1. The algorithm always uses optimized combination calculation
2. For first column (c=1) or last column (c=r), result is always 1
3. Each step involves one multiplication and one division
4. The process maintains integer values throughout calculation
5. The running total at each step represents partial combination value

This breakdown shows how the algorithm efficiently calculates Pascal's Triangle elements without generating the entire triangle. The step-by-step process ensures accuracy while maintaining optimal time and space complexity.

Would you like me to explain any specific part in more detail?

### Edge Cases Handling
1. **First Column**
   ```
   Input: r=5, c=1
   Output: 1
   Always returns 1 for first column
   ```

2. **Same Row Column**
   ```
   Input: r=5, c=5
   Output: 1
   Always returns 1 when row equals column
   ```

TEST CASES:
1. Input: r=5, c=3
   Expected: 6
   Output: 6
2. Input: r=4, c=2
   Expected: 3
   Output: 3
3. Input: r=6, c=4
   Expected: 15
   Output: 15

 */