/* 
Problem: Find the contiguous subarray within an array of integers that has the maximum sum.

Intuition: Generate all possible subarrays using three nested loops:
- Outer two loops (i,j) to generate all possible subarray boundaries
- Inner loop (k) to calculate sum of elements within those boundaries
*/

package Array.Maximum_Subarray;
public class Appraoch1 {
   // Approach 1: Brute Force - Using 3 loops
    public static int maxSubarraySum(int[] arr, int n) {
        // Initialize maximum sum to smallest possible integer
        int maxi = Integer.MIN_VALUE;

        // First loop to fix the starting point
        for (int i = 0; i < n; i++) {
            // Second loop to fix the ending point
            for (int j = i; j < n; j++) {
                int sum = 0;  // Reset sum for new subarray

                // Third loop to calculate sum of current subarray arr[i..j]
                for (int k = i; k <= j; k++) {
                    sum += arr[k];  // Add each element to current sum
                }

                // Update maximum if current sum is larger
                maxi = Math.max(maxi, sum);
            }
        }
        return maxi;
    }

    public static void main(String args[]) {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int n = arr.length;
        int maxSum = maxSubarraySum(arr, n);
        System.out.println("The maximum subarray sum is: " + maxSum);
    }
}

/* 
ALGORITHM:
1. Initialize maximum sum (maxi) to minimum integer value
2. Use two nested loops:
   - Outer loop: `i` as the starting index of the subarray.
   - Inner loop: `j` as the ending index of the subarray.
3. For each pair `(i, j)`, calculate the sum of the subarray `arr[i...j]`:
   - Use another loop to iterate from `i` to `j` and calculate the sum.
4. Update `maxi` with the maximum value between `maxi` and the current sum.
5. Return `maxi`.

Complexity Analysis:
TIME COMPLEXITY: O(n³)
- Three nested loops: i (n) * j (n) * k (n) = n³

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using constant extra space for variables maxi and sum
2. Input Space: O(n)
   - Space needed for input array of size n

Advantages:
1. Simple to understand and implement
2. Works well for very small arrays
3. No additional space required

Limitations:
1. Very high time complexity O(n³)
2. Inefficient for large arrays
3. Performs redundant calculations of sums

Potential Improvements:
1. Use Kadane's Algorithm to achieve O(n) time complexity
2. Use prefix sum array to calculate subarray sums in O(1)
3. Use divide and conquer approach for O(nlogn) solution
4. Implement parallel processing for large arrays
5. Add input validation and error handling

INPUT ARRAY: [-2, 1, -3, 4, -1, 2, 1, -5, 4]

DETAILED EXECUTION FLOW WITH SUBARRAYS AND SUMS:
-------------------------------------------------------------------------------------------------------------
i | j | k         | Current Subarray     | Sum Calculation                    | Current Sum | Maximum Sum
-------------------------------------------------------------------------------------------------------------
0 | 0 | 0         | [-2]                 | -2                                  | -2         | -2
0 | 1 | 0,1       | [-2, 1]              | -2 + 1                              | -1         | -1
0 | 2 | 0,1,2     | [-2, 1, -3]          | -2 + 1 + (-3)                       | -4         | -1
0 | 3 | 0,1,2,3   | [-2, 1, -3, 4]       | -2 + 1 + (-3) + 4                   | 0          | 0
0 | 4 | 0,1,2,3,4 | [-2, 1, -3, 4, -1]   | -2 + 1 + (-3) + 4 + (-1)            | -1         | 0
...
1 | 1 | 1         | [1]                  | 1                                   | 1          | 1
1 | 2 | 1,2       | [1, -3]              | 1 + (-3)                            | -2         | 1
1 | 3 | 1,2,3     | [1, -3, 4]           | 1 + (-3) + 4                        | 2          | 2
1 | 4 | 1,2,3,4   | [1, -3, 4, -1]       | 1 + (-3) + 4 + (-1)                 | 1          | 2
...
3 | 3 | 3         | [4]                  | 4                                   | 4          | 4
3 | 4 | 3,4       | [4, -1]              | 4 + (-1)                            | 3          | 4
3 | 5 | 3,4,5     | [4, -1, 2]           | 4 + (-1) + 2                        | 5          | 5
3 | 6 | 3,4,5,6   | [4, -1, 2, 1]        | 4 + (-1) + 2 + 1                    | 6          | 6
...
8 | 8 | 8 | [4]                          | 4                                   | 4          | 6
-------------------------------------------------------------------------------------------------------------

STEP-BY-STEP EXPLANATION:

1. INITIALIZATION:
   - maxi = Integer.MIN_VALUE
   - Array length n = 9

2. OUTER LOOP (i=0):
   - Starts from index 0
   - This determines the starting point of subarrays
   
3. MIDDLE LOOP (j=i to n-1):
   - For each i, j moves from i to end of array
   - This determines the ending point of subarrays

4. INNER LOOP (k=i to j):
   - For each subarray [i...j], calculates sum
   - Adds each element from index i to j

5. KEY ITERATIONS:
   a) First subarray (i=0,j=0):
      - Just includes -2
      - maxi updated to -2

   b) Finding first positive sum (i=1,j=1):
      - Single element [1]
      - maxi updated to 1

   c) Finding maximum subarray (i=3,j=6):
      - Subarray [4,-1,2,1]
      - Sum = 6
      - maxi updated to 6

FINAL RESULT ANALYSIS:
-------------------------------------------------------------------------------------------------------------
Maximum Sum: 6
Subarray Elements: [4, -1, 2, 1]
Starting Index: 3
Ending Index: 6

VERIFICATION TABLE:
-------------------------------------------------------------------------------------------------------------
Subarray            | Sum | Is Maximum?
-------------------------------------------------------------------------------------------------------------
[-2]                | -2  | No
[1]                 | 1   | No
[4]                 | 4   | No
[4, -1]             | 3   | No
[4, -1, 2]          | 5   | No
[4, -1, 2, 1]       | 6   | Yes (Maximum)
[2, 1]              | 3   | No
[1]                 | 1   | No
[4]                 | 4   | No

KEY OBSERVATIONS FROM DRY RUN:
1. The algorithm examines 45 different subarrays (n*(n+1)/2 = 9*10/2)
2. Maximum sum is found in the middle portion of array
3. Maximum sum subarray includes both positive and negative numbers
4. Algorithm continues checking even after finding maximum (could be optimized)
5. Several subarrays are checked multiple times due to overlapping

Key Observations:
1. The solution recalculates sums for overlapping subarrays
2. Time complexity increases cubically with array size
3. Works with both positive and negative numbers

Sample Validation:
Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
Expected: 6 (subarray [4, -1, 2, 1])
Output: 6

Key Points:
1. Handles edge cases like all negative numbers
2. Can identify multiple subarrays with same maximum sum
3. Memory efficient but computationally expensive

TEST CASES:
1. Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
   Expected: 6
   Output: 6
2. Input: [1]
   Expected: 1
   Output: 1
3. Input: [-1, -2, -3]
   Expected: -1
   Output: -1
4. Input: [1, 2, 3, 4]
   Expected: 10
   Output: 10
*/