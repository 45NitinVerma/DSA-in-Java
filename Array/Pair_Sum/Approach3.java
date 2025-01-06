/* 
Problem: Find all pairs in array that sum to target S
Input: Array ARR[N], target sum S
Output: List of pairs sorted by first element, then second element

Intuition: Using Two Pointers approach after sorting allows us to efficiently find pairs
by moving pointers based on sum comparison with target value. This eliminates need
to check all possible combinations.
*/

package Array.Pair_Sum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Approach3 {
    // Approach 3: Using Two Pointers
    public static List<int[]> pairSum_TwoPointers(int[] arr, int n, int s) {
        // Step 1: Sort the array to enable two-pointer traversal
        Arrays.sort(arr);

        // Step 2: Initialize list to store resulting pairs
        List<int[]> pairs = new ArrayList<>();

        // Step 3: Define two pointers
        int left = 0, right = n - 1;

        // Step 4: Iterate while pointers do not overlap
        while (left < right) {
            int currentSum = arr[left] + arr[right];

            if (currentSum == s) {
                // Step 5: If pair matches target, add to result and adjust pointers
                pairs.add(new int[]{arr[left], arr[right]});
                left++;
                right--;
            } 
            // Step 6: If current sum is less than target, move the left pointer forward
            else if (currentSum < s) {
                left++;
            } 
            // Step 7: If current sum is greater than target, move the right pointer backward
            else {
                right--;
            }
        }

        // Step 8: Return the list of pairs
        return pairs;
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 7, -1, 5};
        int target = 6;
        int n = arr.length;

        List<int[]> result = pairSum_TwoPointers(arr, n, target);

        System.out.println("Pairs that sum to target:");
        for (int[] pair : result) {
            System.out.println(Arrays.toString(pair));
        }
    }
}

/* 
ALGORITHM:
1. Sort input array in ascending order
2. Initialize two pointers - left at start, right at end
3. While left < right:
   - Calculate currentSum = arr[left] + arr[right]
   - If currentSum equals target, add pair and move both pointers
   - If currentSum less than target, move left pointer right
   - If currentSum more than target, move right pointer left
4. Return list of pairs

Complexity Analysis:
TIME COMPLEXITY: O(nlogn)
- Sorting takes O(nlogn)
- Two pointer traversal takes O(n)
- Overall complexity dominated by sort: O(nlogn)

SPACE COMPLEXITY:
1. Auxiliary Space: O(k) where k is number of pairs found
   - Space for storing result pairs
2. Input Space: O(1)
   - Only constant extra space for pointers

Advantages:
1. More efficient than O(n²) brute force approach
2. No extra space needed unlike hash-based solution
3. Returns pairs in sorted order due to initial array sort

Limitations:
1. Modifies original array due to sorting
2. Not suitable when original array order must be preserved
3. Less efficient than hash approach for single pair search

Potential Improvements:
1. Use hashing to achieve O(n) complexity but with O(n) space.
2. Handle duplicate pairs if needed by skipping duplicates during traversal.

EXECUTION FLOW:
Input:
- Array: [4,1,2,3]
- Target Sum: 5
- Required: Find all pairs that sum to 5
-----------------------------------------------------------------------------------------------------------------------------------------
Step | Array State     | Pointers (L,R) | Current Sum | Action                    | Result         | Explanation
-----------------------------------------------------------------------------------------------------------------------------------------
1    | [4,1,2,3]      | -              | -           | Sort array                | [1,2,3,4]      | Sort for two pointer technique
2    | [1,2,3,4]      | (0,3)          | 1+4=5       | Found target! Add pair    | pairs=[[1,4]]  | First pair found
3    | [1,2,3,4]      | (1,2)          | 2+3=5       | Found target! Add pair    | pairs=[[1,4],  | Second pair found
                                                                                    [2,3]]         
4    | [1,2,3,4]      | (2,1)          | -           | L≥R, stop                | Return pairs   | Pointers crossed, complete
-----------------------------------------------------------------------------------------------------------------------------------------
Result:
- Final Pairs: [[1,4], [2,3]]
- Verification: 1+4=5 and 2+3=5
-----------------------------------------------------------------------------------------------------------------------------------------

Here's the step-by-step walkthrough of finding pairs with sum = 5 in array [4,1,2,3]:

1. Initial State
   - Input Array: [4,1,2,3]
   - Target Sum: 5
   - Action: None yet

2. Sorting Step
   - Array becomes: [1,2,3,4]
   - Left pointer (L) = 0
   - Right pointer (R) = 3
   - Action: Sort array

3. First Iteration
   - Current Array: [1,2,3,4]
   - L = 0 (value = 1)
   - R = 3 (value = 4)
   - Current Sum = 1 + 4 = 5
   - Action: Found pair! Add [1,4]
   - Result: pairs = [[1,4]]

4. Second Iteration
   - Current Array: [1,2,3,4]
   - L = 1 (value = 2)
   - R = 2 (value = 3)
   - Current Sum = 2 + 3 = 5
   - Action: Found pair! Add [2,3]
   - Result: pairs = [[1,4], [2,3]]

5. Final Step
   - Current Array: [1,2,3,4]
   - L = 2, R = 1
   - Action: Stop (L ≥ R)
   - Return pairs [[1,4], [2,3]]

TEST CASES:
1. Input: arr=[1,2,3,4], n=4, s=5
   Expected: [[1,4], [2,3]]
   Output: [[1,4], [2,3]]

2. Input: arr=[2,2,2,2], n=4, s=4
   Expected: [[2,2], [2,2]]
   Output: [[2,2], [2,2]]

3. Input: arr=[1,4,3,2], n=4, s=8
   Expected: []
   Output: []
*/