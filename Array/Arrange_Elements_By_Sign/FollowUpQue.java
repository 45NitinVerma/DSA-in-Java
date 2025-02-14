package Array.Arrange_Elements_By_Sign;

import java.util.ArrayList;
import java.util.Arrays;

/* 
Problem: Rearrange array elements with unequal number of positive and negative elements such that:
1. Positive and negative elements alternate
2. Array starts with positive elements
3. Relative order of elements is maintained
4. Leftover elements are placed at the end

Intuition: Since we can't assume equal positive and negative elements, we need to:
1. Separate positive and negative elements while maintaining their order
2. Fill alternatively until we exhaust either positive or negative elements
3. Append remaining elements at the end
*/

public class FollowUpQue {
    public static ArrayList<Integer> RearrangebySign(ArrayList<Integer> A, int n) {
        // Create separate lists for positive and negative numbers
        // to maintain their relative order
        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();

        // Segregate elements while preserving order
        for (int i = 0; i < n; i++) {
            if (A.get(i) > 0) {
                pos.add(A.get(i));  // Collect positive numbers
            } else {
                neg.add(A.get(i));  // Collect negative numbers
            }
        }

        // Case 1: More negative numbers than positive
        if (pos.size() < neg.size()) {
            // Step 1: Fill alternately until positive numbers are exhausted
            for (int i = 0; i < pos.size(); i++) {
                A.set(2 * i, pos.get(i));      // Even indices for positive
                A.set(2 * i + 1, neg.get(i));  // Odd indices for negative
            }

            // Step 2: Fill remaining negative numbers at the end
            int index = pos.size() * 2;  // Start index for remaining elements
            for (int i = pos.size(); i < neg.size(); i++) {
                A.set(index, neg.get(i));
                index++;
            }
        }
        // Case 2: More positive numbers than negative (or equal)
        else {
            // Step 1: Fill alternately until negative numbers are exhausted
            for (int i = 0; i < neg.size(); i++) {
                A.set(2 * i, pos.get(i));      // Even indices for positive
                A.set(2 * i + 1, neg.get(i));  // Odd indices for negative
            }

            // Step 2: Fill remaining positive numbers at the end
            int index = neg.size() * 2;  // Start index for remaining elements
            for (int i = neg.size(); i < pos.size(); i++) {
                A.set(index, pos.get(i));
                index++;
            }
        }
        return A;
    }

    public static void main(String[] args) {
        // Test case initialization
        int n = 6;
        ArrayList<Integer> A = new ArrayList<>(Arrays.asList(1, 2, -4, -5, 3, 4));
        
        // Get and print result
        ArrayList<Integer> ans = RearrangebySign(A, n);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}

/* 
ALGORITHM:
1. Create two ArrayLists to store positive and negative numbers separately
2. Iterate through input array to segregate numbers while maintaining order
3. Compare sizes of positive and negative lists
4. Fill array alternatively until smaller list is exhausted
5. Append remaining elements from larger list at the end

Complexity Analysis:
TIME COMPLEXITY: O(N)
- First pass to segregate: O(N)
- Second pass to rearrange: O(N)
- Overall complexity: O(N)

SPACE COMPLEXITY:
1. Auxiliary Space: O(N)
   - Two additional ArrayLists to store positive and negative numbers
   - In worst case, each can be of size N
2. Input Space: O(N)
   - Space for input array

Advantages:
1. Maintains relative order of elements
2. Handles unequal number of positive and negative elements
3. Simple and straightforward implementation
4. Single pass for segregation

Limitations:
1. Requires extra space O(N)
2. Modifies input array in-place
3. No handling for array containing zeros
4. No input validation

Potential Improvements:
1. Add input validation
   - Check for null array
   - Validate array size
2. Handle edge cases:
   - Empty array
   - Array with all positive or all negative numbers
   - Array containing zeros
3. Option to preserve original array
4. Optimize space usage by using single temporary array
5. Add error handling and exceptions
6. Support for other alternating patterns (e.g., starting with negative)
7. Add method to verify if rearrangement is valid
8. Support for generic number types
9. Add documentation for edge cases
10. Implement unit tests

Input Array: [1, 2, -4, -5, 3, 4]
Expected Output: [1, -4, 2, -5, 3, 4]

DETAILED EXECUTION FLOW TABLE
-------------------------------------------------------------------------------------------------
Step | Operation          | pos ArrayList | neg ArrayList | Output Array    | Description
-------------------------------------------------------------------------------------------------
1    | Initial State     | []            | []            | [1,2,-4,-5,3,4] | Initial input array
-------------------------------------------------------------------------------------------------
2    | Segregate pos/neg | [1]           | []            | [1,2,-4,-5,3,4] | Found 1 (positive)
-------------------------------------------------------------------------------------------------
3    | Segregate pos/neg | [1,2]         | []            | [1,2,-4,-5,3,4] | Found 2 (positive)
-------------------------------------------------------------------------------------------------
4    | Segregate pos/neg | [1,2]         | [-4]          | [1,2,-4,-5,3,4] | Found -4 (negative)
-------------------------------------------------------------------------------------------------
5    | Segregate pos/neg | [1,2]         | [-4,-5]       | [1,2,-4,-5,3,4] | Found -5 (negative)
-------------------------------------------------------------------------------------------------
6    | Segregate pos/neg | [1,2,3]       | [-4,-5]       | [1,2,-4,-5,3,4] | Found 3 (positive)
-------------------------------------------------------------------------------------------------
7    | Segregate pos/neg | [1,2,3,4]     | [-4,-5]       | [1,2,-4,-5,3,4] | Found 4 (positive)
-------------------------------------------------------------------------------------------------
8    | Compare sizes     | pos.size=4    | neg.size=2    | [1,2,-4,-5,3,4] | pos.size > neg.size
-------------------------------------------------------------------------------------------------
9    | Rearrange Step 1  | [1,2,3,4]     | [-4,-5]       | [1,-4,_,_,_,_]  | Place 1,-4 at 0,1
-------------------------------------------------------------------------------------------------
10   | Rearrange Step 2  | [1,2,3,4]     | [-4,-5]       | [1,-4,2,-5,_,_] | Place 2,-5 at 2,3
-------------------------------------------------------------------------------------------------
11   | Fill remaining    | [1,2,3,4]     | [-4,-5]       | [1,-4,2,-5,3,4] | Place 3,4 at end
-------------------------------------------------------------------------------------------------

Step-by-Step Process Explanation:

1. INITIALIZATION PHASE:
   - Input array is loaded: [1, 2, -4, -5, 3, 4]
   - Create empty ArrayLists for positive and negative numbers

2. SEGREGATION PHASE (Steps 2-7):
   a) Process 1: Add to pos ArrayList
   b) Process 2: Add to pos ArrayList
   c) Process -4: Add to neg ArrayList
   d) Process -5: Add to neg ArrayList
   e) Process 3: Add to pos ArrayList
   f) Process 4: Add to pos ArrayList

3. ANALYSIS PHASE (Step 8):
   - Compare sizes: pos.size (4) > neg.size (2)
   - Determine we'll use neg.size as limiting factor for alternating pattern

4. REARRANGEMENT PHASE (Steps 9-10):
   First Iteration:
   - Place pos[0]=1 at index 0
   - Place neg[0]=-4 at index 1

   Second Iteration:
   - Place pos[1]=2 at index 2
   - Place neg[1]=-5 at index 3

5. COMPLETION PHASE (Step 11):
   - Remaining positives: [3,4]
   - Start filling from index 4
   - Place 3 at index 4
   - Place 4 at index 5

Key Points from the Dry Run:
1. Positive numbers maintain their relative order: 1→2→3→4
2. Negative numbers maintain their relative order: -4→-5
3. Pattern starts with positive number and alternates until negatives are exhausted
4. Remaining positive numbers are appended at the end
5. Final array satisfies all given conditions:
   - Starts with positive
   - Alternates until possible
   - Maintains relative order
   - Extra elements at end

Key Observations:
1. Algorithm preserves relative order within positive and negative groups
2. Handles imbalanced positive/negative counts effectively
3. Final arrangement always starts with positive number
4. Extra elements maintain their relative order at the end

Sample Validation:
Input: [1, 2, -4, -5, 3, 4]
Expected: [1, -4, 2, -5, 3, 4]
Output: [1, -4, 2, -5, 3, 4]

Key Points:
1. Solution prioritizes maintaining relative order
2. Efficient for single traversal requirements
3. Suitable for cases with unequal positive/negative counts
4. Memory usage scales with input size

TEST CASES:
1. Input: [1, 2, -4, -5, 3, 4]
   Expected: [1, -4, 2, -5, 3, 4]
   Output: [1, -4, 2, -5, 3, 4]
2. Input: [1, -1, 2, -2, 3]
   Expected: [1, -1, 2, -2, 3]
   Output: [1, -1, 2, -2, 3]
3. Input: [-1, -2, -3, 1]
   Expected: [1, -1, -2, -3]
   Output: [1, -1, -2, -3]
*/