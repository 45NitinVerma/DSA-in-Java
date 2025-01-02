// Approach 2: Hashing Approach
package Array.Missing_Number;
import java.util.*;

public class Approach2 {
    public static int missingNumber(int[] a, int N) {
        // Create hash array to store frequencies
        int[] hash = new int[N + 1];
        
        // Store frequencies of each number in array
        for (int i = 0; i < N - 1; i++) {
            hash[a[i]]++;
        }
        
        // Check frequencies to find missing number
        for (int i = 1; i <= N; i++) {
            if (hash[i] == 0) {
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
 ALGORITHM:
  1. Initialize a hash array of size N+1 (to include numbers 1 to N).
  2. Traverse the input array `a`:
     a. Increment the hash value for each number encountered.
  3. Iterate through numbers 1 to N (excluding 0):
     a. If the hash value for any number is 0, it means the number is missing.
  4. Return the missing number.

Complexity Analysis:
TIME COMPLEXITY:
- First Loop (Frequency Tracking): O(N-1)
- Second Loop (Finding Missing Number): O(N)
- Overall: O(N)

SPACE COMPLEXITY:
- Auxiliary Space: O(N)
  * Hash array of size N+1
- Input Space: O(N)
  * Input array of size N-1

Advantages:
1. Simple and straightforward implementation
2. Linear time complexity
3. Uses frequency counting technique
4. Works with unsorted input array
5. Single pass for frequency tracking

Limitations:
1. Extra space required for hash array
2. Not memory efficient for large N
3. Requires numbers to be in range 1 to N

Missing Number - DETAILED WALKTHROUGH
Input:
- Array a = [1, 2, 4, 5]
- N = 5

Detailed Step-by-Step Process:
-------------------------------------------------------------------------
Step | Operation                | Hash Array State       | Result
-------------------------------------------------------------------------
0    | Initialize hash          | [0,0,0,0,0,0]          | -
1    | Process a[0] = 1         | [0,1,0,0,0,0]          | -
2    | Process a[1] = 2         | [0,1,1,0,0,0]          | -
3    | Process a[2] = 4         | [0,1,1,0,1,0]          | -
4    | Process a[3] = 5         | [0,1,1,0,1,1]          | -
5    | Check i=1: hash[1] = 1   | No change              | Continue
6    | Check i=2: hash[2] = 1   | No change              | Continue
7    | Check i=3: hash[3] = 0   | No change              | Return 3
-------------------------------------------------------------------------
Final Result: 3 (Missing number found)

Step-by-Step Execution:
-------------------------------------------------------------------------
Iteration 1 (Frequency Tracking):
- Initial hash array: [0, 0, 0, 0, 0, 0]
- After tracking frequencies: [0, 1, 1, 0, 1, 1]
  * Frequencies of 1, 2, 4, 5 are marked

Iteration 2 (Finding Missing Number):
- Checking frequencies from 1 to N
- 1: Frequency = 1 ✓
- 2: Frequency = 1 ✓
- 3: Frequency = 0 ✗ (Missing Number!)
- Returns 3

-------------------------------------------------------------------------
Final Result: 3 (Missing Number)

Potential Improvements:
1. Use mathematical formula (sum of first N natural numbers)
2. Use XOR technique for constant space complexity
3. Handle edge cases like empty array or invalid input
*/
