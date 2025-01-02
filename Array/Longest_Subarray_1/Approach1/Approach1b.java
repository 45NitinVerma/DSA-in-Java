// Approach 1b: Brute Force - Using Two Loops
package Array.Longest_Subarray_1.Approach1;
public class Approach1b {
    public static int getLongestSubarray(int[] a, long k) {
        int n = a.length; // size of the array
        
        // Variable to store the maximum length of subarray
        int len = 0;
        
        // Iterate through each possible starting point
        for (int i = 0; i < n; i++) {
            long s = 0; // Running sum for current subarray
            
            // Try all possible ending points for current start
            for (int j = i; j < n; j++) {
                // Add current element to running sum
                s += a[j];
                
                // Update max length if current sum equals target k
                if (s == k) {
                    len = Math.max(len, j - i + 1);
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        // Test input array and target sum
        int[] a = {2, 3, 5, 1, 9};
        long k = 10;
        
        // Find and print the result
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}

/*
 * ALGORITHM:
 * 1. Initialize a variable 'len' to store the maximum length of valid subarray
 * 2. Use nested loops to generate all possible subarrays:
 *    a. Outer loop (i) marks the starting point
 *    b. Inner loop (j) marks the ending point
 * 3. For each possible starting index i:
 *    a. Initialize sum 's' as 0
 *    b. For each possible ending index j from i to end:
 *       - Add current element a[j] to sum
 *       - If sum equals target k:
 *         * Update len if current subarray length (j-i+1) is greater
 * 4. Return the maximum length found
 */

/* 
Complexity Analysis:
TIME COMPLEXITY:
- Worst Case: O(n²) 
  * n = length of input array
  * Outer loop runs n times
  * Inner loop runs up to n times for each outer loop iteration
- Best Case: O(n²)
  * Always needs to check all possible subarrays
- Average Case: O(n²)

SPACE COMPLEXITY:
- Auxiliary Space: O(1)
  * Only using a constant amount of extra space
  * Variables: n, len, s, loop counters
- Input Space: O(n)

Advantages:
1. Simple and straightforward implementation
2. Works with both positive and negative numbers
3. Handles all possible cases correctly
4. No extra space required

Limitations:
1. Quadratic time complexity makes it inefficient for large arrays
2. Not suitable for real-time applications with large datasets
3. Performs unnecessary calculations for cases where no valid subarray exists

Potential Improvements:
1. Use sliding window technique for O(n) complexity when all numbers are positive
2. Use prefix sum + HashMap for O(n) solution that works with negative numbers

Detailed Walkthrough:
Input Array: [2, 3, 5, 1, 9], Target k = 10
-------------------------------------------------------------------------
Starting Index(i) | Ending Index (j) | Current Sum | Max Length | Action
-------------------------------------------------------------------------
i=0               | j=0              | 2           | 0          | Continue
                  | j=1              | 5           | 0          | Continue
                  | j=2              | 10          | 3          | Update len
                  | j=3              | 11          | 3          | Continue
                  | j=4              | 20          | 3          | Continue

i=1               | j=1              | 3           | 3          | Continue
                  | j=2              | 8           | 3          | Continue
                  | j=3              | 9           | 3          | Continue
                  | j=4              | 18          | 3          | Continue

i=2               | j=2              | 5           | 3          | Continue
                  | j=3              | 6           | 3          | Continue
                  | j=4              | 15          | 3          | Continue

i=3               | j=3              | 1           | 3          | Continue
                  | j=4              | 10          | 3          | Continue

i=4               | j=4              | 9           | 3          | Continue
-------------------------------------------------------------------------
Final Result: 3 (subarray [2, 3, 5] has sum = 10)

Iteration Process:
Starting with i = 0:
  j = 0: sum = 2, len = 0
  j = 1: sum = 5, len = 0
  j = 2: sum = 10, len = 3 (Found! Length = 3)
  j = 3: sum = 11, len = 3
  j = 4: sum = 20, len = 3

Starting with i = 1:
  j = 1: sum = 3, len = 3
  j = 2: sum = 8, len = 3
  j = 3: sum = 9, len = 3
  j = 4: sum = 18, len = 3

Starting with i = 2:
  j = 2: sum = 5, len = 3
  j = 3: sum = 6, len = 3
  j = 4: sum = 15, len = 3

Starting with i = 3:
  j = 3: sum = 1, len = 3
  j = 4: sum = 10, len = 3 (Found! But not longer than previous)

Starting with i = 4:
  j = 4: sum = 9, len = 3

*/