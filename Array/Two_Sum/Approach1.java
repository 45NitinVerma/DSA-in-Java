package Array.Two_Sum;

public class Approach1 {
    // Approach 1: Brute Force Using Two Loops
    public static int[] twoSum(int n, int[] arr, int target) {
        int[] ans = new int[2];
        ans[0] = ans[1] = -1; // Initialize result array with -1 indicating no result found.

        // Iterate through each pair of elements in the array.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // Check if the current pair sums up to the target.
                if (arr[i] + arr[j] == target) {
                    ans[0] = i; // Store the first index.
                    ans[1] = j; // Store the second index.
                    return ans; // Return as soon as the pair is found.
                }
            }
        }

        // Return [-1, -1] if no such pair exists.
        return ans;
    }

    public static void main(String args[]) {
        // Input array and parameters.
        int n = 5;
        int[] arr = {2, 6, 5, 8, 11};
        int target = 14;

        // Call the twoSum method.
        int[] ans = twoSum(n, arr, target);

        // Print the result.
        System.out.println("This is the answer: [" + ans[0] + ", " + ans[1] + "]");
    }
}

/*
Problem: 
Given an array of integers 'nums' and an integer 'target', return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.

Intuition: Use a brute force approach by checking all possible pairs in the array 
to see if their sum equals the target. Return their indices if found.

ALGORITHM:
 1. Initialize an array `ans` to store the result indices. Default to [-1, -1].
 2. Use two nested loops to iterate through all pairs of elements in the array:
    - Outer loop runs from 0 to n-1.
    - Inner loop starts from i+1 to n-1.
 3. For each pair, check if their sum equals the target:
    - If true, update `ans` with their indices and return immediately.
 4. If no pair is found, return the default result [-1, -1].

TIME COMPLEXITY: O(n^2)
- Outer loop runs n times
- Inner loop runs (n-1), (n-2),...,1 times
- Total comparisons: (n-1) + (n-2) + ... + 1 = n(n-1)/2
- Therefore worst and average case: O(n^2)
- Best case: O(1) when first pair is the answer

SPACE COMPLEXITY: O(n)

Auxiliary Space: O(1)
- Only uses fixed size array ans[2] for output
- Loop variables i,j use constant space
- No extra space growth with input size

Input Space: O(n) 
- Input array arr[] of size n
- Single integer variables n, target
- Total input space dominated by array size n

Total Space = Auxiliary Space + Input Space = O(n)

Advantages:
1. Simple to implement
2. Works for unsorted arrays
3. Returns first valid solution found

Limitations: 
1. Slow for large arrays due to O(n^2) time complexity
2. Does not find all possible pairs that sum to target

Potential Improvements:
1. Use HashMap for O(n) time complexity
2. Add validation for null/empty arrays
3. Support finding all valid pairs

EXECUTION FLOW
--------------------------------------------------------------------------------------------------
Step | Current State | Variables      | Action                              | Explanation
--------------------------------------------------------------------------------------------------
1    | Initial       | i=0, j=1       | Compare arr[i] + arr[j] with target | Start brute force.
2    | Looping       | i=0, j=2...    | Continue                            | Move through pairs.
3    | Match found   | i=2, j=3       | Return [2,3]                        | Sum matches target.
--------------------------------------------------------------------------------------------------

Key Observations:
1. Each pair checked exactly once
2. Returns first valid pair found
3. Handles positive and negative numbers

Sample Validation:
[2,6,5,8,11], target=14
- (2,6)=8: No match
- (2,5)=7: No match
- (2,8)=10: No match
- (2,11)=13: No match
- (6,8)=14: Match found!
Return [1,3]

Key Points:
1. Guaranteed to find solution if exists
2. Returns [-1,-1] for no solution
3. Maintains original array order

TEST CASES
Input: arr=[2,6,5,8,11], target=14
Expected: [1,3]
Output: [1,3]

Input: arr=[2,6,5,8,11], target=20
Expected: [-1,-1]
Output: [-1,-1]
 */
