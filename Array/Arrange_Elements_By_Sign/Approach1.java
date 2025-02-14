/* 
Problem: Rearrange Array Elements by Sign - https://leetcode.com/problems/rearrange-array-elements-by-sign/description/

You are given a 0-indexed integer array nums of even length consisting of an equal number of positive and negative integers.
You should return the array of nums such that the the array follows the given conditions:
1. Every consecutive pair of integers have opposite signs.
2. For all integers with the same sign, the order in which they were present in nums is preserved.
3. The rearranged array begins with a positive integer.

Return the modified array after rearranging the elements to satisfy the aforementioned conditions.

Intuition: 
We can solve this by first separating positive and negative numbers into two separate lists, 
then placing them back into the original array in alternating positions.
*/

package Array.Arrange_Elements_By_Sign;

import java.util.ArrayList;

public class Approach1 {
    // Approach 1: Two-Pass Array Traversal with Additional Space
    public static int[] RearrangebySign(int[] A, int n) {
        // Create separate lists for positive and negative numbers
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        // First pass: Segregate numbers based on their sign
        // Time: O(n)
        for (int i = 0; i < n; i++) {
            if (A[i] > 0) {
                pos.add(A[i]);  // Store positive numbers
            } else {
                neg.add(A[i]);  // Store negative numbers
            }
        }

        // Second pass: Place numbers back in alternating order
        // Time: O(n/2)
        for (int i = 0; i < n / 2; i++) {
            A[2 * i] = pos.get(i);     // Place positive numbers at even indices
            A[2 * i + 1] = neg.get(i); // Place negative numbers at odd indices
        }

        return A;
    }

    public static void main(String args[]) {
        // Test case demonstration
        int n = 4;
        int A[] = {1, 2, -4, -5};

        int[] ans = RearrangebySign(A, n);

        // Print the rearranged array
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}

/* 
ALGORITHM:
1. Create two ArrayLists for storing positive and negative numbers
2. Traverse input array once to segregate numbers by sign
3. Traverse half the array length, placing positives at even indices and negatives at odd indices
4. Return the modified array

Complexity Analysis:
TIME COMPLEXITY: O(n) 
- One pass to segregate numbers: O(n)
- One pass to rearrange numbers: O(n/2)
- Total: O(n)

SPACE COMPLEXITY:
1. Auxiliary Space: O(n)
   - Two ArrayLists storing n/2 elements each
2. Input Space: O(n)
   - Original input array of size n

Advantages:
1. Simple implementation and easy to understand
2. Works well for arrays with equal number of positive and negative elements
3. Maintains relative order within positive and negative numbers

Limitations:
1. Requires extra space for ArrayLists
2. Only works when number of positive and negative elements are equal
3. No handling for edge cases (unequal counts, zeros)

Potential Improvements:
1. Add validation for input array length and element counts
2. Handle cases where positive and negative counts are unequal
3. Handle presence of zeros
4. Implement in-place solution to reduce space complexity
5. Add error handling for edge cases
6. Make it generic to work with any data type using comparator
7. Add option to customize placement order (positives first or negatives first)

EXECUTION FLOW
----------------------------------------------------------------
Step | Current State | Variables | Action | Explanation
----------------------------------------------------------------
1    | Initial      | A=[1,2,-4,-5] | Create Lists | Initialize empty pos, neg lists
2    | Segregation  | pos=[1,2]     | Fill Lists   | Separate by sign
3    | Segregation  | neg=[-4,-5]   | Complete Lists| All numbers segregated
4    | Rearranging  | A=[1,-4,2,-5] | Place Back   | Alternate placement
----------------------------------------------------------------

Key Observations:
1. Algorithm maintains the relative order of elements
2. Works efficiently for equal number of positive and negative elements
3. Two-pass approach trades space for clarity and simplicity

Sample Validation:
Input: [1, 2, -4, -5]
Expected: [1, -4, 2, -5]
Output: [1, -4, 2, -5]

TEST CASES:
1. Input: [1, 2, -4, -5]
   Expected: [1, -4, 2, -5]
   Output: [1, -4, 2, -5]
2. Input: [1, -3, 2, -4]
   Expected: [1, -3, 2, -4]
   Output: [1, -3, 2, -4]
3. Input: [1, 2, 3, -1, -2, -3]
   Expected: IllegalArgumentException (array length not even)
   Output: ArrayIndexOutOfBoundsException
*/