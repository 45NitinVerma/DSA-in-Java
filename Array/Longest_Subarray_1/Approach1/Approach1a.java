// Approach 1a: Brute Force - Using Three Loops 
package Array.Longest_Subarray_1.Approach1;
public class Approach1a {
    public static int getLongestSubarray(int[] a, long k) {
        int n = a.length; // size of the array
        
        // Variable to store the maximum length
        int len = 0;
        
        // First loop: Choose starting point
        for (int i = 0; i < n; i++) {
            // Second loop: Choose ending point
            for (int j = i; j < n; j++) {
                // Third loop: Calculate sum of current subarray
                long s = 0;
                for (int K = i; K <= j; K++) {
                    s += a[K];
                }
                
                // Update length if sum equals target k
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
        
        // Find and print result
        int len = getLongestSubarray(a, k);
        System.out.println("The length of the longest subarray is: " + len);
    }
}

/*
 * ALGORITHM:
 * 1. Initialize a variable 'len' to store the maximum length of valid subarray.
 * 2. Use three nested loops:
 *    a. First loop (i): Selects the starting point of subarray
 *    b. Second loop (j): Selects the ending point of subarray
 *    c. Third loop (K): Calculates sum of elements from index i to j
 * 3. For each subarray:
 *    - Calculate sum of all elements
 *    - If sum equals target k:
 *      * Update len if current subarray length is greater
 * 4. Return the maximum length found
 */

/* 
Complexity Analysis:
TIME COMPLEXITY:
- Worst Case: O(n³)
  * First loop: O(n)
  * Second loop: O(n)
  * Third loop: O(n)
  * Total: O(n * n * n) = O(n³)
- Best Case: O(n³)
  * No early termination possible in this approach

SPACE COMPLEXITY:
- Auxiliary Space: O(1)
  * Only using a few variables regardless of input size
- Input Space: O(n)
  * Space for input array

Advantages:
1. Simple and straightforward implementation
2. Works for both positive and negative numbers
3. No additional space required
4. Handles all edge cases naturally

Limitations:
1. Very high time complexity O(n³)
2. Not suitable for large arrays
3. Performs redundant calculations of subarray sums
4. Inefficient for real-world applications

Potential Improvements:
1. Use prefix sum array to reduce time complexity to O(n²)
2. Use sliding window technique for O(n) complexity with positive numbers
3. Use HashMap for O(n) solution that works with negative numbers

Detailed Walkthrough with Example:
Input Array: [2, 3, 5, 1, 9], k = 10
-------------------------------------------------------------------------
Iteration | Subarray       | Sum | Length |Max Length | Update?
-------------------------------------------------------------------------
i=0,j=0   | [2]            | 2   | 1      | 0         | No
i=0,j=1   | [2,3]          | 5   | 2      | 0         | No
i=0,j=2   | [2,3,5]        | 10  | 3      | 3         | Yes
i=0,j=3   | [2,3,5,1]      | 11  | 4      | 3         | No
i=0,j=4   | [2,3,5,1,9]    | 20  | 5      | 3         | No

i=1,j=1   | [3]            | 3   | 1      | 3         | No
i=1,j=2   | [3,5]          | 8   | 2      | 3         | No
i=1,j=3   | [3,5,1]        | 9   | 3      | 3         | No
i=1,j=4   | [3,5,1,9]      | 18  | 4      | 3         | No

i=2,j=2   | [5]            | 5   | 1      | 3         | No
i=2,j=3   | [5,1]          | 6   | 2      | 3         | No
i=2,j=4   | [5,1,9]        | 15  | 3      | 3         | No

i=3,j=3   | [1]            | 1   | 1      | 3         | No
i=3,j=4   | [1,9]          | 10  | 2      | 3         | No

i=4,j=4   | [9]            | 9   | 1      | 3         | No
-------------------------------------------------------------------------
Final Result: 3 (longest subarray with sum = 10 is [2,3,5])

Iteration Process:
Starting with i = 0:
  j = 0: 
    K = 0: sum = 2, len = 0
  j = 1:
    K = 0,1: sum = 5, len = 0
  j = 2:
    K = 0,1,2: sum = 10, len = 3 (Found! Length = 3)
  j = 3:
    K = 0,1,2,3: sum = 11, len = 3
  j = 4:
    K = 0,1,2,3,4: sum = 20, len = 3

Starting with i = 1:
  j = 1:
    K = 1: sum = 3, len = 3
  j = 2:
    K = 1,2: sum = 8, len = 3
  [... continues similarly for remaining iterations]

*/