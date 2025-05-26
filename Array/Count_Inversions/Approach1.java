package Array.Count_Inversions;
/* 
# Problem: Count Inversions
For a given integer array/list 'ARR' of size 'N' containing all distinct values, find the total number of 'Inversions' that may exist.

An inversion is defined for a pair of integers in the array/list when the following two conditions are met.
A pair ('ARR[i]', 'ARR[j]') is said to be an inversion when:
1. 'ARR[i] > 'ARR[j]' 
2. 'i' < 'j'
Where 'i' and 'j' denote the indices ranging from [0, 'N').

## Intution: The naive approach is pretty straightforward. We will use nested loops to solve this problem. We know index i must be smaller than index j. So, we will fix i at one index at a time through a loop, and with another loop, we will check(the condition a[i] > a[j]) the elements from index i+1 to N-1  if they can form a pair with a[i]. This is the first naive approach we can think of.
*/
public class Approach1 {
    // Approach 1: Using nested loops
    public static int numberOfInversions(int[] a, int n) {
        // Initialize a counter to keep track of inversions
        int cnt = 0;
        
        // Outer loop: iterate through each element
        for (int i = 0; i < n; i++) {
            // Inner loop: compare current element with all subsequent elements
            for (int j = i + 1; j < n; j++) {
                // If current element is greater than a subsequent element, it forms an inversion
                if (a[i] > a[j]) {
                    cnt++; // Increment the inversion counter
                }
            }
        }
        return cnt; // Return the total count of inversions
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test array [5,4,3,2,1] - a reverse sorted array (maximum inversions)
        int[] a = {5, 4, 3, 2, 1};
        int n = 5;
        
        // Call the function to count inversions
        int cnt = numberOfInversions(a, n);
        
        // Print the result
        System.out.println("The number of inversions is: " + cnt);
    }
}
/*
# Algorithm
1. Initialize an inversion counter to 0
2. Use an outer loop to iterate through each element of the array (index i from 0 to n-1)
3. For each element at index i, use an inner loop to compare it with all subsequent elements (index j from i+1 to n-1)
4. If a[i] > a[j], increment the inversion counter
5. Return the final count of inversions

### Time Complexity Breakdown per Step
1. Outer loop: O(n)
2. Inner loop: O(n)
3. Comparison: O(1)

Total: O(n²) - We compare each pair of elements exactly once

### Space Complexity Breakdown
1. Auxiliary Space: O(1) - We only use a single counter variable
   - No additional data structures needed
2. Input Space: O(n) - The input array of size n
   - No modifications to the input array

Total: O(1) auxiliary space

# Advantages
1. Simple and intuitive implementation - easy to understand and code
2. No additional space required beyond the input array
3. Works well for small arrays where O(n²) is acceptable
4. Direct approach without any preprocessing required

# Limitations
1. O(n²) time complexity makes it inefficient for large arrays
2. Not optimal for real-world large datasets
3. Cannot be parallelized easily due to the sequential nature of the nested loops

# Potential Improvements
1. Use a modified merge sort algorithm to count inversions in O(n log n) time
2. Use a Binary Indexed Tree (Fenwick Tree) for a more efficient solution
3. Use a Segment Tree for counting inversions in more complex scenarios

# Step-by-Step Process with Dry Run

## Example Input: [3, 5, 2, 1, 4]

### Detailed Execution Table
```
Step | i | j | a[i] | a[j] | Condition | Count | Explanation
-----|---|---|------|------|-----------|-------|-------------
1    | 0 | 1 | 3    | 5    | 3 > 5? No | 0     | Not an inversion
2    | 0 | 2 | 3    | 2    | 3 > 2? Yes| 1     | Found inversion
3    | 0 | 3 | 3    | 1    | 3 > 1? Yes| 2     | Found inversion
4    | 0 | 4 | 3    | 4    | 3 > 4? No | 2     | Not an inversion
5    | 1 | 2 | 5    | 2    | 5 > 2? Yes| 3     | Found inversion
6    | 1 | 3 | 5    | 1    | 5 > 1? Yes| 4     | Found inversion
7    | 1 | 4 | 5    | 4    | 5 > 4? Yes| 5     | Found inversion
8    | 2 | 3 | 2    | 1    | 2 > 1? Yes| 6     | Found inversion
9    | 2 | 4 | 2    | 4    | 2 > 4? No | 6     | Not an inversion
10   | 3 | 4 | 1    | 4    | 1 > 4? No | 6     | Not an inversion
```

### Step-by-Step Explanation

1. **Initialize Variables**
   - Set cnt = 0 to count inversions
   ```
   Array: [3, 5, 2, 1, 4]
   cnt = 0
   ```

2. **Process i=0 (value=3)**
   - Compare with all elements to the right
   - Found inversions: (3,2) and (3,1)
   ```
   cnt = 2
   ```

3. **Process i=1 (value=5)**
   - Compare with all elements to the right
   - Found inversions: (5,2), (5,1), and (5,4)
   ```
   cnt = 5
   ```

4. **Process i=2 (value=2)**
   - Compare with all elements to the right
   - Found inversion: (2,1)
   ```
   cnt = 6
   ```

5. **Process i=3 (value=1)**
   - Compare with all elements to the right
   - No inversions found
   ```
   cnt = 6
   ```

### Additional Example Cases

1. **Sorted Array**
```
Input:  [1, 2, 3, 4, 5]
Process: No element is greater than any element after it
Output: 0 inversions
```

2. **Random Array**
```
Input:  [4, 1, 3, 5, 2]
Step 1: For i=0 (value=4), inversions: (4,1), (4,3), (4,2) → cnt=3
Step 2: For i=1 (value=1), no inversions → cnt=3
Step 3: For i=2 (value=3), inversion: (3,2) → cnt=4
Step 4: For i=3 (value=5), inversion: (5,2) → cnt=5
Output: 5 inversions
```

### Edge Cases Handling
1. **Empty Array**
   ```
   Input: []
   Output: 0
   Explanation: No elements to compare, so no inversions
   ```

2. **Single Element Array**
   ```
   Input: [5]
   Output: 0
   Explanation: Need at least two elements to form an inversion
   ```

3. **All Equal Elements**
   ```
   Input: [3, 3, 3, 3]
   Output: 0
   Explanation: Since we need a[i] > a[j], equal elements don't form inversions
   ```

Key Observations:
1. The brute force approach directly implements the definition of inversion
2. Runtime increases quadratically with array size
3. The maximum number of inversions in an array of size n is n*(n-1)/2
4. The algorithm performs exactly n*(n-1)/2 comparisons regardless of array content

Sample Validation:
Input: [5, 4, 3, 2, 1]
Expected: 10 inversions
Output: 10 inversions

TEST CASES:
1. Input: [5, 4, 3, 2, 1]
   Expected: 10
   Output: 10
2. Input: [1, 2, 3, 4, 5]
   Expected: 0
   Output: 0
3. Input: [2, 4, 1, 3, 5]
   Expected: 3
   Output: 3
4. Input: [1, 20, 6, 4, 5]
   Expected: 5
   Output: 5
 */