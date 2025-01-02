// Approach 1: Brute-force (Using Linear Search)
package Array.Missing_Number;
import java.util.*;

public class Approach1 {
    public static int missingNumber(int[] a, int N) {
        // Search for numbers from 1 to N
        for (int i = 1; i <= N; i++) {
            // Flag to track if current number is found
            int flag = 0;
            
            // Linear search through array to find current number
            for (int j = 0; j < N - 1; j++) {
                if (a[j] == i) {
                    flag = 1;  // Number found
                    break;
                }
            }
            
            // If flag is still 0, number was not found
            if (flag == 0) {
                return i;  // Found missing number
            }
        }
        return -1;  // Safety return (never reached for valid input)
    }

    public static void main(String[] args) {
        // Test input array
        int N = 5;
        int[] a = {1, 2, 4, 5};
        
        // Print input array
        System.out.println("Array: " + Arrays.toString(a));
        System.out.println("N value: " + N);
        
        // Find and print missing number
        int result = missingNumber(a, N);
        System.out.println("The missing number is: " + result);
    }
}

/*
 * ALGORITHM:
 * 1. Iterate through numbers 1 to N
 * 2. For each number i:
 *    a. Set flag = 0
 *    b. Search for i in the array using linear search
 *    c. If found, set flag = 1 and break inner loop
 *    d. If flag remains 0, i is the missing number
 * 3. Return the missing number when found
*/

/* 
Complexity Analysis:
TIME COMPLEXITY:
- O(N * (N-1)) ≈ O(N²)
  * Outer loop runs N times
  * Inner loop runs N-1 times
  * Each iteration performs constant time operations

SPACE COMPLEXITY:
- Auxiliary Space: O(1)
  * Only uses a few additional variables
- Input Space: O(n)

Advantages:
1. Simple and intuitive implementation
2. No extra data structures required
3. Works with unsorted arrays

Limitations:
1. Quadratic time complexity
2. Inefficient for large arrays
3. Multiple traversals of the input array

Potential Improvements:
1. Use mathematical sum formula approach
2. Utilize HashSet for O(n) time complexity
3. Apply XOR bit manipulation technique

Missing Number - DETAILED WALKTHROUGH
Input:
- Array a = [1, 2, 4, 5]
- N = 5

Detailed Step-by-Step Process:
-------------------------------------------------------------------------
Iteration | Current Number | Search Result | Flag | Action
-------------------------------------------------------------------------
i = 1     | Check 1        | Found         | 1    | Continue
i = 2     | Check 2        | Found         | 1    | Continue
i = 3     | Check 3        | Not Found     | 0    | RETURN 3
-------------------------------------------------------------------------
Final Result: 3 (Missing Number)

State Tracking:
1. First Iteration (i=1):
   - Searches for 1
   - Found in first position
   - Continues to next number

2. Second Iteration (i=2):
   - Searches for 2
   - Found in second position
   - Continues to next number

3. Third Iteration (i=3):
   - Searches for 3
   - Not found in array
   - Returns 3 as missing number
*/
