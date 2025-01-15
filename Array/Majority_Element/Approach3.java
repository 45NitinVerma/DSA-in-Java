/* 
Problem: Find the majority element in an array that appears more than n/2 times, where n is the size of the array.
If no majority element exists, return -1.

Intuition: Using Moore's Voting Algorithm - The algorithm works on the fact that if a majority element exists,
its frequency will be more than n/2, so even after canceling out each occurrence of any other element with 
one occurrence of the majority element, we will still be left with the majority element.

Moore's Voting Algorithm works on a beautiful principle of pairing and cancellation. Here's the core insight:

1. **Majority Property**
- A majority element appears more than floor(N/2) times
- This means even if we pair each occurrence of majority element with a different element, we'll still have some unpaired occurrences of the majority element
- For example, in [7,7,7,8,9], even after pairing 7s with 8 and 9, one 7 remains

2. **Cancellation Logic**
- When we find a pair of different elements, we can "cancel" them out
- This cancellation doesn't affect the majority element's status because:
  - If we cancel a majority element with a minority element, the majority element still remains majority in the rest of the array
  - If we cancel two non-majority elements, it doesn't affect the majority element at all

3. **Why It Works**
- The algorithm uses a count variable like a "running balance"
- When we see our candidate element, we increment (add a vote)
- When we see a different element, we decrement (cancel a vote)
- If count becomes 0, we've canceled out all previous elements
- The key insight is: the majority element will always "survive" this cancellation process
  - Because it appears more times than all other elements combined

4. **Verification Need**
- The algorithm might give us a candidate that survived the cancellation
- But we still need to verify it appears > N/2 times
- Unless we're guaranteed a majority element exists in the problem constraints

This process is somewhat like having a boxing tournament where the majority element has more fighters than all other teams combined - even after all possible matches, it must have at least one fighter remaining!

*/
package Array.Majority_Element;

public class Approach3 {
    // Approach 3: Moore's Voting Algorithm
    public static int majorityElement(int[] v) {
        // Size of the given array
        int n = v.length;
        int count = 0;  // Counter for keeping track of element frequency
        int candidate = 0;  // Potential majority element

        // Phase 1: Finding potential majority element using Moore's Voting
        for (int i = 0; i < n; i++) {
            // If counter becomes 0, pick current element as new candidate
            if (count == 0) {
                count = 1;
                candidate = v[i];
            }
            // If current element matches candidate, increment counter
            else if (candidate == v[i]) {
                count++;
            }
            // If current element differs from candidate, decrement counter
            else {
                count--;
            }
        }

        // Phase 2: Verifying if the candidate is actually a majority element
        int frequency = 0;
        for (int i = 0; i < n; i++) {
            if (v[i] == candidate) {
                frequency++;
            }
        }

        // Return candidate if it appears more than n/2 times, else -1
        return (frequency > n/2) ? candidate : -1;
    }

    public static void main(String args[]) {
        int[] arr = {2, 2, 1, 1, 1, 2, 2};
        int result = majorityElement(arr);
        System.out.println("The majority element is: " + result);
    }
}

/* 
ALGORITHM:
1. Initialize `cnt` (counter) to 0 and `el` (candidate element) to 0.
2. Traverse the array:
   - If `cnt` is 0, set `el` to the current element and `cnt` to 1.
   - If the current element equals `el`, increment `cnt`.
   - Otherwise, decrement `cnt`.
3. After the loop, verify if `el` is the majority element by counting 
   its occurrences in the array.
4. If its count is greater than n/2, return `el`; otherwise, return -1.

Complexity Analysis:
TIME COMPLEXITY: O(N)
- First pass through array for finding candidate: O(N)
- Second pass through array for verification: O(N)

SPACE COMPLEXITY:
1. Auxiliary Space: O(1)
   - Only using two variables (count and candidate)
2. Input Space: O(N)
   - Space for input array

Advantages:
1. Optimal time complexity O(N)
2. Constant auxiliary space O(1)
3. Simple implementation
4. Works well for arrays with clear majority element

Limitations:
1. Requires two passes through the array
2. Cannot identify majority element frequency without second pass
3. Returns -1 if no majority element exists

Potential Improvements:
1. Add input validation for null or empty arrays
2. Use parallel processing for large arrays
3. Add early termination if frequency count exceeds n/2 during verification
4. Combine with hash map approach for finding frequency of all elements
5. Add handling for edge cases like array with single element
6. Implement generic version to work with different data types

EXECUTION FLOW
Input Array: [2, 2, 1, 1, 1, 2, 2]
Length (n) = 7

PHASE 1: Finding Candidate
-----------------------------------------------------------------------------------------
Index | Array Element | Count Before | Action                    | Count After | Candidate
-----------------------------------------------------------------------------------------
0     | 2            | 0            | New candidate             | 1           | 2
1     | 2            | 1            | Matching, increment       | 2           | 2
2     | 1            | 2            | Non-matching, decrement   | 1           | 2
3     | 1            | 1            | Non-matching, decrement   | 0           | 2
4     | 1            | 0            | New candidate             | 1           | 1
5     | 2            | 1            | Non-matching, decrement   | 0           | 1
6     | 2            | 0            | New candidate             | 1           | 2
-----------------------------------------------------------------------------------------

Detailed Explanation of Phase 1:
1. Start with count = 0, candidate = 0
2. i = 0: Found 2, count was 0, so 2 becomes candidate
3. i = 1: Found 2, matches candidate, increment count to 2
4. i = 2: Found 1, doesn't match, decrement count to 1
5. i = 3: Found 1, doesn't match, decrement count to 0
6. i = 4: Found 1, count was 0, so 1 becomes new candidate
7. i = 5: Found 2, doesn't match, decrement count to 0
8. i = 6: Found 2, count was 0, so 2 becomes new candidate

Final Candidate after Phase 1: 2

PHASE 2: Verification
--------------------------------------------------------------------
Index | Array Element | Matches Candidate? | Running Frequency Count
--------------------------------------------------------------------
0     | 2            | Yes               | 1
1     | 2            | Yes               | 2
2     | 1            | No                | 2
3     | 1            | No                | 2
4     | 1            | No                | 2
5     | 2            | Yes               | 3
6     | 2            | Yes               | 4
--------------------------------------------------------------------

Final Frequency Count: 4
Majority Threshold (n/2): 3.5
Is Majority? Yes (4 > 3.5)

Final Result: 2

Additional Test Cases with Step Analysis:
-------------------------------------------------------------------
1. Single Element Array:
   Input: [1]
   - Phase 1: Immediate candidate selection (1)
   - Phase 2: Frequency count 1, threshold 0.5
   Result: 1 (majority element)

2. No Majority Element:
   Input: [1, 2, 3, 4]
   - Phase 1: Last element becomes candidate
   - Phase 2: No element frequency > n/2
   Result: -1

3. All Same Elements:
   Input: [5, 5, 5, 5]
   - Phase 1: 5 remains candidate throughout
   - Phase 2: Frequency 4, threshold 2
   Result: 5

4. Edge Cases:
   Input: [3, 3, 4, 3, 4]
   - Phase 1: Complex cancellation pattern
   - Phase 2: Verification confirms majority
   Result: 3

Key Visualizations of Algorithm Properties:
--------------------------------------------------------------------
1. Pairing/Cancellation:
   [2, 2, 1, 1] -> (2,1) (2,1) -> 2 survives
   Shows how equal numbers of different elements cancel out

2. Majority Property:
   In [2,2,1,1,1,2,2], element 2 appears 4 times
   4 > 7/2 (3.5), therefore it's a majority element

Common Pitfalls to Watch For:
1. Forgetting verification phase
2. Not handling empty array
3. Wrong majority threshold calculation
4. Not considering non-existent majority case
---------------------------------------------------------------------

Key Observations:
1. Algorithm maintains balance between candidate and other elements
2. Final verification phase is necessary to confirm majority element
3. Works in linear time with constant space

Sample Validation:
Input: [2, 2, 1, 1, 1, 2, 2]
Expected: 2
Output: 2

Key Points:
1. Uses cancellation principle to find potential majority element
2. Requires majority element to appear more than n/2 times
3. Two-phase approach ensures correctness

TEST CASES:
1. Input: [2, 2, 1, 1, 1, 2, 2]
   Expected: 2
   Output: 2
2. Input: [1, 1, 1, 1, 2, 2, 2]
   Expected: 1
   Output: 1
3. Input: [1, 2, 3, 4]
   Expected: -1
   Output: -1
4. Input: [1]
   Expected: 1
   Output: 1
*/