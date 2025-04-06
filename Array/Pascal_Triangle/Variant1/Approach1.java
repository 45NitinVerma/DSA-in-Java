package Array.Pascal_Triangle.Variant1;
/* 
# Problem Title: Finding Element in Pascal's Triangle at Position (r,c)
# Problem: Given row number r and column number c. Print the element at position (r, c) in Pascal’s triangle.

## Naive Approach
### Intuition:
In this case, we are given the row number r and the column number c, and we need to find out the element at position (r,c). 

One of the Naive approaches is to generate the entire Pascal triangle and then find the element at position (r,c). We will discuss in variation 3 how to generate Pascal’s triangle.

We have an easier formula to find out the element i.e. r-1Cc-1. Let’s try to analyze the formula to find the value of the combination assuming r-1 as n and c-1 as r:

nCr = n! / (r! * (n-r)!)

Calculating the value of nCr:
We can separately calculate n!, r!, (n-r)! and using their values we can calculate nCr. This is an extremely naive way to calculate. The time complexity will be O(n)+O(r)+O(n-r).

- We need to find the element at position (r,c) in Pascal's triangle
- Can be calculated using combination formula: C(r-1,c-1)
- Element at any position (r,c) represents how many ways we can choose c-1 items from r-1 items

## Basic Understanding
The element at position (r,c) in Pascal's triangle can be found using the combination formula:
nCr = n!/(r! * (n-r)!) where n = r-1 and r = c-1

## Key Observations with Examples

### Observation 1: Position Mapping
- Row and column numbers are 1-based
- For position (r,c), we calculate C(r-1,c-1)
Example: Position (4,2) corresponds to C(3,1) = 3

### Observation 2: Factorial Pattern
Each element can be calculated using the factorial formula:
nCr = n!/(r! * (n-r)!)
Example: C(4,2) = 4!/(2! * 2!) = 6

## Step-by-Step Example
Let's calculate element at position (5,3):

1. Convert to combination:
   ```
   n = 5-1 = 4
   r = 3-1 = 2
   Calculate C(4,2)
   ```

2. Apply formula:
   ```
   4!/(2! * 2!)
   = 24/(2 * 2)
   = 6
   ```
*/
public class Approach1 {
    // Appraoch 1: Using Factorial Method
    // Calculate factorial of a number
    public static long factorial(int num) {
        long fact = 1;
        for(int i = 2; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }
    
    // Calculate combination nCr using factorial method
    public static long calculateCombination(int n, int r) {
        // Calculate n!, r! and (n-r)!
        long nFact = factorial(n);
        long rFact = factorial(r);
        long nrFact = factorial(n - r);
        
        // Apply combination formula
        return nFact / (rFact * nrFact);
    }
    
    // Find element at position (r,c) in Pascal's triangle
    public static long pascalTriangleElement(int row, int col) {
        // Check if position is valid
        if(col > row) {
            return 0;
        }
        
        // Convert to 0-based indexing for combination
        int n = row - 1;
        int r = col - 1;
        
        return calculateCombination(n, r);
    }
    
    // Main method for testing
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
            {5, 3},  // Expected: 6
            {4, 2},  // Expected: 3
            {3, 3},  // Expected: 1
            {6, 4}   // Expected: 15
        };
        
        for(int[] test : testCases) {
            int row = test[0];
            int col = test[1];
            System.out.printf("Element at position (%d,%d): %d%n", 
                            row, col, 
                            pascalTriangleElement(row, col));
        }
    }
}

/*
# Algorithm
1. Check if position (r,c) is valid
2. Convert to 0-based indexing: n = r-1, k = c-1
3. Calculate n!
4. Calculate r!
5. Calculate (n-r)!
6. Return n!/(r! * (n-r)!)

### Time Complexity Breakdown per Step
1. Factorial calculation for n: O(n)
2. Factorial calculation for r: O(r)
3. Factorial calculation for (n-r): O(n-r)
Total: O(n)

### Space Complexity Breakdown
1. Auxiliary Space: O(1)
2. Input Space: O(1)
Total: O(1)

# Advantages
1. Simple to understand and implement
2. Direct mathematical approach
3. Constant space complexity
4. Works for any valid position

# Limitations
1. Integer overflow for large numbers
2. Time complexity O(n) can be improved
3. Not efficient for calculating multiple elements
4. Limited by factorial calculations

# Step-by-Step Process with Dry Run

## Example Input: row = 5, col = 3

### Detailed Execution Table
```
Step | Operation          | Calculation      | Result
-----|-------------------|------------------|--------
1    | Convert indices   | n=4, r=2         | -
2    | Calculate 4!      | 4*3*2*1         | 24
3    | Calculate 2!      | 2*1             | 2
4    | Calculate (4-2)!  | 2*1             | 2
5    | Final calculation | 24/(2*2)        | 6
```

### Edge Cases Handling
1. **Column > Row**
   ```
   Input: row=3, col=4
   Output: 0
   Explanation: Invalid position
   ```

2. **First/Last Column**
   ```
   Input: row=5, col=1 or col=5
   Output: 1
   Explanation: Edges are always 1
   ```

TEST CASES:
1. Input: row=5, col=3
   Expected: 6
   Output: 6

2. Input: row=4, col=2
   Expected: 3
   Output: 3

3. Input: row=3, col=4
   Expected: 0
   Output: 0

This implementation provides a clear understanding of how to calculate any element in Pascal's triangle using the factorial method, though there are more efficient approaches available for larger numbers.
*/