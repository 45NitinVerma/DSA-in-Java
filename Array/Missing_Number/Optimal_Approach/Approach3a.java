// Approach 3a: Optimal Approach (Using sum)
package Array.Missing_Number.Optimal_Approach;
/*
 Problem: Find Missing Number in Array
 Given an array of integers from 1 to N with one number missing, 
 determine the missing number efficiently.
 */
public class Approach3a {
    public static int missingNumber(int[] a, int N) {
        // Calculate the sum of first N natural numbers 
        // Formula: Sum of first N numbers = N * (N + 1) / 2
        int sum = (N * (N + 1)) / 2;

        // Calculate the sum of all elements in the given array
        int s2 = 0;
        for (int i = 0; i < N - 1; i++) {
            s2 += a[i];
        }

        // Missing number is the difference: expected sum - actual array sum
        int missingNum = sum - s2;
        return missingNum;
    }

    public static void main(String[] args) {
        // Test input
        int N = 5;  // Total expected numbers
        int[] a = {1, 2, 4, 5};  // Array with one number missing

        // Find and print the missing number
        int ans = missingNumber(a, N);
        System.out.println("The missing number is: " + ans);
    }
}

/*
 ALGORITHM:
 1. Calculate the sum of first N natural numbers using mathematical formula
    - Sum = N * (N + 1) / 2
 2. Calculate the sum of all elements in the given array
 3. Subtract array sum from expected sum to find the missing number
 4. Return the missing number
 */

/* 
Complexity Analysis:
TIME COMPLEXITY:
- O(N)
  * Single pass through the array to calculate sum
  * Constant time mathematical formula calculation
- Best Case: O(N)
- Worst Case: O(N)
- Average Case: O(N)

SPACE COMPLEXITY:
- O(1) Auxiliary Space
  * Uses only a few integer variables
  * No extra data structures used
- Input Space: O(N) for input array

Advantages:
1. Extremely efficient time complexity
2. Minimal space usage
3. Single pass solution
4. Works with unsorted arrays
5. No modification of original array

Limitations:
1. Potential integer overflow for very large N
2. Assumes consecutive numbers from 1 to N
3. Only works when exactly one number is missing

Detailed Walkthrough:
-------------------------------------------------------------------------
Missing Number - DETAILED WALKTHROUGH
Input:
- Array = [1, 2, 4, 5]
- N = 5

Step-by-Step Process:
-------------------------------------------------------------------------
Step | Operation                    | Calculation        | Result
-------------------------------------------------------------------------
1    | Calculate expected sum      | 5 * (5 + 1) / 2    | expectedSum = 15
     | Using formula: N*(N+1)/2    |                    |
2    | Calculate actual sum        | 1 + 2 + 4 + 5      | actualSum = 12
     | Iterate through array       |                    |
3    | Find missing number         | 15 - 12            | missingNum = 3
     | expectedSum - actualSum     |                    |

-------------------------------------------------------------------------
Final Result: 3 (The missing number)

Step-by-Step Calculation:
1. Calculate Expected Sum:
   - Formula: 5 * (5 + 1) / 2
   - Sum = 5 * 6 / 2
   - Sum = 30 / 2
   - Sum = 15

2. Calculate Actual Array Sum:
   - 1 + 2 + 4 + 5 = 12

3. Find Missing Number:
   - Missing Number = Expected Sum - Actual Sum
   - 15 - 12 = 3

Result: Missing Number is 3
-------------------------------------------------------------------------

Possible Variations & Improvements:
1. Add input validation for edge cases
2. Handle potential integer overflow
3. Consider XOR-based solution for alternative approach
*/