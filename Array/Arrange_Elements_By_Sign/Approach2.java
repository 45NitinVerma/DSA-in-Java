package Array.Arrange_Elements_By_Sign;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/* 
Problem: Rearrange array elements such that positive and negative numbers are placed alternately.
Given an array with equal number of positive and negative elements, arrange them such that
every positive element is followed by a negative element and vice versa.

Intuition: We can use two pointers (posIndex and negIndex) to track positions where we should 
place positive and negative numbers respectively. By incrementing these pointers by 2, 
we ensure alternating placement.
*/

public class Approach2 {
    // Approach 2: Two Pointer with Extra Space
    public static ArrayList<Integer> RearrangebySign(ArrayList<Integer> A) {
        int n = A.size();

        // Create result array initialized with zeros
        ArrayList<Integer> ans = new ArrayList<>(Collections.nCopies(n, 0));

        // Initialize two pointers
        // posIndex for positive numbers starting at even indices
        // negIndex for negative numbers starting at odd indices
        int posIndex = 0, negIndex = 1;

        // Iterate through input array once
        for (int i = 0; i < n; i++) {
            if (A.get(i) < 0) {
                // Place negative number at next available odd index
                ans.set(negIndex, A.get(i));
                negIndex += 2;  // Move to next odd position
            } else {
                // Place positive number at next available even index
                ans.set(posIndex, A.get(i));
                posIndex += 2;  // Move to next even position
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        // Test case initialization
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, -4, -5));
        ArrayList<Integer> ans = RearrangebySign(A);

        // Print result
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}

/* 
ALGORITHM:
1. Create a new ArrayList 'ans' of same size as input, initialized with zeros
2. Initialize two pointers: posIndex = 0 (for positive numbers) and negIndex = 1 (for negative numbers)
3. Iterate through input array:
   - If element is negative, place at negIndex and increment negIndex by 2
   - If element is positive, place at posIndex and increment posIndex by 2
4. Return the rearranged array

Complexity Analysis:
TIME COMPLEXITY: O(N)
- Single pass through the array where N is the size of input array

SPACE COMPLEXITY:
1. Auxiliary Space: O(N)
   - Extra space needed for result array
2. Input Space: O(N)
   - Space taken by input array

Advantages:
1. Simple implementation with clear logic
2. Single pass solution
3. Maintains relative order of positive and negative numbers within their groups

Limitations:
1. Requires extra space O(N)
2. Assumes equal number of positive and negative elements
3. Doesn't handle edge cases like all positive or all negative numbers

Potential Improvements:
1. Add input validation for array size and element count
2. Handle cases with unequal number of positive/negative elements
3. Implement error handling for edge cases
4. Add option to preserve original array
5. Consider in-place rearrangement to optimize space complexity
6. Add support for zero elements in the array

EXECUTION FLOW
---------------------------------------------------------------
Step | Current State | Variables        | Action               | Explanation
---------------------------------------------------------------
1    | Initial      | A=[1,2,-4,-5]    | Create ans array    | Initialize with zeros
2    | Processing   | posIndex=0       | Place 1 at index 0  | First positive number
3    | Processing   | negIndex=1       | Place -4 at index 1 | First negative number
4    | Processing   | posIndex=2       | Place 2 at index 2  | Second positive number
5    | Processing   | negIndex=3       | Place -5 at index 3 | Second negative number
---------------------------------------------------------------

Key Observations:
1. The algorithm maintains alternating pattern by using even/odd indices
2. Original order within positive and negative numbers is preserved
3. Solution assumes input validation is handled externally

Sample Validation:
Input: [1, 2, -4, -5]
Expected: [1, -4, 2, -5]
Output: [1, -4, 2, -5]

Key Points:
1. Time complexity is optimal for a non-in-place solution
2. Solution is stable for both positive and negative numbers
3. Easy to modify for different alternating patterns

TEST CASES:
1. Input: [1, 2, -4, -5]
   Expected: [1, -4, 2, -5]
   Output: [1, -4, 2, -5]
2. Input: [1, -1, 2, -2]
   Expected: [1, -1, 2, -2]
   Output: [1, -1, 2, -2]
*/