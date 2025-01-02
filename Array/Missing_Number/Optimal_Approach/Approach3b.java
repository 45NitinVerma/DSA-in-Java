// Approach 3b: Optimal Approach(Using XOR)
package Array.Missing_Number.Optimal_Approach;
import java.util.Arrays;
public class Approach3b {
    public static int missingNumber(int[] a, int N) {
        // XOR variables to track array and expected range elements
        int xor1 = 0, xor2 = 0;

        // Iterate through array and compute XOR of array elements and expected range
        for (int i = 0; i < N - 1; i++) {
            xor2 = xor2 ^ a[i]; // XOR of array elements
            xor1 = xor1 ^ (i + 1); //XOR up to [1...N-1]
        }
        xor1 = xor1 ^ N; //XOR up to [1...N]

        return (xor1 ^ xor2); // the missing number
    }

    public static void main(String[] args) {
        // Test input
        int N = 5;
        int[] a = {1, 2, 4, 5};
        
        // Print input array
        System.out.println("Input Array: " + Arrays.toString(a));
        System.out.println("Total Expected Numbers: " + N);
        
        // Find and print missing number
        int ans = missingNumber(a, N);
        System.out.println("The missing number is: " + ans);
    }
}

/*
ALGORITHM:
1. Initialize two XOR variables: xor1 and xor2
2. Iterate through the array:
   a. XOR array elements into xor2
   b. XOR numbers from 1 to N-1 into xor1
3. XOR N into xor1 to cover full range
4. XOR xor1 and xor2 to find missing number

XOR PROPERTY EXPLANATION:
- a ^ a = 0 (XOR of same number is 0)
- a ^ 0 = a (XOR with 0 returns the number itself)
- XOR is commutative and associative

Detailed Walkthrough:
Input: 
- Array a = [1, 2, 4, 5]
- N = 5 (Expected numbers: 1, 2, 3, 4, 5)

Step-by-Step XOR Process:
------------------------------------------------
Iteration | xor1       | xor2       | Action
------------------------------------------------
Initial   | 0          | 0          | Start
i=0       | 1          | 1          | XOR 1
i=1       | 3          | 3          | XOR 2
i=2       | 0          | 7          | XOR 4
Final     | 5          | 7          | XOR N=5

Result: 5 ^ 7 = 3 (Missing Number)
*/

/* 
Complexity Analysis:
TIME COMPLEXITY:
- O(N)
  * Single pass through the array
  * Constant time XOR operations

SPACE COMPLEXITY:
- O(1)
  * Only two integer variables used
  * No extra data structures

Advantages:
1. Extremely memory efficient
2. Single pass solution
3. Handles integer overflow
4. Works without modifying input array

Limitations:
1. Relies on XOR bit manipulation
2. Slightly less readable than other approaches
3. Works only for consecutive number ranges

Optimization Techniques:
- Bit manipulation reduces space complexity
- Eliminates need for additional data structures
- Constant extra space usage

Potential Variations:
1. Can be extended to find multiple missing numbers
2. Adaptable to different range constraints
3. Useful in memory-constrained environments
*/